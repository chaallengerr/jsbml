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
 * 4. The University of California, San Diego, La Jolla, CA, USA
 * 
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation. A copy of the license agreement is provided
 * in the file named "LICENSE.txt" included with this software distribution
 * and also available online as <http://sbml.org/Software/JSBML/License>.
 * ----------------------------------------------------------------------------
 */
package org.sbml.jsbml.ext.fbc;

import java.util.Locale;
import java.util.Map;

import org.sbml.jsbml.AbstractNamedSBase;
import org.sbml.jsbml.LevelVersionError;
import org.sbml.jsbml.SBMLException;
import org.sbml.jsbml.Reaction;
import org.sbml.jsbml.UniqueNamedSBase;
import org.sbml.jsbml.util.StringTools;

/**
 * 
 * @author Nicolas Rodriguez
 * @version $Rev$
 * @since 1.0
 * @date 27.10.2011
 */
public class FluxBound extends AbstractNamedSBase implements UniqueNamedSBase {

  /**
   * Allowable operations for flux bounds.
   * 
   * @author Andreas Dr&auml;ger
   * @since 1.0
   * @version $Rev$
   */
  public enum Operation {
    /**
     * equal
     */
    EQUAL("equal"),
    /**
     * greaterEqual
     */
    GREATER_EQUAL("greaterEqual"),
    /**
     * lessEqual
     */
    LESS_EQUAL("lessEqual");
    
    /**
     * SBML attribute name.
     */
    private String id;
    
    /**
     * Memorize the SBML attribute name as id.
     * 
     * @param id
     */
    private Operation(String id) {
      this.id = id;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
      return id;
    }
	
	public static Operation fromString(String value)
	{
		if (value == null)
		{
			throw new IllegalArgumentException();
		}
		
		for(Operation v : values())
		{
			if(value.equalsIgnoreCase(v.id))
			{
				return v;
			}
		}
		throw new IllegalArgumentException();

	}

  }

  /**
   * Generated serial version identifier.
   */
  private static final long serialVersionUID = -8885319163985464653L;
  private boolean isSetValue = false;
  private Operation operation;
  private String reaction;
  private Double value;
  

  /**
   * Creates an instance of FluxBound.
   */
  public FluxBound() {
    super();
    initDefaults();
  }

  /**
   * 
   * @param fb
   */
  public FluxBound(FluxBound fb) {
    super(fb);
    this.operation = fb.getOperation();
    this.reaction = new String(fb.getReaction());
    this.value = fb.isSetValue() ? new Double(fb.getValue()) : null;
    this.isSetValue = fb.isSetValue();
  }

  /**
   * Creates a FluxBound instance with a level and version. 
   * 
   * @param level
   * @param version
   */
  public FluxBound(int level, int version) {
    this(null, null, level, version);
  }

  /**
   * Creates a FluxBound instance with an id. 
   * 
   * @param id
   */
  public FluxBound(String id) {
    super(id);
    initDefaults();
  }

  /**
   * Creates a {@link FluxBound} instance with an id, level, and version. 
   * 
   * @param id
   * @param level
   * @param version
   */
  public FluxBound(String id, int level, int version) {
    this(id, null, level, version);
  }

