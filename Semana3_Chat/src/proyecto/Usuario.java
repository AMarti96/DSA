package proyecto;

import java.net.Socket;

/**
 * Created by Adri on 08/10/2016.
 */
public class Usuario {
    public int id;
    public String name;
    public Socket con;

    public Usuario (int id, String name, Socket con){
        this.id = id;
        this.name = name;
        this.con = con;
    }
}
