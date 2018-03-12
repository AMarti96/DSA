package Proyecto;

/**
 * Created by Adri on 19/09/2016.
 */
public interface Stack<E>
{
   public void push(E e) throws PilaLlena;
    public E pop() throws PilaVacia;
    public int size();

}

