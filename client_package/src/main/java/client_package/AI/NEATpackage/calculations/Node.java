package client_package.AI.NEATpackage.calculations;

import java.util.ArrayList;

public class Node implements Comparable<Node>{

    private double x;
    private double output;
    private ArrayList<Connection> connections = new ArrayList<>();

    public Node(double x){
        this.x = x;
    }

    public void calculate(){
        double s = 0;
        for(Connection connection:connections){
            if(connection.isEnabled()){
                s += connection.getWeight() * connection.getOrigin().getOutput();
            }
        }
        output = activationFunction(s);
    }

    private double activationFunction(double x){
        return 1d/(1+Math.exp(-x));
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getOutput() {
        return output;
    }

    public void setOutput(double output) {
        this.output = output;
    }

    public ArrayList<Connection> getConnections() {
        return connections;
    }

    public void setConnections(ArrayList<Connection> connections) {
        this.connections = connections;
    }

    @Override
    public int compareTo(Node o) {
        if(this.x > o.x) return -1;
        if(this.x < o.x) return 1;
        return 0;
    }
}
