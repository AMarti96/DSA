package edu.upc.dsa;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adri on 20/12/2016.
 */
public class Singleton {
    private static Singleton ourInstance = new Singleton();
    public static List<Usuari> list;
    public static Singleton getInstance() {
        if (ourInstance==null) {
            ourInstance = new Singleton();
        }
        if (list == null){
            list = new ArrayList<>();
        }
        return ourInstance;
    }
    private Singleton() {
    }
    public List<Usuari> getList(){
        return this.list;
    }
}