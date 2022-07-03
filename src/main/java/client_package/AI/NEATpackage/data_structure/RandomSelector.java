package client_package.AI.NEATpackage.data_structure;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class RandomSelector<T> {
    private ArrayList<T> objects = new ArrayList<>();
    private ArrayList<Double> scores = new ArrayList<>();

    private double total_score = 0;

    public void add(T element, double score){
        objects.add(element);
        scores.add(score);
        total_score +=score;
    }

    public  T random(){
        double v = ThreadLocalRandom.current().nextDouble(0,total_score);
        double c = 0;

        for (int i = 0; i < objects.size(); i++) {
            c += scores.get(i);
            if(c > v){
                return objects.get(i);
            }
        }
        return null;
    }

    public void reset(){
        objects.clear();
        scores.clear();
        total_score = 0;
    }

}
