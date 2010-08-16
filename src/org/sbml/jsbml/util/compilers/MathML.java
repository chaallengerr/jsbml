/*
 * $Id: MathMLComnpiler.java 97 2009-12-10 09:08:54Z andreas-draeger $
 * $URL: https://jsbml.svn.sourceforge.net/svnroot/jsbml/trunk/src/org/sbml/jsbml/util/MathMLComnpiler.java $
 *
 * 
 *==================================================================================
 * Copyright (c) 2009 The jsbml team.
 *
 * This file is part of jsbml, the pure java SBML library. Please visit
 * http://sbml.org for more information about SBML, and http://jsbml.sourceforge.net/
 * to get the latest version of jsbml.
 *
 * jsbml is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * jsbml is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with jsbml.  If not, see <http://www.gnu.org/licenses/old-licenses/lgpl-2.1.html>.
 *
 *===================================================================================
 *
 */
package org.sbml.jsbml.util.compilers;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;

import org.sbml.jsbml.ASTNode;
import org.sbml.jsbml.Compartment;
import org.sbml.jsbml.FunctionDefinition;
import org.sbml.jsbml.NamedSBaseWithDerivedUnit;
import org.sbml.jsbml.SBMLException;
import org.sbml.jsbml.Unit;
import org.sbml.jsbml.util.StringTools;
import org.sbml.jsbml.xml.parsers.MathMLParser;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * With this compiler, an {@link ASTNode} can be transformed into a MathML
 * string.
 * 
 * @author Andreas Dr&auml;ger
 * @date 2010-05-18
 * 
 */
public class MathML implements ASTNodeCompiler {

	/**
	 * XML document for the output.
	 */
	private Document document;

	/**
	 * The last top {@link Element} that has been created in the previous step.
	 */
	private Element lastElementCreated;

	/**
	 * The SBML level to be used in this class.
	 */
	private int level;

	/**
	 * 
	 * @throws ParserConfigurationException
	 */
	public MathML() throws XMLStreamException {
		init();
	}

