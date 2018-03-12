package proyecto;

public class Main {

    public static void main(String[] args) {
        try {
            Stack<Integer> stI = new StackImpl<Integer>(10);
            stI.push(1);
            stI.push(2);
            stI.push(3);
            stI.push(4);
            stI.push(5);
            stI.push(6);
            stI.push(7);
            stI.push(8);
            stI.push(9);
            stI.push(10);
            System.out.println(stI.size());
            stI.pop();
            stI.pop();
            stI.pop();
            stI.pop();
            stI.pop();
            stI.pop();
            stI.pop();
            stI.pop();
            stI.pop();
            stI.pop();
            stI.pop();
            stI.pop();
            stI.pop();

        }
        catch (PilaLlena e)
        {
            System.out.println(e.getMessage());
        }
        catch (PilaVacia e)
        {
            System.out.println(e.getMessage());
        }
    }
}
