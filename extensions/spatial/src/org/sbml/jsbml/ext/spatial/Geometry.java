/*
 * $Id$
 * $URL$
 * ----------------------------------------------------------------------------
 * This file is part of JSBML. Please visit <http://sbml.org/Software/JSBML>
 * for the latest version of JSBML and more information about SBML.
 *
 * Copyright (C) 2009-2014 jointly by the following organizations:
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
package org.sbml.jsbml.ext.spatial;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.Map;
import java.util.ResourceBundle;

import javax.swing.tree.TreeNode;

import org.sbml.jsbml.AbstractSBase;
import org.sbml.jsbml.ListOf;
import org.sbml.jsbml.PropertyUndefinedError;
import org.sbml.jsbml.util.ResourceManager;
import org.sbml.jsbml.util.TreeNodeChangeEvent;

/**
 * @author Alex Thomas
 * @author Andreas Dr&auml;ger
 * @since 1.0
 * @version $Rev$
 */
public class Geometry extends AbstractSBase {

  /**
   * Generated serial version identifier.
   */
  private static final long serialVersionUID = 9115597691155572976L;

  /**
   * 
   */
  private ListOf<CoordinateComponent> listOfCoordinateComponents;

  /**
   * 
   */
  private ListOf<DomainType> listOfDomainTypes;

  /**
   * 
   */
  private ListOf<Domain> listOfDomains;

  /**
   * 
   */
  private ListOf<AdjacentDomains> listOfAdjacentDomains;

  /**
   * 
   */
  private ListOf<GeometryDefinition> listOfGeometryDefinitions;

  /**
   * 
   */
  private String coordinateSystem;

  private static final ResourceBundle bundle = ResourceManager.getBundle("org.sbml.jsbml.ext.spatial.Messages");

  /**
   * 
   */
  public Geometry() {
    super();
  }

  /**
   * @param sb
   */
  public Geometry(Geometry sb) {
    super(sb);
    if (sb.isSetCoordinateSystem()) {
      coordinateSystem = new String(sb.getCoordinateSystem());
    }
    if (sb.isSetListOfAdjacentDomains()) {
      listOfAdjacentDomains = sb.getListOfAdjacentDomains().clone();
    }
    if (sb.isSetListOfDomains()) {
      listOfDomains = sb.getListOfDomains().clone();
    }
    if (sb.isSetListOfDomainTypes()) {
      listOfDomainTypes = sb.getListOfDomainTypes().clone();
    }
    if (sb.isSetListOfGeometryDefinitions()) {
      listOfGeometryDefinitions = sb.getListOfGeometryDefinitions()
          .clone();
    }
  }

