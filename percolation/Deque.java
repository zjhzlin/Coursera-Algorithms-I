/* *****************************************************************************
 *  Name:              Lynn Zhang
 *  Coursera User ID:
 *  Last modified:     30/04/2021 08:55 - 09:53
 *                     1/5/2021 09:17
 *                     2/5/2021 08:40 - 08:59 use double linked list
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first, last;
    private int size;

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return first == null;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("item is null");
        }
        Node oldFirst = first;  // corner case; first = null
        first = new Node();
        first.item = item;
        first.prev = null;
        if (size == 0) {
            last = first;
            first.next = null;
        }
        else {
            first.next = oldFirst;
            oldFirst.prev = first;
        }
        size += 1;

    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("item is null");
        }
        Node oldLast = last;  // special case oldLast = null
        last = new Node();
        last.item = item;
        last.next = null;

        if (isEmpty()) {
            first = last;
            last.prev = null;
        }
        else {
            oldLast.next = last;
            last.prev = oldLast;
        }
        size += 1;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("the queue is already null");
        }
        Item item = first.item;
        if (size == 1) {
            first = null;
            last = null;
        }
        else {
            first = first.next;
            first.prev = null;
        }
        size -= 1;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("the queue is already null");
        }
        Item item = last.item;
        if (size == 1) {
            first = null;
            last = null;
        }
        else {
            last = last.prev;
            last.next = null;
        }
        size -= 1;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {

        private Node current = first;

        public boolean hasNext() {
            if (current == null) {
                return false;
            }
            return current != null;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more next items");
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException("remove operation is not supported");
        }

    }

    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);
        // deque.addFirst(2);
        // deque.addFirst(3);
        System.out.println("Deque size is: " + deque.size());
        // System.out.println("Remove Last: " + deque.removeLast());
        // System.out.println("Remove Last: " + deque.removeLast());
        //
        //
        // System.out.println("Remove First: " + deque.removeFirst());
        // System.out.println("Remove First: " + deque.removeFirst());
        // System.out.println("Remove First: " + deque.removeFirst());


        for (int i: deque) {
           System.out.println("iterate: " +  i);
        }
        // Iterator<Integer> dequeI = deque.iterator();
        // while (dequeI.hasNext()) {
        //     System.out.println("iterate: " + dequeI.next());
        // }
    }
}
