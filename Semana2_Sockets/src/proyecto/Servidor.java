package proyecto;
import java.net.*;
import java.io.*;

/**
 * Created by Adri on 26/09/2016.
 */
public class Servidor {

    public static void main(String[] args) throws IOException {



        int portNumber = 1234;

        try (
                ServerSocket serverSocket =new ServerSocket(portNumber);

                Socket clientSocket = serverSocket.accept();
                PrintWriter out =new PrintWriter(clientSocket.getOutputStream(), true);

                BufferedReader in = new BufferedReader( new InputStreamReader(clientSocket.getInputStream()));

        ) {
            String inputLine;
            inputLine = in.readLine();
            inputLine=inputLine.toUpperCase();
            out.println(inputLine);
            System.exit(1);


        } catch (IOException e) {
            System.out.println("Error con el puerto "+ portNumber);

            System.out.println(e.getMessage());
        }
    }
}
