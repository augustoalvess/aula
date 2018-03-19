import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class MFTPServer {

    public static void main(String[] args) throws Exception {
        FileInputStream file = new FileInputStream("/home/augusto/Downloads/imagem.jpg");
        DatagramSocket serverSocket = new DatagramSocket();
        serverSocket.setSoTimeout(5);

        byte[] buffer = new byte[1024];
        int bytes = 0;
        while ((bytes = file.read(buffer)) != -1) {
            Mensagem m = new Mensagem(buffer, bytes);
            MFTPServer.enviarMensagem(serverSocket, m, true);
        }
        Mensagem m = new Mensagem(buffer, -1);
        MFTPServer.enviarMensagem(serverSocket, m, true);

        file.close();
        serverSocket.close();
    }

    public static void enviarMensagem(DatagramSocket serverSocket, Mensagem m, boolean retransmitir) {
        byte[] sendData = new byte[2048];
        byte[] receiveData = new byte[1024];

        try {        
            ByteArrayOutputStream streamBytes = new ByteArrayOutputStream();
            ObjectOutputStream streamMensagem = new ObjectOutputStream(streamBytes);
            streamMensagem.writeObject(m);
            streamMensagem.close();
            sendData = streamBytes.toByteArray();
            
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("230.0.0.1"), 9876);
            serverSocket.send(sendPacket);

            // Política Stop-and-Wait
            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);
                String receive = new String(receivePacket.getData());
                System.out.println("Pacote de " + receive.length() + " bytes enviados para o client " + receivePacket.getAddress().getHostAddress());
                break;
            }
        } catch (InterruptedIOException e) {
            System.out.print("Tempo de transmissão excedido");
            if (e.getMessage() != "Receive timed out") { // Caso a resposta do cliente tenha excedido o timeout, somente notifica.
                System.out.println("..." + e.getMessage());
            } else { // Caso tenha sito o envio que tenha ocasionado timeout, executa o reenvio do pacote.
                if (retransmitir) {
                    System.out.println("...reenviando pacote para o client");
                    MFTPServer.enviarMensagem(serverSocket, m, false);
                } else {
                    System.out.println();
                }
            }
        }catch(IOException ioe){
            System.out.println("Falha na transmissão de rede");
        }
    }
}
