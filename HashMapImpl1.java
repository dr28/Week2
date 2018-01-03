package week2.intq;
import java.util.*;
/*Implement a Hashtable
Implement a simple hashtable without using any special collections classes or helpers (you can use native Java arrays).
Your implementation should minimally meet the following requirements:

Support generics for both key and value types
Implement the standard get, put, remove, size, clear, and isEmpty operations as defined in java.util.Map
Support an initial default capacity of 16 entries
Support dynamic allocation of additional capacity as needed
Bonus 1
Implement the entire java.util.Map interface by adding support for the remaining operations:

Implement containsKey and containsValue
Implement keySet, keySet, and values
Implement putAll
Hint: One standard approach to this problem would involve using two-dimensional arrays.*/

// Using two dimensional array.
public class HashMapImpl1<K,V> {

    // bucketArray is used to store array of chains
    private  HashNode<K, V> [][] bucketArray;

    // Current capacity of array list
    private int numBuckets;

    // Current size of array list
    private int size;

    // Constructor (Initializes capacity, size and
    // empty chains.
    public HashMapImpl1() {
        numBuckets = 16;
        size = 0;
        bucketArray = new HashNode[numBuckets][numBuckets];

    }

    public int size() { return size; }

    public boolean isEmpty() { return size() == 0; }

    public void clear() {

        for (int i = 0; i < numBuckets; i++) {

            for (int j = 0; j < numBuckets; j++)
                bucketArray[i][j] = null;

        }
        size = 0;
    }


    // This implements hash function to find index
    // for a key
    private int getBucketIndex(K key) {
        int hashCode = key.hashCode();
        int index = hashCode % numBuckets;
        return index;
    }

    // Returns value for a key
    public V get(K key) {

        // Find head of chain for given key
        int bucketIndex = getBucketIndex(key);
        HashNode<K, V>[] heads = bucketArray[bucketIndex];
        for (int i = 0; i < heads.length; i++) {
            HashNode<K, V> head = heads[i];
            // Search key in chain
            if (head != null) {
                if (head.key.equals(key))
                    return head.value;
            }
        }

        // If key not found
        return null;
    }

    public void putAll(HashMapImpl1 hm) {
        if (hm == null) throw new NullPointerException();

        for (int i = 0; i<hm.numBuckets; i++ ) {
            HashNode<K, V>[] heads = hm.bucketArray[i];

            for (int j = 0; j < heads.length; j++) {
                HashNode<K, V> head = heads[j];

                if (head != null) {
                    // headfrom new hashmap and put in this
                    this.put( head.key, head.value);
                }
            }
        }
    }

    // Adds a key value pair to hash
    public void put(K key, V value) {

        // Find head of chain for given key
        int bucketIndex = getBucketIndex(key);

        HashNode<K, V>[] heads = bucketArray[bucketIndex];

        for (int i = 0; i < numBuckets/*heads.length*/; i++) {
            HashNode<K, V> head = heads[i];

            // Check if key is already present
            if (head != null) {

                if (head.key.equals(key)) {
                    head.value = value;
                    bucketArray[bucketIndex][i] = head;
                    break;

                }
            } else {
                head = new HashNode(key, value);
                bucketArray[bucketIndex][i] = head;

                // Insert key in chain
                size++;
                break;
            }
        }

        if( heads.length < numBuckets) {
            HashNode<K, V> newNode = new HashNode<K, V>(key, value);

            bucketArray[bucketIndex][heads.length+1] = newNode;
        }

        // If load factor goes beyond threshold, then
        // double hash table size
        if ((1.0*size)/numBuckets >= 0.7)
        {
            HashNode<K, V>[][] temp = bucketArray;
            numBuckets = 2 * numBuckets;
            bucketArray = new HashNode[numBuckets][numBuckets];
            size = 0;
            for (int i = 0; i < temp.length; i++) {
                HashNode<K, V>[] heads1 = temp[i];
                if (heads1 != null) {
                    for (int j = 0; j < heads1.length; j++) {
                        if (heads1[j] != null) {
                            HashNode<K, V> headNode = heads1[j];
                            put(headNode.key, headNode.value);

                        }
                    }
                }
            }
        }
    }

