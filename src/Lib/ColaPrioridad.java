package Lib;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ColaPrioridad<Llave extends Comparable<Llave>> implements Iterable<Integer> {
    private int maxN;        // numero maximo de elementos dea cola
    private int n;           // numero de elementos de la cola
    private int[] pq;        // binary heap con indexacion
    private int[] qp;
    private Llave[] llave;      // keys[i] = prioridad de i

    public ColaPrioridad(int maxN) {
        if (maxN < 0) throw new IllegalArgumentException();
        this.maxN = maxN;
        n = 0;
        llave = (Llave[]) new Comparable[maxN + 1];
        pq   = new int[maxN + 1];
        qp   = new int[maxN + 1];
        for (int i = 0; i <= maxN; i++)
            qp[i] = -1;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public boolean contains(int i) {
        if (i < 0 || i >= maxN) throw new IllegalArgumentException();
        return qp[i] != -1;
    }

    public int get_tam() {
        return n;
    }

    public void insertar(int i, Llave key) {
        if (i < 0 || i >= maxN) throw new IllegalArgumentException();
        if (contains(i)) throw new IllegalArgumentException("Llave existente");
        n++;
        qp[i] = n;
        pq[n] = i;
        llave[i] = key;
        swim(n);
    }


    public int minIndex() {
        if (n == 0) throw new NoSuchElementException("Cola vacia");
        return pq[1];
    }

    public Llave minKey() {
        if (n == 0) throw new NoSuchElementException("Cola vacia");
        return llave[pq[1]];
    }

    public int delMin() {
        if (n == 0) throw new NoSuchElementException("Cola vacia");
        int min = pq[1];
        intercambiar(1, n--);
        sink(1);
        assert min == pq[n+1];
        qp[min] = -1;        // eliminar
        llave[min] = null;    // ayudar al recolector
        pq[n+1] = -1;
        return min;
    }

    public Llave keyOf(int i) {
        if (i < 0 || i >= maxN) throw new IllegalArgumentException();
        if (!contains(i)) throw new NoSuchElementException("error de indice");
        else return llave[i];
    }

    public void cambiar_llave(int i, Llave key) {
        if (i < 0 || i >= maxN) throw new IllegalArgumentException();
        if (!contains(i)) throw new NoSuchElementException("error de indice");
        llave[i] = key;
        swim(qp[i]);
        sink(qp[i]);
    }

    @Deprecated
    public void cambiar(int i, Llave key) {
        cambiar_llave(i, key);
    }


    public void decrementar_llave(int i, Llave key) {
        if (i < 0 || i >= maxN) throw new IllegalArgumentException();
        if (!contains(i)) throw new NoSuchElementException("error de indice");
        if (llave[i].compareTo(key) <= 0)
            throw new IllegalArgumentException("no es posible decrentar");
        llave[i] = key;
        swim(qp[i]);
    }


    public void incrementar_llave(int i, Llave key) {
        if (i < 0 || i >= maxN) throw new IllegalArgumentException();
        if (!contains(i)) throw new NoSuchElementException("eror de indice");
        if (llave[i].compareTo(key) >= 0)
            throw new IllegalArgumentException("no es posible incrementar llave");
        llave[i] = key;
        sink(qp[i]);
    }


    public void eliminar(int i) {
        if (i < 0 || i >= maxN) throw new IllegalArgumentException();
        if (!contains(i)) throw new NoSuchElementException("error de indice");
        int index = qp[i];
        intercambiar(index, n--);
        swim(index);
        sink(index);
        llave[i] = null;
        qp[i] = -1;
    }

    private boolean get_mayor(int i, int j) {
        return llave[pq[i]].compareTo(llave[pq[j]]) > 0;
    }

    private void intercambiar(int i, int j) {
        int swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }


    private void swim(int k) {
        while (k > 1 && get_mayor(k/2, k)) {
            intercambiar(k, k/2);
            k = k/2;
        }
    }

    private void sink(int k) {
        while (2*k <= n) {
            int j = 2*k;
            if (j < n && get_mayor(j, j+1)) j++;
            if (!get_mayor(k, j)) break;
            intercambiar(k, j);
            k = j;
        }
    }


    public Iterator<Integer> iterator() { return new HeapIterator(); }

    private class HeapIterator implements Iterator<Integer> {
        private ColaPrioridad<Llave> copia_cola;

        public HeapIterator() {
            copia_cola = new ColaPrioridad<Llave>(pq.length - 1);
            for (int i = 1; i <= n; i++)
                copia_cola.insertar(pq[i], llave[pq[i]]);
        }

        public boolean hasNext()  { return !copia_cola.isEmpty();                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Integer next() {
            if (!hasNext()) throw new NoSuchElementException();
            return copia_cola.delMin();
        }
    }
}
