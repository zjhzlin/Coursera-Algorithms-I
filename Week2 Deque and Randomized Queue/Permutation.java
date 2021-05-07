/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;

public class Permutation {

    // get the starting index of the string after first space
    private static int getNextIndex(String string) {
        int index = string.indexOf(' ');
        // String result = string.substring(0, index);
        return index + 1;
    }

    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        // int k = 9;
        // String strings = StdIn.readString();
        // String strings = "A B C D E F G H I";
        RandomizedQueue<String> ranQue = new RandomizedQueue<>();

        String string = StdIn.readString();
        while (!string.isEmpty()) {
            ranQue.enqueue(string);
            string = StdIn.readString();
        }


        // int start = 0;
        // int nextStringIndex = getNextIndex(strings);
        // while (nextStringIndex != 0) {
        //     String next = strings.substring(start, nextStringIndex-1);   // next string before space
        //     ranQue.enqueue(next);   // add to the random queue
        //     strings = strings.substring(nextStringIndex);    // update the strings to be the one without the added string
        //     nextStringIndex = getNextIndex(strings);
        // }
        // if (!strings.isEmpty()) ranQue.enqueue(strings);    // add the last element

        int i = 0;
        for (String queue: ranQue) {
            System.out.println(queue);
            i++;
            if (i > k) break;
        }

    }
}
