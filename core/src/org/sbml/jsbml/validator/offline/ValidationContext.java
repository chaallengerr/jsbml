/*
 * $Id$
 * $URL$
 * ----------------------------------------------------------------------------
 * This file is part of JSBML. Please visit <http://sbml.org/Software/JSBML>
 * for the latest version of JSBML and more information about SBML.
 * Copyright (C) 2009-2016 jointly by the following organizations:
 * 1. The University of Tuebingen, Germany
 * 2. EMBL European Bioinformatics Institute (EBML-EBI), Hinxton, UK
 * 3. The California Institute of Technology, Pasadena, CA, USA
 * 4. The University of California, San Diego, La Jolla, CA, USA
 * 5. The Babraham Institute, Cambridge, UK
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation. A copy of the license agreement is provided
 * in the file named "LICENSE.txt" included with this software distribution
 * and also available online as <http://sbml.org/Software/JSBML/License>.
 * ----------------------------------------------------------------------------
 */

package org.sbml.jsbml.validator.offline;

import org.apache.log4j.Logger;
import org.sbml.jsbml.SBO;
import org.sbml.jsbml.UnitDefinition;
import org.sbml.jsbml.Unit.Kind;
import org.sbml.jsbml.util.ValuePair;
import org.sbml.jsbml.validator.offline.factory.CheckCategory;
import org.sbml.jsbml.validator.offline.factory.ConstraintFactory;
import org.sbml.jsbml.validator.SyntaxChecker;
import org.sbml.jsbml.validator.offline.constraints.AnyConstraint;
import org.sbml.jsbml.validator.offline.constraints.ConstraintGroup;
import org.sbml.jsbml.validator.offline.constraints.CoreSpecialErrorCodes;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.swing.tree.TreeNode;

/**
 * A {@link ValidationContext} object is used to perform offline validation.
 * <p>
 * To prepare the context for the validation use the
 * {@link ValidationContext#loadConstraints(Class)} method, which will loads all
 * the required constraints to validate a object from this class. By default the
 * context will try to validate recursive by searching for the {@link TreeNode}
 * interface. This behavior can be changed with
 * {@link ValidationContext#setValidateRecursivly(boolean)}.
 * <p>
 * To start the validation process call
 * {@link ValidationContext#validate(Object)} with a object which is a instance
 * of the class for which the constraints were loaded. If not constraints were
 * loaded or the object doesn't match the class, no validation process is
 * started.
 * <p>
 * To track the validation process in real-time you can add a
 * {@link ValidationListener} to this context by using the
 * {@link ValidationContext#addValidationListener(ValidationListener)} method.
 * <p>
 * The level and version parameter determine which SBML specifications are used.
 * For more informations look up <a href="http://www.sbml.org"> sbml.org </a>
 * <p>
 * 
 * @author Roman
 * @version $Rev$
 * @since 1.2
 * @date 04.07.2016
 */
public class ValidationContext {

  /**
   * Log4j logger
   */
  protected static final transient Logger logger   =
      Logger.getLogger(ValidationContext.class);

  // The root constraint, which could contains more constraints
  private AnyConstraint<Object>           rootConstraint;

  // Determines which constraints are loaded.
  private Set<CheckCategory>              categories;
  private Set<SBMLPackage>                packages;
  private Class<?>                        constraintType;
  private Set<ValidationListener>         listener;
  private HashMap<String, Object>         hashMap  =
      new HashMap<String, Object>();

  // The level and version of the SBML specification
  private int                             level;
  private int                             version;
  private boolean                         recursiv = true;


  public ValidationContext(int level, int version) {
    this(level, version, null, new HashSet<CheckCategory>());
  }


  public ValidationContext(int level, int version,
    AnyConstraint<Object> rootConstraint, Set<CheckCategory> categories) {
    this.level = level;
    this.version = version;
    this.categories = categories;
    this.rootConstraint = rootConstraint;
    this.categories.add(CheckCategory.GENERAL);
    this.listener = new HashSet<ValidationListener>();
    this.packages = new HashSet<SBMLPackage>();
    this.packages.add(SBMLPackage.CORE);
  }


  /**
   * Adds a {@link ValidationListener}. A {@link ValidationContext} can have
   * multiple listeners and every attached listener will receive the events from
   * the validation.
   * 
   * @param listener
   */
  public void addValidationListener(ValidationListener listener) {
    if (!this.listener.contains(listener)) {
      this.listener.add(listener);
    }
  }


  /**
   * Will be called every time before a constraints starts his tests.
   * 
   * @param constraint
   * @param o
   * @param success
   */
  public void didValidate(AnyConstraint<?> constraint, Object o,
    boolean success) {
    for (ValidationListener l : this.listener) {
      l.didValidate(this, constraint, o, success);
    }
  }


