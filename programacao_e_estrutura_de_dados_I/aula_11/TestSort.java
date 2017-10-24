import java.util.*;

public class TestSort {
	public static void main(String[] args) {
		ArrayList<Integer> a = new ArrayList<Integer>();
		a.add(4); a.add(1); a.add(5); a.add(3); a.add(2); 
		Collections.sort(a);
		System.out.println(a.get(0));
	}
}