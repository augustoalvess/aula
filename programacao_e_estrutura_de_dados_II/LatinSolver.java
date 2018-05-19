public class LatinSolver {

    public static int count;

    public static void main(String[] args) {
        solve(new LatinState(""));
        System.out.println("count: " + count);
    }
    
    public static void solve(PackingState state) {
        if (state.seq.length() == 14) {
            count++;
            double val = state.evaluate();
            if (val < best_val) {
                best_str = state.seq;
                best_val = val;
            }
        }
        else {
            solve(new PackingState(state.seq + "a"));
            solve(new PackingState(state.seq + "b"));
            solve(new PackingState(state.seq + "c"));
            solve(new PackingState(state.seq + "d"));
        }
    }
}

