/* *****************************************************************************
 *  Name:              Lynn Zhang
 *  Coursera User ID:  123456
 *  Last modified:     2021-05-09 07:30 - 08:08
 *                     2021-05-10 06:24 - 06:43
 **************************************************************************** */

/*
** examines 4 points at a time and checks whether they all lie on the same line segment,
** returning all such line segments.
*/

import edu.princeton.cs.algs4.StdDraw;

import java.util.Arrays;

public class BruteCollinearPoints {

    private final int CAPACITY = 100;
    private LineSegment[] lineSegments = new LineSegment[CAPACITY];   // resize needed if bigger than capacity

    private int numLineSegments = 0;

    private final Point[] points;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        // corner cases: argument is null
        if (points == null) throw new IllegalArgumentException("Input is null");
        this.points = points;
    }

    // the number of line segments
    public int numberOfSegments() {
        return numLineSegments;
    }

    // the line segments
    // should include each line segment containing 4 points exactly once.
    public LineSegment[] segments() {
        // any point in the array in null - illegal
        // contains a repeated point - illegal
        int n = 0;
        for (int i = 0; i < points.length; i++) {
            Point p = points[i];
            if (p == null) throw new IllegalArgumentException("p point is null");
            for (int j = i+1; j < points.length; j++) {
                Point q = points[j];
                if (q.equals(p) || q == null)
                    throw new IllegalArgumentException("Duplicate points or q is null");
                for (int k = j+1; k < points.length; k++) {
                    Point r = points[k];
                    if (r.equals(p) || r.equals(q) || r == null)
                        throw new IllegalArgumentException("Duplicate points or r is null");
                    for (int m = k+1; m < points.length; m++) {
                        Point s = points[m];
                        if (s.equals(r) || s.equals(q) || s.equals(p) || s == null) {
                            throw new IllegalArgumentException("Duplicate point or s is null");
                        }
                        double slopeQ = p.slopeTo(q);
                        double slopeR = p.slopeTo(r);
                        double slopeS = p.slopeTo(s);
                        if (slopeQ == slopeR && slopeR == slopeS) {
                            // they are collinear
                            Point[] fourP = { p, q, r, s };
                            Arrays.sort(fourP);
                            lineSegments[n++] = new LineSegment(fourP[0], fourP[3]);
                            numLineSegments++;
                        }
                    }
                }
            }
        }

        return lineSegments;
    }

    public static void main(String[] args) {
        StdDraw.setXscale(0, 6);
        StdDraw.setYscale(0,6);

        Point p1 = new Point(2,2);
        Point p2 = new Point(3, 3);
        Point p3 = new Point (4,4);
        Point p4 = new Point(5,5);
        p1.draw();
        p2.draw();
        p3.draw();
        p4.draw();
        Point[] points = {p4, p3, p2, p1};

        BruteCollinearPoints bcp = new BruteCollinearPoints(points);
        bcp.segments();
        System.out.print(bcp.numberOfSegments());

    }
}