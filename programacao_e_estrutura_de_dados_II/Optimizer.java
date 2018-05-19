public class Optimizer
{
    public static int states;
    public static String best_str;
    public static double best_val = 1000;

    public static void main(String[] args)
    {
        tuple(14, "", 0);
        System.out.println("states: " + states);
        System.out.println("best: " + best_str + " " + best_val);
    }
    
    public static void tuple(int n, String s, double v)
    {
        if (n == 0) {
            states++;
            if (Math.abs(50.2 - v) < Math.abs(50.2 - best_val)) {
                best_str = s;
                best_val = v;
            }
        }
        else {
            tuple(n - 1, s + "a", v + 2.5);
            tuple(n - 1, s + "b", v + 3);
            tuple(n - 1, s + "c", v + 4);
            tuple(n - 1, s + "d", v + 1.25);
        }
    }

    public static void permutation(int n, String s)
    {
        if (n == 0) {
            states++;
            System.out.println(s);
        }
        else {
            if (! s.contains("a")) { permutation(n - 1, s + "a"); }
            if (! s.contains("b")) { permutation(n - 1, s + "b"); }
            if (! s.contains("c")) { permutation(n - 1, s + "c"); }
            if (! s.contains("d")) { permutation(n - 1, s + "d"); }
            if (! s.contains("e")) { permutation(n - 1, s + "e"); }
        }
    }

    public static void combination(int n, String s)
    {
        if (n == 0) {
            states++;
            System.out.println(s);
        }
        else {
            int l = s.length();
            if (l==0 || s.charAt(l-1) < 'a') { combination(n - 1, s + "a"); }
            if (l==0 || s.charAt(l-1) < 'b') { combination(n - 1, s + "b"); }
            if (l==0 || s.charAt(l-1) < 'c') { combination(n - 1, s + "c"); }
            if (l==0 || s.charAt(l-1) < 'd') { combination(n - 1, s + "d"); }
            if (l==0 || s.charAt(l-1) < 'e') { combination(n - 1, s + "e"); }
        }
    }

}

