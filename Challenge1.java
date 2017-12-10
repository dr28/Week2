package week2.challenges;

/*Challenge 1 - Implement a Linked List
Implement a simple linked list in Java without using any collections classes. The list should be implemented using a single class
such that each instance represents a single node in the list, encapsulating the node's value and a reference to the following node,
as well as a convenience method to initialize a whole list from an array of values. The class should implement the following interface:

public interface LinkedListNode<E> {

    // getter/setter for this node's value
    E getValue();
            void setValue(E value);

    // getter/setter for the subsequent node, or null if this is the last node
            LinkedListNode<E> getNext();
        void setNext(LinkedListNode<E> next);

        //**
        // * Initialize this node and all of its subsequent nodes from
        // * an array of values, starting with the head value at index 0
        // *
        // * @param listValues - the ordered values for the whole list
        //
        void setValuesFromArray(E[] listValues);

        }
        When complete, you should be able to successfully run the following unit test using your implementation:

        import static org.junit.Assert.*;
        import org.junit.Test;

public class LinkedListNodeTest {

    /*@Test*
    public void test() {
        LinkedListNode<Integer> head = null;
        Integer[] listValues = new Integer[] {1, 2, 3};

        head = new LinkedListNodeImpl<>(); // replace with your implementation
        head.setValuesFromArray(listValues);

        assertEquals(listValues[0], head.getValue());
        assertNotNull(head.getNext());
        assertEquals(listValues[1], head.getNext().getValue());
        assertNotNull(head.getNext().getNext());
        assertEquals(listValues[2], head.getNext().getNext().getValue());
        assertNull(head.getNext().getNext().getNext());
    }

}*/
public class Challenge1<E> implements LinkedListNode<E> {

    private E value;
    private LinkedListNode next;

    public Challenge1(E value) {
        this.value = value;
        next = null;
    }

    public Challenge1() {
        value = null;
        next = null;
    }

    // getter/setter for this node's value
    public E getValue() {
        return this.value;
    }

    public void setValue(E value) {
        this.value = value;
    }

    // getter/setter for the subsequent node, or null if this is the last node
    public LinkedListNode<E> getNext() {
        return this.next;
    }

    public void setNext(LinkedListNode<E> next) {
        this.next = next;

    }

    /**
     * Initialize this node and all of its subsequent nodes from
     * an array of values, starting with the head value at index 0
     *
     * @param listValues - the ordered values for the whole list
     */

    public void setValuesFromArray(E[] listValues) {
        if(listValues != null) {
            this.value = listValues[0];
            LinkedListNode current = this;

            for (int i = 1; i < listValues.length; i++) {
                LinkedListNode node = new Challenge1<E>(listValues[i]);
                current.setNext(node);
                current = node;

            }
        }

    }
}


