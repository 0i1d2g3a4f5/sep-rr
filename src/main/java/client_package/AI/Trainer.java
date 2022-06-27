package client_package.AI;

import java.util.ArrayList;
import java.util.Random;

public class Trainer {
    private final int SEED;
    private final int SAMPLE_NUMBER;
    Random random;
    public Trainer(int seed, int sampleNumber){
        this.SEED = seed;
        this.SAMPLE_NUMBER = sampleNumber;
        random = new Random(SEED);
    }


    public ArrayList<Sample> generateSample(){
        ArrayList<Sample> samples = new ArrayList<Sample>();
        for (int i = 0; i < SAMPLE_NUMBER; i++) {
            //samples.add(new Sample());
        }
        return samples;
    }



}
