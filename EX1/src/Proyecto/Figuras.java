package Proyecto;

/**
 * Created by Adri on 14/09/2016.
 */
public interface Figuras
{
    public double area();
}
class rectangulo implements Figuras{
    double l1, l2;
    public rectangulo(double l1, double l2) {
        this.l1=l1;
        this.l2=l2;
        
    }
    public double area(){
        return this.l1*this.l2;
    }
}
class cuadrado extends rectangulo{
    public cuadrado(double l){
        super (l,l);
    }
}
class triangulo implements Figuras{
    double b,h;
    public triangulo(double b, double h){
        this.b=b;
        this.h=h;
    }
    public double area(){
        return (this.b*this.h/2);
    }
}
class circulo implements Figuras{
    double r;
    public circulo(double r){
        this.r=r;
    }

    @Override
    public double area() {
        return Math.PI*this.r*this.r;
    }
}
