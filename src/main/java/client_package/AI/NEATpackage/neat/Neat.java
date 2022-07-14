package client_package.AI.NEATpackage.neat;

import client_package.AI.NEATpackage.genome.Genome;
import client_package.AI.NEATpackage.genome.ConnectionGene;
import client_package.AI.NEATpackage.genome.NodeGene;
import client_package.Client;
import server_package.Server;

import java.util.ArrayList;
import java.util.HashMap;

public class Neat {
    public static final int MAX_NODES = (int) Math.pow(2,20);

    private double C1 = 1, C2 = 1, C3 = 1;

    private double WEIGHT_SHIFT_STRENGTH = 0.3;
    private double WEIGHT_RANDOM_STRENGTH = 1.0;

    private double PROBABILITY_MUTATE_LINK = 0.23;
    private double PROBABILITY_MUTATE_NODE = 0.37;
    private double PROBABILITY_MUTATE_WEIGHT_RANDOM= 0.123;
    private double PROBABILITY_MUTATE_WEIGHT_SHIFT = 0.223;
    private double PROBABILITY_MUTATE_TOGGLE_LINK = 0.21;
    private HashMap<ConnectionGene,ConnectionGene> allConnections = new HashMap<>();
    private ArrayList<NodeGene> allNodes;

    private int inputSize;
    private int maxClients;
    private int outputSize;

    public double getPROBABILITY_MUTATE_LINK() {
        return PROBABILITY_MUTATE_LINK;
    }

    public double getPROBABILITY_MUTATE_NODE() {
        return PROBABILITY_MUTATE_NODE;
    }

    public double getPROBABILITY_MUTATE_WEIGHT_RANDOM() {
        return PROBABILITY_MUTATE_WEIGHT_RANDOM;
    }

    public double getPROBABILITY_MUTATE_WEIGHT_SHIFT() {
        return PROBABILITY_MUTATE_WEIGHT_SHIFT;
    }

    public double getPROBABILITY_MUTATE_TOGGLE_LINK() {
        return PROBABILITY_MUTATE_TOGGLE_LINK;
    }

    public double getC1() {
        return C1;
    }

    public double getC2() {
        return C2;
    }

    public double getC3() {
        return C3;
    }

    public double getWEIGHT_SHIFT_STRENGTH() {
        return WEIGHT_SHIFT_STRENGTH;
    }

    public double getWEIGHT_RANDOM_STRENGTH() {
        return WEIGHT_RANDOM_STRENGTH;
    }

    public int getInputSize() {
        return inputSize;
    }

    public int getOutputSize() {
        return outputSize;
    }

    public Neat(int inputSize, int outputSize, int clients){
        this.reset(inputSize,outputSize,clients);
    }

    public Genome emptyGenome(){
        Genome genome = new Genome(this);
        for (int i = 0; i < inputSize + outputSize; i++) {
            genome.getNodes().add(getNode(i+1));
        }
        return genome;

    }

    private void reset(int inputSize, int outputSize, int clients) {
        this.inputSize = inputSize;
        this.outputSize = outputSize;
        this.maxClients = clients;

        allConnections.clear();
        allNodes.clear();

        for (int i = 0; i < inputSize; i++) {
            NodeGene node = getNode();
            node.setX(0.1);
            node.setY((i+1)/(double)(inputSize+1));
        }
        for (int i = 0; i < outputSize; i++) {
            NodeGene node = getNode();
            node.setX(0.9);
            node.setY((i+1)/(double)(outputSize+1));
        }
    }


    public ConnectionGene getConnection(ConnectionGene connection){
        ConnectionGene connectionGene = new ConnectionGene(connection.getOriginGene(), connection.getTargetGene());
        connectionGene.setWeight(connection.getWeight());
        connectionGene.setEnabled(connection.isEnabled());
        return connectionGene;

    }
    public ConnectionGene getConnection(NodeGene node1, NodeGene node2){
        ConnectionGene connectionGene = new ConnectionGene(node1,node2);
        if(allConnections.containsKey(connectionGene)) {
            connectionGene.setInnovationNumber(allConnections.get(connectionGene).getInnovationNumber());
        }else {
            connectionGene.setInnovationNumber(allConnections.size() +1);
            allConnections.put(connectionGene,connectionGene);
        }

        return connectionGene;
    }
    public NodeGene getNode(){
       NodeGene node = new NodeGene(allNodes.size()+1);
       allNodes.add(node);
       return node;
    }

    public NodeGene getNode(int id){
        if(id<= allNodes.size()) return allNodes.get(id);
        return getNode();
    }

    public static void main(String[] args){
        Neat neat = new Neat(3,3,100);
        Genome genome = neat.emptyGenome();
        Client.clientLogger.info(genome.getNodes().size());

    }

}
