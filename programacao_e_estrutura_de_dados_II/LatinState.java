public class LatinState {

    public char[][] m = {
        {' ', ' ', ' '},
        {' ', ' ', ' '},
        {' ', ' ', ' '}
    };

    public LatinState() {}

    private boolean colIsValid(int col) {
        int a = 0, b = 0, c = 0;
        for (int row = 0; row <= 2; row ++) {
            if (this.m[row][col] == 'a') { a++; }
            if (this.m[row][col] == 'b') { a++; }
            if (this.m[row][col] == 'c') { c++; }
        }
        return a <= 1 && b <= 1 && c <= 1;
    }

    private boolean rowIsValid(int row) {
        int a = 0, b = 0, c = 0;
        for (int col = 0; col <= 2; col ++) {
            if (this.m[row][col] == 'a') { a++; }
            if (this.m[row][col] == 'b') { a++; }
            if (this.m[row][col] == 'c') { c++; }
        }
        return a <= 1 && b <= 1 && c <= 1;
    }

    public boolean isSolution() {
        return colIsValid(0) && colIsValid(1) && colIsValid(2) && rowIsValid(0) && rowIsValid(1) && rowIsValid(2);
    }
}

