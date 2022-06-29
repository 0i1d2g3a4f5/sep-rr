package client_package.AI.NEATpackage.genome;

import client_package.AI.NEATpackage.data_structure.RandomHashSet;
import client_package.AI.NEATpackage.neat.Neat;

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
        //TODO remove NODE, Remove Connection
        if(neat.getPROBABILITY_MUTATE_LINK() > Math.random())
            mutateLink();
        if(neat.getPROBABILITY_MUTATE_NODE() > Math.random())
            mutateNode();
        if(neat.getPROBABILITY_MUTATE_WEIGHT_RANDOM() > Math.random())
            mutateWeightRandom();
        if(neat.getPROBABILITY_MUTATE_WEIGHT_SHIFT() > Math.random())
            mutateWeightShift();
        if(neat.getPROBABILITY_MUTATE_TOGGLE_LINK() > Math.random())
            mutateLinkToggle();
    }

    public void mutateLink(){
        for (int i= 0; i < 100; i++) {
            NodeGene a = nodes.randomElement();
            NodeGene b = nodes.randomElement();

            if(a.getX() == b.getX()){
                continue;
            }
            ConnectionGene con;
            if(a.getX()<b.getX()){
                con = new ConnectionGene(a,b);
            } else {
                con = new ConnectionGene(b,a);
            }
            con = neat.getConnection(con.getOriginGene(),con.getTargetGene());
            con.setWeight(ThreadLocalRandom.current().nextDouble(-1,1)*neat.getWEIGHT_RANDOM_STRENGTH());
            connections.addSorted(con);
            return;

        }

    }
    public void mutateNode(){
        ConnectionGene con = connections.randomElement();
        if(con == null) return;
        NodeGene originGene = con.getOriginGene();
        NodeGene targetGene = con.getTargetGene();

        NodeGene middle = neat.getNode();
        middle.setX((originGene.getX() + targetGene.getX()) / 2);
        middle.setY((originGene.getY() + targetGene.getY()) / 2);

        ConnectionGene con1 = neat.getConnection(originGene,middle);
        ConnectionGene con2 = neat.getConnection(middle,targetGene);

        con1.setWeight(1);
        con2.setWeight(con.getWeight());
        con2.setEnabled(con.isEnabled());

        connections.remove(con);
        connections.add(con1);
        connections.add(con2);
        nodes.add(middle);

    }
    public void mutateWeightShift(){
        ConnectionGene connectionGene = connections.randomElement();
        if(connectionGene != null){
            connectionGene.setWeight(connectionGene.getWeight()+ThreadLocalRandom.current().nextDouble(-1,1)*neat.getWEIGHT_SHIFT_STRENGTH());
        }
    }
    public void mutateWeightRandom(){
        ConnectionGene connectionGene = connections.randomElement();
        if(connectionGene != null){
            connectionGene.setWeight(ThreadLocalRandom.current().nextDouble(-1,1)*neat.getWEIGHT_RANDOM_STRENGTH());
        }
    }
    public void mutateLinkToggle(){
        ConnectionGene connectionGene = connections.randomElement();
        if(connectionGene != null){
            connectionGene.setEnabled(!connectionGene.isEnabled());
        }
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
