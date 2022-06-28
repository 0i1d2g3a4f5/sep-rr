package client_package.AI.genome;

import client_package.AI.data_structure.RandomHashSet;
import client_package.AI.neat.Neat;

import java.util.concurrent.ThreadLocalRandom;

public class Genome {
    private RandomHashSet<ConnectionGene> connections = new RandomHashSet<>();
    private RandomHashSet<NodeGene> nodes = new RandomHashSet<>();

    private Neat neat;

    public Genome(Neat neat){
        this.neat = neat;
    }

    public double distance(Genome genome2){

        Genome genome1 = this;

        int highestInnovationGene1 = genome1.getConnections().get(genome1.getConnections().size()-1).getInnovationNumber();
        int highestInnovationGene2 = genome2.getConnections().get(genome2.getConnections().size()-1).getInnovationNumber();

        if (highestInnovationGene1 < highestInnovationGene2){
            Genome temp = genome1;
            genome1 = genome2;
            genome2 = temp;
        }

        int index1 = 0;
        int index2 = 0;

        int disjoint = 0;
        int excess = 0;
        double weightDifference = 0;
        int similar =  0;


        while(index1<genome1.getConnections().size()&&index2<genome2.getConnections().size()){
            ConnectionGene gene1 = genome1.getConnections().get(index1);
            ConnectionGene gene2 = genome1.getConnections().get(index2);

            int innovNum1 = gene1.getInnovationNumber();
            int innovNum2 = gene2.getInnovationNumber();

            if(innovNum1 == innovNum2){
                //similar Gene
                weightDifference += Math.abs(gene1.getWeight()-gene2.getWeight());
                similar++;
                index1++;
                index2++;
            } else if(innovNum1>innovNum2){
                //disjoint Gene of b
                disjoint++;
                index2++;

            } else {
                //disjoint Gene of a
                disjoint++;
                index1++;
            }
        }
        weightDifference /= similar;
        excess = genome1.getConnections().size() - index1;

        double N = Math.max(genome1.getConnections().size(),genome2.getConnections().size());
        if(N<20) N = 1;

        return neat.getC1()*disjoint/N + neat.getC2()*excess/N + neat.getC3()*weightDifference / N;

    }

    public static  Genome crossOver(Genome genome1, Genome genome2){
        Neat neat = genome1.getNeat();
        int index1 = 0;
        int index2 = 0;

        Genome childGenome = neat.emptyGenome();

        while(index1<genome1.getConnections().size()&&index2<genome2.getConnections().size()){

            ConnectionGene gene1 = genome1.getConnections().get(index1);
            ConnectionGene gene2 = genome1.getConnections().get(index2);


            int innovNum1 = gene1.getInnovationNumber();
            int innovNum2 = gene2.getInnovationNumber();

            if(innovNum1 == innovNum2){
                //similar Gene
                if(ThreadLocalRandom.current().nextBoolean()){
                    childGenome.getConnections().add(neat.getConnection(gene1));
                } else{
                    childGenome.getConnections().add(neat.getConnection(gene2));
                }
                index1++;
                index2++;
            } else if(innovNum1>innovNum2){
                //disjoint Gene of b
                index2++;

            } else {
                //disjoint Gene of a
                childGenome.getConnections().add(neat.getConnection(gene1));
                index1++;
            }
        }

        while(index1 < genome1.getConnections().size()){
            ConnectionGene gene1 = genome1.getConnections().get(index1);
            childGenome.getConnections().add(neat.getConnection(gene1));
            index1++;
        }
        for(ConnectionGene c:childGenome.getConnections().getData()){
            childGenome.getNodes().add(c.getOriginGene());
            childGenome.getNodes().add(c.getTargetGene());
        }
        return childGenome;
    }

    public void mutate(){

    }

    public RandomHashSet<ConnectionGene> getConnections() {
        return connections;
    }

    public void setConnections(RandomHashSet<ConnectionGene> connections) {
        this.connections = connections;
    }

    public RandomHashSet<NodeGene> getNodes() {
        return nodes;
    }

    public void setNodes(RandomHashSet<NodeGene> nodes) {
        this.nodes = nodes;
    }

    public Neat getNeat() {
        return neat;
    }

    public void setNeat(Neat neat) {
        this.neat = neat;
    }
}
