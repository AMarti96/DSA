package proyecto;

/**
 * Created by Adri on 23/09/2016.
 */
public interface Queue<E> {
    int size();
    void enqueue(E e);
    E dequeue () throws ColaVacia;

}
