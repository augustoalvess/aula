import java.io.Serializable;

public class Mensagem implements Serializable {
    
    private byte[] buffer;
    private int bytes;    

    public Mensagem(byte[] buffer, int bytes) {
        this.buffer = buffer;
        this.bytes = bytes;
    }
    
    public byte[] obterBuffer() {
        return this.buffer;        
    }
    
    public int obterBytes() {
        return this.bytes;
    }
}
