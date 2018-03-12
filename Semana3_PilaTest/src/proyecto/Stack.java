package proyecto;

/**
 * Created by Adri on 26/09/2016.
 */
public interface Stack<E> {

    public void push(E e) throws PilaLlena;
    public E pop() throws PilaVacia;
    public int size();
}
