package proyecto;

/**
 * Created by Adri on 23/09/2016.
 */
public class QueueImpl<E> implements Queue<E>{

    int escritor =-1;
    int lector =0;
    int size=0;
    int len;
    int vuelta=0;
    E[] elements;

    public QueueImpl(int len) {
        this.elements= (E[])new Object[len];
        this.len=len;
    }

    public int size() {
        return this.size;
    }

    public void enqueue (E e){

        if(this.lector ==this.len)
        {
            this.lector =0;
        }
        if(vuelta<1) {
            this.escritor++;
        }

        if(this.escritor >=this.len)
        {
            this.escritor =0;
            vuelta++;
        }
        if(this.escritor ==this.lector)
        {
            if(vuelta>0) {
                this.lector++;

            }

        }
        elements[this.escritor] = e;
        if(vuelta>0)
        {
            this.escritor++;
        }


        if(this.size!=len) {
            this.size++;
        }



    }
    public E dequeue() throws ColaVacia {

        E copia;
        copia=elements[this.lector];

        for(int x = this.lector; x<len; x++) {

            if(this.size==0)
            {
                throw new ColaVacia();
            }


            if (x == len-1) {
                if(this.escritor !=x) {

                    elements[x] = elements[0];
                    x = -1;
                    this.lector--;
                    this.escritor--;
                    if(this.escritor ==0)
                    {
                        this.escritor =len-1;
                    }
                    if(this.lector==-1)
                    {
                        this.lector=0;
                    }
                }
                else
                {
                    x=len;
                }
            }
            else {
                elements[x] = elements[x + 1];
            }
        }

        this.size--;
        return copia;

    }

}

