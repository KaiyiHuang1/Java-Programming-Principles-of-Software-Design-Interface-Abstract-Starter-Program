
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }
 
    abstract public String getRandomText(int numChars);
    
    protected ArrayList<String> getFllows(String key){
        ArrayList<String> result = new ArrayList<String>();
        int pos = 0;
        int index = 0;
        while(pos < myText.length()){
            index = myText.indexOf(key,pos);
            //System.out.println(index);
            if(index < 0){
                break;
            }
            if(key.length() >= myText.length() - index){
                break;
            }
            result.add(myText.substring(index+key.length(),index+key.length()+1));
            pos = index + key.length();
           }
           return result;
       };

}
