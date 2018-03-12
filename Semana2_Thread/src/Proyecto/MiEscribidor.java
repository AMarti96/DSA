package Proyecto;

/**
 * Created by Adri on 21/09/2016.
 */
public class MiEscribidor extends Thread {

    int numero = 0;

    public MiEscribidor (int i) {
        this.numero = i;
    }
    public void run ()
    {
        for (int i=0;i<10;i++){
            System.out.println("Soy el thread "+numero + " i mi contador es "+i);
        }
    }


}
