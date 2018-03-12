package proyecto;

public class Main {

    public static void main(String[] args) throws ColaVacia {


        Queue<Integer> cola = new QueueImpl<Integer>(7);
        try {
            cola.enqueue(0);
            cola.enqueue(1);
            cola.enqueue(2);
            cola.enqueue(3);
            cola.enqueue(4);
            cola.enqueue(5);
            cola.enqueue(6);
            cola.enqueue(7);
            cola.enqueue(8);
            cola.dequeue();
            for (int i = 0; i < 7; i++) {

                System.out.println(cola.dequeue());
            }
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }
}
