package edu.upc.dsa;

import java.util.List;

/**
 * Created by Adri on 20/12/2016.
 */
public class Usuari {
    public String nom;
    public String contra;
    public List<Etakemon> lista;

    public String getNom(){
        return nom;
    }
    public void setNom (String nom){ this.nom = nom; }
    public void setLista(List<Etakemon> lista){ this.lista = lista;}
    public List<Etakemon> getLista (){ return lista;}
    public String getContra(){return contra;}
    public void setContra(String contra){ this.contra = contra; }
}
