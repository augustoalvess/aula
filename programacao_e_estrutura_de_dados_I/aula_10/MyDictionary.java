import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author augusto.silva
 * Prova, vai cair as buscas sequenciais, binárias e hash
 *  Complexidade e dicionário (key e value). Fazer cola A4
 */
public class MyDictionary {
    
    private class Item {
        public String key;
        public Object value;
        
        public Item(String k, Object v) {
            this.key = k;
            this.value = v;
        }

        public String toString() {
            return this.key + ": " + this.value.toString();
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
        return null;
    }

    public int size() {
        return this.array.size();
    }

    public String toString() {
        return this.array.toString();
    }
}
