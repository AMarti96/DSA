package Parte3;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Adri on 18/11/2016.
 */
public class Fachada2 implements Etakemon2{
    final static org.apache.log4j.Logger Logger = org.apache.log4j.Logger.getLogger(Fachada2.class);
    private static Fachada2 instance = null;
    private HashMap<String, customers> cache;

    private Fachada2(){
        cache = new HashMap<String, customers>();
        Logger.info("Se crea la cahce de Usuarios");
    }

    public static Fachada2 getInstance(){ //SINGLETON
        if (instance == null) instance = new Fachada2();
        return instance;
    }

    public void updateUsuario(String nombre, String pass, List<Criatura>atrapados) throws SQLException, NoSuchMethodException, ClassNotFoundException, IllegalAccessException, InvocationTargetException {
        Logger.info("Se actualizara el usuario " + nombre);
        customers ud = new customers(nombre);
        ud.update();
    }

    public List<customers> returnUsuarios(){

        Logger.info("Devolucion de usuarios por name");
        List<customers> lista = new ArrayList<customers>(cache.values());
        List<String> nombres = new ArrayList<String>();
        for (int i = 0; i < lista.size();i++){
            nombres.add(lista.get(i).getName());
        }
        Collections.sort(nombres);
        lista.clear();
        for (int j = 0;j<nombres.size();j++){
            lista.add(cache.get(nombres.get(j)));
        }
        return lista;
    }

    public customers returnUsuario(String nombre) throws ClassNotFoundException, SQLException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {

        customers u = new customers(nombre);
        return u.select();
    }
    public List<Criatura> returnEtakemons (customers cu) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, ClassNotFoundException, SQLException {

      List<Criatura> atrapados =  cu.selectEtakmons();
        return atrapados;

    }

    public void addEtakemon (customers cu, int numero) throws ClassNotFoundException, SQLException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Logger.info("Añadimos un Etakemon a la lista de pokemon de un usuario");
        List<Criatura> atrapados;
        Criatura cr = new Criatura(numero);
        try{atrapados = cu.selectEtakmons();}
        catch (Exception e){
            atrapados = new ArrayList<Criatura>();
            atrapados.add(cr);
        }
        if (atrapados == null){
            atrapados = new ArrayList<Criatura>();
        }
        atrapados.add(cr);
        cu.actualizar(atrapados);
        cu.updateEtakemon();
    }

    public void addUsuario(String nombre, String pass){
        Logger.info("Se añade un usuario de name" + nombre + " y contraseña" + pass);
        customers ud = new customers(nombre, pass);
        cache.put(nombre,ud);

    }
}
