
/**
 * Write a description of EfficientMarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Random;
import java.util.*;

public class EfficientMarkovModel extends AbstractMarkovModel{
    private HashMap<String, ArrayList<String>>result;
    private int n;
    
    public EfficientMarkovModel(int order) {
        myRandom = new Random();
        result =  new HashMap<String, ArrayList<String>>();
        n = order;
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }
    
    public String getRandomText(int numChars){
        StringBuffer sb = new StringBuffer();
        int index = myRandom.nextInt(myText.length() - 1);
        String key = myText.substring(index, index +1);
        sb.append(key);
        
        for(int k=0; k < numChars - 1; k++){
           ArrayList<String> follows = getFllows(key);
           if(follows.size() == 0){
               break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = next;
        }
        
        return sb.toString();
    }
    
    public void orderString(){
        System.out.println("This is Markov model of order: "+ n);
    }
    
    public void buildMap(){
        for(int i = 0; i < myText.length() - n; i ++){
            String keycurr = myText.substring(i, i + n);
            if(!result.containsKey(keycurr)){
                ArrayList<String> listcurr = getFllows(keycurr);
                result.put(keycurr, listcurr);
            }
        }
        
    }
    
    public void printHashMapInfo(){
        buildMap();
        /*for(String keys: result.keySet()){
            System.out.println(keys);
            for(int n = 0; n < result.get(keys).size(); n ++){
                System.out.println(result.get(keys).get(n));
            }
        }//print out the whole Map
        */
        System.out.println("Total # of Keys: "+(result.size()+1));
        
        int size = 0;
        String maxKey = null;
        for(String keys: result.keySet()){
            if(result.get(keys).size() > size){
                size = result.get(keys).size();
                maxKey = keys;
            }
        }
        System.out.println("Key: "+ maxKey + " have the max size of: "+ size);
        System.out.println("MaxKey has list: " + result.get(maxKey));
    }

}
