/* *****************************************************************************
 *  Name:              Lynn Zhang
 *  Coursera User ID:
 *  Last modified:     4/5/2021 08:48 - 09:50
 *                     6/5/2021 07:55
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] s;
    private int size = 0;
    private int capacity = 10; // need to resize -> capacity

    // construct an empty randomized queue
    public RandomizedQueue() {
        s = (Item[]) new Object[capacity];  // using cast for general array creation; cast not recommended
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new NullPointerException("item is null");
        if (size == s.length) resize(2 * s.length);  // if array is full, create a new array twice the length
        s[size++] = item;
    }

    private void resize(int c) {
        Item[] copy = (Item[]) new Object[c];
        for (int i = 0; i < size; i++) {
            copy[i] = s[i];
        }
        s = copy;
        this.capacity = s.length;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("No item left");
        int ranNum = StdRandom.uniform(size);  // random int number [0,size)
        Item item = s[ranNum];
        // put all the items after ranNum 1 forward
        for (int i = ranNum; i < size; i++) {
            if (i + 1 < capacity) {
                s[i] = s[i + 1];
            }
            else {
                s[i] = null;
            }
        }
        size -= 1;
        if (size > 0 && size == s.length/4) resize(s.length/2);  // halve array when array is one quarter full
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("No item left");
        int ranNum = StdRandom.uniform(size);
        Item result = s[ranNum];
        return result;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {

        private int i = 0;
        private final int[] permutation = StdRandom.permutation(size);  // a uniformly random permutation of n elements.

        public boolean hasNext() {
            if (i >= capacity) {
                return false;
            }
            return s[i] != null;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more next items");
            }
            int index = permutation[i];
            Item item = s[index];
            i++;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException("remove operation is not supported");
        }
    }


    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> ranQue = new RandomizedQueue<>();
        ranQue.enqueue(1);
        ranQue.enqueue(2);
        ranQue.enqueue(3);
        ranQue.enqueue(4);

        System.out.println(ranQue.size);
        System.out.println("Sample is " + ranQue.sample());
        System.out.println(ranQue.sample());
        System.out.println(ranQue.sample());

        for (int queue: ranQue) {
            System.out.println("Iterable: " + queue);
        }

        System.out.println("Dequeue: " + ranQue.dequeue());
        System.out.println("Dequeue: " + ranQue.dequeue());
        System.out.println("Dequeue: " + ranQue.dequeue());


        for (int queue: ranQue) {
            System.out.println("Iterable: " + queue);
        }

    }
}