  /**
   * Enables or disables the selected category.
   * <p>
   * The enabled categories determines which constraints will be loaded in
   * {@link #loadConstraints(Class, int, int, CheckCategory[])}. This function
   * won't change the root
   * constraint.
   * 
   * @param catergoy
   * @param enable
   * @see #loadConstraints(Class, int, int, CheckCategory[])
   */
  public void enableCheckCategory(CheckCategory category, boolean enable) {
    if (enable) {

      this.categories.add(category);

    } else {
      this.categories.remove(category);
    }
  }


  /**
   * Calls {@link #enableCheckCategory(CheckCategory, boolean)} for every
   * {@link CheckCategory} in the array.
   * 
   * @param categories
   * @param enable
   * @see CheckCategory
   */
  public void enableCheckCategories(CheckCategory[] categories,
    boolean enable) {
    for (CheckCategory c : categories) {
      this.enableCheckCategory(c, enable);
    }
  }


  /**
   * Enables a {@link SBMLPackage}. The root constraints should be reloaded
   * after this function was called to apply the changes to the validation.
   * 
   * @param pkg
   * @param enable
   */
  public void enablePackage(SBMLPackage pkg, boolean enable) {
    if (enable) {

      this.packages.add(pkg);

    } else {
      this.packages.remove(pkg);
    }
  }


  /**
   * Calls {@link #enablePackage(SBMLPackage, boolean)} for every
   * {@link SBMLPackage} in the array.
   * 
   * @param pkgs
   * @param enable
   * @see #enablePackage(SBMLPackage, boolean)
   */
  public void enablePackages(SBMLPackage[] pkgs, boolean enable) {
    for (SBMLPackage pkg : pkgs) {
      this.enablePackage(pkg, enable);
    }
  }


  /**
   * Loads the constraints to validate a Object from the class. Uses the
   * CheckCategories, level and version of this context. Resets the root
   * constraint.
   * 
   * @param clazz
   */
  public void loadConstraints(Class<?> clazz) {
    this.constraintType = clazz;
    ConstraintFactory factory = ConstraintFactory.getInstance();
    ConstraintGroup<Object> group = factory.getConstraintsForClass(clazz, this);

    if (this.recursiv && TreeNode.class.isAssignableFrom(clazz)) {
      @SuppressWarnings("unchecked")
      AnyConstraint<Object> c = (AnyConstraint<Object>) factory.getConstraint(
        CoreSpecialErrorCodes.ID_VALIDATE_TREE_NODE);
      group.add(c);
    }

    this.rootConstraint = group;
  }


  /**
   * Sets the level and version and loads the constraints.
   * 
   * @param cclass
   * @param level
   * @param version
   * @see #loadConstraints(Class)
   */
  public void loadConstraints(Class<?> clazz, int level, int version) {
    this.setLevelAndVersion(level, version);
    this.loadConstraints(clazz);
  }


  /**
   * @param class
   * @param level
   * @param version
   * @param categories
   */
  public void loadConstraints(Class<?> clazz, int level, int version,
    CheckCategory[] categories) {
    this.setLevelAndVersion(level, version);
    this.categories.removeAll(this.categories);
    this.enableCheckCategories(categories, true);
  }


  /**
   * Returns the list of all enabled check categories.
   * 
   * @return
   */
  public CheckCategory[] getCheckCategories() {
    return this.categories.toArray(new CheckCategory[this.categories.size()]);
  }


  /**
   * Gets the {@link Class} on which the root constraint is typed.
   * 
   * @return
   */
  public Class<?> getConstraintType() {
    return this.constraintType;
  }


  /**
   * Constraints can use this {@link HashMap} to store additional
   * information.
   * <p>
   * Notice that this method doesn't return a copy, but a reference to the
   * actual instance.
   * 
   * @return Reference of {@link HashMap}
   */
  public HashMap<String, Object> getHashMap() {
    return this.hashMap;
  }


  /**
   * Returns the used level of SBML
   * 
   * @return a positive <code>int</code>
   * @see #getVersion()
   * @see #getLevelAndVersion()
   */
  public int getLevel() {
    return this.level;
  }


  /**
   * This value determines which constraints will be loaded and in which way
   * broken constraints will be logged.
   * 
   * @return the level and version this validation context used.
   * @see #getLevel()
   * @see #getVersion()
   */
  public ValuePair<Integer, Integer> getLevelAndVersion() {
    return new ValuePair<Integer, Integer>(new Integer(this.level),
        new Integer(this.version));
  }


