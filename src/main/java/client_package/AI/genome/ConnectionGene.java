package client_package.AI.genome;

import client_package.AI.neat.Neat;

import java.util.Objects;

public class ConnectionGene extends Gene{
    private NodeGene originGene;
    private NodeGene targetGene;

    private double weight;
    private boolean enabled = true;

    public ConnectionGene(NodeGene from, NodeGene to){
        this.originGene = from;
        this.targetGene = to;
    }

    public NodeGene getOriginGene() {
        return originGene;
    }

    public void setOriginGene(NodeGene originGene) {
        this.originGene = originGene;
    }

    public NodeGene getTargetGene() {
        return targetGene;
    }

    public void setTargetGene(NodeGene targetGene) {
        this.targetGene = targetGene;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ConnectionGene)) return false;
        ConnectionGene that = (ConnectionGene) o;
        return Objects.equals(getOriginGene(), that.getOriginGene()) && Objects.equals(getTargetGene(), that.getTargetGene());
    }

    @Override
    public int hashCode() {
        return originGene.getInnovationNumber() * Neat.MAX_NODES + targetGene.getInnovationNumber();
    }
}
