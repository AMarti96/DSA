import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Adri on 18/11/2016.
 */
public class FachadaTest {
    customers ud;
     customers ud2;
    HashMap<String, customers> cache = new HashMap<String, customers>();
    @Before
    public void setUp() throws Exception {
        ud = new customers("Adri","hola");
        ud2 = new customers("Pepe","123");
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

        List<customers> lista = new ArrayList<customers>(cache.values());
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
        customers ud = new customers();
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