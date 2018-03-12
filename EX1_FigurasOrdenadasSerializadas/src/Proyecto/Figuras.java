package Proyecto;
import java.io.*;
import java.lang.Comparable;

/**
 * Created by Turpitude on 14/09/2016.
 */

abstract class Figuras implements Comparable<Figuras>,Serializable
{

    public abstract double area();


    public int compareTo(Figuras f)
    {
        if(this.area()<f.area())
        {
            return -1;
        }
        if(this.area()>f.area())
        {
            return 1;
        }
        return 0;
    }



}
class rectangulo extends Figuras
{
    double l1, l2;
    public rectangulo(double l1, double l2)
    {
        this.l1 = l1;
        this.l2=l2;
    }
    public double area()
    {

        return this.l1*this.l2;
    }

    @Override
    public int compareTo(Figuras f)
    {
        return super.compareTo(f);
    }
}
class cuadrado extends rectangulo
{
    public cuadrado(double l){
        super (l,l);
    }
}
class triangulo extends Figuras
{
    double b,h;
    public triangulo(double b, double h)
    {
        this.b=b;
        this.h=h;
    }
    public double area()
    {

        return (this.b*this.h/2);
    }

    @Override
    public int compareTo(Figuras f)
    {
        return super.compareTo(f);
    }
}
class circulo extends Figuras
{
    double radio;
    public circulo(double radio)
    {
        this.radio = radio;
    }


    public double area()
    {

        return Math.PI*this.radio *this.radio;
    }

    @Override
    public int compareTo(Figuras f)
    {
        return super.compareTo(f);
    }
}
