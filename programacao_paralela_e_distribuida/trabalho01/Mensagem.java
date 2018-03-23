import java.io.Serializable;

public class Mensagem implements Serializable {
    
    private int id;
    private byte[] buffer;
    private int bytes;    

    public Mensagem(int id, byte[] buffer, int bytes) {
	this.id = id;
        this.buffer = buffer;
        this.bytes = bytes;
    }
    
    public byte[] obterBuffer() {
        return this.buffer;        
    }
    
    public int obterBytes() {
        return this.bytes;
    }

    public int obterId() {
        return this.id;
    }
}
