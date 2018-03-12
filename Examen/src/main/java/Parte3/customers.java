package Parte3;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adri on 16/11/2016.
 */
public class customers extends Dao{

    public String name;
    public String password;
    public List<Criatura> pokemon;

    public customers(){}

    public customers(String nombre, String pass) {

        this.name = nombre;
        this.password = pass;
        this.pokemon = new ArrayList<Criatura>();
    }
    public customers(String nombre){this.name = nombre;}
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Criatura> getPokemon(){return this.pokemon;}
    public void actualizar(List<Criatura> lista){this.pokemon = lista;}
    public void setPokemon (Criatura cr){
        pokemon.add(cr);
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}