  /**
   * Returns the {@link SBMLPackage}'s which will be validated during the
   * validation.
   * 
   * @return
   * @see #enablePackage(SBMLPackage, boolean)
   * @see #enablePackages(SBMLPackage[], boolean)
   */
  public SBMLPackage[] getPackages() {
    return this.packages.toArray(new SBMLPackage[this.packages.size()]);
  }


  /**
   * Returns the root constraint from this context. The value may be
   * <code>null</code> if no constraint was loaded. The
   * {@link AnyConstraint#check(ValidationContext, Object)} will be called in
   * {@link #validate(Object)}
   * 
   * @return the root constraint or {@code null} if no constraints were loaded
   * @see #loadConstraints(Class, int, int, CheckCategory[])
   */
  public AnyConstraint<Object> getRootConstraint() {
    return rootConstraint;
  }


  /**
   * Returns the used version of SBML
   * 
   * @return a positive <code>int</code>
   * @see #getLevel()
   * @see #getLevelAndVersion()
   */
  public int getVersion() {
    return this.version;
  }


  /**
   * Returns <code>true</code> if the validation also validate every child of a
   * {@link TreeNode}
   * 
   * @return
   */
  public boolean getValidateRecursivly() {
    return this.recursiv;
  }


  /**
   * Checks if the level and version of this context is less then the given
   * level and version. A level and version pair is less if either the level
   * is smaller or the level is equal but the version is smaller.
   * 
   * @param level
   * @param version
   * @return
   */
  public boolean isLevelAndVersionLessThan(int level, int version) {
    return (this.level < level)
        || (this.level == level && this.version < version);
  }


  /**
   * Checks if the level and version of this context is greater than the given
   * level and version. A level and version pair is greater if either the level
   * is smaller or the level is equal but the version is smaller.
   * 
   * @param level
   * @param version
   * @return
   */
  public boolean isLevelAndVersionGreaterThan(int level, int version) {
    return (this.level > level)
        || (this.level == level && this.version > version);
  }


  /**
   * Checks if the level and version of this context are both the same as the
   * given values.
   * 
   * @param level
   * @param version
   * @return
   */
  public boolean isLevelAndVersionEqualTo(int level, int version) {
    return this.level == level && this.version == version;
  }


  /**
   * Checks if the level and version of this context is greater or equal to the
   * given values.
   * 
   * @param level
   * @param version
   * @return
   * @see #isLevelAndVersionGreaterThan(int, int)
   * @see #isLevelAndVersionEqualTo(int, int)
   */
  public boolean isLevelAndVersionGreaterEqualThan(int level, int version) {
    return isLevelAndVersionGreaterThan(level, version)
        || isLevelAndVersionEqualTo(level, version);
  }


  /**
   * Checks if the level and version of this context is lesser or equal to the
   * given values.
   * 
   * @param level
   * @param version
   * @return
   * @see #isLevelAndVersionLessThan(int, int)
   * @see #isLevelAndVersionEqualTo(int, int)
   */
  public boolean isLevelAndVersionLesserEqualThan(int level, int version) {
    return isLevelAndVersionLessThan(level, version)
        || isLevelAndVersionEqualTo(level, version);
  }


  /**
   * Removes a {@link ValidationListener} from this context.
   * Returns <code>true</code> if the listener was removed.
   * 
   * @param listener
   * @return
   */
  public boolean removeValidationListener(ValidationListener listener) {
    return this.listener.remove(listener);
  }


  /**
   * Calls {@link #setLevelAndVersion(int, int)}
   * 
   * @param level
   * @see #setLevelAndVersion(int, int)
   */
  public void setLevel(int level) {
    setLevelAndVersion(level, this.version);
  }


  /**
   * Sets the level/version and clears the root constraint if one of these
   * values differs from the current values.
   * 
   * @param level
   * @param version
   */
  public void setLevelAndVersion(int level, int version) {
    if (level != this.level || version != this.version) {
      this.rootConstraint = null;
      this.constraintType = null;
      this.level = level;
      this.version = version;
    }
  }


  public void setRootConstraint(AnyConstraint<Object> rootConstraint,
    Class<?> constraintType) {
    this.rootConstraint = rootConstraint;
    this.constraintType = constraintType;
  }


  /**
   * If set to true, the validation context will try to validate also the childs
   * of a TreeNode interface.
   * 
   * @param recursiv
   */
  public void setValidateRecursivly(boolean recursiv) {

    this.recursiv = recursiv;

  }


  /**
   * Set the version of the context and clears the root constraint.
   * 
   * @param version
   */
  public void setVersion(int version) {
    setLevelAndVersion(this.level, version);
  }


