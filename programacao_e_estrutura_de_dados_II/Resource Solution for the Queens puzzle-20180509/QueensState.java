public class QueensState
{
    // legenda:
    // 0 - espaço vazio, a ser ocupado
    // 1 - rainha
    // 2 - espaço sem rainha
    public int m[][] = new int[4][4];
    
    public QueensState() {
    }

    public QueensState(QueensState s) {
        for (int row = 0; row <= 3; row++) {
            for (int col = 0; col <= 3; col++) {
                this.m[row][col] = s.m[row][col];
            }
        }
    }
        
    public boolean isRowValid(int row) {
        int q = 0;
        for (int col = 0; col <= 3; col++) {
            if (m[row][col] == 1) { q++; } // conta uma rainha
        }
        return q <= 1;
    }
    
    public boolean isColValid(int col) {
        int q = 0;
        for (int row = 0; row <= 3; row++) {
            if (m[row][col] == 1) { q++; } // conta uma rainha
        }
        return q <= 1;
    }

    public boolean isDiagValid(int col, int row) {
        int q = 0;
        while (row <= 3 && col <= 3) {
            if (m[row][col] == 1) { q++; } // conta uma rainha
            row++;
            col++;
        }
        return q <= 1;
    }

    public boolean isBackValid(int col, int row) {
        int q = 0;
        while (row <= 3 && col >= 0) {
            if (m[row][col] == 1) { q++; } // conta uma rainha
            row++;
            col--;
        }
        return q <= 1;
    }
    
    public boolean isValid() {
        return isRowValid(0) && isRowValid(1) && 
               isRowValid(2) && isRowValid(3) &&
               isColValid(0) && isColValid(1) && 
               isColValid(2) && isColValid(3) &&
               isDiagValid(2,0) && isDiagValid(1,0) && 
               isDiagValid(0,0) && isDiagValid(0,1) &&
               isDiagValid(0,2) &&
               isBackValid(0,1) && isBackValid(0,2) &&
               isBackValid(0,3) && isBackValid(1,3) &&
               isBackValid(2,3);
    }
   
    public boolean isSolution() {
        int e = 0, q = 0;
        for (int row = 0; row <= 3; row++) {
            for (int col = 0; col <= 3; col++) {
                if (m[row][col] == 0) { e++; }
                if (m[row][col] == 1) { q++; }
            }
        }
        return e == 0 && q == 4;
    }

    public String toString() {
        String str = "";
        for (int row = 0; row <= 3; row++) {
            for (int col = 0; col <= 3; col++) {
                str += m[row][col] + " ";
            }
            str += '\n';
        }
        return str; 
    }
}