  /**
   * Creates a FluxBound instance with an id, name, level, and version. 
   * 
   * @param id
   * @param name
   * @param level
   * @param version
   */
  public FluxBound(String id, String name, int level, int version) {
    super(id, name, level, version);
    if (getLevelAndVersion().compareTo(
      Integer.valueOf(FBCConstants.MIN_SBML_LEVEL),
      Integer.valueOf(FBCConstants.MIN_SBML_VERSION)) < 0) {
      throw new LevelVersionError(getElementName(), level, version);
    }
    initDefaults();
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.AbstractSBase#clone()
   */
  public FluxBound clone() {
    return new FluxBound(this);
  }

  /**
   * Returns the operation
   * 
   * @return the operation
   */
  public Operation getOperation() {
    return operation;
  }


  /**
   * Returns the reaction id
   * 
   * @return the reaction id
   */
  public String getReaction() {
    return reaction;
  }

  /**
   * Returns the value
   * 
   * @return the value
   */
  public double getValue() {
    return isSetValue() ? value : Double.valueOf(0d);
  }

  /**
   * Initializes the default values using the namespace.
   */
  public void initDefaults() {
    addNamespace(FBCConstants.namespaceURI);
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.NamedSBase#isIdMandatory()
   */
  public boolean isIdMandatory() {
    return false;
  }

  /**
   * 
   * @return
   */
  public boolean isSetValue() {
    return isSetValue;
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.element.SBase#readAttribute(String attributeName, String prefix, String value)
   */
  @Override
  public boolean readAttribute(String attributeName, String prefix, String value) {
    boolean isAttributeRead = super.readAttribute(attributeName, prefix,
      value);

    if (!isAttributeRead) {
      isAttributeRead = true;

      if (attributeName.equals(FBCConstants.reaction)) {
        setReaction(value);
      } else if (attributeName.equals(FBCConstants.operation)) {
    	  try 
    	  {
    		  setOperation(Operation.fromString(value));
    	  }
    	  catch (Exception e) 
    	  {
    		  throw new SBMLException("Could not recognized the value '" + value
    				  + "' for the attribute " + FBCConstants.operation
    				  + " on the 'fluxBound' element.");
    	  }
    	  return true;
      } else if (attributeName.equals(FBCConstants.value)) {
        setValue(StringTools.parseSBMLDouble(value));
      } else {
        isAttributeRead = false;
      }

    }

    return isAttributeRead;
  }

  /**
   * 
   * @param operation  the operation to set
   */
  public void setOperation(Operation operation) {
    Operation oldOperation = this.operation;
    this.operation = operation;
    firePropertyChange(FBCConstants.operation, oldOperation, operation);
  }

  /**
   * 
   * @param reaction
   */
  public void setReaction(Reaction reaction) {
    setReaction(reaction.getId());
  }

  /**
   * Sets the the reaction id
   * 
   * @param reaction the reaction id to set
   */
  public void setReaction(String reaction) {
    String oldReaction = this.reaction;
    this.reaction = reaction;
    firePropertyChange(FBCConstants.reaction, oldReaction, reaction);
  }

  /**
   * @param value the value to set
   */
  public void setValue(double value) {
    Double oldValue = this.value;
    this.value = value;
    isSetValue = true;
    firePropertyChange(FBCConstants.value, oldValue, this.value);
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.element.SBase#writeXMLAttributes()
   */
  @Override
  public Map<String, String> writeXMLAttributes() {
    Map<String, String> attributes = super.writeXMLAttributes();

    if (reaction != null) {
      attributes.put(FBCConstants.shortLabel + ':' + FBCConstants.reaction,
        getReaction());			
    }
    if (operation != null) {
      attributes.put(FBCConstants.shortLabel + ':' + FBCConstants.operation,
        getOperation().toString());
    }
    if (isSetValue()) {
      attributes.put(FBCConstants.shortLabel + ':' + FBCConstants.value,
        StringTools.toString(Locale.ENGLISH, getValue()));
    }
    if (isSetId()) {
      attributes.remove("id");
      attributes.put(FBCConstants.shortLabel + ":id", getId());
    }    
    if (isSetName()) {
        attributes.remove("name");
        attributes.put(FBCConstants.shortLabel + ":id", getId());
    }

    return attributes;
  }

  

  /**
   * Unsets the variable value 
   *
   * @return {@code true}, if value was set before, 
   *         otherwise {@code false}
   */
  public boolean unsetValue() {
	  if (isSetValue()) {
		  double oldValue = this.value;
		  this.value = null;
		  firePropertyChange(FBCConstants.value, oldValue, this.value);
		  return true;
	  }
	  return false;
  }
  


  /**
   * Returns whether operation is set 
   *
   * @return whether operation is set 
   */
  public boolean isSetOperation() {
	  return this.operation != null;
  }


  /**
   * Unsets the variable operation 
   *
   * @return {@code true}, if operation was set before, 
   *         otherwise {@code false}
   */
  public boolean unsetOperation() {
	  if (isSetOperation()) {
		  Operation oldOperation = this.operation;
		  this.operation = null;
		  firePropertyChange(FBCConstants.operation, oldOperation, this.operation);
		  return true;
	  }
	  return false;
  }

  

  /**
   * Returns whether reaction is set 
   *
   * @return whether reaction is set 
   */
  public boolean isSetReaction() {
	  return this.reaction != null;
  }

  /**
   * Unsets the variable reaction 
   *
   * @return {@code true}, if reaction was set before, 
   *         otherwise {@code false}
   */
  public boolean unsetReaction() {
	  if (isSetReaction()) {
		  String oldReaction = this.reaction;
		  this.reaction = null;
		  firePropertyChange(FBCConstants.reaction, oldReaction, this.reaction);
		  return true;
	  }
	  return false;
  }

}
