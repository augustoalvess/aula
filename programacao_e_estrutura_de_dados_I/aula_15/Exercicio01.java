public class Exercicio01 {
    public static void main(String args[]) {
        int[] v = {1,2,3,4,5,6,7,8,9,10};

        if (Exercicio01.binarySearch(v, 7, 0, (v.length - 1))) {
            System.out.println("Encontrou");
        } else {
            System.out.println("Não Encontrou");
        }
    }

    public static boolean binarySearch(int[] v, int n, int start, int end) {
        int middle = (start + end) / 2;

        if (v[middle] == n) { 
            return true; 
        }
        if (v[middle] < n ) {
            if (middle != start) {
                return Exercicio01.binarySearch(v, n, middle, end);
            } else {
                return false;
            }
        } else {
            if (middle != end) {
                return Exercicio01.binarySearch(v, n, start, middle);
            } else {
                return false;
            }
        }
    }
}