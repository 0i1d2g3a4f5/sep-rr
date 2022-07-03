package client_package.AI.NEATpackage.calculations;

import client_package.AI.NEATpackage.data_structure.RandomHashSet;
import client_package.AI.NEATpackage.genome.ConnectionGene;
import client_package.AI.NEATpackage.genome.Genome;
import client_package.AI.NEATpackage.genome.NodeGene;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class Calculator {
    private ArrayList<Node> inputNodes = new ArrayList<>();
    private ArrayList<Node> hiddenNodes = new ArrayList<>();
    private ArrayList<Node> outputNodes = new ArrayList<>();

    public Calculator(Genome g){
        RandomHashSet<NodeGene> nodes = g.getNodes();
        RandomHashSet<ConnectionGene> cons = g.getConnections();

        HashMap<Integer,Node> nodeHashMap = new HashMap<>();

        for(NodeGene n:nodes.getData()){
            Node node = new Node(n.getX());
            nodeHashMap.put(n.getInnovationNumber(),node);

            if(n.getX() <=0.1){
                inputNodes.add(node);
            } else if (n.getX() >=0.9) {
                outputNodes.add(node);
            } else {
                hiddenNodes.add(node);
            }

        }

        hiddenNodes.sort(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.compareTo(o2);
            }
        });
        for (ConnectionGene c:cons.getData()) {
            NodeGene origin = c.getOriginGene();
            NodeGene target = c.getTargetGene();

            Node nodeOrigin = nodeHashMap.get(origin.getInnovationNumber());
            Node nodeTarget = nodeHashMap.get(target.getInnovationNumber());

            Connection con = new Connection(nodeOrigin,nodeTarget);
            con.setWeight(c.getWeight());
            con.setEnabled(c.isEnabled());

            nodeTarget.getConnections().add(con);

        }

    }

    public double[] calculate(double... input){
        if(input.length != inputNodes.size()) throw new RuntimeException("Amount of inputs doesnt match amount of inputNodes");
        for (int i = 0; i < inputNodes.size(); i++) {
            inputNodes.get(i).setOutput(input[i]);
        }
        for(Node n:hiddenNodes){
            n.calculate();
        }
        double[] output = new double[outputNodes.size()];
        for(int i = 0;i< outputNodes.size();i++){
            outputNodes.get(i).calculate();
            output[i] = outputNodes.get(i).getOutput();
        }
        return output;
    }
}
