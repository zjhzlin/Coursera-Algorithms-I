
/* *****************************************************************************
 *  Name:              Lynn Zhang
 *  Coursera User ID:
 *  Last modified:    2021-05-10 07:40 - 09:26
 *
 **************************************************************************** */

/*
 ** examines 4 points at a time and checks whether they all lie on the same line segment,
 ** returning all such line segments.
 */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class FastCollinearPoints {

    private final Point[] points;
    private int numOfSegments = 0;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException("Input is null");
        this.points = Arrays.copyOf(points, points.length);
        Arrays.sort(this.points);
        Point prevP = null;
        for (Point p: this.points) {
            if (p == null) throw new IllegalArgumentException("point is null");
            if (p.equals(prevP)) throw new IllegalArgumentException("duplicates");
            prevP = p;
        }

    }

    // the number of line segments
    public int numberOfSegments() {
        return numOfSegments;
    }

    // the line segments
    // include each maximal line segment containing 4 (or more) points exactly once.
    // For example, if 5 points appear on a line segment in the order p→q→r→s→t, then do not include the subsegments p→s or q→t.
    public LineSegment[] segments() {

        int numOfP = points.length;
        // int capacity = numOfP * (numOfP - 1) * (numOfP - 2) * (numOfP - 3);
        // int capacity = numOfP / 4;
        int capacity = numOfP * (numOfP - 1) / 3;

        LineSegment[] lineSegments = new LineSegment[capacity];
        Point[] pointsCopy = Arrays.copyOf(points, numOfP);
        ArrayList<Point> pointsCollinear = new ArrayList<>();
        // loop in the n points
        for (Point p: points) {
            if (p == null) throw new IllegalArgumentException("p is null");
            // if p already found in collinear, no need to find it again
            if (pointsCollinear.contains(p)) continue;
            // given a point p, use this as origin
            // sort the array according to the slope order to p
            Comparator<Point> bySlopeOrder = p.slopeOrder();
            Arrays.sort(pointsCopy, bySlopeOrder);  // p is always the first
            // for each other point in this array, calculate its slope to the origin
            Double[] slopes = new Double[numOfP];
            int i = 0;
            for (Point q: pointsCopy) {
                if (q == null) throw new IllegalArgumentException("q is null");
                double slope = p.slopeTo(q);
                if (i > 0 && slope == Double.NEGATIVE_INFINITY) throw new IllegalArgumentException("duplicate");  // if there is another point with same x and same y as p -> duplicates
                slopes[i++] = slope;
            }
            // Check if any 3 (or more) adjacent points in the sorted order have equal slopes with respect to p.
            int countSameSlope = 0;
            int startIndex = 0;
            for (int m = 0; m < slopes.length; m++) {
                if (countSameSlope >= 2) break;
                for (int n = m + 1; n < slopes.length; n++) {
                    if (!slopes[m].equals(slopes[n]) && countSameSlope >= 2) {
                        startIndex = m;
                        break;  // find the colinear
                    }
                    else if (slopes[m].equals(slopes[n])) {
                        countSameSlope++;
                    }
                }
            }
            // If so, these points, together with p, are collinear
            if (countSameSlope >= 2) {
                Point[] pointsCovered = Arrays.copyOfRange(pointsCopy, startIndex, startIndex + countSameSlope + 1);
                Point[] pointsOnLine = new Point[pointsCovered.length + 1];
                for (int index = 0; index < pointsCovered.length; index++) {
                    pointsOnLine[index] = pointsCovered[index];
                }
                pointsOnLine[pointsOnLine.length-1] = p;
                Arrays.sort(pointsOnLine);
                LineSegment lineSeg = new LineSegment(pointsOnLine[0], pointsOnLine[pointsOnLine.length-1]);
                lineSegments[numOfSegments++] = lineSeg;
                // if these points are already collinear, then next loop, no need to look for its collinear points.
                Collections.addAll(pointsCollinear, pointsCovered);
            }

        }
        return Arrays.copyOfRange(lineSegments, 0, numOfSegments);
    }


    public static void main(String[] args) {

        // Point p1 = new Point(2,2);
        // Point p2 = new Point(3, 3);
        // Point p3 = new Point (4,4);
        // Point p4 = new Point(5,5);
        // p1.draw();
        // p2.draw();
        // p3.draw();
        // p4.draw();
        // Point[] points = {p4, p3, p2, p1};
        //
        // BruteCollinearPoints bcp = new BruteCollinearPoints(points);
        // bcp.segments();
        // System.out.print(bcp.numberOfSegments());


        // read the n points from a file
        In in = new In(args[0]);
        // In in = new In("/collinear/input8.txt");      // input file
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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        System.out.println("in total: " + collinear.numOfSegments + " collinear");
        StdDraw.show();
    }
}
