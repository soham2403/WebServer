import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public void run() throws IOException {
        int port = 8080;
        ServerSocket socket = new ServerSocket(port);
        socket.setSoTimeout(10000);
        while (true) {
            try {
                System.out.println("Server is running on port " + port);
                Socket acceptedConnection = socket.accept();
                System.out.println("Connection accepted from client " + acceptedConnection.getRemoteSocketAddress());

                // Set up input and output streams
                PrintWriter toClient = new PrintWriter(acceptedConnection.getOutputStream(), true);
                BufferedReader fromClient = new BufferedReader(
                        new InputStreamReader(acceptedConnection.getInputStream()));

                // Read message from client
                String clientMessage = fromClient.readLine();
                System.out.println("Received from client: " + clientMessage);

                // Send response back to client
                toClient.println("Hello from the server!");

                // close all sockets
                toClient.close();
                fromClient.close();
                acceptedConnection.close();
                socket.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        try {
            server.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
