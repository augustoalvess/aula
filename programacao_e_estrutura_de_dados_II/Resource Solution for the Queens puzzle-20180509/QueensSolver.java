public class QueensSolver
{
    public static int counter = 0;
    public static boolean solve(QueensState state)
    {
        System.out.println(state);

        // faz o corte, interrompendo a recursão já em estados
        // parciais que sejam inválidos
        if (state.isValid() == false) {
            return false;
        }
        counter++;

        if (state.isSolution() == true) {
            System.out.println("Solution found!\n");
            return true;
        }

        // procura próxima posição vazia -> será dada por [row][col]
        boolean found = false;
        int row = 0, col = 0;
        for (row = 0; row <= 3; row++) {
            for (col = 0; col <= 3; col++) {
                if (state.m[row][col] == 0) {
                    found = true;
                    break;
                }
            }
            if (found == true) {
                break;
            }
        }
        if (found == false) {
            // tabuleiro já está cheio
            return false;
        }

        // baseado no estado parcial atual, gera e testa diferentes variações

        QueensState s1 = new QueensState(state);
        s1.m[row][col] = 1; // rainha
        if (solve(s1) == true) {
            return true;
        }

        QueensState s2 = new QueensState(state);
        s2.m[row][col] = 2; // sem rainha
        if (solve(s2) == true) {
            return true;
        }

        return false;
    }

    public static void main(String[] args)
    {
        QueensState s = new QueensState();
        System.out.println("solution was found?: " + solve(s));
        System.out.println("valid states tested: " + counter);
    }
}
