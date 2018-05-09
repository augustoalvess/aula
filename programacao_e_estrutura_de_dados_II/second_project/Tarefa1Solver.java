public class Tarefa1Solver {

    public static int count;

    public static void main(String[] args) {
        solve(new Tarefa1State(2, 7));
    }
    
    public static void solve(Tarefa1State state) {
    	/**
    	if (state.isSolution()) {

    	} else {
    		solve(new Tarefa1State("a"));
            solve(new Tarefa1State("b"));
            solve(new Tarefa1State("c"));
            solve(new Tarefa1State("d"));
    	}
    	*/


        System.out.println(state);
    }
}

