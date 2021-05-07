/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

public class Permutation {

    // get the first string before space

    private static int getNextIndex(String string) {
        int index = string.indexOf(' ');
        // String result = string.substring(0, index);
        return index + 1;
    }

    public static void main(String[] args) {
         // int k = Integer.parseInt(args[0]);
        int k = 3;
        // String strings = StdIn.readString();
        String strings = "A B C D E F G H I";
        RandomizedQueue<String> ranQue = new RandomizedQueue<>();

        int start = 0;
        int nextSpaceIndex = getNextIndex(strings);
        while (nextSpaceIndex != 0) {
            String next = strings.substring(start, nextSpaceIndex-1);
            ranQue.enqueue(next);
            strings = strings.substring(nextSpaceIndex);
            nextSpaceIndex = getNextIndex(strings);
        }
        for (int i = 0; i < k; i++) {
            System.out.println(ranQue.dequeue());
        }

    }
}