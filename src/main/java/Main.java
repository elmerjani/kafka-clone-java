import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;

public class Main {
  public static void main(String[] args){
    System.err.println("Logs from your program will appear here!");
    ServerSocket serverSocket = null;
    Socket clientSocket = null;
    int port = 9092;
    try {
      serverSocket = new ServerSocket(port);
      serverSocket.setReuseAddress(true);
      // Wait for connection from client.
      clientSocket = serverSocket.accept();
      InputStream input = clientSocket.getInputStream();
      ByteBuffer inputBuffer = ByteBuffer.wrap(input.readAllBytes());
      OutputStream output = clientSocket.getOutputStream();
      ByteBuffer outputBuffer = ByteBuffer.allocate(8);
      outputBuffer.putInt(4,inputBuffer.getInt(8));
      output.write(outputBuffer.array());
      
    } catch (IOException e) {
      System.out.println("IOException: " + e.getMessage());
    } finally {
      try {
        if (clientSocket != null) {
          clientSocket.close();
        }
      } catch (IOException e) {
        System.out.println("IOException: " + e.getMessage());
      }
    }
  }
}
