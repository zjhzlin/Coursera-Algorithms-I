/* *****************************************************************************
 *  Name:              Lynn Zhang
 *  Coursera User ID:  123456
 *  Last modified:     2021-05-09 07:30 - 08:08
 *                     2021-05-10 06:24 - 06:43
 *                     2021-05-11
 *                     2021-05-12 06:38
 **************************************************************************** */

/*
 ** examines 4 points at a time and checks whether they all lie on the same line segment,
 ** returning all such line segments.
 */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class BruteCollinearPoints {

    private int numLineSegments = 0;

    private final Point[] points;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        // corner cases: argument is null
        if (points == null) throw new IllegalArgumentException("Input is null");
        this.points = Arrays.copyOf(points, points.length);
        Arrays.sort(this.points);
        // any point in the array in null - illegal
        // contains a repeated point - illegal
        Point prevP = null;
        for (Point p : this.points) {
            if (p == null) throw new IllegalArgumentException("point is null");
            if (p.equals(prevP)) throw new IllegalArgumentException("duplicates");
            prevP = p;
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return numLineSegments;
    }

    // the line segments
    // should include each line segment containing 4 points exactly once.
    public LineSegment[] segments() {
        int numOfP = points.length;
        int capacity = numOfP * (numOfP - 1) / 3;

        LineSegment[] lineSegments = new LineSegment[capacity];

        for (int i = 0; i < points.length; i++) {
            Point p = points[i];
            for (int j = i + 1; j < points.length; j++) {
                Point q = points[j];
                for (int k = j + 1; k < points.length; k++) {
                    Point r = points[k];
                    for (int m = k + 1; m < points.length; m++) {
                        Point s = points[m];
                        if (p.slopeTo(q) == p.slopeTo(r) && p.slopeTo(r) == p.slopeTo(s)) {
                            // they are collinear
                            Point[] fourP = { p, q, r, s };
                            Arrays.sort(fourP);
                            lineSegments[numLineSegments++] = new LineSegment(fourP[0], fourP[3]);
                        }
                    }
                }
            }
        }

        return Arrays.copyOfRange(lineSegments, 0, numLineSegments);
    }

    public static void main(String[] args) {
        // StdDraw.setXscale(0, 6);
        // StdDraw.setYscale(0, 6);
        //
        // Point p1 = new Point(2, 2);
        // Point p2 = new Point(3, 3);
        // Point p3 = new Point(4, 4);
        // Point p4 = new Point(5, 5);
        // p1.draw();
        // p2.draw();
        // p3.draw();
        // p4.draw();
        // Point[] points = {p4, p3, p2, p1};
        //
        // BruteCollinearPoints bcp = new BruteCollinearPoints(points);
        // bcp.segments();
        // System.out.print(bcp.numberOfSegments());

        In in = new In(args[0]);

        // In in = new In("/collinear/equidistant.txt");      // input file
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        System.out.println("in total: " + collinear.numberOfSegments() + " collinear");
        StdDraw.show();


    }
}
