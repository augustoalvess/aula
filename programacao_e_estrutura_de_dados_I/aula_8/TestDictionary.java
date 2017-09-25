/**
 * @author augusto.silva
 */
public class TestDictionary {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MyDictionary dict = new MyDictionary();
        dict.put("abc", 123);
        dict.put("def", 456);
        dict.put("abc", 789);
        System.out.println("[abc] " + dict.get("abc"));
        System.out.println("[def] " + dict.get("def"));
    }
    
}
