import java.io.*;
import java.net.*;

public class MFTPServer {

    public static void main(String[] args) throws Exception {
        FileInputStream file = new FileInputStream("/home/augusto.silva/Downloads/imagem.jpg");
        DatagramSocket serverSocket = new DatagramSocket();
        serverSocket.setSoTimeout(5);

        byte[] buffer = new byte[1024];
        int bytes = 0;
	int id = 1;
        while ((bytes = file.read(buffer)) != -1) {
            Mensagem m = new Mensagem(id, buffer, bytes);
            MFTPServer.enviarMensagem(serverSocket, m);
	    id++;
	    Thread.currentThread().sleep(1000);
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
                ByteArrayOutputStream streamBytes = new ByteArrayOutputStream();
                ObjectOutputStream streamMensagem = new ObjectOutputStream(streamBytes);
                streamMensagem.writeObject(m);
                streamMensagem.close();
                sendData = streamBytes.toByteArray();
            
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("230.37.40.22"), 9876);
                serverSocket.send(sendPacket);

	        for (int x = 1; x <= 2; x ++)  { // Espera a resposta de 2 clients
		    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    serverSocket.receive(receivePacket);
                    String receive = new String(receivePacket.getData());
                    System.out.println("Pacote de " + receive.length() + " bytes enviados para o client " + receivePacket.getAddress().getHostAddress());	         
	        }
		enviado = true;	
            } catch (Exception e) {
                System.out.println("Tempo de execução excedido...reenviando pacote para o client");
	        enviado = false;	
	    }
	}
    }
}
