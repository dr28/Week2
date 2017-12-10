package week2.challenges;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/*Challenge 5 - Multimaps
One common pattern when using hash tables requires building a Map whose values are Collection instances. In this challenge, we'll take
the output of the previous challenge and invert it.

Write a program that takes as its input a HashMap<String,Integer> and returns a HashMap<Integer,HashSet<String>> containing the same data as
the input map, only inverted, such that the input map's values are the output map's keys and the input map's keys are the output map's values.

Example:

Consider the example output for Challenge #4. Using that map as the input, the output for this challenge would be:

2 → ["to", "be"]
1 → ["or", "not", "that", "is", "the", "question"]*/
public class Challenge5 {
    HashMap<Integer, HashSet<String>> multiMaps (HashMap<String, Integer> a) {
        HashMap<Integer, HashSet<String>> result = new HashMap<Integer, HashSet<String>>();

        Iterator i = a.keySet().iterator();
        while(i.hasNext()) {
            String key = (String)i.next();
            Integer val = a.get(key);

            if(result.containsKey(val)) {
                result.get(val).add(key);
            }
            else {
                HashSet setVal = new HashSet<String>();
                setVal.add(key);
                result.put(val, setVal);

            }
        }

        return result;

    }


    public static void main(String args[])
    {
        HashMap input = new HashMap();
        input.put("to", 2);
        input.put("be", 2);
        input.put("or", 1);
        input.put("not", 1);
        input.put("that", 1);
        input.put("is", 1);
        input.put("the", 1);
        input.put("question", 1);

        HashMap result = new Challenge5().multiMaps(input);

        Iterator i = result.keySet().iterator();
        while (i.hasNext()) {
            Integer key = (Integer)i.next();
            System.out.println("key "+ key + " count "+result.get(key));

        }

    }
}
