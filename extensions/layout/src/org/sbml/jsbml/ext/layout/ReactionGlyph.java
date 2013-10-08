/*
 * $Id$
 * $URL$
 * ----------------------------------------------------------------------------
 * This file is part of JSBML. Please visit <http://sbml.org/Software/JSBML>
 * for the latest version of JSBML and more information about SBML.
 *
 * Copyright (C) 2009-2013 jointly by the following organizations:
 * 1. The University of Tuebingen, Germany
 * 2. EMBL European Bioinformatics Institute (EBML-EBI), Hinxton, UK
 * 3. The California Institute of Technology, Pasadena, CA, USA
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation. A copy of the license agreement is provided
 * in the file named "LICENSE.txt" included with this software distribution
 * and also available online as <http://sbml.org/Software/JSBML/License>.
 * ----------------------------------------------------------------------------
 */
package org.sbml.jsbml.ext.layout;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.Map;

import javax.swing.tree.TreeNode;

import org.sbml.jsbml.ListOf;
import org.sbml.jsbml.NamedSBase;
import org.sbml.jsbml.Reaction;

/**
 * @author Nicolas Rodriguez
 * @author Sebastian Fr&ouml;lich
 * @author Andreas Dr&auml;ger
 * @since 1.0
 * @version $Rev$
 */
public class ReactionGlyph extends AbstractReferenceGlyph {

  /**
   * Generated serial version identifier.
   */
  private static final long serialVersionUID = 8770691813350594995L;

  /**
   * 
   */
  private Curve curve;

  /**
   * 
   */
  private ListOf<SpeciesReferenceGlyph> listOfSpeciesReferencesGlyph;

  /**
   * 
   */
  public ReactionGlyph() {
    super();
  }

  /**
   * 
   * @param level
   * @param version
   */
  public ReactionGlyph(int level, int version) {
    super(level, version);
  }

  /**
   * 
   * @param reactionGlyph
   */
  public ReactionGlyph(ReactionGlyph reactionGlyph) {
    super(reactionGlyph);
    if (reactionGlyph.isSetCurve()) {
      this.curve = reactionGlyph.getCurve().clone();
    }
    if (reactionGlyph.isSetListOfSpeciesReferencesGlyphs()) {
      this.listOfSpeciesReferencesGlyph = reactionGlyph
          .getListOfSpeciesReferenceGlyphs().clone();
    }
  }

  /**
   * 
   * @param id
   */
  public ReactionGlyph(String id) {
    super(id);
  }

  /**
   * 
   * @param id
   * @param level
   * @param version
   */
  public ReactionGlyph(String id, int level, int version) {
    super(id, level, version);
  }

