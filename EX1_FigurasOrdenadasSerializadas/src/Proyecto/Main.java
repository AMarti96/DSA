package Proyecto;
import java.io.*;
import java.util.Arrays;


public class Main {

    public static void main(String[] args) throws IOException
    {
        Figuras[] ll = new Figuras[4];
        ll[0]=new rectangulo(2,7);
        ll[1]=new cuadrado(10);
        ll[2]=new circulo(8);
        ll[3]=new triangulo(6,9);
        double suma=0;
        for (Figuras f:ll){
            suma=suma+f.area();

        }
        System.out.print(suma+"//////");
        Arrays.sort(ll);

        for(Figuras f:ll)
        {

            System.out.print(f.area()+",");

        }

        System.out.print("//////");



        File file = new File("fichero.txt");
        FileOutputStream fos = new FileOutputStream(file);
        FileInputStream fis = new FileInputStream("fichero.txt");
        ObjectOutputStream out = new ObjectOutputStream(fos);
        ObjectInputStream in = new ObjectInputStream(fis);
        //Convertir objeto a byte y meter en fichero
        out.writeObject(ll);
        //Convertir bytes y recuperar objeto
        Figuras[] llrecuperar= new Figuras[4];
        try
        {
            llrecuperar=(Figuras[])in.readObject();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        //Una vez recuperado el archivo, vamos a mostrar de nuevo las areas ordenadas
        for(Figuras f:llrecuperar)
        {

            System.out.print(f.area()+",");

        }


    }

}
