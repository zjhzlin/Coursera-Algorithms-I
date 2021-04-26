/* *****************************************************************************
 *  Name:              Lynn Zhang
 *  Coursera User ID:  Smilynn Zhang
 *  Last modified:     26/04/2021 08:34 - 09:34
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private WeightedQuickUnionUF[] grid;   // array of WQU
    private int rows;
    private int cols;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        this.rows = n;
        this.cols = n;
        this.grid = new WeightedQuickUnionUF[n];
        for(int i = 0; i < n; i++) {
            grid[i] = new WeightedQuickUnionUF(n);
        }

    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col){
        // row and col from 1 to n
        if( row <=0 || col <= 0 || row > rows || col > cols) {
            throw new IllegalArgumentException("row or col is illegal");
        }
        int row_index = row-1;
        int col_index = col-1;
        if(!isOpen(row, col)) {
            WeightedQuickUnionUF wqu = grid[row_index];
        }

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if( row <=0 || col <= 0 || row > rows || col > cols) {
            throw new IllegalArgumentException("row or col is illegal");
        }
        int row_index = row-1;
        int col_index = col-1;
        WeightedQuickUnionUF wqu = grid[row_index];
        if ( col_index == wqu.find(col_index) ) {  // root is himself - blocked
            return false;
        }
        else {
            return true;
        }
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if( row <=0 || col <= 0 || row > rows || col > cols) {
            throw new IllegalArgumentException("row or col is illegal");
        }
        return true;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return 1;
    }

    // does the system percolate?
    public boolean percolates() {
        return true;
    }

    // test client (optional)
    public static void main(String[] args) {

    }

}
