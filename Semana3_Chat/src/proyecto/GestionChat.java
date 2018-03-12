package proyecto;

import javax.print.DocFlavor;
import java.net.Socket;
import java.io.*;
import java.util.List;
import java.util.Objects;

/**
 * Created by Adri on 28/09/2016.
 */
public class GestionChat extends Thread {

    Socket cliente;
    String inputLine;
    PrintWriter out;
    BufferedReader in;

    public GestionChat(Socket cliente) throws IOException {

        this.cliente = cliente;

    }

    public void run() {

        try {
            int id = 0;
            Usuario miCliente = null;
            in = new BufferedReader(new InputStreamReader(this.cliente.getInputStream()));
            out = new PrintWriter(cliente.getOutputStream(), true);
            out.println("Sala 0: " + Salas.sala1.size() +" personas  "+ " Sala 1: " + Salas.sala2.size()+" personas  " + " Sala 2: " + Salas.sala3.size()+" personas  " + " Sala 3: " + Salas.sala4.size()+" personas");

            while ((inputLine = in.readLine()) != null) {
                String[] mensaje = inputLine.split(" ");
                int sala = Integer.parseInt(mensaje[1]); //Transformar la parte del mensaje a int
                if (Objects.equals("CONNECT",mensaje[0])) { //Conectarse a una sala
                    List<Usuario> usuariosala = null;

                    if (sala == 0) {//Sacar la lista de usuarios de la sala
                        usuariosala = Salas.sala1;
                    }
                    if (sala == 1) { //Sacar la lista de usuarios de la sala
                        usuariosala = Salas.sala2;
                    }
                    if (sala == 2) {
                        usuariosala = Salas.sala3;
                    }
                    if (sala == 3) {
                        usuariosala = Salas.sala4;
                    }
                    int leng = usuariosala.size();

                    for (int j = 0; j <= leng; j++) {
                        if (j == 4) {
                            out.println("ERROR Sala " + sala + " llena");
                        } else {
                            try {
                                if (Objects.nonNull(usuariosala.get(j))) { //Condicion que dice que haga el if si lo que devuelde no es nulo
                                    miCliente = new Usuario(j, mensaje[2], cliente);
                                    usuariosala.add(miCliente);
                                    out.println("OK Conectado a la sala " + sala);
                                    j = leng;
                                }
                            } catch (Exception e) { //Este catch esta porque la primera vez que se añada a alguien se devolvera null en el if
                                j = leng;
                                miCliente = new Usuario(id, mensaje[2], cliente);
                                usuariosala.add(miCliente);
                                out.println("OK Connectado a la sala " + sala);
                            }
                        }

                    }
                    if (sala == 0) { //Volver a meter la lista de usuarios en la sala que toca
                        Salas.sala1 = usuariosala;
                    }
                    if (sala == 1) {
                        Salas.sala2 = usuariosala;
                    }
                    if (sala == 2) {
                        Salas.sala3 = usuariosala;
                    }
                    if (sala == 3) {
                        Salas.sala4 = usuariosala;
                    }
                }

                if (Objects.equals("TEXT",mensaje[0])) { //Procesar lo que escribe cierto cliente de una sala
                    String input = null;

                    String[] msj = inputLine.split("TEXT " + sala+ " "); //Coges del mensaje original lo que va despues de "TEXT sala"
                    input = msj[1];

                    int leng = 0;

                    if (Objects.equals("EXIT",mensaje[2])) {
                        if (sala == 0) { //Sacar a alguien de la sala
                            leng = Salas.sala1.size();
                            for (int j = 0; j < leng; j++) {
                                if (Objects.equals(Salas.sala1.get(j).name, mensaje[3])) {
                                    Salas.sala1.remove(j);
                                    j = leng;
                                }
                            }
                        }
                        if (sala == 1) { //Sacar a alguien de la sala
                            leng = Salas.sala2.size();
                            for (int j = 0; j < leng; j++) {
                                if (Objects.equals(Salas.sala2.get(j).name, mensaje[3])) {
                                    Salas.sala2.remove(j);
                                    j = leng;
                                }
                            }
                        }
                        if (sala == 2) { //Sacar a alguien de la sala
                            leng = Salas.sala3.size();
                            for (int j = 0; j < leng; j++) {
                                if (Objects.equals(Salas.sala3.get(j).name, mensaje[3])) {
                                    Salas.sala3.remove(j);
                                    j = leng;
                                }
                            }
                        }
                        if (sala == 3) { //Sacar a alguien de la sala
                            leng = Salas.sala4.size();
                            for (int j = 0; j < leng; j++) {
                                if (Objects.equals(Salas.sala4.get(j).name, mensaje[3])) {
                                    Salas.sala4.remove(j);
                                    j = leng;
                                }
                            }
                        }

                    }
                    List<Usuario> misala = null;
                    if (sala == 0) {
                        misala = Salas.sala1;
                    }
                    if (sala == 1) {
                        misala = Salas.sala2;
                    }
                    if (sala == 2) {
                        misala = Salas.sala3;
                    }
                    if (sala == 3) {
                        misala = Salas.sala4;
                    }

                    for (int i = 0; i < misala.size(); i++) { //Se envia el mensaje a todos los de la sala
                        out = new PrintWriter(misala.get(i).con.getOutputStream(), true);
                        if (misala.get(i).con != this.cliente) { //Menos al que lo ha enviado
                            if (Objects.equals("EXIT",mensaje[2])){
                                out.println(mensaje[3] + " se ha desconeconectado!!");
                            }
                            else {
                                out.println(miCliente.name+": "+input);
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
