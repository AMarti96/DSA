package Proyecto;

import java.util.ArrayList;

/**
 * Created by Adri on 19/09/2016.
 */

public class StackImpl<E> implements Stack<E>
{
    int size=0, len = 0;
    E[] elems;


   public StackImpl(int len)
   {
       this.elems = (E[])new Object[len];
       this.len = len;
   }

    public int size()
    {
        return this.size;
    }
    public void push(E e) throws PilaLlena
    {
        if (this.size >= len)
        {
            throw new PilaLlena();
        }

            this.elems[size]= e;
            this.size ++;


    }
    public E pop () throws PilaVacia
    {
        size--;
        if (this.size <= 0)
        {
            throw new PilaVacia();
        }
            return this.elems[size];

    }
}
