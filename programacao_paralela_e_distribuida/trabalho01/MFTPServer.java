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
        serverSocket.setSoTimeout(5000);
        InetAddress IPAddress = InetAddress.getByName("230.0.0.1");

        try {
            byte[] sendData = new byte[2048];
            byte[] receiveData = new byte[1024];
            byte[] buffer = new byte[1024];
            int bytes = 0;

            while ((bytes = file.read(buffer)) != -1) {
                Mensagem m = new Mensagem(buffer, bytes);
                ByteArrayOutputStream streamBytes = new ByteArrayOutputStream();
                ObjectOutputStream streamMensagem = new ObjectOutputStream(streamBytes);
                streamMensagem.writeObject(m);
                streamMensagem.close();
                sendData = streamBytes.toByteArray();
                
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
                serverSocket.send(sendPacket);

                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);

                String receive = new String(receivePacket.getData());
                if (receive.length() == bytes) {
                    System.out.println("Pacote recebido pelo client: " + receive.length());
                }
            }

            // Envia uma última mensagem sinalizando o fim do arquivo.
            Mensagem m = new Mensagem(buffer, -1);
            ByteArrayOutputStream streamBytes = new ByteArrayOutputStream();
            ObjectOutputStream streamMensagem = new ObjectOutputStream(streamBytes);
            streamMensagem.writeObject(m);
            streamMensagem.close();
            sendData = streamBytes.toByteArray();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
            serverSocket.send(sendPacket);
            file.close();
            serverSocket.close();

        } catch (InterruptedIOException e) {
            System.out.println("Tempo de transmissão estourado");
        }catch(IOException ioe){
            System.out.println("Falha na transmissão de rede");
        }
    }

}
