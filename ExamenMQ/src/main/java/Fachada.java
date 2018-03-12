
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Adri on 18/11/2016.
 */
public class Fachada implements Etakemon {
    final static org.apache.log4j.Logger Logger = org.apache.log4j.Logger.getLogger(Fachada.class);
    private static Fachada instance = null;
    private HashMap<String, Usuario> cache;

    private Fachada(){
        cache = new HashMap<String, Usuario>();
        Logger.info("Se crea la cahce de Usuarios");
    }

    public static Fachada getInstance(){ //SINGLETON
        if (instance == null) instance = new Fachada();
        return instance;
    }

    public void updateUsuario(String nombre, String pass, List<Integer>atrapados){
        Logger.info("Se actualizara el usuario " + nombre);
        Usuario ud = new Usuario();
        try{
            if (cache.containsKey(nombre)){
                ud = cache.get(nombre);
            }
        }
        catch (Exception e){
            Logger.error("El usuario no esta registrado");
        }
        ud.Update(nombre, pass, atrapados);
        cache.remove(nombre);
        cache.put(nombre,ud);
    }

    public List<Usuario> returnUsuarios(){
        Logger.info("Devolucion de usuarios por nombre");
        List<Usuario> lista = new ArrayList<Usuario>(cache.values());
        List<String> nombres = new ArrayList<String>();
        for (int i = 0; i < lista.size();i++){
            nombres.add(lista.get(i).nombre);
        }
        Collections.sort(nombres);
        lista.clear();
        for (int j = 0;j<nombres.size();j++){
            lista.add(cache.get(nombres.get(j)));
        }
        return lista;
    }

    public Usuario returnUsuario(String nombre){

        return cache.get(nombre);
    }
    public List<Integer> returnEtakemons (String nombre) {
        Usuario ud = new Usuario();
        ud = cache.get(nombre);
        return ud.atrapados;

    }

    public void addEtakemon (String nombre,int numero)
    {
        Logger.info("Añadimos un Etakemon a la lista de atrapados de un usuario");
        Usuario ud = new Usuario();
        try{
            if (cache.containsKey(nombre)){
                ud = cache.get(nombre);
            }
        }
        catch (Exception e){
            Logger.error("El usuario no esta registrado");
        }
        ud.addEtakemon(numero);
        cache.remove(nombre);
        cache.put(nombre,ud);

    }

    public void addUsuario(String nombre, String pass){
        Logger.info("Se añade un usuario de nombre" + nombre + " y contraseña" + pass);
        Usuario ud = new Usuario (nombre, pass);
        cache.put(nombre,ud);

    }
}
