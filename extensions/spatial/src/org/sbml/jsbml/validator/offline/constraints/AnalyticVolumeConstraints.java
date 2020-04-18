/*
 * ----------------------------------------------------------------------------
 * This file is part of JSBML. Please visit <http://sbml.org/Software/JSBML>
 * for the latest version of JSBML and more information about SBML.
 * 
 * Copyright (C) 2009-2019 jointly by the following organizations:
 * 1. The University of Tuebingen, Germany
 * 2. EMBL European Bioinformatics Institute (EBML-EBI), Hinxton, UK
 * 3. The California Institute of Technology, Pasadena, CA, USA
 * 4. The Babraham Institute, Cambridge, UK
 * 
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation. A copy of the license agreement is provided
 * in the file named "LICENSE.txt" included with this software distribution
 * and also available online as <http://sbml.org/Software/JSBML/License>.
 * ----------------------------------------------------------------------------
 */

package org.sbml.jsbml.validator.offline.constraints;

import java.util.Set;

import org.sbml.jsbml.ext.spatial.AnalyticVolume;
import org.sbml.jsbml.ext.spatial.DomainType;
import org.sbml.jsbml.ext.spatial.SpatialConstants;
import org.sbml.jsbml.ext.spatial.SpatialModelPlugin;
import org.sbml.jsbml.validator.SBMLValidator.CHECK_CATEGORY;
import org.sbml.jsbml.validator.offline.ValidationContext;
import org.sbml.jsbml.validator.offline.constraints.helper.DuplicatedMathValidationFunction;
import org.sbml.jsbml.validator.offline.constraints.helper.InvalidAttributeValidationFunction;
import org.sbml.jsbml.validator.offline.constraints.helper.UnknownCoreAttributeValidationFunction;
import org.sbml.jsbml.validator.offline.constraints.helper.UnknownCoreElementValidationFunction;
import org.sbml.jsbml.validator.offline.constraints.helper.UnknownPackageAttributeValidationFunction;

/**
 * Defines validation rules (as {@link ValidationFunction} instances) for the {@link AnalyticVolume} class.
 * 
 * @author Bhavye Jain
 * @since 1.5
 */
public class AnalyticVolumeConstraints extends AbstractConstraintDeclaration {

  /* (non-Javadoc)
   * @see org.sbml.jsbml.validator.offline.constraints.ConstraintDeclaration#addErrorCodesForAttribute(java.util.Set, int, int, java.lang.String)
   */
  @Override
  public void addErrorCodesForAttribute(Set<Integer> set, int level,
    int version, String attributeName, ValidationContext context) 
  {
    // TODO 

  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.validator.offline.constraints.ConstraintDeclaration#addErrorCodesForCheck(java.util.Set, int, int, org.sbml.jsbml.validator.SBMLValidator.CHECK_CATEGORY)
   */
  @Override
  public void addErrorCodesForCheck(Set<Integer> set, int level, int version,
    CHECK_CATEGORY category, ValidationContext context) {
    switch (category) {
    case GENERAL_CONSISTENCY:
      if(level >= 3){		    		
        addRangeToSet(set, SPATIAL_21901, SPATIAL_21908);
      }
      break;
    case IDENTIFIER_CONSISTENCY:
      break;
    case MATHML_CONSISTENCY:
      break;
    case MODELING_PRACTICE:
      break;
    case OVERDETERMINED_MODEL:
      break;
    case SBO_CONSISTENCY:
      break;
    case UNITS_CONSISTENCY:
      break;
    }
  }

  @Override
  public ValidationFunction<?> getValidationFunction(int errorCode, ValidationContext context){
    ValidationFunction<AnalyticVolume> func = null;

    switch (errorCode) {

    case SPATIAL_21901:
    {
      // An AnalyticVolume object may have the optional SBML Level 3 Core attributes metaid and sboTerm.
      // No other attributes from the SBML Level 3 Core namespaces are permitted on an AnalyticVolume.

      func = new UnknownCoreAttributeValidationFunction<AnalyticVolume>();
      break;
    }

    case SPATIAL_21902:
    {
      // An AnalyticVolume object may have the optional SBML Level 3 Core subobjects for notes and 
      // annotations. No other elements from the SBML Level 3 Core namespaces are permitted on an 
      // AnalyticVolume.

      func = new UnknownCoreElementValidationFunction<AnalyticVolume>();
      break;
    }

    case SPATIAL_21903:
    {
      // An AnalyticVolume object must have the required attributes spatial:id, spatial:functionType 
      // and spatial:domainType, and may have the optional attributes spatial:name and spatial:ordinal. 
      // No other attributes from the SBML Level 3 Spatial Processes namespaces are permitted on an
      // AnalyticVolume object.

      func = new UnknownPackageAttributeValidationFunction<AnalyticVolume>(SpatialConstants.shortLabel){

        @Override
        public boolean check(ValidationContext ctx, AnalyticVolume av) {

          if(!av.isSetId()) {
            return false;
          }

          if(!av.isSetFunctionType()) {
            return false;
          }

          if(!av.isSetDomainType()) {
            return false;
          }

          return super.check(ctx, av);
        }
      };
      break;
    }

    case SPATIAL_21904:
    {
      // An AnalyticVolume object may contain one and only one instance of the ASTNode element. 
      // No other elements from the SBML Level 3 Spatial Processes namespaces are permitted on an 
      // AnalyticVolume object.

      func = new DuplicatedMathValidationFunction<AnalyticVolume>(true);
      break;
    }

    case SPATIAL_21905:
    {
      // The value of the attribute spatial:functionType of an AnalyticVolume object must conform 
      // to the syntax of SBML data type FunctionKind and may only take on the allowed values of 
      // FunctionKind defined in SBML; that is, the value must be one of the following: "layered".

      func = new InvalidAttributeValidationFunction<AnalyticVolume>(SpatialConstants.functionType);
      break;
    }

    case SPATIAL_21906:
    {
      // The value of the attribute spatial:domainType of an AnalyticVolume object must be the 
      // identifier of an existing DomainType object defined in the enclosing Model object.

      func = new ValidationFunction<AnalyticVolume>() {

        @Override 
        public boolean check(ValidationContext ctx, AnalyticVolume av) {

          if(av.isSetDomainType()) {
            SpatialModelPlugin smp = (SpatialModelPlugin) av.getModel().getPlugin(SpatialConstants.shortLabel);
            if(smp.isSetGeometry()) {
              DomainType dom = smp.getGeometry().getDomainType(av.getDomainType());
              if(dom == null) {
                return false;
              }
            }		  					
          }
          return true;
        }
      };
      break;
    }

    case SPATIAL_21907:
    {
      // The attribute spatial:name on an AnalyticVolume must have a value of data type string.

      func = new ValidationFunction<AnalyticVolume>() {

        @Override
        public boolean check(ValidationContext ctx, AnalyticVolume av) {

          // nothing to check as Java can read any kind of string.
          return true;
        }
      };
      break;
    }

    case SPATIAL_21908:
    {
      // The attribute spatial:ordinal on an AnalyticVolume must have a value of data type integer.

      func = new InvalidAttributeValidationFunction<AnalyticVolume>(SpatialConstants.ordinal);
      break;
    }
    }

    return func;
  }

}