/* *****************************************************************************
 *  Name:              Lynn Zhang
 *  Coursera User ID:
 *  Last modified:     2/5/2021 09:00 - 09:38
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Node first;
    private int size;

    private class Node {
        Item item;
        Node next;
    }

    // construct an empty randomized queue
    public RandomizedQueue() {
        first = null;
        size = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return first == null;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    // add from the first
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("item is null");
        }
        Node oldFirst = first;  // corner case; first = null
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        size += 1;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        int randomNum = StdRandom.uniform(size);  // return a random integer uniformly in [0, n)
        Node current = first;
        Node prev = null;
        for (int i = 0; i < randomNum; i++) {
            prev = current;
            current = current.next;
            if (i == randomNum - 1) {
                // delete this current one
                prev.next = current.next;
            }
        }
        if (randomNum == 0) {  // delete the first node, note if first is the only node
            if (size == 1) {
                first = null;
            }
            else {
                first = first.next;
            }
        }
        size -= 1;
        return current.item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        int randomNum = StdRandom.uniform(size);  // return a random integer uniformly in [0, n)
        Node current = first;
        for (int i = 0; i < randomNum; i++) {
            current = current.next;
        }
        return current.item;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ListIterator();
    }


    private class ListIterator implements Iterator <Item> {

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
        RandomizedQueue<Integer> ranQue = new RandomizedQueue<>();
        ranQue.enqueue(1);
        ranQue.enqueue(2);
        ranQue.enqueue(3);
        System.out.println("Random Queue size is: " + ranQue.size());
        System.out.println("dequeue is: " + ranQue.dequeue());
        // System.out.println("dequeue is: " + ranQue.dequeue());
        // System.out.println("sample is: " + ranQue.sample());

        for (int i: ranQue) {
            System.out.println("iterate: " +  i);
        }

    }
}
