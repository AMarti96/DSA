package Proyecto;

import java.util.Arrays;
import java.util.Collections;

public class Main
{

    public static void main(String[] args)
    {
        Figuras[] ll = new Figuras[4];
        ll[0]=new rectangulo(2,7);
        ll[1]=new cuadrado(10);
        ll[2]=new circulo(8);
        ll[3]=new triangulo(6,9);
        double suma=0;
        for (Figuras f:ll)
        {
            suma = suma+f.area();
        }
        System.out.print(suma);

        System.out.print(suma+"//////");
        Arrays.sort(ll);

        for(Figuras f:ll)
        {
            System.out.print(f.area()+",");
        }
    }
}
