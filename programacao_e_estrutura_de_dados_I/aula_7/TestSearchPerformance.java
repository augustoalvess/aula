import java.util.*;

public class TestSearchPerformance
{
    public static boolean binarySearch(int[] v, int n)
	{
		int start = 0, middle, end = v.length - 1;

		while (start <= end) {
			middle = (start + end) / 2;
			if (v[middle] == n) { return true; }
			if (v[middle] < n) { start = middle + 1; } // subarray on right side
			else               {   end = middle - 1; } // subarray on left side
		}
		return false;
	}

    public static boolean sequentialSearch(int[] v, int n)
    {
        for (int i = 0; i < v.length; i++)
        {
            if (v[i] == n)
            {
                return true;
            }
        }
        return false;
    }
    
    // runs many searches and prints the total time for each case
    public static void main(String[] args)
    {
		int size = 50000;
        int[] array = new int[size];
		HashSet<Integer> hs = new HashSet<Integer>();
		for (int i = 0; i < size - 1; i++) { array[i] = i + 1; hs.add(i + 1); }

        int trials = 1000000;
        System.out.println("data size : " + size + " integers");
        System.out.println("searches  : " + trials);

		long start = System.currentTimeMillis();
        for (int i = 0; i < trials; i++)
        {
            int r = (int)(Math.random() * size) + 1;
            sequentialSearch(array, r);
        }
        System.out.println("sequential:  " + (System.currentTimeMillis() - start) / 1000.0 + " s");

		start = System.currentTimeMillis();
        for (int i = 0; i < trials; i++)
        {
            int r = (int)(Math.random() * size) + 1;
			binarySearch(array, r);
        }
        System.out.println("binary    :  " + (System.currentTimeMillis() - start) / 1000.0 + " s");

		start = System.currentTimeMillis();
        for (int i = 0; i < trials; i++)
        {
            int r = (int)(Math.random() * size) + 1;
			hs.contains(r);
        }
        System.out.println("hash      :  " + (System.currentTimeMillis() - start) / 1000.0 + " s");
    }
}

