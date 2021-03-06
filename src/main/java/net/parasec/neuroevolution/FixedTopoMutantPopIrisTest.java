package net.parasec.neuroevolution;

import net.parasec.neuroevolution.logging.Logger;

import net.parasec.neuroevolution.util.MathUtil;

import net.parasec.neuroevolution.network.Network;
import net.parasec.neuroevolution.network.TransferNode;
import net.parasec.neuroevolution.network.Sigmoid;
import net.parasec.neuroevolution.network.Edge;
import net.parasec.neuroevolution.network.Node;
import net.parasec.neuroevolution.network.NetworkBuilder;

import net.parasec.neuroevolution.genetic.Selector;
import net.parasec.neuroevolution.genetic.Replicator;
import net.parasec.neuroevolution.genetic.Mutator;
import net.parasec.neuroevolution.genetic.Procreator;
import net.parasec.neuroevolution.genetic.MutantPopulationProceator;
import net.parasec.neuroevolution.genetic.IndividualEvaluator;
import net.parasec.neuroevolution.genetic.PopulationEvaluator;
import net.parasec.neuroevolution.genetic.Population;
import net.parasec.neuroevolution.genetic.PopulationFitness;
import net.parasec.neuroevolution.genetic.Evolution;
import net.parasec.neuroevolution.genetic.RankSelector;
import net.parasec.neuroevolution.genetic.OptDir;
import net.parasec.neuroevolution.genetic.Minimisation;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;


public final class FixedTopoMutantPopIrisTest {

  private static final Logger LOG = Logger.getLogger(FixedTopoMutantPopIrisTest.class);


  // 4-6-3
  private static FixedTopo individual(final Random prng, final double minr,
      final double maxr, final OptDir optDir) {
    final Network net 
        = NetworkBuilder.buildFeedForward(new int[]{4,6,3}, minr, maxr, prng);
    return new FixedTopo(net, optDir);
  }
  