  /**
   * Appends the specified element to the end of the
   * {@link #listOfSpeciesReferencesGlyph}.
   * 
   * @param glyph
   * @return {@code true} (as specified by {@link Collection#add(E)})
   * @throws NullPointerException
   *             if the specified element is null and this list does not
   *             permit null elements
   * @throws IllegalArgumentException
   *             if some property of this element prevents it from being added
   *             to this list
   */
  public boolean addSpeciesReferenceGlyph(SpeciesReferenceGlyph glyph) {
    return getListOfSpeciesReferenceGlyphs().add(glyph);
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.ext.layout.GraphicalObject#clone()
   */
  public ReactionGlyph clone() {
    return new ReactionGlyph(this);
  }

  /**
   * Creates and adds a new {@link SpeciesReferenceGlyph}
   * @param id the identifier for the {@link SpeciesReferenceGlyph} to be created.
   * @return a new {@link SpeciesReferenceGlyph}.
   */
  public SpeciesReferenceGlyph createSpeciesReferenceGlyph(String id) {
    SpeciesReferenceGlyph glyph = new SpeciesReferenceGlyph(id, getLevel(), getVersion());
    addSpeciesReferenceGlyph(glyph);
    return glyph;
  }

  /**
   * Creates and adds a new {@link SpeciesReferenceGlyph}
   * 
   * @param id
   *        the identifier for the {@link SpeciesReferenceGlyph} to be created.
   * @param speciesGlyph
   *        corresponding {@link SpeciesGlyph} ID.
   * @return a new {@link SpeciesReferenceGlyph} that points to the given
   *         {@code speciesGlyph}.
   */
  public SpeciesReferenceGlyph createSpeciesReferenceGlyph(String id, String speciesGlyph) {
    SpeciesReferenceGlyph glyph = createSpeciesReferenceGlyph(id);
    glyph.setSpeciesGlyph(speciesGlyph);
    return glyph;
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.AbstractNamedSBase#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object object) {
    boolean equals = super.equals(object);
    if (equals) {
      ReactionGlyph reactionGlyph = (ReactionGlyph) object;
      equals &= reactionGlyph.isSetCurve() == isSetCurve();
      if (equals && isSetCurve()) {
        equals &= reactionGlyph.getCurve().equals(getCurve());
      }
      equals &= reactionGlyph.isSetListOfSpeciesReferencesGlyphs() == isSetListOfSpeciesReferencesGlyphs();
      if (equals && isSetListOfSpeciesReferencesGlyphs()) {
        equals &= reactionGlyph.getListOfSpeciesReferenceGlyphs().equals(getListOfSpeciesReferenceGlyphs());
      }
    }
    return equals;
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.ext.layout.GraphicalObject#getChildAt(int)
   */
  @Override
  public TreeNode getChildAt(int index) {
    if (index < 0) {
      throw new IndexOutOfBoundsException(Integer.toString(index));
    }
    int count = super.getChildCount(), pos = 0;
    if (index < count) {
      return super.getChildAt(index);
    } else {
      index -= count;
    }
    if (isSetCurve()) {
      if (pos == index) {
        return getCurve();
      }
      pos++;
    }
    if (isSetListOfSpeciesReferencesGlyphs()) {
      if (pos == index) {
        return getListOfSpeciesReferenceGlyphs();
      }
      pos++;
    }
    throw new IndexOutOfBoundsException(MessageFormat.format(
      "Index {0,number,integer} >= {1,number,integer}",
      index, +((int) Math.min(pos, 0))));
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.ext.layout.GraphicalObject#getChildCount()
   */
  @Override
  public int getChildCount() {
    int count = super.getChildCount();
    if (isSetCurve()) {
      count++;
    }
    if (isSetListOfSpeciesReferencesGlyphs()) {
      count++;
    }
    return count;
  }

  /**
   * 
   * @return
   */
  public Curve getCurve() {
    return curve;
  }

  /**
   * If the {@link #listOfSpeciesReferencesGlyph} has not yet been initialized, this
   * will be done by this method.
   * 
   * @return the {@link #listOfSpeciesReferencesGlyph}
   */
  public ListOf<SpeciesReferenceGlyph> getListOfSpeciesReferenceGlyphs() {
    if (!isSetListOfSpeciesReferencesGlyphs()) {
      listOfSpeciesReferencesGlyph = new ListOf<SpeciesReferenceGlyph>();
      listOfSpeciesReferencesGlyph.addNamespace(LayoutConstants.namespaceURI);
      listOfSpeciesReferencesGlyph.setSBaseListType(ListOf.Type.other);
      registerChild(listOfSpeciesReferencesGlyph);
    }
    return listOfSpeciesReferencesGlyph;
  }

  /**
   * 
   * @return
   */
  public String getReaction() {
    return getReference();
  }

  /**
   * Note that the return type of this method is {@link NamedSBase} because it
   * could be possible to link some element from other packages to this glyph.
   * 
   * @return
   */
  public NamedSBase getReactionInstance() {
    return getNamedSBaseInstance();
  }

  /**
   * 
   * @param i
   * @return
   */
  public SpeciesReferenceGlyph getSpeciesReferenceGlyph(int i) {
    return getListOfSpeciesReferenceGlyphs().get(i);
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.AbstractSBase#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 953;
    int hashCode = super.hashCode();
    if (isSetCurve()) {
      hashCode += prime * getCurve().hashCode();
    }
    if (isSetListOfSpeciesReferencesGlyphs()) {
      hashCode += prime * getListOfSpeciesReferenceGlyphs().hashCode();
    }
    return hashCode;
  }

  /**
   * @return
   */
  public boolean isSetCurve() {
    return curve != null;
  }

  /**
   * @return
   */
  public boolean isSetListOfSpeciesReferencesGlyphs() {
    return listOfSpeciesReferencesGlyph != null;
  }

  /**
   * @return
   */
  public boolean isSetReaction() {
    return isSetReference();
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.AbstractNamedSBase#readAttribute(java.lang.String, java.lang.String, java.lang.String)
   */
  @Override
  public boolean readAttribute(String attributeName, String prefix,
    String value) {
    boolean isAttributeRead = super.readAttribute(attributeName, prefix,
      value);

    if (!isAttributeRead) {

      isAttributeRead = true;			
      if (attributeName.equals("reaction")) {				
        setReaction(value);
      } else {
        return false;
      }
    }

    return isAttributeRead;
  }

  /**
   * 
   * @param curve
   */
  public void setCurve(Curve curve) {
    if (this.curve != null) {
      Curve oldValue = this.curve;
      this.curve = null;
      oldValue.fireNodeRemovedEvent();
    }
    this.curve = curve;
    registerChild(this.curve);
  }

  /**
   * 
   * @param listOfSpeciesReferencesGlyph
   */
  public void setListOfSpeciesReferencesGlyph(
    ListOf<SpeciesReferenceGlyph> listOfSpeciesReferencesGlyph) {
    unsetListOfSpeciesReferencesGlyph();
    this.listOfSpeciesReferencesGlyph = listOfSpeciesReferencesGlyph;
    registerChild(this.listOfSpeciesReferencesGlyph);
  }

  /**
   * 
   * @param reaction
   */
  public void setReaction(Reaction reaction) {
    setReaction(reaction.getId());
  }

  /**
   * 
   * @param reaction
   */
  public void setReaction(String reaction) {
    setReference(reaction, LayoutConstants.reaction);
  }

  /**
   * 
   */
  private void unsetListOfSpeciesReferencesGlyph() {
    if (this.listOfSpeciesReferencesGlyph != null) {
      ListOf<SpeciesReferenceGlyph> oldValue = this.listOfSpeciesReferencesGlyph;
      this.listOfSpeciesReferencesGlyph = null;
      oldValue.fireNodeRemovedEvent();
    }
  }

  /**
   * 
   */
  public void unsetReaction() {
    unsetReference();
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.ext.layout.GraphicalObject#writeXMLAttributes()
   */
  @Override
  public Map<String, String> writeXMLAttributes() {
    Map<String, String> attributes = super.writeXMLAttributes();

    if (isSetReaction()) {
      attributes.put(LayoutConstants.shortLabel + ':'
        + LayoutConstants.reaction, getReaction());
    }

    return attributes;
  }

}
