import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MFTPClient {

    public static void main(String[] args) throws Exception {
        MulticastSocket clientSocket = new MulticastSocket(9876);
        clientSocket.joinGroup(InetAddress.getByName("230.0.0.1"));

        byte[] receiveData = new byte[2048];
        byte[] sendData = new byte[1024];
        
        String nomeArquivo = "/home/augusto/Downloads/saida.jpg";
        FileOutputStream arquivo = new FileOutputStream(nomeArquivo);
        boolean iniciouTransmissao = false;

        while (true) {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            System.out.println("aguardando recebimento do arquivo...");
            clientSocket.receive(receivePacket);

            if (!iniciouTransmissao) {
                arquivo = new FileOutputStream(nomeArquivo);
                iniciouTransmissao = true;
            }
            
            ObjectInputStream streamMensagem = new ObjectInputStream(new ByteArrayInputStream(receivePacket.getData()));
            Mensagem m = (Mensagem) streamMensagem.readObject();
            if (m.obterBytes() == -1) {
                arquivo.close();
                System.out.println("Arquivo recebido...");
                iniciouTransmissao = false;
                continue;
            }
            arquivo.write(m.obterBuffer(), 0, m.obterBytes());
            
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, 
                    receivePacket.getAddress(), receivePacket.getPort());

            System.out.println("respondendo mensagem para o servidor... " + receivePacket.getAddress().getHostAddress());
            clientSocket.send(sendPacket);
        }
    }
}
