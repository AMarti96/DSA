package proyecto;
import java.io.*;
import java.net.*;

/**
 * Created by Adri on 26/09/2016.
 */
public class Cliente {

    public static void main(String[] args)throws  IOException {
        String hostIP = "127.0.0.1";
        int portNumber = 1234;

        try (
                Socket echoSocket = new Socket(hostIP, portNumber);
                PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);

                BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));


                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))

        )

        {
            String userInput;
            userInput = stdIn.readLine();
            out.println(userInput);
            System.out.println("Servidor: " + in.readLine());
            System.exit(1);

        } catch (UnknownHostException e) {
            System.err.println("No se conoce IP : " + hostIP);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Imposible conectarse a : " + hostIP);

            System.exit(1);
        }
    }
}