  /**
   * @param level
   * @param version
   */
  public Geometry(int level, int version) {
    super(level, version);
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.AbstractSBase#clone()
   */
  @Override
  public Geometry clone() {
    return new Geometry(this);
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.AbstractSBase#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object object) {
    boolean equal =  super.equals(object);
    if (equal) {

      Geometry gm = (Geometry) object;

      equal &= gm.isSetCoordinateSystem() == isSetCoordinateSystem();
      if (equal && isSetCoordinateSystem()) {
        equal &= gm.getCoordinateSystem().equals(getCoordinateSystem());
      }
      equal &= gm.isSetListOfAdjacentDomains() == isSetListOfAdjacentDomains();
      if (equal && isSetListOfAdjacentDomains()) {
        equal &= gm.getListOfAdjacentDomains().equals(getListOfAdjacentDomains());
      }
      equal &= gm.isSetListOfCoordinateComponents() == isSetListOfCoordinateComponents();
      if (equal && isSetListOfCoordinateComponents()) {
        equal &= gm.getListOfCoordinateComponents().equals(getListOfCoordinateComponents());
      }
      equal &= gm.isSetListOfDomains() == isSetListOfDomains();
      if (equal && isSetListOfDomains()) {
        equal &= gm.getListOfDomains().equals(getListOfDomains());
      }
      equal &= gm.isSetListOfDomainTypes() == isSetListOfDomainTypes();
      if (equal && isSetListOfDomainTypes()) {
        equal &= gm.getListOfDomainTypes().equals(getListOfDomainTypes());
      }
      equal &= gm.isSetListOfGeometryDefinitions() == isSetListOfGeometryDefinitions();
      if (equal && isSetListOfGeometryDefinitions()) {
        equal &= gm.getListOfGeometryDefinitions().equals(getListOfGeometryDefinitions());
      }
    }
    return equal;
  }


  /**
   * Returns the value of coordinateSystem
   *
   * @return the value of coordinateSystem
   */
  public String getCoordinateSystem() {
    if (isSetCoordinateSystem()) {
      return coordinateSystem;
    }
    // This is necessary if we cannot return null here.
    throw new PropertyUndefinedError(SpatialConstants.coordinateSystem, this);
  }


  /**
   * Returns whether coordinateSystem is set
   *
   * @return whether coordinateSystem is set
   */
  public boolean isSetCoordinateSystem() {
    return coordinateSystem != null;
  }


  /**
   * Sets the value of coordinateSystem
   */
  public void setCoordinateSystem(String coordinateSystem) {
    String oldCoordinateSystem = this.coordinateSystem;
    this.coordinateSystem = coordinateSystem;
    firePropertyChange(SpatialConstants.coordinateSystem, oldCoordinateSystem, this.coordinateSystem);
  }


  /**
   * Unsets the variable coordinateSystem
   *
   * @return {@code true}, if coordinateSystem was set before,
   *         otherwise {@code false}
   */
  public boolean unsetCoordinateSystem() {
    if (isSetCoordinateSystem()) {
      String oldCoordinateSystem = coordinateSystem;
      coordinateSystem = null;
      firePropertyChange(SpatialConstants.coordinateSystem, oldCoordinateSystem, coordinateSystem);
      return true;
    }
    return false;
  }


  /**
   * Returns {@code true}, if listOfGeometryDefinitions contains at least one element.
   *
   * @return {@code true}, if listOfGeometryDefinitions contains at least one element,
   *         otherwise {@code false}
   */
  public boolean isSetListOfGeometryDefinitions() {
    if ((listOfGeometryDefinitions == null) || listOfGeometryDefinitions.isEmpty()) {
      return false;
    }
    return true;
  }


  /**
   * Returns the listOfGeometryDefinitions. Creates it if it is not already existing.
   *
   * @return the listOfGeometryDefinitions
   */
  public ListOf<GeometryDefinition> getListOfGeometryDefinitions() {
    if (!isSetListOfGeometryDefinitions()) {
      listOfGeometryDefinitions = new ListOf<GeometryDefinition>(getLevel(),
          getVersion());
      listOfGeometryDefinitions.setNamespace(SpatialConstants.namespaceURI);
      listOfGeometryDefinitions.setSBaseListType(ListOf.Type.other);
      registerChild(listOfGeometryDefinitions);
    }
    return listOfGeometryDefinitions;
  }


  /**
   * Sets the given {@code ListOf<GeometryDefinition>}. If listOfGeometryDefinitions
   * was defined before and contains some elements, they are all unset.
   *
   * @param listOfGeometryDefinitions
   */
  public void setListOfGeometryDefinitions(ListOf<GeometryDefinition> listOfGeometryDefinitions) {
    unsetListOfGeometryDefinitions();
    this.listOfGeometryDefinitions = listOfGeometryDefinitions;
    registerChild(this.listOfGeometryDefinitions);
  }


  /**
   * Returns {@code true}, if listOfGeometryDefinitions contain at least one element,
   *         otherwise {@code false}
   *
   * @return {@code true}, if listOfGeometryDefinitions contain at least one element,
   *         otherwise {@code false}
   */
  public boolean unsetListOfGeometryDefinitions() {
    if (isSetListOfGeometryDefinitions()) {
      ListOf<GeometryDefinition> oldGeometryDefinitions = listOfGeometryDefinitions;
      listOfGeometryDefinitions = null;
      oldGeometryDefinitions.fireNodeRemovedEvent();
      return true;
    }
    return false;
  }


  /**
   * Adds a new {@link GeometryDefinition} to the listOfGeometryDefinitions.
   * <p>The listOfGeometryDefinitions is initialized if necessary.
   *
   * @param geometryDefinition the element to add to the list
   * @return true (as specified by {@link Collection.add})
   */
  public boolean addGeometryDefinition(GeometryDefinition geometryDefinition) {
    return getListOfGeometryDefinitions().add(geometryDefinition);
  }


  /**
   * Removes an element from the listOfGeometryDefinitions.
   *
   * @param geometryDefinition the element to be removed from the list
   * @return true if the list contained the specified element
   * @see List#remove(Object)
   */
  public boolean removeGeometryDefinition(GeometryDefinition geometryDefinition) {
    if (isSetListOfGeometryDefinitions()) {
      return getListOfGeometryDefinitions().remove(geometryDefinition);
    }
    return false;
  }


  /**
   * Removes an element from the listOfGeometryDefinitions at the given index.
   *
   * @param i the index where to remove the {@link GeometryDefinition}
   * @throws IndexOutOfBoundsException if the listOf is not set or
   * if the index is out of bound (index < 0 || index > list.size)
   */
  public void removeGeometryDefinition(int i) {
    if (!isSetListOfGeometryDefinitions()) {
      throw new IndexOutOfBoundsException(Integer.toString(i));
    }
    getListOfGeometryDefinitions().remove(i);
  }


  /**
   * TODO: if the ID is mandatory for GeometryDefinition objects,
   * one should also add this methods
   */
  //public void removeGeometryDefinition(String id) {
  //  getListOfGeometryDefinitions().removeFirst(new NameFilter(id));
  //}
  /**
   * Creates a new GeometryDefinition element and adds it to the ListOfGeometryDefinitions list
   */
  public SampledFieldGeometry createSampledFieldGeometryn() {
    return createSampledFieldGeometry(null);
  }

  public AnalyticGeometry createAnalyticGeometry() {
    return createAnalyticGeometry(null);
  }

  public CSGeometry createCSGeometry() {
    return createCSGeometry(null);
  }

  public ParametricGeometry createParametricGeometry() {
    return createParametricGeometry(null);
  }


  /**
   * Creates a new {@link GeometryDefinition} element and adds it to the ListOfGeometryDefinitions list
   *
   * @return a new {@link GeometryDefinition} element
   */
  public SampledFieldGeometry createSampledFieldGeometry(String id) {
    SampledFieldGeometry def = new SampledFieldGeometry(id, getLevel(), getVersion());
    addGeometryDefinition(def);
    return def;
  }

  public AnalyticGeometry createAnalyticGeometry(String id) {
    AnalyticGeometry def = new AnalyticGeometry(id, getLevel(), getVersion());
    addGeometryDefinition(def);
    return def;
  }

  public CSGeometry createCSGeometry(String id) {
    CSGeometry def = new CSGeometry(id, getLevel(), getVersion());
    addGeometryDefinition(def);
    return def;
  }

  public ParametricGeometry createParametricGeometry(String id) {
    ParametricGeometry def = new ParametricGeometry(id, getLevel(), getVersion());
    addGeometryDefinition(def);
    return def;
  }

  /**
   * TODO: optionally, create additional create methods with more
   * variables, for instance "bar" variable
   */
  // public GeometryDefinition createGeometryDefinition(String id, int bar) {
  //   GeometryDefinition geometryDefinition = createGeometryDefinition(id);
  //   geometryDefinition.setBar(bar);
  //   return geometryDefinition;
  // }

  /**
   * Returns {@code true}, if listOfAdjacentDomains contains at least one element.
   *
   * @return {@code true}, if listOfAdjacentDomains contains at least one element,
   *         otherwise {@code false}
   */
  public boolean isSetListOfAdjacentDomains() {
    if ((listOfAdjacentDomains == null) || listOfAdjacentDomains.isEmpty()) {
      return false;
    }
    return true;
  }


  /**
   * Returns the listOfAdjacentDomains. Creates it if it is not already existing.
   *
   * @return the listOfAdjacentDomains
   */
  public ListOf<AdjacentDomains> getListOfAdjacentDomains() {
    if (!isSetListOfAdjacentDomains()) {
      listOfAdjacentDomains = new ListOf<AdjacentDomains>(getLevel(),
          getVersion());
      listOfAdjacentDomains.setNamespace(SpatialConstants.namespaceURI);
      listOfAdjacentDomains.setSBaseListType(ListOf.Type.other);
      registerChild(listOfAdjacentDomains);
    }
    return listOfAdjacentDomains;
  }


  /**
   * Sets the given {@code ListOf<AdjacentDomain>}. If listOfAdjacentDomains
   * was defined before and contains some elements, they are all unset.
   *
   * @param listOfAdjacentDomains
   */
  public void setListOfAdjacentDomains(ListOf<AdjacentDomains> listOfAdjacentDomains) {
    unsetListOfAdjacentDomains();
    this.listOfAdjacentDomains = listOfAdjacentDomains;
    registerChild(this.listOfAdjacentDomains);
  }


  /**
   * Returns {@code true}, if listOfAdjacentDomains contain at least one element,
   *         otherwise {@code false}
   *
   * @return {@code true}, if listOfAdjacentDomains contain at least one element,
   *         otherwise {@code false}
   */
  public boolean unsetListOfAdjacentDomains() {
    if (isSetListOfAdjacentDomains()) {
      ListOf<AdjacentDomains> oldAdjacentDomains = listOfAdjacentDomains;
      listOfAdjacentDomains = null;
      oldAdjacentDomains.fireNodeRemovedEvent();
      return true;
    }
    return false;
  }


  /**
   * Adds a new {@link AdjacentDomain} to the listOfAdjacentDomains.
   * <p>The listOfAdjacentDomains is initialized if necessary.
   *
   * @param adjacentDomains the element to add to the list
   * @return true (as specified by {@link Collection.add})
   */
  public boolean addAdjacentDomain(AdjacentDomains adjacentDomains) {
    return getListOfAdjacentDomains().add(adjacentDomains);
  }


  /**
   * Removes an element from the listOfAdjacentDomains.
   *
   * @param adjacentDomains the element to be removed from the list
   * @return true if the list contained the specified element
   * @see List#remove(Object)
   */
  public boolean removeAdjacentDomain(AdjacentDomains adjacentDomains) {
    if (isSetListOfAdjacentDomains()) {
      return getListOfAdjacentDomains().remove(adjacentDomains);
    }
    return false;
  }


  /**
   * Removes an element from the listOfAdjacentDomains at the given index.
   *
   * @param i the index where to remove the {@link AdjacentDomain}
   * @throws IndexOutOfBoundsException if the listOf is not set or
   * if the index is out of bound (index < 0 || index > list.size)
   */
  public void removeAdjacentDomain(int i) {
    if (!isSetListOfAdjacentDomains()) {
      throw new IndexOutOfBoundsException(Integer.toString(i));
    }
    getListOfAdjacentDomains().remove(i);
  }


  /**
   * TODO: if the ID is mandatory for AdjacentDomain objects,
   * one should also add this methods
   */
  //public void removeAdjacentDomain(String id) {
  //  getListOfAdjacentDomains().removeFirst(new NameFilter(id));
  //}
  /**
   * Creates a new AdjacentDomain element and adds it to the ListOfAdjacentDomains list
   */
  public AdjacentDomains createAdjacentDomain() {
    return createAdjacentDomain(null);
  }


  /**
   * Creates a new {@link AdjacentDomain} element and adds it to the ListOfAdjacentDomains list
   *
   * @return a new {@link AdjacentDomain} element
   */
  public AdjacentDomains createAdjacentDomain(String id) {
    AdjacentDomains adjacentDomains = new AdjacentDomains(id, getLevel(), getVersion());
    addAdjacentDomain(adjacentDomains);
    return adjacentDomains;
  }

  /**
   * TODO: optionally, create additional create methods with more
   * variables, for instance "bar" variable
   */
  // public AdjacentDomain createAdjacentDomain(String id, int bar) {
  //   AdjacentDomain adjacentDomains = createAdjacentDomain(id);
  //   adjacentDomains.setBar(bar);
  //   return adjacentDomains;
  // }

  /**
   * Returns {@code true}, if listOfDomainTypes contains at least one element.
   *
   * @return {@code true}, if listOfDomainTypes contains at least one element,
   *         otherwise {@code false}
   */
  public boolean isSetListOfDomainTypes() {
    if ((listOfDomainTypes == null) || listOfDomainTypes.isEmpty()) {
      return false;
    }
    return true;
  }


  /**
   * Returns the listOfDomainTypes. Creates it if it is not already existing.
   *
   * @return the listOfDomainTypes
   */
  public ListOf<DomainType> getListOfDomainTypes() {
    if (!isSetListOfDomainTypes()) {
      listOfDomainTypes = new ListOf<DomainType>(getLevel(),
          getVersion());
      listOfDomainTypes.setNamespace(SpatialConstants.namespaceURI);
      listOfDomainTypes.setSBaseListType(ListOf.Type.other);
      registerChild(listOfDomainTypes);
    }
    return listOfDomainTypes;
  }


  /**
   * Sets the given {@code ListOf<DomainType>}. If listOfDomainTypes
   * was defined before and contains some elements, they are all unset.
   *
   * @param listOfDomainTypes
   */
  public void setListOfDomainTypes(ListOf<DomainType> listOfDomainTypes) {
    unsetListOfDomainTypes();
    this.listOfDomainTypes = listOfDomainTypes;
    registerChild(this.listOfDomainTypes);
  }


  /**
   * Returns {@code true}, if listOfDomainTypes contain at least one element,
   *         otherwise {@code false}
   *
   * @return {@code true}, if listOfDomainTypes contain at least one element,
   *         otherwise {@code false}
   */
  public boolean unsetListOfDomainTypes() {
    if (isSetListOfDomainTypes()) {
      ListOf<DomainType> oldDomainTypes = listOfDomainTypes;
      listOfDomainTypes = null;
      oldDomainTypes.fireNodeRemovedEvent();
      return true;
    }
    return false;
  }


  /**
   * Adds a new {@link DomainType} to the listOfDomainTypes.
   * <p>The listOfDomainTypes is initialized if necessary.
   *
   * @param domainType the element to add to the list
   * @return true (as specified by {@link Collection.add})
   */
  public boolean addDomainType(DomainType domainType) {
    return getListOfDomainTypes().add(domainType);
  }


  /**
   * Removes an element from the listOfDomainTypes.
   *
   * @param domainType the element to be removed from the list
   * @return true if the list contained the specified element
   * @see List#remove(Object)
   */
  public boolean removeDomainType(DomainType domainType) {
    if (isSetListOfDomainTypes()) {
      return getListOfDomainTypes().remove(domainType);
    }
    return false;
  }


  /**
   * Removes an element from the listOfDomainTypes at the given index.
   *
   * @param i the index where to remove the {@link DomainType}
   * @throws IndexOutOfBoundsException if the listOf is not set or
   * if the index is out of bound (index < 0 || index > list.size)
   */
  public void removeDomainType(int i) {
    if (!isSetListOfDomainTypes()) {
      throw new IndexOutOfBoundsException(Integer.toString(i));
    }
    getListOfDomainTypes().remove(i);
  }


  /**
   * TODO: if the ID is mandatory for DomainType objects,
   * one should also add this methods
   */
  //public void removeDomainType(String id) {
  //  getListOfDomainTypes().removeFirst(new NameFilter(id));
  //}
  /**
   * Creates a new DomainType element and adds it to the ListOfDomainTypes list
   */
  public DomainType createDomainType() {
    return createDomainType(null);
  }


  /**
   * Creates a new {@link DomainType} element and adds it to the ListOfDomainTypes list
   *
   * @return a new {@link DomainType} element
   */
  public DomainType createDomainType(String id) {
    DomainType domainType = new DomainType(id, getLevel(), getVersion());
    addDomainType(domainType);
    return domainType;
  }

  /**
   * TODO: optionally, create additional create methods with more
   * variables, for instance "bar" variable
   */
  // public DomainType createDomainType(String id, int bar) {
  //   DomainType domainType = createDomainType(id);
  //   domainType.setBar(bar);
  //   return domainType;
  // }


  /**
   * Returns {@code true}, if listOfDomains contains at least one element.
   *
   * @return {@code true}, if listOfDomains contains at least one element,
   *         otherwise {@code false}
   */
  public boolean isSetListOfDomains() {
    if ((listOfDomains == null) || listOfDomains.isEmpty()) {
      return false;
    }
    return true;
  }


  /**
   * Returns the listOfDomains. Creates it if it is not already existing.
   *
   * @return the listOfDomains
   */
  public ListOf<Domain> getListOfDomains() {
    if (!isSetListOfDomains()) {
      listOfDomains = new ListOf<Domain>(getLevel(),
          getVersion());
      listOfDomains.setNamespace(SpatialConstants.namespaceURI);
      listOfDomains.setSBaseListType(ListOf.Type.other);
      registerChild(listOfDomains);
    }
    return listOfDomains;
  }


  /**
   * Sets the given {@code ListOf<Domain>}. If listOfDomains
   * was defined before and contains some elements, they are all unset.
   *
   * @param listOfDomains
   */
  public void setListOfDomains(ListOf<Domain> listOfDomains) {
    unsetListOfDomains();
    this.listOfDomains = listOfDomains;
    registerChild(this.listOfDomains);
  }


  /**
   * Returns {@code true}, if listOfDomains contain at least one element,
   *         otherwise {@code false}
   *
   * @return {@code true}, if listOfDomains contain at least one element,
   *         otherwise {@code false}
   */
  public boolean unsetListOfDomains() {
    if (isSetListOfDomains()) {
      ListOf<Domain> oldDomains = listOfDomains;
      listOfDomains = null;
      oldDomains.fireNodeRemovedEvent();
      return true;
    }
    return false;
  }


  /**
   * Adds a new {@link Domain} to the listOfDomains.
   * <p>The listOfDomains is initialized if necessary.
   *
   * @param domain the element to add to the list
   * @return true (as specified by {@link Collection.add})
   */
  public boolean addDomain(Domain domain) {
    return getListOfDomains().add(domain);
  }


  /**
   * Removes an element from the listOfDomains.
   *
   * @param domain the element to be removed from the list
   * @return true if the list contained the specified element
   * @see List#remove(Object)
   */
  public boolean removeDomain(Domain domain) {
    if (isSetListOfDomains()) {
      return getListOfDomains().remove(domain);
    }
    return false;
  }


  /**
   * Removes an element from the listOfDomains at the given index.
   *
   * @param i the index where to remove the {@link Domain}
   * @throws IndexOutOfBoundsException if the listOf is not set or
   * if the index is out of bound (index < 0 || index > list.size)
   */
  public void removeDomain(int i) {
    if (!isSetListOfDomains()) {
      throw new IndexOutOfBoundsException(Integer.toString(i));
    }
    getListOfDomains().remove(i);
  }


  /**
   * TODO: if the ID is mandatory for Domain objects,
   * one should also add this methods
   */
  //public void removeDomain(String id) {
  //  getListOfDomains().removeFirst(new NameFilter(id));
  //}
  /**
   * Creates a new Domain element and adds it to the ListOfDomains list
   */
  public Domain createDomain() {
    return createDomain(null);
  }


  /**
   * Creates a new {@link Domain} element and adds it to the ListOfDomains list
   *
   * @return a new {@link Domain} element
   */
  public Domain createDomain(String id) {
    Domain domain = new Domain(id, getLevel(), getVersion());
    addDomain(domain);
    return domain;
  }

  /**
   * TODO: optionally, create additional create methods with more
   * variables, for instance "bar" variable
   */
  // public Domain createDomain(String id, int bar) {
  //   Domain domain = createDomain(id);
  //   domain.setBar(bar);
  //   return domain;
  // }



  /**
   * Returns {@code true}, if listOfCoordinateComponents contains at least one element.
   *
   * @return {@code true}, if listOfCoordinateComponents contains at least one element,
   *         otherwise {@code false}
   */
  public boolean isSetListOfCoordinateComponents() {
    if ((listOfCoordinateComponents == null) || listOfCoordinateComponents.isEmpty()) {
      return false;
    }
    return true;
  }


  /**
   * Returns the listOfCoordinateComponents. Creates it if it is not already existing.
   *
   * @return the listOfCoordinateComponents
   */
  public ListOf<CoordinateComponent> getListOfCoordinateComponents() {
    if (!isSetListOfCoordinateComponents()) {
      listOfCoordinateComponents = new ListOf<CoordinateComponent>(getLevel(),
          getVersion());
      listOfCoordinateComponents.setNamespace(SpatialConstants.namespaceURI);
      listOfCoordinateComponents.setSBaseListType(ListOf.Type.other);
      registerChild(listOfCoordinateComponents);
    }
    return listOfCoordinateComponents;
  }


  /**
   * Sets the given {@code ListOf<CoordinateComponent>}. If listOfCoordinateComponents
   * was defined before and contains some elements, they are all unset.
   *
   * @param listOfCoordinateComponents
   */
  public void setListOfCoordinateComponents(ListOf<CoordinateComponent> listOfCoordinateComponents) {
    unsetListOfCoordinateComponents();
    this.listOfCoordinateComponents = listOfCoordinateComponents;
    registerChild(this.listOfCoordinateComponents);
  }


  /**
   * Returns {@code true}, if listOfCoordinateComponents contain at least one element,
   *         otherwise {@code false}
   *
   * @return {@code true}, if listOfCoordinateComponents contain at least one element,
   *         otherwise {@code false}
   */
  public boolean unsetListOfCoordinateComponents() {
    if (isSetListOfCoordinateComponents()) {
      ListOf<CoordinateComponent> oldCoordinateComponents = listOfCoordinateComponents;
      listOfCoordinateComponents = null;
      oldCoordinateComponents.fireNodeRemovedEvent();
      return true;
    }
    return false;
  }


  /**
   * Adds a new {@link CoordinateComponent} to the listOfCoordinateComponents.
   * <p>The listOfCoordinateComponents is initialized if necessary.
   *
   * @param coordinateComponents the element to add to the list
   * @return true (as specified by {@link Collection.add})
   */
  public boolean addCoordinateComponent(CoordinateComponent coordinateComponents) {
    return getListOfCoordinateComponents().add(coordinateComponents);
  }


  /**
   * Removes an element from the listOfCoordinateComponents.
   *
   * @param coordinateComponents the element to be removed from the list
   * @return true if the list contained the specified element
   * @see List#remove(Object)
   */
  public boolean removeCoordinateComponent(CoordinateComponent coordinateComponents) {
    if (isSetListOfCoordinateComponents()) {
      return getListOfCoordinateComponents().remove(coordinateComponents);
    }
    return false;
  }


  /**
   * Removes an element from the listOfCoordinateComponents at the given index.
   *
   * @param i the index where to remove the {@link CoordinateComponent}
   * @throws IndexOutOfBoundsException if the listOf is not set or
   * if the index is out of bound (index < 0 || index > list.size)
   */
  public void removeCoordinateComponent(int i) {
    if (!isSetListOfCoordinateComponents()) {
      throw new IndexOutOfBoundsException(Integer.toString(i));
    }
    getListOfCoordinateComponents().remove(i);
  }


  /**
   * TODO: if the ID is mandatory for CoordinateComponent objects,
   * one should also add this methods
   */
  //public void removeCoordinateComponent(String id) {
  //  getListOfCoordinateComponents().removeFirst(new NameFilter(id));
  //}
  /**
   * Creates a new CoordinateComponent element and adds it to the ListOfCoordinateComponents list
   */
  public CoordinateComponent createCoordinateComponent() {
    return createCoordinateComponent(null);
  }


  /**
   * Creates a new {@link CoordinateComponent} element and adds it to the ListOfCoordinateComponents list
   *
   * @return a new {@link CoordinateComponent} element
   */
  public CoordinateComponent createCoordinateComponent(String id) {
    CoordinateComponent coordinateComponents = new CoordinateComponent(id, getLevel(), getVersion());
    addCoordinateComponent(coordinateComponents);
    return coordinateComponents;
  }

  /*
   * (non-Javadoc)
   * @see org.sbml.jsbml.AbstractSBase#toString()
   */
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("Geometry [coordinateSystem=");
    builder.append(coordinateSystem);
    builder.append("]");
    return builder.toString();
  }

