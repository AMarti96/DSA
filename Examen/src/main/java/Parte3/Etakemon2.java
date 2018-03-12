package Parte3;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Adri on 18/11/2016.
 */
public interface Etakemon2 {
    void addUsuario(String nombre, String pass);
    void updateUsuario(String nombre, String pass, List<Criatura> atrapados) throws SQLException, NoSuchMethodException, ClassNotFoundException, IllegalAccessException, InvocationTargetException;
    List<customers> returnUsuarios();
    customers returnUsuario(String nombre) throws ClassNotFoundException, SQLException, InvocationTargetException, IllegalAccessException, NoSuchMethodException;
    void addEtakemon(customers cu, int numero) throws ClassNotFoundException, SQLException, InvocationTargetException, IllegalAccessException, NoSuchMethodException;
    List<Criatura> returnEtakemons(customers cu) throws ClassNotFoundException, InvocationTargetException, SQLException, IllegalAccessException, NoSuchMethodException;
}
