import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adri on 18/11/2016.
 */
public class customers {
    public String nombre;
    public String password;
    public List<Integer> atrapados;

    public customers(){}

    public customers(String nombre, String pass) {

        this.nombre = nombre;
        this.password = pass;
        this.atrapados = new ArrayList<Integer>();
    }

    public void addEtakemon (int numero){
        atrapados.add(numero);
    }
    public void Update (String nombre, String pass, List<Integer> atrapados){
        this.nombre = nombre;
        this.password = pass;
        this.atrapados = atrapados;
    }
    public String getName() {
        return nombre;
    }

    public void setName(String name) {
        this.nombre = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
