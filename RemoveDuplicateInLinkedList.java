package week2.intq;

import java.util.HashMap;

/*Write a program which takes as its input an unsorted linked list of integers and deletes any duplicate values from
the list without using a temporary buffer collection or any additional collection classed, such as a HashSet. Use the
LinkedListNode implementation from this week's challenge.

Example: Given a linked list 1→2→3→3→4→4, the program should output: 1→2→3→4

Once you've implemented the algorithm, identify the time and space complexity of your solution using Big O notation.

Bonus 1

Implement an alternative solution, this time using a temporary storage buffer. This second solution should represent a
significant improvement over the first in terms of time complexity, though it will come at the cost of additional space
complexity. Be sure you can identify both using Big O notation.

Hint: Refer to the Big O Notation page and consult the the noted examples to determine which of them is the best fit.

*/
public class RemoveDuplicateInLinkedList {
    // time complexity: O(n^2)
    // space complexity: O(1)
    // Unsorted List
    LinkedListNode removeDuplicates (LinkedListNode a) {
        LinkedListNode current = a; //1
        LinkedListNode prev, next;
        //1→2→3→3→4→4

        while (current!= null) {
            prev = current;
            next = current.getNext();

            while (next != null) {

                if (current.getValue() == next.getValue()) {
                    prev.setNext(next.getNext());
                    next = next.getNext();

                } else {
                    prev = prev.getNext();
                    next = next.getNext();

                }
            }
            current = current.getNext();
        }
        return a;
    }


    // time complexity: O(n^2)
    // space complexity: O(1)
    // Sorted List
    LinkedListNode removeDuplicatesSorted (LinkedListNode a) {
        LinkedListNode current = a; //1
        LinkedListNode prev, next;
        //1→2→3→3→4→4
        prev = current;
        next = current.getNext();
        while (next != null) {

            if (prev.getValue() == next.getValue()) {
                LinkedListNode tempNext = next.getNext();

                while (tempNext!= null) {

                    if (prev.getValue() == tempNext.getValue())
                        tempNext = tempNext.getNext();

                    else {
                        prev.setNext(tempNext.getNext());
                        next = tempNext.getNext().getNext();
                        break;
                    }
                }
            } else {
                prev = prev.getNext();
                next = next.getNext();
            }

            prev = current;
            next = current.getNext();

            current = current.getNext();
        }
        return a;
    }

    // time complexity: O(n)
    // space complexity: O(n)
    LinkedListNode removeDuplicatesBonus (LinkedListNode a) {
        if (a==null) return null;
        LinkedListNode current = a;
        LinkedListNode prev;

        HashMap map = new HashMap();
        map.put(a.getValue(), 1);
        while(current!= null && current.getNext() != null) {
            prev = current;
            current = current.getNext();

            if(map.containsKey(current.getValue())) {
                prev.setNext(current.getNext());
                current = current.getNext(); // current = prev;
            } else {
                map.put(current.getValue(), 1);
            }
        }
        return a;
    }

    public static void main(String[] args) {
        LinkedListNode a = new LinkedList<Integer>();
        //Integer[] a1 = {1, 2, 2, 2, 2, 3, 3, 3, 4, 4};
        Integer[] a1 = {1, 1, 2, 3, 3};

        a.setValuesFromArray(a1);

        LinkedListNode result = new RemoveDuplicateInLinkedList().removeDuplicates(a);
        new RemoveDuplicateInLinkedList().printNode(result);

        result = new RemoveDuplicateInLinkedList().removeDuplicatesBonus(a);
        new RemoveDuplicateInLinkedList().printNode(result);

        LinkedListNode result1 = new RemoveDuplicateInLinkedList().removeDuplicatesSorted(a);
        new RemoveDuplicateInLinkedList().printNode(result1);
    }

    public void printNode(LinkedListNode result) {
        System.out.print(result.getValue() + " ");
        LinkedListNode next = result.getNext();
        while (next != null) {
            System.out.print(next.getValue() + " ");
            next = next.getNext();
        }
        System.out.print("\n");
    }
}