  public static void main(String[] args) {

    // XOR instances
    double[][][] instances = new double[][][] {
{{5.1,3.5,1.4,0.2},{0.9,0.1,0.1}},
{{4.9,3.0,1.4,0.2},{0.9,0.1,0.1}},
{{4.7,3.2,1.3,0.2},{0.9,0.1,0.1}},
{{4.6,3.1,1.5,0.2},{0.9,0.1,0.1}},
{{5.0,3.6,1.4,0.2},{0.9,0.1,0.1}},
{{5.4,3.9,1.7,0.4},{0.9,0.1,0.1}},
{{4.6,3.4,1.4,0.3},{0.9,0.1,0.1}},
{{5.0,3.4,1.5,0.2},{0.9,0.1,0.1}},
{{4.4,2.9,1.4,0.2},{0.9,0.1,0.1}},
{{4.9,3.1,1.5,0.1},{0.9,0.1,0.1}},
{{5.4,3.7,1.5,0.2},{0.9,0.1,0.1}},
{{4.8,3.4,1.6,0.2},{0.9,0.1,0.1}},
{{4.8,3.0,1.4,0.1},{0.9,0.1,0.1}},
{{4.3,3.0,1.1,0.1},{0.9,0.1,0.1}},
{{5.8,4.0,1.2,0.2},{0.9,0.1,0.1}},
{{5.7,4.4,1.5,0.4},{0.9,0.1,0.1}},
{{5.4,3.9,1.3,0.4},{0.9,0.1,0.1}},
{{5.1,3.5,1.4,0.3},{0.9,0.1,0.1}},
{{5.7,3.8,1.7,0.3},{0.9,0.1,0.1}},
{{5.1,3.8,1.5,0.3},{0.9,0.1,0.1}},
{{5.4,3.4,1.7,0.2},{0.9,0.1,0.1}},
{{5.1,3.7,1.5,0.4},{0.9,0.1,0.1}},
{{4.6,3.6,1.0,0.2},{0.9,0.1,0.1}},
{{5.1,3.3,1.7,0.5},{0.9,0.1,0.1}},
{{4.8,3.4,1.9,0.2},{0.9,0.1,0.1}},
{{5.0,3.0,1.6,0.2},{0.9,0.1,0.1}},
{{5.0,3.4,1.6,0.4},{0.9,0.1,0.1}},
{{5.2,3.5,1.5,0.2},{0.9,0.1,0.1}},
{{5.2,3.4,1.4,0.2},{0.9,0.1,0.1}},
{{4.7,3.2,1.6,0.2},{0.9,0.1,0.1}},
{{4.8,3.1,1.6,0.2},{0.9,0.1,0.1}},
{{5.4,3.4,1.5,0.4},{0.9,0.1,0.1}},
{{5.2,4.1,1.5,0.1},{0.9,0.1,0.1}},
{{5.5,4.2,1.4,0.2},{0.9,0.1,0.1}},
{{4.9,3.1,1.5,0.1},{0.9,0.1,0.1}},
{{5.0,3.2,1.2,0.2},{0.9,0.1,0.1}},
{{5.5,3.5,1.3,0.2},{0.9,0.1,0.1}},
{{4.9,3.1,1.5,0.1},{0.9,0.1,0.1}},
{{4.4,3.0,1.3,0.2},{0.9,0.1,0.1}},
{{5.1,3.4,1.5,0.2},{0.9,0.1,0.1}},
{{5.0,3.5,1.3,0.3},{0.9,0.1,0.1}},
{{4.5,2.3,1.3,0.3},{0.9,0.1,0.1}},
{{4.4,3.2,1.3,0.2},{0.9,0.1,0.1}},
{{5.0,3.5,1.6,0.6},{0.9,0.1,0.1}},
{{5.1,3.8,1.9,0.4},{0.9,0.1,0.1}},
{{4.8,3.0,1.4,0.3},{0.9,0.1,0.1}},
{{5.1,3.8,1.6,0.2},{0.9,0.1,0.1}},
{{4.6,3.2,1.4,0.2},{0.9,0.1,0.1}},
{{5.3,3.7,1.5,0.2},{0.9,0.1,0.1}},
{{5.0,3.3,1.4,0.2},{0.9,0.1,0.1}},
{{7.0,3.2,4.7,1.4},{0.1,0.9,0.1}},
{{6.4,3.2,4.5,1.5},{0.1,0.9,0.1}},
{{6.9,3.1,4.9,1.5},{0.1,0.9,0.1}},
{{5.5,2.3,4.0,1.3},{0.1,0.9,0.1}},
{{6.5,2.8,4.6,1.5},{0.1,0.9,0.1}},
{{5.7,2.8,4.5,1.3},{0.1,0.9,0.1}},
{{6.3,3.3,4.7,1.6},{0.1,0.9,0.1}},
{{4.9,2.4,3.3,1.0},{0.1,0.9,0.1}},
{{6.6,2.9,4.6,1.3},{0.1,0.9,0.1}},
{{5.2,2.7,3.9,1.4},{0.1,0.9,0.1}},
{{5.0,2.0,3.5,1.0},{0.1,0.9,0.1}},
{{5.9,3.0,4.2,1.5},{0.1,0.9,0.1}},
{{6.0,2.2,4.0,1.0},{0.1,0.9,0.1}},
{{6.1,2.9,4.7,1.4},{0.1,0.9,0.1}},
{{5.6,2.9,3.6,1.3},{0.1,0.9,0.1}},
{{6.7,3.1,4.4,1.4},{0.1,0.9,0.1}},
{{5.6,3.0,4.5,1.5},{0.1,0.9,0.1}},
{{5.8,2.7,4.1,1.0},{0.1,0.9,0.1}},
{{6.2,2.2,4.5,1.5},{0.1,0.9,0.1}},
{{5.6,2.5,3.9,1.1},{0.1,0.9,0.1}},
{{5.9,3.2,4.8,1.8},{0.1,0.9,0.1}},
{{6.1,2.8,4.0,1.3},{0.1,0.9,0.1}},
{{6.3,2.5,4.9,1.5},{0.1,0.9,0.1}},
{{6.1,2.8,4.7,1.2},{0.1,0.9,0.1}},
{{6.4,2.9,4.3,1.3},{0.1,0.9,0.1}},
{{6.6,3.0,4.4,1.4},{0.1,0.9,0.1}},
{{6.8,2.8,4.8,1.4},{0.1,0.9,0.1}},
{{6.7,3.0,5.0,1.7},{0.1,0.9,0.1}},
{{6.0,2.9,4.5,1.5},{0.1,0.9,0.1}},
{{5.7,2.6,3.5,1.0},{0.1,0.9,0.1}},
{{5.5,2.4,3.8,1.1},{0.1,0.9,0.1}},
{{5.5,2.4,3.7,1.0},{0.1,0.9,0.1}},
{{5.8,2.7,3.9,1.2},{0.1,0.9,0.1}},
{{6.0,2.7,5.1,1.6},{0.1,0.9,0.1}},
{{5.4,3.0,4.5,1.5},{0.1,0.9,0.1}},
{{6.0,3.4,4.5,1.6},{0.1,0.9,0.1}},
{{6.7,3.1,4.7,1.5},{0.1,0.9,0.1}},
{{6.3,2.3,4.4,1.3},{0.1,0.9,0.1}},
{{5.6,3.0,4.1,1.3},{0.1,0.9,0.1}},
{{5.5,2.5,4.0,1.3},{0.1,0.9,0.1}},
{{5.5,2.6,4.4,1.2},{0.1,0.9,0.1}},
{{6.1,3.0,4.6,1.4},{0.1,0.9,0.1}},
{{5.8,2.6,4.0,1.2},{0.1,0.9,0.1}},
{{5.0,2.3,3.3,1.0},{0.1,0.9,0.1}},
{{5.6,2.7,4.2,1.3},{0.1,0.9,0.1}},
{{5.7,3.0,4.2,1.2},{0.1,0.9,0.1}},
{{5.7,2.9,4.2,1.3},{0.1,0.9,0.1}},
{{6.2,2.9,4.3,1.3},{0.1,0.9,0.1}},
{{5.1,2.5,3.0,1.1},{0.1,0.9,0.1}},
{{5.7,2.8,4.1,1.3},{0.1,0.9,0.1}},
{{6.3,3.3,6.0,2.5},{0.1,0.1,0.9}},
{{5.8,2.7,5.1,1.9},{0.1,0.1,0.9}},
{{7.1,3.0,5.9,2.1},{0.1,0.1,0.9}},
{{6.3,2.9,5.6,1.8},{0.1,0.1,0.9}},
{{6.5,3.0,5.8,2.2},{0.1,0.1,0.9}},
{{7.6,3.0,6.6,2.1},{0.1,0.1,0.9}},
{{4.9,2.5,4.5,1.7},{0.1,0.1,0.9}},
{{7.3,2.9,6.3,1.8},{0.1,0.1,0.9}},
{{6.7,2.5,5.8,1.8},{0.1,0.1,0.9}},
{{7.2,3.6,6.1,2.5},{0.1,0.1,0.9}},
{{6.5,3.2,5.1,2.0},{0.1,0.1,0.9}},
{{6.4,2.7,5.3,1.9},{0.1,0.1,0.9}},
{{6.8,3.0,5.5,2.1},{0.1,0.1,0.9}},
{{5.7,2.5,5.0,2.0},{0.1,0.1,0.9}},
{{5.8,2.8,5.1,2.4},{0.1,0.1,0.9}},
{{6.4,3.2,5.3,2.3},{0.1,0.1,0.9}},
{{6.5,3.0,5.5,1.8},{0.1,0.1,0.9}},
{{7.7,3.8,6.7,2.2},{0.1,0.1,0.9}},
{{7.7,2.6,6.9,2.3},{0.1,0.1,0.9}},
{{6.0,2.2,5.0,1.5},{0.1,0.1,0.9}},
{{6.9,3.2,5.7,2.3},{0.1,0.1,0.9}},
{{5.6,2.8,4.9,2.0},{0.1,0.1,0.9}},
{{7.7,2.8,6.7,2.0},{0.1,0.1,0.9}},
{{6.3,2.7,4.9,1.8},{0.1,0.1,0.9}},
{{6.7,3.3,5.7,2.1},{0.1,0.1,0.9}},
{{7.2,3.2,6.0,1.8},{0.1,0.1,0.9}},
{{6.2,2.8,4.8,1.8},{0.1,0.1,0.9}},
{{6.1,3.0,4.9,1.8},{0.1,0.1,0.9}},
{{6.4,2.8,5.6,2.1},{0.1,0.1,0.9}},
{{7.2,3.0,5.8,1.6},{0.1,0.1,0.9}},
{{7.4,2.8,6.1,1.9},{0.1,0.1,0.9}},
{{7.9,3.8,6.4,2.0},{0.1,0.1,0.9}},
{{6.4,2.8,5.6,2.2},{0.1,0.1,0.9}},
{{6.3,2.8,5.1,1.5},{0.1,0.1,0.9}},
{{6.1,2.6,5.6,1.4},{0.1,0.1,0.9}},
{{7.7,3.0,6.1,2.3},{0.1,0.1,0.9}},
{{6.3,3.4,5.6,2.4},{0.1,0.1,0.9}},
{{6.4,3.1,5.5,1.8},{0.1,0.1,0.9}},
{{6.0,3.0,4.8,1.8},{0.1,0.1,0.9}},
{{6.9,3.1,5.4,2.1},{0.1,0.1,0.9}},
{{6.7,3.1,5.6,2.4},{0.1,0.1,0.9}},
{{6.9,3.1,5.1,2.3},{0.1,0.1,0.9}},
{{5.8,2.7,5.1,1.9},{0.1,0.1,0.9}},
{{6.8,3.2,5.9,2.3},{0.1,0.1,0.9}},
{{6.7,3.3,5.7,2.5},{0.1,0.1,0.9}},
{{6.7,3.0,5.2,2.3},{0.1,0.1,0.9}},
{{6.3,2.5,5.0,1.9},{0.1,0.1,0.9}},
{{6.5,3.0,5.2,2.0},{0.1,0.1,0.9}},
{{6.2,3.4,5.4,2.3},{0.1,0.1,0.9}},
{{5.9,3.0,5.1,1.8},{0.1,0.1,0.9}}
};

    final OptDir optDir = new Minimisation();

    final double minr = -0.1, maxr = 0.1;
    final int maxit = 1000000;
    final int popSize = 100;
    final boolean eliteism = true;

    final Random prng = new Random();
    final ArrayList<FixedTopo> lst = new ArrayList<FixedTopo>(popSize);
    for(int i = 0; i < popSize; i++)
      lst.add(individual(prng, minr, maxr, optDir));

    final Selector<FixedTopo> s = new RankSelector<FixedTopo>(prng); 
    final Replicator<FixedTopo> r = new FixedTopoReplicator(); 
    final Mutator<FixedTopo> m = new FixedTopoMutator(prng); 
    final Procreator<FixedTopo> p 
        = new MutantPopulationProceator<FixedTopo>(s, r, m, eliteism);
    final IndividualEvaluator<FixedTopo> ie 
        = new FixedTopoSupervised(instances);
    final PopulationEvaluator<FixedTopo> e = new FixedTopoPopSupervised(ie);

    final Population<FixedTopo> pop = new Population<FixedTopo>(lst, e, p);

    final List<PopulationFitness> f = Evolution.evolve(pop, maxit);
       

  }

}

