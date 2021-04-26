/* *****************************************************************************
 *  Name:              Lynn Zhang
 *  Coursera User ID:  Smilynn Zhang
 *  Last modified:     26/04/2021 08:34 - 09:34
 *                                21:06 - 22:35
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private WeightedQuickUnionUF grid;   // array of WQU, for connectivity
    private int rows;
    private int cols;
    private boolean[][] gridData;  // determine whether it is open or blocked. false - blocked; true - open

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        this.rows = n;
        this.cols = n;
        this.grid = new WeightedQuickUnionUF(n*n+2);  // 2 extra is the virtual nodes
        this.gridData = new boolean[n][n];
        // first n is the virtual node 0
        for (int i = 1; i <= n; i++) {
            grid.union(0, i);
        }
        // last n's parent is the virtual node n*n+1
        for (int i = n * n - n + 1; i <= n * n; i++) {
            grid.union(n * n + 1, i);
        }

    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col){
        // row and col from 1 to n
        if( row <=0 || col <= 0 || row > rows || col > cols) {
            throw new IllegalArgumentException("row or col is illegal");
        }
        if (!isOpen(row, col)) {
            gridData[row - 1][col - 1] = true;
        }
        // connect to its neighboring open sites
        // union() at least 4 times
        int index = getIndex(row, col);  // find target index in the WQU
        // exceptions: top row
        if (row == 1 && col == 1) {  // upper left corner
            connectIfOpen(index, row, col+1);
            connectIfOpen(index, row+1, col);
        }
        else if (row == 1 && col == cols) {  // upper right corner
            connectIfOpen(index, row, col-1);
            connectIfOpen(index, row+1, col);
        }
        else if (row == rows && col == 1) { // lower left corner
            connectIfOpen(index, row, col+1);
            connectIfOpen(index, row-1, col);
        }
        else if (row == rows && col == cols) {  // lower right corner
            connectIfOpen(index, row, col-1);
            connectIfOpen(index, row-1, col);
        }
        else if (row == 1) {    // other members in the first row
            connectIfOpen(index, row, col+1);
            connectIfOpen(index, row, col-1);
            connectIfOpen(index, row+1, col);
        }
        else if (col == 1) {  // other members in the first column
            connectIfOpen(index, row-1, col);
            connectIfOpen(index, row+1, col);
            connectIfOpen(index, row, col+1);
        }
        else if (col == cols) {  //others in the last column
            connectIfOpen(index, row-1, col);
            connectIfOpen(index, row+1, col);
            connectIfOpen(index, row, col-1);
        }
        else if (row == rows) {   // others in the last row
            connectIfOpen(index, row-1, col);
            connectIfOpen(index, row, col+1);
            connectIfOpen(index, row, col-1);
        }
        else {
            connectIfOpen(index, row-1, col);
            connectIfOpen(index, row+1, col);
            connectIfOpen(index, row, col-1);
            connectIfOpen(index, row, col+1);
        }
    }

    // given row and col, get the index in the WQU - starting from 1 to n*n
    private int getIndex(int row, int col) {
        int index = (row-1) * rows + col;
        return index;
    }

    // connect two nodes if the neighbor(row,col) is open
    private void connectIfOpen(int p, int row, int col){
        if (isOpen(row, col)){
            this.grid.union(p, getIndex(row, col));
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row <= 0 || col <= 0 || row > rows || col > cols) {
            throw new IllegalArgumentException("row or col is illegal");
        }
        if (gridData[row - 1][col - 1]) {
            return true;
        }
        else {
            return false;
        }
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if( row <=0 || col <= 0 || row > rows || col > cols) {
            throw new IllegalArgumentException("row or col is illegal");
        }
        // if it is connected to the top virtual node = isFull
        int index = (row-1) * rows + col;  // find its index in the WQU
        if (grid.find(index) == grid.find(0)){
            return true;
        }
        else {
            return false;
        }
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return grid.count();
    }

    // does the system percolate?
    public boolean percolates() {
        if (grid.find(0) == grid.find(rows*cols+1)){
            return true;
        }
        else {
            return false;
        }

    }

    // test client (optional)
    public static void main(String[] args) {

    }

}
