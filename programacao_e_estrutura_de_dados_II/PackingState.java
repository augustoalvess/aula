public class PackingState {

    public String seq;

    public PackingState(String s) {
        this.seq = s;
    }

    public double evaluate() {
        double sum = 0;
        for (char c : this.seq.toCharArray()) {
            if (c == 'a') { sum += 2.5; }
            if (c == 'b') { sum += 3; }
            if (c == 'c') { sum += 4; }
            if (c == 'd') { sum += 1.25; }
        }
        return Math.abs(50.2 - sum);
    }
}