	/**
	 * 
	 * @param ast
	 * @throws XMLStreamException
	 * @throws SBMLException
	 */
	public MathML(ASTNode ast) throws XMLStreamException, SBMLException {
		this();
		ast.compile(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sbml.jsbml.ASTNodeCompiler#abs(org.sbml.jsbml.ASTNode)
	 */
	public ASTNodeValue abs(ASTNode value) throws DOMException, SBMLException {
		return createApplyNode("abs", value);
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#and(java.util.List)
	 */
	public ASTNodeValue and(List<ASTNode> values) throws DOMException,
			SBMLException {
		if (values.size() > 0) {
			return createApplyNode("and", values);
		}
		throw new IllegalArgumentException(
				"cannot create and node for empty element list");
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#arccos(org.sbml.jsbml.ASTNode)
	 */
	public ASTNodeValue arccos(ASTNode value) throws DOMException,
			SBMLException {
		return createApplyNode("arccos", value);
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#arccosh(org.sbml.jsbml.ASTNode)
	 */
	public ASTNodeValue arccosh(ASTNode value) throws DOMException,
			SBMLException {
		return createApplyNode("arccosh", value);
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#arccot(org.sbml.jsbml.ASTNode)
	 */
	public ASTNodeValue arccot(ASTNode value) throws DOMException,
			SBMLException {
		return createApplyNode("arccot", value);
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#arccoth(org.sbml.jsbml.ASTNode)
	 */
	public ASTNodeValue arccoth(ASTNode value) throws DOMException,
			SBMLException {
		return createApplyNode("arccoth", value);
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#arccsc(org.sbml.jsbml.ASTNode)
	 */
	public ASTNodeValue arccsc(ASTNode value) throws DOMException,
			SBMLException {
		return createApplyNode("arccsc", value);
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#arccsch(org.sbml.jsbml.ASTNode)
	 */
	public ASTNodeValue arccsch(ASTNode value) throws DOMException,
			SBMLException {
		return createApplyNode("arccsch", value);
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#arcsec(org.sbml.jsbml.ASTNode)
	 */
	public ASTNodeValue arcsec(ASTNode value) throws DOMException,
			SBMLException {
		return createApplyNode("arcsec", value);
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#arcsech(org.sbml.jsbml.ASTNode)
	 */
	public ASTNodeValue arcsech(ASTNode value) throws DOMException,
			SBMLException {
		return createApplyNode("arcsech", value);
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#arcsin(org.sbml.jsbml.ASTNode)
	 */
	public ASTNodeValue arcsin(ASTNode value) throws DOMException,
			SBMLException {
		return createApplyNode("arcsin", value);
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#arcsinh(org.sbml.jsbml.ASTNode)
	 */
	public ASTNodeValue arcsinh(ASTNode value) throws DOMException,
			SBMLException {
		return createApplyNode("arcsinh", value);
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#arctan(org.sbml.jsbml.ASTNode)
	 */
	public ASTNodeValue arctan(ASTNode value) throws DOMException,
			SBMLException {
		return createApplyNode("arctan", value);
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#arctanh(org.sbml.jsbml.ASTNode)
	 */
	public ASTNodeValue arctanh(ASTNode value) throws DOMException,
			SBMLException {
		return createApplyNode("arctanh", value);
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#ceiling(org.sbml.jsbml.ASTNode)
	 */
	public ASTNodeValue ceiling(ASTNode value) throws DOMException,
			SBMLException {
		return createApplyNode("ceiling", value);
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#compile(org.sbml.jsbml.Compartment)
	 */
	public ASTNodeValue compile(Compartment c) {
		return compile((NamedSBaseWithDerivedUnit) c);
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#compile(double, int, java.lang.String)
	 */
	public ASTNodeValue compile(double mantissa, int exponent, String units) {
		return new ASTNodeValue(createCnElement(mantissa, exponent, units),
				this);
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#compile(double, java.lang.String)
	 */
	public ASTNodeValue compile(double real, String units) {
		return new ASTNodeValue(createCnElement(Double.valueOf(real), units),
				this);
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#compile(int, java.lang.String)
	 */
	public ASTNodeValue compile(int integer, String units) {
		return new ASTNodeValue(
				createCnElement(Integer.valueOf(integer), units), this);
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#compile(org.sbml.jsbml.NamedSBaseWithDerivedUnit)
	 */
	public ASTNodeValue compile(NamedSBaseWithDerivedUnit variable) {
		setLevel(variable.getLevel());
		return compile(variable.getId());
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#compile(java.lang.String)
	 */
	public ASTNodeValue compile(String name) {
		return createCiElement(name);
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#cos(org.sbml.jsbml.ASTNode)
	 */
	public ASTNodeValue cos(ASTNode value) throws DOMException, SBMLException {
		return createApplyNode("cos", value);
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#cosh(org.sbml.jsbml.ASTNode)
	 */
	public ASTNodeValue cosh(ASTNode value) throws DOMException, SBMLException {
		return createApplyNode("cosh", value);
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#cot(org.sbml.jsbml.ASTNode)
	 */
	public ASTNodeValue cot(ASTNode value) throws DOMException, SBMLException {
		return createApplyNode("cot", value);
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#coth(org.sbml.jsbml.ASTNode)
	 */
	public ASTNodeValue coth(ASTNode value) throws DOMException, SBMLException {
		return createApplyNode("coth", value);
	}

	/**
	 * 
	 * @param tag
	 * @param childNodes
	 * @return
	 * @throws DOMException
	 * @throws SBMLException
	 */
	private ASTNodeValue createApplyNode(Node tag, ASTNodeValue... childNodes)
			throws DOMException, SBMLException {
		lastElementCreated = document.createElement("apply");
		lastElementCreated.appendChild(tag);
		for (ASTNodeValue n : childNodes) {
			lastElementCreated.appendChild(n.toNode());
		}
		if (childNodes.length > 0) {
			setLevel(childNodes[0].getLevel());
		}
		return new ASTNodeValue(lastElementCreated, this);
	}

	/**
	 * Creates an apply {@link Node} with the given tag {@link Node} as its
	 * child and {@link ASTNodeValue}s on the same level as the tag {@link Node}
	 * .
	 * 
	 * @param tag
	 * @param childNodes
	 * @return
	 * @throws SBMLException
	 * @throws DOMException
	 */
	private ASTNodeValue createApplyNode(Node tag, List<ASTNode> childNodes)
			throws DOMException, SBMLException {
		lastElementCreated = document.createElement("apply");
		lastElementCreated.appendChild(tag);
		for (ASTNode n : childNodes) {
			lastElementCreated.appendChild(n.compile(this).toNode());
		}
		if (childNodes.size() > 0) {
			setLevel(childNodes.get(0).getParentSBMLObject().getLevel());
		}
		return new ASTNodeValue(lastElementCreated, this);
	}

	/**
	 * 
	 * @param tagName
	 * @param childNodes
	 * @return
	 * @throws DOMException
	 * @throws SBMLException
	 */
	private ASTNodeValue createApplyNode(String tagName, ASTNode... childNodes)
			throws DOMException, SBMLException {
		LinkedList<ASTNode> l = new LinkedList<ASTNode>();
		for (ASTNode ast : childNodes) {
			l.add(ast);
		}
		return createApplyNode(tagName, l);
	}

	/**
	 * 
	 * @param tagName
	 * @param childNodes
	 * @return
	 * @throws DOMException
	 * @throws SBMLException
	 */
	private ASTNodeValue createApplyNode(String tagName,
			ASTNodeValue... childNodes) throws DOMException, SBMLException {
		lastElementCreated = document.createElement("apply");
		lastElementCreated.appendChild(document.createElement(tagName));
		for (ASTNodeValue value : childNodes) {
			lastElementCreated.appendChild(value.toNode());
		}
		if (childNodes.length > 0) {
			setLevel(childNodes[0].getLevel());
		}
		return new ASTNodeValue(lastElementCreated, this);
	}

	/**
	 * Creates a {@link Node} with the tag "apply" and adds the given
	 * {@link Node} as its child. Then it creates a new {@link ASTNodeValue}
	 * that wraps the apply {@link Node}.
	 * 
	 * @param tagName
	 *            The name of the node to be created, e.g., "abs" or "and".
	 * @param childNodes
	 *            at least one child should be passed to this method.
	 * @return
	 * @throws SBMLException
	 * @throws DOMException
	 */
	private ASTNodeValue createApplyNode(String tagName,
			List<ASTNode> childNodes) throws DOMException, SBMLException {
		lastElementCreated = document.createElement("apply");
		lastElementCreated.appendChild(document.createElement(tagName));
		for (ASTNode value : childNodes) {
			lastElementCreated.appendChild(value.compile(this).toNode());
		}
		if (childNodes.size() > 0) {
			setLevel(childNodes.get(0).getParentSBMLObject().getLevel());
		}
		return new ASTNodeValue(lastElementCreated, this);
	}

	/**
	 * 
	 * @param name
	 * @return
	 */
	private ASTNodeValue createCiElement(String name) {
		lastElementCreated = document.createElement("ci");
		lastElementCreated.setTextContent(writeName(name.trim()));
		return new ASTNodeValue(lastElementCreated, this);
	}

	/**
	 * 
	 * @param mantissa
	 * @param exponent
	 * @param units
	 *            Can be null.
	 * @return
	 */
	private Element createCnElement(double mantissa, int exponent, String units) {
		lastElementCreated = document.createElement("cn");
		lastElementCreated.setAttribute("type", "e-notation");
		if ((units != null) && (level > 2)) {
			lastElementCreated.setAttribute(MathMLParser
					.getSBMLUnitsAttribute(), units);
		}
		lastElementCreated.appendChild(document.createTextNode(writeName(Double
				.valueOf(mantissa))));
		lastElementCreated.appendChild(document.createElement("sep"));
		lastElementCreated.appendChild(document
				.createTextNode(writeName(Integer.valueOf(exponent))));
		return lastElementCreated;
	}

	/**
	 * 
	 * @param value
	 * @param units
	 *            Can be null.
	 * @return
	 */
	private Element createCnElement(Number value, String units) {
		lastElementCreated = document.createElement("cn");
		if ((units != null) && (level > 2)) {
			lastElementCreated.setAttribute(MathMLParser
					.getSBMLUnitsAttribute(), units);
		}
		String type = value instanceof Integer ? "integer" : "real";
		lastElementCreated.setAttribute("type", type);
		lastElementCreated.setTextContent(writeName(value));
		return lastElementCreated;
	}

	/**
	 * Creates an {@link ASTNodeValue} with the single node as defined by the
	 * tagName.
	 * 
	 * @param tagName
	 * @return
	 */
	private ASTNodeValue createConstant(String tagName) {
		lastElementCreated = document.createElement(tagName);
		return new ASTNodeValue(lastElementCreated, this);
	}

	/**
	 * 
	 * @param name
	 * @param definitionURI
	 * @return
	 */
	private Element createCSymbol(String name, String definitionURI) {
		lastElementCreated = document.createElement("csymbol");
		lastElementCreated.setAttribute("encoding", "text");
		lastElementCreated.setAttribute("definitionURL", definitionURI);
		lastElementCreated.setTextContent(writeName(name));
		return lastElementCreated;
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#csc(org.sbml.jsbml.ASTNode)
	 */
	public ASTNodeValue csc(ASTNode value) throws DOMException, SBMLException {
		return createApplyNode("csc", value);
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#csch(org.sbml.jsbml.ASTNode)
	 */
	public ASTNodeValue csch(ASTNode value) throws DOMException, SBMLException {
		return createApplyNode("csch", value);
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#delay(java.lang.String, org.sbml.jsbml.ASTNode, double, java.lang.String)
	 */
	public ASTNodeValue delay(String delayName, ASTNode x, ASTNode delay,
			String timeUnit) throws DOMException, SBMLException {
		setLevel(x.getParentSBMLObject().getLevel());
		return createApplyNode(createCSymbol(delayName, MathMLParser
				.getDefinitionURIdelay()), compile(x.toString()), compile(delay.toString()));
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#eq(org.sbml.jsbml.ASTNode, org.sbml.jsbml.ASTNode)
	 */
	public ASTNodeValue eq(ASTNode left, ASTNode right) throws DOMException,
			SBMLException {
		return createApplyNode("eq", left, right);
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#exp(org.sbml.jsbml.ASTNode)
	 */
	public ASTNodeValue exp(ASTNode value) throws DOMException, SBMLException {
		return createApplyNode("exp", value);
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#factorial(org.sbml.jsbml.ASTNode)
	 */
	public ASTNodeValue factorial(ASTNode value) throws DOMException,
			SBMLException {
		return createApplyNode("factorial", value);
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#floor(org.sbml.jsbml.ASTNode)
	 */
	public ASTNodeValue floor(ASTNode value) throws DOMException, SBMLException {
		return createApplyNode("floor", value);
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#frac(org.sbml.jsbml.ASTNode, org.sbml.jsbml.ASTNode)
	 */
	public ASTNodeValue frac(ASTNode numerator, ASTNode denominator)
			throws DOMException, SBMLException {
		return createApplyNode("divide", numerator, denominator);
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#frac(int, int)
	 */
	public ASTNodeValue frac(int numerator, int denominator)
			throws DOMException, SBMLException {
		return createApplyNode("divide", compile(numerator, null), compile(
				denominator, null));
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#function(org.sbml.jsbml.FunctionDefinition, java.util.List)
	 */
	public ASTNodeValue function(FunctionDefinition functionDefinition,
			List<ASTNode> args) throws DOMException, SBMLException {
		return createApplyNode(compile(functionDefinition).toNode(), args);
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#geq(org.sbml.jsbml.ASTNode, org.sbml.jsbml.ASTNode)
	 */
	public ASTNodeValue geq(ASTNode left, ASTNode right) throws DOMException,
			SBMLException {
		return createApplyNode("geq", left, right);
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#getConstantAvogadro(java.lang.String)
	 */
	public ASTNodeValue getConstantAvogadro(String name) {
		return new ASTNodeValue(createCSymbol(name, MathMLParser
				.getDefinitionURIavogadro()), this);
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#getConstantE()
	 */
	public ASTNodeValue getConstantE() {
		return createConstant("exponentiale");
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#getConstantFalse()
	 */
	public ASTNodeValue getConstantFalse() {
		return createConstant(Boolean.FALSE.toString());
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#getConstantPi()
	 */
	public ASTNodeValue getConstantPi() {
		return createConstant("pi");
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#getConstantTrue()
	 */
	public ASTNodeValue getConstantTrue() {
		return createConstant(Boolean.TRUE.toString());
	}

	/**
	 * @return the document
	 * @throws SBMLException
	 */
	public Document getDocument() throws SBMLException {
		return MathMLParser.createMathMLDocumentFor(lastElementCreated, level);
	}

	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#getNegativeInfinity()
	 */
	public ASTNodeValue getNegativeInfinity() throws DOMException,
			SBMLException {
		return uMinus(getPositiveInfinity());
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#getPositiveInfinity()
	 */
	public ASTNodeValue getPositiveInfinity() {
		return createConstant("infinity");
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#gt(org.sbml.jsbml.ASTNode, org.sbml.jsbml.ASTNode)
	 */
	public ASTNodeValue gt(ASTNode left, ASTNode right) throws DOMException,
			SBMLException {
		return createApplyNode("gt", left, right);
	}

	/**
	 * Re-initializes the {@link Document} object belonging to this class. Only
	 * in this way a new {@link ASTNode} tree can be compiled. To this end, also
	 * the level attribute is reset (because maybe next time we want to compile
	 * some {@link ASTNode} with from a different source.
	 * 
	 * @throws XMLStreamException
	 */
	private void init() throws XMLStreamException {
		level = -1;
		try {
			// create document
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			factory.setNamespaceAware(true);
			DocumentBuilder builder;
			builder = factory.newDocumentBuilder();
			document = builder.newDocument();
		} catch (ParserConfigurationException e) {
			throw new XMLStreamException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#lambda(java.util.List)
	 */
	public ASTNodeValue lambda(List<ASTNode> values) throws DOMException,
			SBMLException {
		lastElementCreated = document.createElement("lambda");
		if (values.size() > 0) {
			setLevel(values.get(0).getParentSBMLObject().getLevel());
			if (values.size() > 1) {
				for (int i = 0; i < values.size() - 1; i++) {
					Element bvar = document.createElement("bvar");
					lastElementCreated.appendChild(bvar);
					bvar.appendChild(values.get(i).compile(this).toNode());
				}
			}
			lastElementCreated.appendChild(values.get(values.size() - 1)
					.compile(this).toNode());
		}
		return new ASTNodeValue(lastElementCreated, this);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#lambdaFunction(java.util.List)
	 */
	public ASTNodeValue lambdaFunction(List<ASTNode> values) throws DOMException,
			SBMLException {
		return lambda(values);
	}
	

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#leq(org.sbml.jsbml.ASTNode, org.sbml.jsbml.ASTNode)
	 */
	public ASTNodeValue leq(ASTNode left, ASTNode right) throws DOMException,
			SBMLException {
		return createApplyNode("leq", left, right);
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#ln(org.sbml.jsbml.ASTNode)
	 */
	public ASTNodeValue ln(ASTNode value) throws DOMException, SBMLException {
		return createApplyNode("ln", value);
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#log(org.sbml.jsbml.ASTNode)
	 */
	public ASTNodeValue log(ASTNode value) throws DOMException, SBMLException {
		return log(null, value);
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#log(org.sbml.jsbml.ASTNode, org.sbml.jsbml.ASTNode)
	 */
	public ASTNodeValue log(ASTNode base, ASTNode value) throws DOMException,
			SBMLException {
		ASTNodeValue v = createApplyNode("log", value);
		if (base != null) {
			Element logbase = document.createElement("logbase");
			logbase.appendChild(base.compile(this).toNode());
			v.toNode().insertBefore(logbase,
					v.toNode().getFirstChild().getNextSibling());
		}
		return v;
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#lt(org.sbml.jsbml.ASTNode, org.sbml.jsbml.ASTNode)
	 */
	public ASTNodeValue lt(ASTNode left, ASTNode right) throws DOMException,
			SBMLException {
		return createApplyNode("lt", left, right);
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#minus(java.util.List)
	 */
	public ASTNodeValue minus(List<ASTNode> values) throws DOMException,
			SBMLException {
		return createApplyNode("minus", values);
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#neq(org.sbml.jsbml.ASTNode, org.sbml.jsbml.ASTNode)
	 */
	public ASTNodeValue neq(ASTNode left, ASTNode right) throws DOMException,
			SBMLException {
		return createApplyNode("neq", left, right);
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#not(org.sbml.jsbml.ASTNode)
	 */
	public ASTNodeValue not(ASTNode value) throws DOMException, SBMLException {
		return createApplyNode("not", value);
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#or(java.util.List)
	 */
	public ASTNodeValue or(List<ASTNode> values) throws DOMException,
			SBMLException {
		return createApplyNode("or", values);
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#piecewise(java.util.List)
	 */
	public ASTNodeValue piecewise(List<ASTNode> values) throws DOMException,
			SBMLException {
		if (values.size() > 0) {
			setLevel(values.get(0).getParentSBMLObject().getLevel());
			lastElementCreated = document.createElement("apply");
			Element tag = document.createElement("piecewise");
			lastElementCreated.appendChild(tag);
			Element piece = null;
			for (int i = 0; i < values.size(); i++) {
				if ((i % 2) == 0) {
					piece = document.createElement("piece");
					tag.appendChild(piece);
				} else if (i == values.size() - 1) {
					piece = document.createElement("otherwise");
					tag.appendChild(piece);
				}
				if (piece != null) {
					piece.appendChild(values.get(i).compile(this).toNode());
				}
			}
			return new ASTNodeValue(lastElementCreated, this);
		}
		throw new IllegalArgumentException(
				"cannot create piecewise function with empty argument list");
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#plus(java.util.List)
	 */
	public ASTNodeValue plus(List<ASTNode> values) throws DOMException,
			SBMLException {
		return createApplyNode("plus", values);
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#pow(org.sbml.jsbml.ASTNode, org.sbml.jsbml.ASTNode)
	 */
	public ASTNodeValue pow(ASTNode base, ASTNode exponent)
			throws DOMException, SBMLException {
		return createApplyNode("pow", base, exponent);
	}

	/**
	 * Allows to re-use this object to compile another {@link ASTNode}. Without
	 * reseting, this compiler can only be used one time. Otherwise it cannot be
	 * guaranteed that the results will be correct.
	 * 
	 * @throws XMLStreamException
	 */
	public void reset() throws XMLStreamException {
		init();
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#root(org.sbml.jsbml.ASTNode, org.sbml.jsbml.ASTNode)
	 */
	public ASTNodeValue root(ASTNode rootExponent, ASTNode radiant)
			throws DOMException, SBMLException {
		if (rootExponent != null) {
			return root(rootExponent.compile(this), radiant);
		}
		return createApplyNode("root", radiant);
	}

	/**
	 * 
	 * @param rootExponent
	 * @param radiant
	 * @return
	 * @throws DOMException
	 * @throws SBMLException
	 */
	private ASTNodeValue root(ASTNodeValue rootExponent, ASTNode radiant)
			throws DOMException, SBMLException {
		Element logbase = document.createElement("degree");
		logbase.appendChild(rootExponent.toNode());
		ASTNodeValue v = createApplyNode("root", radiant);
		Node v_node = v.toNode();
		
		// TODO : Not working - not correcting it as the mathMl output is wrong anyway and will be re-implemented.
		v_node.insertBefore(logbase, v_node.getFirstChild().getNextSibling());
		
		return v;
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#root(double, org.sbml.jsbml.ASTNode)
	 */
	public ASTNodeValue root(double rootExponent, ASTNode radiant)
			throws DOMException, SBMLException {
		ASTNodeValue exponent;
		String dimensionless = (0 < level) && (level < 3) ? null
				: Unit.Kind.DIMENSIONLESS.toString().toLowerCase();
		if (rootExponent - ((int) rootExponent) == 0) {
			exponent = compile((int) rootExponent, dimensionless);
		} else {
			exponent = compile(rootExponent, dimensionless);
		}
		return root(exponent, radiant);
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#sec(org.sbml.jsbml.ASTNode)
	 */
	public ASTNodeValue sec(ASTNode value) throws DOMException, SBMLException {
		return createApplyNode("sec", value);
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#sech(org.sbml.jsbml.ASTNode)
	 */
	public ASTNodeValue sech(ASTNode value) throws DOMException, SBMLException {
		return createApplyNode("sech", value);
	}

	/**
	 * @param level
	 *            the level to set
	 */
	private void setLevel(int level) {
		if ((0 < this.level) && (this.level <= 3)
				&& ((level < 0) || (3 < level))) {
			throw new IllegalArgumentException(StringTools.concat(
					"cannot set level from the valid value ",
					Integer.toString(this.level), " to invalid value ",
					Integer.toString(level)).toString());
		}
		this.level = level;
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#sin(org.sbml.jsbml.ASTNode)
	 */
	public ASTNodeValue sin(ASTNode value) throws DOMException, SBMLException {
		return createApplyNode("sin", value);
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#sinh(org.sbml.jsbml.ASTNode)
	 */
	public ASTNodeValue sinh(ASTNode value) throws DOMException, SBMLException {
		return createApplyNode("sinh", value);
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#sqrt(org.sbml.jsbml.ASTNode)
	 */
	public ASTNodeValue sqrt(ASTNode value) throws DOMException, SBMLException {
		return root(2, value);
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#symbolTime(java.lang.String)
	 */
	public ASTNodeValue symbolTime(String time) {
		return new ASTNodeValue(createCSymbol(time, MathMLParser
				.getDefinitionURItime()), this);
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#tan(org.sbml.jsbml.ASTNode)
	 */
	public ASTNodeValue tan(ASTNode value) throws DOMException, SBMLException {
		return createApplyNode("tan", value);
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#tanh(org.sbml.jsbml.ASTNode)
	 */
	public ASTNodeValue tanh(ASTNode value) throws DOMException, SBMLException {
		return createApplyNode("tanh", value);
	}

	/**
	 * Convenient method to perform internal changes.
	 * 
	 * @param values
	 * @return
	 * @throws DOMException
	 * @throws SBMLException
	 */
	private ASTNodeValue times(ASTNodeValue... values) throws DOMException,
			SBMLException {
		return createApplyNode("times", values);
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#times(java.util.List)
	 */
	public ASTNodeValue times(List<ASTNode> values) throws DOMException,
			SBMLException {
		return createApplyNode("times", values);
	}

	/**
	 * 
	 * @param omitXMLDeclaration
	 * @param indenting
	 * @param indent
	 * @return
	 * @throws IOException
	 * @throws SBMLException
	 */
	public String toMathML(boolean omitXMLDeclaration, boolean indenting,
			int indent) throws IOException, SBMLException {
		return MathMLParser.toMathML(getDocument(), omitXMLDeclaration,
				indenting, indent);
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#uMinus(org.sbml.jsbml.ASTNode)
	 */
	public ASTNodeValue uMinus(ASTNode value) throws DOMException,
			SBMLException {
		ASTNodeValue v = value.compile(this);
		v.setLevel(value.getParentSBMLObject().getLevel());
		return uMinus(v);
	}

	/**
	 * 
	 * @param value
	 * @return
	 * @throws DOMException
	 * @throws SBMLException
	 */
	private ASTNodeValue uMinus(ASTNodeValue value) throws DOMException,
			SBMLException {
		setLevel(value.getLevel());
		return times(compile(-1, (0 < level) && (level < 3) ? null
				: Unit.Kind.DIMENSIONLESS.toString().toLowerCase()), value);
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#unknownValue()
	 */
	public ASTNodeValue unknownValue() throws SBMLException {
		throw new SBMLException(
				"cannot write unknown syntax tree nodes to a MathML document");
	}

	/**
	 * Converts the given {@link Object} into a {@link String}. Then it removes
	 * leading and tailing white spaces from the given {@link String} and then
	 * inserts exactly one white space at the beginning and the end of the given
	 * {@link String}.
	 * 
	 * @param name
	 * @return
	 */
	private String writeName(Object name) {
		return StringTools.concat(Character.toString(' '),
				name.toString().trim(), Character.toString(' ')).toString();
	}

	/*
	 * (non-Javadoc)
	 * @see org.sbml.jsbml.util.compilers.ASTNodeCompiler#xor(java.util.List)
	 */
	public ASTNodeValue xor(List<ASTNode> values) throws DOMException,
			SBMLException {
		return createApplyNode("xor", values);
	}
}