  @Override
  public boolean readAttribute(String attributeName, String prefix, String value) {
    boolean isAttributeRead = super.readAttribute(attributeName, prefix, value);
    if (!isAttributeRead) {
      isAttributeRead = true;
      if (attributeName.equals(SpatialConstants.coordinateSystem)) {
        try {
          setCoordinateSystem(value);
        } catch (Exception e) {
          MessageFormat.format(bundle.getString("COULD_NOT_READ"), value,
            SpatialConstants.coordinateSystem);
        }
      }
      else {
        isAttributeRead = false;
      }
    }
    return isAttributeRead;
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.AbstractSBase#writeXMLAttributes()
   */
  @Override
  public Map<String, String> writeXMLAttributes() {
    Map<String, String> attributes = super.writeXMLAttributes();
    if (isSetCoordinateSystem()) {
      attributes.remove("coordinateSystem");
      attributes.put(SpatialConstants.shortLabel + ':' + SpatialConstants.coordinateSystem, coordinateSystem);
    }
    if (isSetSBOTerm()) {
      attributes.remove(TreeNodeChangeEvent.sboTerm);
      attributes.put(SpatialConstants.shortLabel + ":" + TreeNodeChangeEvent.sboTerm, getSBOTermID());
    }
    if (isSetMetaId()) {
      attributes.remove(TreeNodeChangeEvent.metaId);
      attributes.put(SpatialConstants.shortLabel + ":" + TreeNodeChangeEvent.metaId, getMetaId());
    }

    return attributes;
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.AbstractSBase#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 1103;
    int hashCode = super.hashCode();
    if (isSetCoordinateSystem()) {
      hashCode += prime * coordinateSystem.hashCode();
    }
    return hashCode;
  }



  @Override
  public boolean getAllowsChildren() {
    return true;
  }


  @Override
  public int getChildCount() {
    int count = super.getChildCount();
    if (isSetListOfAdjacentDomains()) {
      count++;
    }
    if (isSetListOfCoordinateComponents()) {
      count++;
    }
    if (isSetListOfDomains()) {
      count++;
    }
    if (isSetListOfDomainTypes()) {
      count++;
    }
    if (isSetListOfGeometryDefinitions()) {
      count++;
    }
    return count;
  }


  @Override
  public TreeNode getChildAt(int index) {
    if (index < 0) {
      throw new IndexOutOfBoundsException(index + " < 0");
    }
    int count = super.getChildCount(), pos = 0;
    if (index < count) {
      return super.getChildAt(index);
    } else {
      index -= count;
    }
    if (isSetListOfAdjacentDomains()) {
      if (pos == index) {
        return getListOfAdjacentDomains();
      }
      pos++;
    }
    if (isSetListOfCoordinateComponents()) {
      if (pos == index) {
        return getListOfCoordinateComponents();
      }
      pos++;
    }
    if (isSetListOfDomains()) {
      if (pos == index) {
        return getListOfDomains();
      }
      pos++;
    }
    if (isSetListOfDomainTypes()) {
      if (pos == index) {
        return getListOfDomainTypes();
      }
      pos++;
    }
    if (isSetListOfGeometryDefinitions()) {
      if (pos == index) {
        return getListOfGeometryDefinitions();
      }
      pos++;
    }
    throw new IndexOutOfBoundsException(MessageFormat.format(
      "Index {0,number,integer} >= {1,number,integer}", index,
      +Math.min(pos, 0)));
  }
}
