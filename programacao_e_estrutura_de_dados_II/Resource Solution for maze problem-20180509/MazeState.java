public class MazeState
{
    private int[][] m = {{1, 1, 1, 1, 1, 1, 1, 1}, // labirinto zerado
                         {1, 0, 1, 0, 0, 0, 1, 1},
                         {1, 0, 0, 0, 1, 1, 4, 1},
                         {1, 0, 1, 0, 0, 1, 0, 1},
                         {1, 0, 1, 1, 0, 0, 0, 1},
                         {1, 1, 1, 1, 1, 1, 1, 1}};
    private int row = 1, col = 1; // posição inicial
    
    public MazeState() {}

    public MazeState(MazeState s) {
        for (int r=0; r<6; r++) {
            for (int c=0; c<8; c++) {
                this.m[r][c] = s.m[r][c]; } } 
        this.row = s.row;
        this.col = s.col; }
    
    public int get(int r, int c) { return this.m[r][c]; }
    public int getRow()          { return this.row; }
    public int getCol()          { return this.col; }

    public void set(int r, int c, int v) { this.m[r][c] = v; }
    public void setRow(int r)            { this.row = r; }
    public void setCol(int c)            { this.col = c; }
    
    public String toString() {
        String s = "";
        for (int r=0; r<6; r++) {
            for (int c=0; c<8; c++) {
                s += this.m[r][c];
            }
            s += '\n';
        }
        return s;
    }        
}

