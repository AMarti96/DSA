import java.util.List;

/**
 * Created by Adri on 18/11/2016.
 */
public interface Etakemon {
    void addUsuario(String nombre, String pass);
    void updateUsuario(String nombre, String pass, List<Integer>atrapados);
    List<customers> returnUsuarios();
    customers returnUsuario(String nombre);
    void addEtakemon (String nombre,int numero);
    List<Integer> returnEtakemons (String nombre);
}
