package client_package.AI.NEATpackage.genome;

public class Gene {


    protected int innovationNumber;

    public Gene(int innovationNumber){
        this.innovationNumber = innovationNumber;
    }
    public Gene(){

    }

    public int getInnovationNumber() {
        return innovationNumber;
    }

    public void setInnovationNumber(int innovationNumber) {
        this.innovationNumber = innovationNumber;
    }

}
