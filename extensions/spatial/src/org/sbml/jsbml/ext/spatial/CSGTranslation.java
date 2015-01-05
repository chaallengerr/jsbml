/*
 * $Id$
 * $URL$
 * ----------------------------------------------------------------------------
 * This file is part of JSBML. Please visit <http://sbml.org/Software/JSBML>
 * for the latest version of JSBML and more information about SBML.
 * 
 * Copyright (C) 2009-2015 jointly by the following organizations:
 * 1. The University of Tuebingen, Germany
 * 2. EMBL European Bioinformatics Institute (EBML-EBI), Hinxton, UK
 * 3. The California Institute of Technology, Pasadena, CA, USA
 * 4. The University of California, San Diego, La Jolla, CA, USA
 * 5. The Babraham Institute, Cambridge, UK
 * 
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation. A copy of the license agreement is provided
 * in the file named "LICENSE.txt" included with this software distribution
 * and also available online as <http://sbml.org/Software/JSBML/License>.
 * ----------------------------------------------------------------------------
 */
package org.sbml.jsbml.ext.spatial;

import java.text.MessageFormat;
import java.util.Map;

import org.sbml.jsbml.PropertyUndefinedError;
import org.sbml.jsbml.util.StringTools;

/**
 * @author Alex-Thomas
 * @version $Rev$
 * @since 1.0
 * @date Jan 20, 2014
 */
public class CSGTranslation extends CSGTransformation {

  /**
   * 
   */
  private static final long serialVersionUID = 7030963917812170311L;
  /**
   * 
   */
  private Double translateX;
  /**
   * 
   */
  private Double translateY;
  /**
   * 
   */
  private Double translateZ;

  /**
   * 
   */
  public CSGTranslation() {
    super();
  }


  /**
   * @param csgt
   */
  public CSGTranslation(CSGTranslation csgt) {
    super(csgt);
    if (csgt.isSetTranslateX()) {
      translateX = new Double(csgt.getTranslateX());
    }
    if (csgt.isSetTranslateY()) {
      translateY = new Double(csgt.getTranslateY());
    }
    if (csgt.isSetTranslateZ()) {
      translateZ = new Double(csgt.getTranslateZ());
    }
  }


  /**
   * @param level
   * @param version
   */
  public CSGTranslation(int level, int version) {
    super(level, version);
  }


  /**
   * 
   * @param id
   * @param level
   * @param version
   */
  public CSGTranslation(String id, int level, int version) {
    super(id, level, version);
  }


  @Override
  public CSGTranslation clone() {
    return new CSGTranslation(this);
  }


  @Override
  public boolean equals(Object object) {
    boolean equal = super.equals(object);
    if (equal) {
      CSGTranslation csgt = (CSGTranslation) object;

      equal &= csgt.isSetTranslateX() == isSetTranslateX();
      if (equal && isSetTranslateX()) {
        equal &= csgt.getTranslateX() == getTranslateX();
      }

      equal &= csgt.isSetTranslateY() == isSetTranslateY();
      if (equal && isSetTranslateY()) {
        equal &= csgt.getTranslateY() == getTranslateY();
      }

      equal &= csgt.isSetTranslateZ() == isSetTranslateZ();
      if (equal && isSetTranslateZ()) {
        equal &= csgt.getTranslateZ() == getTranslateZ();
      }
    }
    return equal;
  }


  /**
   * Returns the value of translateX
   *
   * @return the value of translateX
   */
  public double getTranslateX() {
    if (isSetTranslateX()) {
      return translateX;
    }
    // This is necessary if we cannot return null here.
    throw new PropertyUndefinedError(SpatialConstants.translateX, this);
  }


  /**
   * Returns whether translateX is set
   *
   * @return whether translateX is set
   */
  public boolean isSetTranslateX() {
    return translateX != null;
  }


  /**
   * Sets the value of translateX
   * @param translateX
   */
  public void setTranslateX(double translateX) {
    double oldTranslateX = this.translateX;
    this.translateX = translateX;
    firePropertyChange(SpatialConstants.translateX, oldTranslateX, this.translateX);
  }


  /**
   * Unsets the variable translateX
   *
   * @return {@code true}, if translateX was set before,
   *         otherwise {@code false}
   */
  public boolean unsetTranslateX() {
    if (isSetTranslateX()) {
      double oldTranslateX = translateX;
      translateX = null;
      firePropertyChange(SpatialConstants.translateX, oldTranslateX, translateX);
      return true;
    }
    return false;
  }


  /**
   * Returns the value of translateY
   *
   * @return the value of translateY
   */
  public double getTranslateY() {
    if (isSetTranslateY()) {
      return translateY;
    }
    // This is necessary if we cannot return null here.
    throw new PropertyUndefinedError(SpatialConstants.translateY, this);
  }