    // Method to remove a given key
    public ArrayList<V> remove(K key) {

        // Apply hash function to find index for given key
        int bucketIndex = getBucketIndex(key);

        // Get head of chain
        HashNode<K, V>[] heads = bucketArray[bucketIndex];
        ArrayList vals = new ArrayList<V>();
        // If key was not there
        if (heads == null)
            return null;

        for (int i = 0; i < heads.length; i++) {

            // Search for key in its chain
            HashNode<K, V> head = heads[i];
            if (head != null) {
                // If Key found
                if (head.key.equals(key)) {
                    vals.add(head.value);
                    bucketArray[bucketIndex][i] = null;
                    // Reduce size
                    size--;
                    if(i>=0) {
                        for (int j = i+1; j < heads.length; j++) {
                            bucketArray[bucketIndex][i++] =  bucketArray[bucketIndex][j];

                        }
                    }
                    break;
                }
            }
        }
        return vals;
    }

    public boolean containsKey(K key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    public boolean containsValue(V value) {
        if (value == null) throw new IllegalArgumentException("argument to contains() is null");

        // Loop through bucket to check for value
        for(int i=0; i<bucketArray.length; i++) {
            HashNode<K, V>[] heads = bucketArray[i];

            if (heads != null) {
                for (int j = 0; j < heads.length; j++) {
                    HashNode<K, V> head = heads[j];

                    if (head != null) {
                        // Search value in chain
                        if (head.value.equals(value))
                            return true;

                    }
                }
            }
        }

        // If value not found
        return false;
    }

    public Set<K> keySet() {
        Set keys = new HashSet();
        for (int i = 0; i < numBuckets; i++) {
            HashNode<K, V>[] heads = bucketArray[i];
            for (int j = 0; j < heads.length; j++) {
                HashNode<K, V> head = heads[j];

                if (head != null)
                    keys.add(head.key);

            }
        }
        return keys;
    }

    public Collection<V> values() {
        Collection values = new ArrayList();
        for (int i = 0; i < numBuckets; i++) {
            HashNode<K, V>[] heads = bucketArray[i];
            for (int j = 0; j < heads.length; j++) {
                HashNode<K, V> head = heads[j];

                if (head != null)
                    values.add(head.value);
            }
        }
        return values;
    }

    public void printBucketArr(){

        for(int i = 0; i<bucketArray.length;i++){
            if (bucketArray[i] != null) {

                for(int j = 0; j<bucketArray[i].length; j++) {
                    System.out.print((bucketArray[i][j] != null) ? bucketArray[i][j].key + ":" + bucketArray[i][j].value + "   " : "  null  " + "      ");
                }
            }
            System.out.println(" ");
        }
    }
    public static void main(String[] args)
    {
        HashMapImpl1<String, Integer>map = new HashMapImpl1<>();
        map.put("this",1 );
        map.put("coder",2 );
        map.put("is",4 );
        map.put("great",5 );
        map.put("at",6 );
        map.put("codin",7 );
        map.put("ths",8 );
        map.put("o",9 );
        map.put("s",10 );
        map.put("r",11 );
        map.put("w",12 );
        map.put("t",13 );
        map.put("j",14 );
        map.put("a",15 );
        map.put("u",16 );
        map.put("i",17 );
        map.put("i",19 );
        map.put("iiii",21 );

        //map.printBucketArr();

        System.out.println("map size is "+map.size());
        // System.out.println(map.remove("this"));
        //    map.clear();
        System.out.println("map isEmpty() "+map.isEmpty());
        System.out.println("containsValue "+map.containsValue(17));
        System.out.println("values "+map.values());


        HashMapImpl1<String, Integer>map1 = new HashMapImpl1<>();
        map1.put("f",1 );
        map1.put("l",2 );
        map1.put("m",3 );

        map.putAll(map1);

        System.out.println("map size is "+map.size());
        System.out.println("keySet "+map.keySet());
        System.out.println("values "+map.values());

    }
}
// A node of chains
/*class HashNode<K, V>
{
    K key;
    V value;

    // Reference to next node
    HashNode<K, V> next;

    // Constructor
    public HashNode(K key, V value)
    {
        this.key = key;
        this.value = value;
    }
}*/