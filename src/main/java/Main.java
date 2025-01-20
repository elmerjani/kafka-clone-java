import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
  public static void main(String[] args){
    // You can use print statements as follows for debugging, they'll be visible when running tests.
    System.err.println("Logs from your program will appear here!");

    // Uncomment this block to pass the first stage
    
    ServerSocket serverSocket = null;
    Socket clientSocket = null;
    int port = 9092;
    try {
      serverSocket = new ServerSocket(port);
      // Since the tester restarts your program quite often, setting SO_REUSEADDR
      // ensures that we don't run into 'Address already in use' errors
      serverSocket.setReuseAddress(true);
      // Wait for connection from client.
      clientSocket = serverSocket.accept();
      OutputStream out = clientSocket.getOutputStream();
      byte[] message = new byte[8];
            // message_size (4 bytes)
            message[0] = 0x00;
            message[1] = 0x00;
            message[2] = 0x00;
            message[3] = 0x00; // 0 (big-endian)
            // correlation_id (4 bytes)
            message[4] = 0x00;
            message[5] = 0x00;
            message[6] = 0x00;
            message[7] = 0x07;
      out.write(message);
      
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
