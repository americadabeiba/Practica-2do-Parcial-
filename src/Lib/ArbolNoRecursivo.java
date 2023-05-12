package Lib;

public class ArbolNoRecursivo<Llave extends Comparable<Llave>, Valor> {
    private Nodo raiz;

    class Nodo {
        private Llave llave;
        private Valor valor;
        private Nodo izquierda, derecha;
        private int tam;

        public Nodo(Llave llave, Valor valor, int tam) {
            this.llave = llave;
            this.valor = valor;
            this.tam = tam;
        }
    }

    public Valor getValorByLlave(Llave l) {
        Nodo x = raiz;
        while (x != null) {
            int comp = l.compareTo(x.llave);
            if (comp < 0) x = x.izquierda;
            else if (comp > 0) x = x.derecha;
            else return x.valor;
        }
        return null;
    }

    public void insertar(Llave l, Valor v) {
        Nodo nuevo = new Nodo(l, v, 1);
        if (raiz == null) {
            raiz = nuevo;
            return;
        }
        Nodo actual = raiz;
        Nodo padre = null;
        while (true) {
            padre = actual;
            int comp = l.compareTo(actual.llave);
            if (comp < 0) {
                actual = actual.izquierda;
                if (actual == null) {
                    padre.izquierda = nuevo;
                    return;
                }
            } else {
                actual = actual.derecha;
                if (actual == null) {
                    padre.derecha = nuevo;
                    return;
                }
            }
        }
    }
}

