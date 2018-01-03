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

// A node of chains
class HashNode<K, V>
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
}

// Using one dimensional array.
public class HashMapImpl<K,V> {

    // bucketArray is used to store array of chains
    private  HashNode<K, V> [] bucketArray;

    // Current capacity of array list
    private int numBuckets;

    // Current size of array list
    private int size;

    // Constructor (Initializes capacity, size and
    // empty chains.
    public HashMapImpl()
    {
        numBuckets = 16;
        size = 0;
        bucketArray = new HashNode[numBuckets];


        // Create empty chains
        //for (int i = 0; i < numBuckets; i++)
            //bucketArray.add(null);
    }

    public int size() {
       /* for (int i = 0; i < numBuckets; i++) {

            if (bucketArray[i] == null) {
                if (i == 0)  return i;
                else
                 return i + 1;
            }
            size++;


        }*/
        return size;
    }

    public boolean isEmpty() { return size() == 0; }

    public void clear() {
        for (int i = 0; i < size; i++)
            bucketArray[i] = null;
        size = 0;
    }


    // This implements hash function to find index
    // for a key
    private int getBucketIndex(K key)
    {
        int hashCode = key.hashCode();
        int index = hashCode % numBuckets;

        return index;
    }

    // Returns value for a key
    public V get(K key)
    {
        // Find head of chain for given key
        int bucketIndex = getBucketIndex(key);
        HashNode<K, V> head = bucketArray[bucketIndex];

        // Search key in chain
        while (head != null)
        {
            if (head.key.equals(key))
                return head.value;
            head = head.next;
        }

        // If key not found
        return null;
    }

    public void putAll(HashMapImpl hm) {
        if (hm == null) throw new NullPointerException();

        for (int i = 0; i<hm.numBuckets; i++ ){
            HashNode<K, V> head = hm.bucketArray[i];
            while (head != null) {
                // headfrom new hashmap and put in this
                this.put( head.key, head.value);
                head = head.next;
            }
        }

    }


    // Adds a key value pair to hash
    public void put(K key, V value)
    {
        // Find head of chain for given key
        int bucketIndex = getBucketIndex(key);
        HashNode<K, V> head = bucketArray[bucketIndex];
       // System.out.println("put bucketIndex "+bucketIndex);
        //System.out.println("head "+head);

        // Check if key is already present
        while (head != null)
        {
            if (head.key.equals(key))
            {
                head.value = value;
                return;
            }
            head = head.next;
        }

        // Insert key in chain
        size++;
        head = bucketArray[bucketIndex];
        HashNode<K, V> newNode = new HashNode<K, V>(key, value);
        newNode.next = head;
        bucketArray[bucketIndex] = newNode;
        //System.out.println("bucketArray ["+bucketIndex+"] "+bucketArray[bucketIndex].value);

        // If load factor goes beyond threshold, then
        // double hash table size
        if ((1.0*size)/numBuckets >= 0.7)
        {
            HashNode<K, V>[] temp = bucketArray;
            numBuckets = 2 * numBuckets;
            bucketArray = new HashNode[numBuckets];
            size = 0;
            //for (int i = 0; i < numBuckets; i++)
            //    bucketArray.add(null);

            for (HashNode<K, V> headNode : temp)
            {
                while (headNode != null)
                {
                    put(headNode.key, headNode.value);
                    headNode = headNode.next;
                }
            }
        }
    }

    // Method to remove a given key
    public V remove(K key)
    {
        // Apply hash function to find index for given key
        int bucketIndex = getBucketIndex(key);

        // Get head of chain
        HashNode<K, V> head = bucketArray[bucketIndex];

        // Search for key in its chain
        HashNode<K, V> prev = null;
        while (head != null)
        {
            // If Key found
            if (head.key.equals(key))
                break;

            // Else keep moving in chain
            prev = head;
            head = head.next;
        }

        // If key was not there
        if (head == null)
            return null;

        // Reduce size
        size--;

        // Remove key
        if (prev != null)
            prev.next = head.next;
        else
            bucketArray[bucketIndex] = head.next;

        return head.value;
    }

