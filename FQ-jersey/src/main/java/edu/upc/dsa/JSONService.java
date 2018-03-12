package edu.upc.dsa;

import com.google.gson.Gson;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.lang.reflect.InvocationTargetException;
import java.security.Signature;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Path("/json")
public class JSONService {


    public JSONService() throws SQLException, NoSuchMethodException, ClassNotFoundException, IllegalAccessException, InvocationTargetException {


    }

    public int comprobar (Usuari u){
        int sol = 1;
        for (int i = 0; i<Singleton.getInstance().getList().size();i++){
            if (Singleton.getInstance().getList().get(i).getNom().equals(u.getNom())&&Singleton.getInstance().getList().get(i).getContra().equals(u.getContra())){
                sol = 0;
                i = Singleton.getInstance().getList().size();
            }
            else{ sol = 1;}
        }
        return sol;
    }

    public int foundUserEtakemon(String u){
        for(int i = 0;i<Singleton.getInstance().getList().size();i++){
            if (Singleton.getInstance().getList().get(i).getNom().equals(u)){
                return i;
            }
        }
        return -1;
    }

    @GET
    @Path("/get/{name}") //Devolvemos la lista de etakemons de un usuario
    @Produces(MediaType.APPLICATION_JSON)
    public String getEtakemonInJSON(@PathParam("name") String name) {

        if (foundUserEtakemon(name) != -1){
            String json = new Gson().toJson(Singleton.getInstance().getList().get(foundUserEtakemon(name)).getLista());
            return json;
        }
        return "ERROR";
    }

    @POST
    @Path("/new/{nombre}") //añadir un etakemon a un usuario
    @Consumes(MediaType.APPLICATION_JSON)
    public String newTrack(Etakemon etak, @PathParam("nombre") String name) {

        if (foundUserEtakemon(name) != -1) {

            Singleton.getInstance().getList().get(foundUserEtakemon(name)).getLista().add(etak);
            return "200";
        }
        return "500";
    }
    @POST
    @Path("/login") //Iniciar sesion
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String iniciarSesion (Usuari user){
        int i = comprobar (user);
        if (i==0) {
            return "200";}
        else{ return "500"; }
    }

    @POST
    @Path("/register") //Registrarse
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String registro (Usuari user){

         Singleton singleTON = Singleton.getInstance();

         int i = comprobar (user);
         if (i==0) { return "500";}
         else{

            List<Etakemon> lista = new ArrayList<>();
            user.setLista(lista);
            singleTON.getList().add(user);

            return "200"; }
    }


}