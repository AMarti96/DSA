package edu.upc.dsa;

/**
 * Created by Adri on 20/12/2016.
 */
public class Etakemon {
    public String nombre;
    public String descripcion;

    public Etakemon (String nombre, String descripcion){
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    public Etakemon (){

    }
    public String getNombre(){ return nombre; }
    public void setNombre ( String nombre){ this.nombre = nombre; }
    public String getDescripcion (){ return descripcion;}
    public void setDescripcion(String descripcion){ this.descripcion = descripcion;}
}
