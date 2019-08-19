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

import org.sbml.jsbml.ListOf;
import org.sbml.jsbml.ext.spatial.GeometryDefinition;
import org.sbml.jsbml.ext.spatial.MixedGeometry;
import org.sbml.jsbml.ext.spatial.OrdinalMapping;
import org.sbml.jsbml.ext.spatial.SpatialConstants;
import org.sbml.jsbml.validator.SBMLValidator.CHECK_CATEGORY;
import org.sbml.jsbml.validator.offline.ValidationContext;
import org.sbml.jsbml.validator.offline.constraints.helper.DuplicatedElementValidationFunction;
import org.sbml.jsbml.validator.offline.constraints.helper.UnknownAttributeValidationFunction;
import org.sbml.jsbml.validator.offline.constraints.helper.UnknownCoreAttributeValidationFunction;
import org.sbml.jsbml.validator.offline.constraints.helper.UnknownCoreElementValidationFunction;
import org.sbml.jsbml.validator.offline.constraints.helper.UnknownElementValidationFunction;
import org.sbml.jsbml.validator.offline.constraints.helper.UnknownPackageElementValidationFunction;

/**
 * Defines validation rules (as {@link ValidationFunction} instances) for the {@link MixedGeometry} class.
 * 
 * @author Bhavye Jain
 * @since 1.5
 */
public class MixedGeometryConstraints extends AbstractConstraintDeclaration {

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
        addRangeToSet(set, SPATIAL_23801, SPATIAL_23807);
        set.add(SPATIAL_23850);
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
    ValidationFunction<MixedGeometry> func = null;

    switch (errorCode) {
    
    case SPATIAL_23801:
    {
      // A MixedGeometry object may have the optional SBML Level 3 Core attributes metaid and 
      // sboTerm. No other attributes from the SBML Level 3 Core namespaces are permitted on a 
      // MixedGeometry.

      func = new UnknownCoreAttributeValidationFunction<MixedGeometry>();
      break;
    }
    
    case SPATIAL_23802:
    {
      // A MixedGeometry object may have the optional SBML Level 3 Core subobjects for notes and 
      // annotations. No other elements from the SBML Level 3 Core namespaces are permitted on a 
      // MixedGeometry.

      func = new UnknownCoreElementValidationFunction<MixedGeometry>();
      break;
    }
    
    case SPATIAL_23803:
    {
      // A MixedGeometry object may contain one and only one instance of each of the ListOfGeometryDefinitions
      // and ListOfOrdinalMappings elements. No other elements from the SBML Level 3 Spatial Processes namespaces 
      // are permitted on a MixedGeometry object.

      func = new ValidationFunction<MixedGeometry>() {
        
        @Override
        public boolean check(ValidationContext ctx, MixedGeometry mg) {
          
          return new DuplicatedElementValidationFunction<MixedGeometry>(SpatialConstants.listOfGeometryDefinitions).check(ctx, mg)
              && new DuplicatedElementValidationFunction<MixedGeometry>(SpatialConstants.listOfOrdinalMappings).check(ctx, mg)
              && new UnknownPackageElementValidationFunction<MixedGeometry>(SpatialConstants.shortLabel).check(ctx, mg);
        }
      };
      
      break;
    }
    
    case SPATIAL_23804:
    {
      // Apart from the general notes and annotations subobjects permitted on all SBML objects, a 
      // ListOfGeometryDefinitions container object may only contain GeometryDefinition objects.

      func = new ValidationFunction<MixedGeometry>() {
        
        @Override
        public boolean check(ValidationContext ctx, MixedGeometry mg) {
          
          if(mg.isSetListOfGeometryDefinitions()) {
            return new UnknownElementValidationFunction<ListOf<GeometryDefinition>>().check(ctx, mg.getListOfGeometryDefinitions());
          }          
          return true;
        }
      };
      
      break;
    }
    
    case SPATIAL_23805:
    {
      // Apart from the general notes and annotations subobjects permitted on all SBML objects, a 
      // ListOfOrdinalMappings container object may only contain OrdinalMapping objects.

      func = new ValidationFunction<MixedGeometry>() {
        
        @Override
        public boolean check(ValidationContext ctx, MixedGeometry mg) {
          
          if(mg.isSetListOfGeometryDefinitions()) {
            return new UnknownElementValidationFunction<ListOf<OrdinalMapping>>().check(ctx, mg.getListOfOrdinalMappings());
          }          
          return true;
        }
      };
      
      break;
    }
    
    case SPATIAL_23806:
    {
      // A ListOfGeometryDefinitions object may have the optional SBML Level 3 Core attributes 
      // metaid and sboTerm. No other attributes from the SBML Level 3 Core namespaces are permitted 
      // on a ListOfGeometryDefinitions object.

      func = new ValidationFunction<MixedGeometry>() {
        
        @Override
        public boolean check(ValidationContext ctx, MixedGeometry mg) {
          
          if(mg.isSetListOfGeometryDefinitions()) {
            return new UnknownAttributeValidationFunction<ListOf<GeometryDefinition>>().check(ctx, mg.getListOfGeometryDefinitions());
          }          
          return true;
        }
      };

      break;
    }
    
    case SPATIAL_23807:
    {
      // A ListOfOrdinalMappings object may have the optional SBML Level 3 Core attributes metaid 
      // and sboTerm. No other attributes from the SBML Level 3 Core namespaces are permitted on a 
      // ListOfOrdinalMappings object.

      func = new ValidationFunction<MixedGeometry>() {
        
        @Override
        public boolean check(ValidationContext ctx, MixedGeometry mg) {
          
          if(mg.isSetListOfGeometryDefinitions()) {
            return new UnknownAttributeValidationFunction<ListOf<OrdinalMapping>>().check(ctx, mg.getListOfOrdinalMappings());
          }          
          return true;
        }
      };
      
      break;
    }
    
    case SPATIAL_23850:
    {
      func = new ValidationFunction<MixedGeometry>() {
        
        @Override
        public boolean check(ValidationContext ctx, MixedGeometry mg) {
          
          if(mg.isSetListOfGeometryDefinitions()) {
            ListOf<GeometryDefinition> logd = mg.getListOfGeometryDefinitions();
            for(GeometryDefinition gd : logd) {
              if(gd.getIsActive() == true) {
                return false;
              }
            }
          }
          
          return true;
        }
      };
      break;
    }
    }    

    return func;
  }

}

