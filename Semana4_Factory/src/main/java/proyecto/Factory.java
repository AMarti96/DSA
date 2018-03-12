package proyecto;

import java.util.HashMap;
import org.apache.log4j.Logger;

/**
 * Created by Adri on 03/10/2016.
 */
public class Factory {
    final static Logger Logger = org.apache.log4j.Logger.getLogger(Factory.class);
    private static Factory instance = null;
    private HashMap <String, Command> cache;

    private Factory(){
        cache = new HashMap<String, Command>();
    }

    public static Factory getInstance(){ //SINGLETON
        if (instance == null) instance = new Factory();
        return instance;
    }
    public Command getCommand(String cmd) throws Exception{
        Command c = cache.get(cmd);
        if(c == null ){
            Logger.info("SIDA");
            Class cl = Class.forName("proyecto."+cmd);  //Se pone proyecto. porque las clases comandas estan dentro de proyecto
           c = (Command) cl.newInstance();
            cache.put(cmd,c);
        }
        return c;
    }
}

