import java.util.*;

public class TestSetMap
{
    public static void main(String[] args) throws Exception
    {
        TreeSet<Integer> ts = new TreeSet<Integer>(); // Para percorrer o dicionário de ordem ordenada, caso contrário utilizar o HashSet
        
        ts.add(37);
        ts.add(68);
        ts.add(14);
        for (int e : ts)
        {
            System.out.println(e);
        }

        HashMap<Integer,String> hm = new HashMap<Integer,String>();
        
        hm.put(37, "yesterday");
        hm.put(68, "today");
        hm.put(14, "tomorrow");
        for (int k : hm.keySet())
        {
            System.out.println(hm.get(k));
        }
    }
}