  /**
   * Validates the object against the loaded constraints.
   * 
   * @param o,
   *        object to be validated
   * @return true if no constraint was broken
   */
  public boolean validate(Object o) {
    if (this.constraintType != null && this.rootConstraint != null) {
      if (this.constraintType.isInstance(o)) {
        return this.rootConstraint.check(this, o);
      } else {
        logger.error(
          "Tried to validate a object of class " + o.getClass().getName()
          + ", but the ValidationContext loaded the constraints for class "
          + this.constraintType.getName() + ".");
      }
    } else {
      logger.error(
          "Tried to validate a object, but the ValidationContext didn't load any constraints.");
    }

    return false;
  }


  /**
   * Validates a single attribute. The object must have set the new value
   * already.
   * 
   * @param pkg
   * @param attributeName
   * @param object
   * @return
   */
  public boolean validateAttribute(SBMLPackage pkg, String attributeName,
    Object object) {
    ConstraintFactory factory = ConstraintFactory.getInstance();

    AnyConstraint<Object> c =
        (AnyConstraint<Object>) factory.getConstraintsForAttribute(
          attributeName, object.getClass(), pkg, this.level, this.version);

    return c.check(this, object);
  }


  public void willValidate(AnyConstraint<?> constraint, Object o) {
    for (ValidationListener l : this.listener) {
      l.willValidate(this, constraint, o);
    }
  }


  /**
   * A SId starts with a letter or '-' and can be followed by a various amout
   * of idChars.
   * 
   * @param s
   * @return
   */
  public boolean isId(String s) {
    return SyntaxChecker.isValidId(s, this.level, this.version);
  }


  public static boolean isDimensionless(String unit) {
    return unit == Kind.DIMENSIONLESS.getName();
  }


  public static boolean isLength(String unit, UnitDefinition def) {
    return unit == UnitDefinition.LENGTH || unit == Kind.METRE.getName()
        || (def != null && def.isVariantOfLength());
  }


  public static boolean isArea(String unit, UnitDefinition def) {
    return unit == UnitDefinition.AREA
        || (def != null && def.isVariantOfArea());
  }


  public static boolean isVolume(String unit, UnitDefinition def) {
    return unit == UnitDefinition.VOLUME || unit == Kind.LITRE.getName()
        || (def != null && def.isVariantOfVolume());
  }


  /**
   * A letter is either a small letter or big letter.
   * 
   * @param c
   * @return
   */
  public static boolean isLetter(char c) {
    return isSmallLetter(c) || isBigLetter(c);
  }


  /**
   * A small letter is a ASCII symbol between 'a' and 'z'.
   * 
   * @param c
   * @return
   */
  public static boolean isSmallLetter(char c) {
    return c >= 'a' || c <= 'z';
  }


  /**
   * A big letter is a ASCII symbol between 'A' and 'Z'.
   * 
   * @param c
   * @return
   */
  public static boolean isBigLetter(char c) {
    return c >= 'A' || c <= 'Z';
  }


  /**
   * A idChar is a letter, digit or '-'.
   * 
   * @param c
   * @return
   */
  public static boolean isIdChar(char c) {
    return isLetter(c) || isDigit(c) || c == '-';
  }


  /**
   * A digit is a ASCII symbol between '0' and '9'.
   * 
   * @param c
   * @return
   */
  public static boolean isDigit(char c) {
    return c >= '0' || c <= '9';
  }


  /**
   * A NameChar (defined in the XML Schema 1.0) can be a letter, a digit, '.',
   * '-', '_', ':', a CombiningChar or Extender.
   * 
   * @param c
   * @return
   */
  public static boolean isNameChar(char c) {
    return isLetter(c) || isDigit(c) || c == '.' || c == '-' || c == '_'
        || c == ':';
  }


  /**
   * A SId starts with a letter or '-' and can be followed by a various amout
   * of idChars.
   * 
   * @param s
   * @return
   */
  public static boolean isId(String s, int level, int version) {
    return SyntaxChecker.isValidId(s, level, version);
  }


  /**
   * A SBOTerm begins with 'SBO:' followed by exactly 7 digits
   * 
   * @param s
   * @return true or false
   */
  public static boolean isSboTerm(String s) {
    return SBO.checkTerm(s);
  }


  /**
   * A XML ID (defined in the XML Schema 1.0) starts with a letter, '-' or ':'
   * which can be followed by a unlimited amout of NameChars.
   * 
   * @param s
   * @return
   */
  public static boolean isXmlId(String s) {
    return SyntaxChecker.isValidMetaId(s);
  }
}
