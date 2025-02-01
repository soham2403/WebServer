import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.IOException;

public class Client {

    public void run() throws UnknownHostException, IOException {
        int port = 8080;
        InetAddress address = InetAddress.getByName("localhost");
        Socket socket = new Socket(address, port);

        // Set up input and output streams
        PrintWriter toSocket = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader fromSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        // Send message to server
        toSocket.println("Hello from client");

        // Read response from server
        String response = fromSocket.readLine();
        System.out.println("Response from server: " + response);

        // Close the socket connection
        socket.close();
        toSocket.close();
        fromSocket.close();
    }

    public static void main(String[] args) {
        try {
            Client client = new Client();
            client.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
