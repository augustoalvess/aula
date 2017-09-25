import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author augusto.silva
 */
public class MyDictionary {
    
    private class Item {
        public String key;
        public String value;
        
        public Item(String k, String v) {
            this.key = k;
            this.value = v;
        }

        public String toString() {
            return this.key + ": " + this.value;
        }
    }
    
    private ArrayList<Item> array;

    public MyDictionary() {
        this.array = new ArrayList<Item>();
    }
    
    public void put(String k, String v) {
        for (int i = 0; i < this.array.size(); i ++) {
            if (this.array.get(i).key.equals(k)) {
                this.array.get(i).value = v;
                return;
            }
        }
        this.array.add(new Item(k, v));
    }
    
    public String get(String k) {
        for (int i = 0; i < this.array.size(); i ++) {
            if (this.array.get(i).key.equals(k)) {
                return this.array.get(i).value;
            }
        }
        return "0";
    }

    public int size() {
        return this.array.size();
    }

    public String toString() {
        return this.array.toString();
    }

    public void sort() {
        // Sorting
        Collections.sort(this.array, new Comparator<Item>() {
            @Override
            public int compare(Item item1, Item item2) {
                return item1.key.compareTo(item2.key);
            }
        });
    }
}
