import java.io.*;
import java.net.*;

public class MFTPClient {

    public static void main(String[] args) throws Exception {
        MulticastSocket clientSocket = new MulticastSocket(9876);
        clientSocket.joinGroup(InetAddress.getByName("230.37.40.22"));

        byte[] receiveData = new byte[2048];
	    int lastId = 0;
        
        String nomeArquivo = "/home/augusto.silva/Downloads/saida.jpg";
        FileOutputStream arquivo = new FileOutputStream(nomeArquivo);
        boolean iniciouTransmissao = false;

        while (true) {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            System.out.println("Aguardando recebimento do arquivo...");
            clientSocket.receive(receivePacket);

            if (!iniciouTransmissao) {
                arquivo = new FileOutputStream(nomeArquivo);
                iniciouTransmissao = true;
            }
            
            ObjectInputStream streamMensagem = new ObjectInputStream(new ByteArrayInputStream(receivePacket.getData()));
            Mensagem m = (Mensagem) streamMensagem.readObject();
            String receive = new String(receivePacket.getData());
            System.out.println("Pacote " + m.obterId() + " de " + receive.length() + " bytes recebidos pelo servidor " + receivePacket.getAddress().getHostAddress());

            if (m.obterBytes() == -1) {
                arquivo.close();
                iniciouTransmissao = false;                
                System.out.println("Arquivo recebido...");                
            } else {
		        if (m.obterId() != lastId) {
                    arquivo.write(m.obterBuffer(), 0, m.obterBytes());
		            lastId = m.obterId();
		        }
            }

            DatagramPacket sendPacket = new DatagramPacket(receiveData, receiveData.length, receivePacket.getAddress(), receivePacket.getPort());
            System.out.println("Confirmando recebimento do pacote " + m.obterId() + " para o servidor " + receivePacket.getAddress().getHostAddress());
            clientSocket.send(sendPacket);
        }
    }
}
