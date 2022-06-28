package client_package.AI.NEATpackage.data_structure;

import client_package.AI.NEATpackage.genome.Gene;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.ThreadLocalRandom;

public class RandomHashSet<T> {
    HashSet<T> set;
    ArrayList<T> data;

    public RandomHashSet(){
        set = new HashSet<>();
        data = new ArrayList<>();
    }
    public boolean contains(T object){
        return set.contains(object);
    }
    public T random_element(){
        if(size()>0){
            return data.get(ThreadLocalRandom.current().nextInt(0,size()));
        }
        return null;
    }
    public int size(){
        return data.size();
    }
    public void add(T object){
        if(!set.contains(object)){
            set.add(object);
            data.add(object);
        }
    }

    public void addSorted(Gene object){
        for(int i = 0;i<this.size();i++){
            int innovation = ((Gene)data.get(i)).getInnovationNumber();
            if(object.getInnovationNumber()<innovation){
                data.add(i, (T)object);
                set.add((T)object);
                return;
            }
        }
    }

    public void clear(){
        set.clear();
        data.clear();
    }

    public T get(int index){
        if(index<0 || index >= size()) throw new IndexOutOfBoundsException("Index "+index+" is out of bounds");
        else return data.get(index);
    }

    public void remove(int index){
        if(index < 0 || index >= size()) throw new IndexOutOfBoundsException("Index "+index+" is out of bounds");
        else{
            set.remove(data.get(index));
            data.remove(index);
        }

    }

    public void remove(T object){
        set.remove(object);
        data.remove(object);
    }
    public ArrayList<T> getData(){
        return data;
    }

}