    public boolean containsKey(K key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    public boolean containsValue(V value) {
        if (value == null) throw new IllegalArgumentException("argument to contains() is null");
        //System.out.println("containsValue size() "+size());

        // Loop through bucket to check for value
        for(int i=0; i<numBuckets; i++) {
            HashNode<K, V> head = bucketArray[i];
            //System.out.println("containsValue head "+i+" "+head);

            while (head != null) {
                // Search value in chain
                if (head.value.equals(value))
                    return true;

                head = head.next;

            }
        }

        // If value not found
        return false;
    }

    public Set<K> keySet() {
        Set keys = new HashSet();
        for (int i = 0; i < numBuckets; i++) {
            HashNode<K, V> head = bucketArray[i];
            if (head != null) {
                keys.add(head.key);
                head = head.next;
            }
        }
        return keys;
    }

    public Collection<V> values() {
        Collection values = new ArrayList();
        for (int i = 0; i < numBuckets; i++) {
            HashNode<K, V> head = bucketArray[i];
            while (head != null) {

                values.add(head.value);
                head = head.next;

            }
        }
        return values;
    }
    public static void main(String[] args)
    {
        HashMapImpl<String, Integer>map = new HashMapImpl<>();
        map.put("this",1 );
        map.put("coder",2 );
        map.put("v",4 );
        map.put("h",5 );
        map.put("tis",6 );
        map.put("cder",7 );
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
        map.put("iy",17 );

        System.out.println("map size is "+map.size());
        //System.out.println(map.remove("this"));
        //System.out.println(map.remove("this"));
        //System.out.println(map.size());
        // map.clear();
        System.out.println("map isEmpty() "+map.isEmpty());
        System.out.println("containsValue "+map.containsValue(16));
        System.out.println("keySet "+map.keySet());
        System.out.println("values "+map.values());

        HashMapImpl<String, Integer>map1 = new HashMapImpl<>();
        map1.put("f",1 );
        map1.put("l",2 );
        map1.put("m",3 );
        map.putAll(map1);
        System.out.println("map size is "+map.size());
        System.out.println("containsValue "+map.containsValue(3));

        System.out.println("keySet "+map.keySet());
        System.out.println("values "+map.values());
    }
}
/*class HashNode<K,V> {
    K key;
    V value;
    HashNode<K,V> next;

    HashNode(K key, V value) {
        this.key = key;
        this.value = value;

    }

}



class Solution<K,V> {

    private int capacity;

    private int size;


    private HashNode<K,V>[] bucket;

    public Solution() {
        capacity = 16;
        size = 0;
        bucket = new HashNode[capacity];

    }


    int size() {
        return size;
    }

    void clear() {

        for(int i = 0 ; i<capacity ; i++) {
            bucket[i] = null;
        }

    }

    Boolean isEmpty() {

        return (size()==0);

    }

    int getIndex(K key) {

        int hashcode = key.hashCode();
        int index = hashcode%capacity;

        return index;
    }

    public V get (K key) {
        int kindex = getIndex(key);
        HashNode<K,V> head = bucket[kindex];


        while(head != null) {

            if(head.key.equals(key))
                return head.value;

            head = head.next;

        }

        return null;

    }

    //index = 1

    //key = 1, value = 2
    //key = 1, value = 1000


    public V put(K key, V value){
        int kindex = getIndex(key);
        HashNode<K,V> head = bucket[kindex];

        HashNode<K,V> prev = null;
        while(head != null) {

            if(head.key.equals(key)) {
                V oldVal = head.value;
                head.value = value;

                return oldVal;
            }
            prev = head;
            head = head.next;
        }

        if (head == null) {

            size++;
            prev = new HashNode<K,V>( key, value);
            bucket[kindex] = prev;
        }

        return null;


    }

    V remove (K key) {
        int kindex = getIndex(key);
        HashNode<K,V> head = bucket[kindex];
        HashNode<K,V> prev = head;

        while(head != null) {

            if(head.key.equals(key)) {
                V val = head.value;

                if(head.next != null)
                    head.next = head.next.next;

                else {
                    prev.next = null;
                }
                return val;

            }
            prev = head;
            head = head.next;
        }

        return null;
    }


    public static void main(String[] args) {
        Solution<String, Integer>map = new Solution<>();
        map.put("this",1 );
        map.put("is",2 );

        System.out.println("isEmpty "+map.isEmpty());

        System.out.println("size "+map.size());
        System.out.println("get "+map.get("this"));

    }
}*/