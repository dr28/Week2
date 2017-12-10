package week2.challenges;
/*Challenge 2 - Add two numbers
In this exercise, we'll use your LinkedListNode implementation to represent a non-negative integer such that each node in the list represents
a single digit (in base 10) and the digits are stored in reverse order.

1     == 1
1→2   == 21
1→2→3 == 321
Write a program which takes as its input two such lists, a and b, and adds them arithmetically one decimal at a time. Your algorithm should
traverse both lists together, adding the values for each node and carrying the 1 to the next place when the sum >= 10. The result should be
returned as a linked list in the same format as the input lists.

Examples:

1→2     +   5→3   == 6→5      // 21 + 35 == 56
1→2     +   1→2→3 == 2→4→3    // 21 + 321 == 342
1→2→3   +   7→8→9 == 8→0→3→1  // 321 + 987 == 1308*/
public class Challenge2 {

    public LinkedListNode addTwoNumbers(LinkedListNode<Integer> a, LinkedListNode<Integer> b) {
        int carry = 0;
        LinkedListNode result = new Challenge1<Integer>(0);
        LinkedListNode node = result;

        while (a != null || b!=null || carry>0) {
            int value1 = 0;
            int value2 = 0;

            if(a != null) {
                value1 = a.getValue().intValue();
                a = a.getNext();
            }

            if(b != null) {
                value2 = b.getValue().intValue();
                b = b.getNext();
            }

            int sum = value1 + value2 + carry;
            System.out.println(sum%10);
            node.setNext(new Challenge1(sum%10));
            carry = sum/10;
            node = node.getNext();

        }
        return result.getNext();

    }

    public static void main(String[] args) {
        LinkedListNode a = new Challenge1<Integer>();
        //Integer[] a1 = {1,2};
        Integer[] a1 = {1,2,3};

        a.setValuesFromArray(a1);

        LinkedListNode b = new Challenge1<Integer>();
        //Integer[] b1 = {5,3};
       // Integer[] b1 = {1,2,3};
        Integer[] b1 = {7,8,9};

        b.setValuesFromArray(b1);

        LinkedListNode result = new Challenge2().addTwoNumbers(a, b);
        new Challenge2().printNode(result);
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
