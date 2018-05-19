public class Tarefa1Solver {

    public static int count;

    public static void main(String[] args) {
        int[][] bloqueados = new int[][]{{0, 5}, {1, 4}};
        solve(new Tarefa1State(2, 7, bloqueados));
        System.out.println("count: " + count);
    }
    
    public static void solve(Tarefa1State state) {
    	if (state.isSolution()) {
            System.out.println(state);
            count++;
    	} else {
    	   solve(new Tarefa1State(state, new char[]{'a', 'b', 'c', 'd'}));
    	}
    }
}

