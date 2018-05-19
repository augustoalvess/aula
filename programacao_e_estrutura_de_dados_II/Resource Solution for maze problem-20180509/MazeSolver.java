public class MazeSolver
{
    // contador de estados gerados
    public static int count = 0;
    
    public static boolean solve(MazeState state)
    {
        count++;
        System.out.println(state);
        
        // obtém posição atual
        int r = state.getRow();
        int c = state.getCol();
    
        if (state.get(r, c) == 4) { // saída encontrada
            System.out.println("EXIT FOUND!");
            return true;
        }
        
        // baseado no estado parcial atual, gera novos estados
        if (state.get(r - 1, c) == 0 || state.get(r - 1, c) == 4) {
            // existe caminho não explorado ao norte
            MazeState n = new MazeState(state); n.set(r, c, 3); n.setRow(r - 1);
            if (solve(n) == true) { return true; }
        }
        if (state.get(r + 1, c) == 0 || state.get(r + 1, c) == 4) {
            // existe caminho não explorado ao sul 
            MazeState s = new MazeState(state); s.set(r, c, 3); s.setRow(r + 1);
            if (solve(s) == true) { return true; }
        }
        if (state.get(r, c - 1) == 0 || state.get(r, c - 1) == 4) {
            // existe caminho não explorado a oeste
            MazeState o = new MazeState(state); o.set(r, c, 3); o.setCol(c - 1);
            if (solve(o) == true) { return true; }
        }
        if (state.get(r, c + 1) == 0 || state.get(r, c + 1) == 4) {
            // existe caminho não explorado a leste
            MazeState l = new MazeState(state); l.set(r, c, 3); l.setCol(c + 1);
            if (solve(l) == true) { return true; }
        }
        
        return false; // tudo mais falhou, retorne falso
    }
    
    public static void main(String[] args)
    {
        long start = System.currentTimeMillis();
        MazeState first = new MazeState();
        solve(first);
        System.out.println("count: " + count);
        System.out.println("time : " + (System.currentTimeMillis()-start)/1000.0 + " s");
    }
}

