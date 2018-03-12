import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Adri on 18/11/2016.
 */
public class FachadaTest {
    Usuario ud;
     Usuario ud2;
    HashMap<String, Usuario> cache = new HashMap<String, Usuario>();
    @Before
    public void setUp() throws Exception {
        ud = new Usuario("Adri","hola");
        ud2 = new Usuario("Pepe","123");
        this.cache.put(ud.nombre,ud);
        this.cache.put(ud2.nombre,ud2);
    }

    @After
    public void tearDown() throws Exception {

        ud = null;
        ud2 = null;
    }

    @Test
    public void updateUsuario() throws Exception {

    }

    @Test
    public void returnUsuarios() throws Exception {

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

    }

    @Test
    public void returnUsuario() throws Exception {



    }

    @Test
    public void returnEtakemons() throws Exception {

    }

    @Test
    public void addEtakemon() throws Exception {
        Usuario ud = new Usuario();
        try{
            if (cache.containsKey("Pepe")){
                ud = cache.get("Pepe");
            }
        }
        catch (Exception e){
        }
        ud.addEtakemon(10);
        cache.remove("Pepe");
        cache.put("Pepe",ud);

    }

    @Test
    public void addUsuario() throws Exception {

    }

}