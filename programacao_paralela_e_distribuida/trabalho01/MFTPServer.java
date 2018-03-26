import java.io.*;
import java.net.*;
import java.util.*;

public class MFTPServer {

    public static void main(String[] args) throws Exception {
        FileInputStream file = new FileInputStream("/home/augusto.silva/Downloads/imagem.jpg");
        DatagramSocket serverSocket = new DatagramSocket();
        serverSocket.setSoTimeout(100);

        byte[] buffer = new byte[1024];
        int bytes = 0;
	    int id = 1;
        while ((bytes = file.read(buffer)) != -1) {
            Mensagem m = new Mensagem(id, buffer, bytes);
            MFTPServer.enviarMensagem(serverSocket, m);
	        id++;
        }
        Mensagem m = new Mensagem(id, buffer, -1);
        MFTPServer.enviarMensagem(serverSocket, m);

        file.close();
        serverSocket.close();
    }

    public static void enviarMensagem(DatagramSocket serverSocket, Mensagem m) {
	    DatagramSocket originalServerSocket = serverSocket;
        byte[] sendData = new byte[2048];
        byte[] receiveData = new byte[1024];

	    boolean enviado = false;
        while (!enviado) {
            try {
                //Thread.currentThread().sleep(1000);
                ByteArrayOutputStream streamBytes = new ByteArrayOutputStream();
                ObjectOutputStream streamMensagem = new ObjectOutputStream(streamBytes);
                streamMensagem.writeObject(m);
                streamMensagem.close();
                sendData = streamBytes.toByteArray();
            
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("230.37.40.22"), 9876);
                serverSocket.send(sendPacket);

                HashMap<String, String> clients = new HashMap<String, String>();
                boolean recebido = false;
                while (!recebido) {
                    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    System.out.println("Aguardando confirmação de recebimento do pacote " + m.obterId() + "...");
                    serverSocket.receive(receivePacket);

                    String receive = new String(receivePacket.getData());
                    System.out.println("Pacote " + m.obterId() + " de " + receive.length() + " bytes recebidos pelo client " + receivePacket.getAddress().getHostAddress());

                    // Adiciona o host do client que recebeu o pacote na lista de clients confirmados.
                    clients.put(receivePacket.getAddress().getHostAddress(), receive.length() + "");
                    if (clients.size() < 2) {
                        continue;
                    }

                    recebido = true;
                }

		        enviado = true;
            } catch (Exception e) {
                System.out.println("Tempo de execução excedido...reenviando pacote para o client");
	            enviado = false;	
	        }
	    }
    }
}