  /**
   * Returns whether translateY is set
   *
   * @return whether translateY is set
   */
  public boolean isSetTranslateY() {
    return translateY != null;
  }


  /**
   * Sets the value of translateY
   * @param translateY
   */
  public void setTranslateY(double translateY) {
    double oldTranslateY = this.translateY;
    this.translateY = translateY;
    firePropertyChange(SpatialConstants.translateY, oldTranslateY, this.translateY);
  }


  /**
   * Unsets the variable translateY
   *
   * @return {@code true}, if translateY was set before,
   *         otherwise {@code false}
   */
  public boolean unsetTranslateY() {
    if (isSetTranslateY()) {
      double oldTranslateY = translateY;
      translateY = null;
      firePropertyChange(SpatialConstants.translateY, oldTranslateY, translateY);
      return true;
    }
    return false;
  }


  /**
   * Returns the value of translateZ
   *
   * @return the value of translateZ
   */
  public double getTranslateZ() {
    if (isSetTranslateZ()) {
      return translateZ;
    }
    // This is necessary if we cannot return null here.
    throw new PropertyUndefinedError(SpatialConstants.translateZ, this);
  }


  /**
   * Returns whether translateZ is set
   *
   * @return whether translateZ is set
   */
  public boolean isSetTranslateZ() {
    return translateZ != null;
  }


  /**
   * Sets the value of translateZ
   * @param translateZ
   */
  public void setTranslateZ(double translateZ) {
    double oldTranslateZ = this.translateZ;
    this.translateZ = translateZ;
    firePropertyChange(SpatialConstants.translateZ, oldTranslateZ, this.translateZ);
  }


  /**
   * Unsets the variable translateZ
   *
   * @return {@code true}, if translateZ was set before,
   *         otherwise {@code false}
   */
  public boolean unsetTranslateZ() {
    if (isSetTranslateZ()) {
      double oldTranslateZ = translateZ;
      translateZ = null;
      firePropertyChange(SpatialConstants.translateZ, oldTranslateZ, translateZ);
      return true;
    }
    return false;
  }


  @Override
  public int hashCode() {
    final int prime = 313;//Change this prime number
    int hashCode = super.hashCode();
    if (isSetTranslateX()) {
      hashCode += prime * getTranslateX();
    }
    if (isSetTranslateY()) {
      hashCode += prime * getTranslateY();
    }
    if (isSetTranslateZ()) {
      hashCode += prime * getTranslateZ();
    }
    return hashCode;
  }


  @Override
  public Map<String, String> writeXMLAttributes() {
    Map<String, String> attributes = super.writeXMLAttributes();
    if (isSetTranslateX()) {
      attributes.remove("translateX");
      attributes.put(SpatialConstants.shortLabel + ":translateX", String.valueOf(getTranslateX()));
    }
    if (isSetTranslateY()) {
      attributes.remove("translateY");
      attributes.put(SpatialConstants.shortLabel + ":translateY", String.valueOf(getTranslateY()));
    }
    if (isSetTranslateZ()) {
      attributes.remove("translateZ");
      attributes.put(SpatialConstants.shortLabel + ":translateZ", String.valueOf(getTranslateZ()));
    }
    return attributes;
  }


  @Override
  public boolean readAttribute(String attributeName, String prefix, String value) {
    boolean isAttributeRead = (super.readAttribute(attributeName, prefix, value))
        && (SpatialConstants.shortLabel == prefix);
    if (!isAttributeRead) {
      isAttributeRead = true;
      if (attributeName.equals(SpatialConstants.translateX)) {
        try {
          setTranslateX(StringTools.parseSBMLInt(value));
        } catch (Exception e) {
          MessageFormat.format(SpatialConstants.bundle.getString("COULD_NOT_READ"), value,
            SpatialConstants.translateX);
        }
      }
      else if (attributeName.equals(SpatialConstants.translateY)) {
        try {
          setTranslateY(StringTools.parseSBMLInt(value));
        } catch (Exception e) {
          MessageFormat.format(SpatialConstants.bundle.getString("COULD_NOT_READ"), value,
            SpatialConstants.translateY);
        }
      }
      else if (attributeName.equals(SpatialConstants.translateZ)) {
        try {
          setTranslateZ(StringTools.parseSBMLInt(value));
        } catch (Exception e) {
          MessageFormat.format(SpatialConstants.bundle.getString("COULD_NOT_READ"), value,
            SpatialConstants.translateZ);
        }
      }
      else {
        isAttributeRead = false;
      }
    }
    return isAttributeRead;
  }


  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("CSGTranslation [translateX=");
    builder.append(translateX);
    builder.append(", translateY=");
    builder.append(translateY);
    builder.append(", translateZ=");
    builder.append(translateZ);
    builder.append("]");
    return builder.toString();
  }


}
