import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adri on 18/11/2016.
 */
public class Usuario {
    public String nombre;
    public String pass;
    public List<Integer> atrapados;

    public Usuario (){}

    public Usuario (String nombre, String pass) {

        this.nombre = nombre;
        this.pass = pass;
        this.atrapados = new ArrayList<Integer>();
    }

    public void addEtakemon (int numero){
        atrapados.add(numero);
    }
    public void Update (String nombre, String pass, List<Integer> atrapados){
        this.nombre = nombre;
        this.pass = pass;
        this.atrapados = atrapados;
    }


}
