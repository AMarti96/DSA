package proyecto;

import static org.junit.Assert.*;

/**
 * Created by Adri on 26/09/2016.
 */
public class StackImplTest {

    StackImpl<Integer> stI;
    @org.junit.Before
    public void setUp() throws Exception {
        stI = new StackImpl<Integer>(10);
        stI.push(1);
        stI.push(2);
    }

    @org.junit.After
    public void tearDown() throws Exception {
         this.stI=null;
    }

    @org.junit.Test
    public void size() throws Exception {
        assertNotEquals(3,stI.size);
    }

    @org.junit.Test (expected = PilaLlena.class)
    public void push() throws Exception {
        stI.push(4);
        stI.push(4);
        stI.push(4);
        stI.push(4);
        stI.push(4);
        stI.push(4);
        stI.push(4);
        stI.push(4);
        stI.push(4);
        stI.push(4);
    }

    @org.junit.Test (expected = PilaVacia.class)
    public void pop() throws Exception {
        stI.pop();
        stI.pop();
        stI.pop();
    }

}