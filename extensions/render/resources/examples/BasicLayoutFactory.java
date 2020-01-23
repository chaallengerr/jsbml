package examples;

import org.sbml.jsbml.ext.render.director.AssociationNode;
import org.sbml.jsbml.ext.render.director.Catalysis;
import org.sbml.jsbml.ext.render.director.Compartment;
import org.sbml.jsbml.ext.render.director.Consumption;
import org.sbml.jsbml.ext.render.director.DissociationNode;
import org.sbml.jsbml.ext.render.director.Inhibition;
import org.sbml.jsbml.ext.render.director.LayoutFactory;
import org.sbml.jsbml.ext.render.director.Macromolecule;
import org.sbml.jsbml.ext.render.director.Modulation;
import org.sbml.jsbml.ext.render.director.NecessaryStimulation;
import org.sbml.jsbml.ext.render.director.NucleicAcidFeature;
import org.sbml.jsbml.ext.render.director.OmittedProcessNode;
import org.sbml.jsbml.ext.render.director.PerturbingAgent;
import org.sbml.jsbml.ext.render.director.ProcessNode;
import org.sbml.jsbml.ext.render.director.Production;
import org.sbml.jsbml.ext.render.director.ReversibleConsumption;
import org.sbml.jsbml.ext.render.director.SimpleChemical;
import org.sbml.jsbml.ext.render.director.SourceSink;
import org.sbml.jsbml.ext.render.director.Stimulation;
import org.sbml.jsbml.ext.render.director.UncertainProcessNode;
import org.sbml.jsbml.ext.render.director.UnspecifiedNode;


public class BasicLayoutFactory implements LayoutFactory<String, String> {

  private double lineWidth = 1;
  private double arrowScale = 2;
  private double reactionNodeSize = 10;  // <- TODO: into constructor.
  
  /**
   * 
   * @param lineWidth the width of lines in the resulting drawing; in pt
   * @param arrowScale the scale of arrow-heads
   */
  public BasicLayoutFactory(double lineWidth, double arrowScale, double reactionNodeSize) {
    this.lineWidth = lineWidth;
    this.arrowScale = arrowScale;
    this.reactionNodeSize = reactionNodeSize;
  }


  @Override
  public AssociationNode<String> createAssociationNode() {
    return new LaTeXAssociationNode(lineWidth, reactionNodeSize);
  }


  @Override
  public Compartment<String> createCompartment() {
    return new LaTeXCompartment(lineWidth);
  }


  @Override
  public DissociationNode<String> createDissociationNode() {
    return new LaTeXDissociationNode(lineWidth, reactionNodeSize);
  }


  @Override
  public Macromolecule<String> createMacromolecule() {
    return new LaTeXMacromolecule(lineWidth);
  }


  @Override
  public NucleicAcidFeature<String> createNucleicAcidFeature() {
    return new LaTeXNucleicAcidFeature(lineWidth);
  }


  @Override
  public OmittedProcessNode<String> createOmittedProcessNode() {
    return new LaTeXOmittedProcessNode(lineWidth, reactionNodeSize);
  }


  @Override
  public PerturbingAgent<String> createPerturbingAgent() {
    return new LaTeXPerturbingAgent(lineWidth);
  }


  @Override
  public ProcessNode<String> createProcessNode() {
    return new LaTeXProcessNode(lineWidth, reactionNodeSize);
  }


  @Override
  public SimpleChemical<String> createSimpleChemical() {
    return new LaTeXSimpleChemical(lineWidth);
  }


  @Override
  public SourceSink<String> createSourceSink() {
    return new LaTeXSourceSink();
  }


  @Override
  public UncertainProcessNode<String> createUncertainProcessNode() {
    return new LaTeXUncertainProcessNode(lineWidth, reactionNodeSize);
  }


  @Override
  public UnspecifiedNode<String> createUnspecifiedNode() {
    return new LaTeXUnspecifiedNode(lineWidth);
  }


  @Override
  public Catalysis<String> createCatalysis() {
    return new LaTeXCatalysis(arrowScale);
  }


  @Override
  public Consumption<String> createConsumption() {
    return new LaTeXConsumption();
  }


  @Override
  public ReversibleConsumption<String> createReversibleConsumption() {
    return new LaTeXReversibleConsumption(arrowScale);
  }


  @Override
  public Inhibition<String> createInhibition() {
    return new LaTeXInhibition(arrowScale);
  }


  @Override
  public Modulation<String> createModulation() {
    return new LaTeXModulation(arrowScale);
  }


  @Override
  public NecessaryStimulation<String> createNecessaryStimulation() {
    return new LaTeXNecessaryStimulation(arrowScale);
  }


  @Override
  public Production<String> createProduction() {
    return new LaTeXProduction(arrowScale);
  }


  @Override
  public Stimulation<String> createStimulation() {
    return new LaTeXStimulation(arrowScale);
  }
}