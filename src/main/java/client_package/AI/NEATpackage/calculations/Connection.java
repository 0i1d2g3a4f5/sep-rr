package client_package.AI.NEATpackage.calculations;

import client_package.AI.NEATpackage.genome.ConnectionGene;
import client_package.AI.NEATpackage.genome.NodeGene;

import java.util.Objects;

public class Connection {
    private Node origin;
    private Node target;

    private double weight;
    private boolean enabled = true;

    public Connection(Node from, Node to){
        this.origin = from;
        this.target = to;
    }

    public Node getOrigin() {
        return origin;
    }

    public void setOriginGene(Node origin) {
        this.origin= origin;
    }

    public Node getTarget() {
        return target;
    }

    public void setTarget(Node target) {
        this.target = target;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }



}
