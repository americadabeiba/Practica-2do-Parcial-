package Lib;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Arbol <Llave extends Comparable<Llave>, Valor> {
    private Nodo raiz;
    class Nodo {
        private Llave llave;
        private Valor valor;
        private Nodo izquierda;
        private Nodo derecha;
        private int tam;
        private int altura;
        int sumaProfundidades;

        public Nodo(Llave llave, Valor valor, int tam) {
            this.llave = llave;
            this.valor = valor;
            this.tam = tam;
            this.altura = 0;
        }
    }
    private int comparaciones;
    public Arbol() {comparaciones = 0;}
    public boolean esta_vacio() {
        return raiz == null;
    }
    public int get_tam() {
        return get_tam(raiz);
    }
    public int get_tam(Nodo x) {
        if (x == null) {
            return 0;
        }
        return x.tam;
    }
    public Valor getValorByLlave(Llave l) {
        return getValorByLlave(raiz, l);
    }
    public Valor getValorByLlave(Nodo x, Llave l) {
        if (x == null) {
            return null;
        }
        int comp = l.compareTo(x.llave);
        if (comp < 0) {
            return getValorByLlave(x.izquierda, l);
        } else if (comp > 0) {
            return getValorByLlave(x.derecha, l);
        } else return x.valor;
    }
    public void insertar(Llave l, Valor v) {
        raiz = insertar(raiz, l, v);
    }
    public Nodo insertar(Nodo x, Llave l, Valor v) {
        if (x == null)
            return new Nodo(l, v, 1);
        int comp = l.compareTo(x.llave);
        comparaciones++;
        if (comp < 0) {
            x.izquierda = insertar(x.izquierda, l, v);
        } else if (comp > 0) {
            x.derecha = insertar(x.derecha, l, v);
        } else {
            x.valor = v;
        }
        x.tam = 1 + get_tam(x.izquierda) + get_tam(x.derecha);
        x.sumaProfundidades = get_tam(x.izquierda) + get_tam(x.derecha) + (x.izquierda != null ? x.izquierda.sumaProfundidades : 0) + (x.derecha != null ? x.derecha.sumaProfundidades : 0);
        return x;
    }
    public double avgComparesFast() {return (double) raiz.sumaProfundidades / get_tam() + 1;}
    public int getComparaciones() {return comparaciones;}
    public Llave getMin() {return getMin(raiz).llave;}
    private Nodo getMin(Nodo x) {
        if (x.izquierda == null)
            return x;
        return getMin(x.izquierda);
    }
    public Llave getMax() {return getMax(raiz).llave;}
    private Nodo getMax(Nodo x) {
        if (x.derecha == null)
            return x;
        return getMax(x.derecha);
    }
    public List<Llave> menores(Llave num) {
        List<Llave> lista = new ArrayList<>();
        getValoresMenores(raiz, num, lista);
        return lista;
    }
    private void getValoresMenores(Nodo x, Llave num, List<Llave> lista) {
        if (x == null)
            return;
        int comp = num.compareTo(x.llave);
        if(comp<0)
            getValoresMenores(x.izquierda,num,lista);
        else {
            lista.add(x.llave);
            getValoresMenores(x.derecha, num, lista);
        }
    }
    public void mostrarHijos() {mostrarHijos(raiz);}
    private void mostrarHijos(Nodo x) {
        if (x == null)
            return;
        System.out.println("Llave: " + x.llave + ", Valor: " + x.valor);
        if (x.derecha != null) {
            if (x.derecha.izquierda != null || x.derecha.derecha != null)
                mostrarHijos(x.derecha);
            else
                System.out.println("Llave: " + x.derecha.llave + ", Valor: " + x.derecha.valor);
        }
        if (x.izquierda != null) {
            if (x.izquierda.izquierda != null || x.izquierda.derecha != null)
                mostrarHijos(x.izquierda);
            else
                System.out.println("Llave: " + x.izquierda.llave + ", Valor: " + x.izquierda.valor);
        }
    }
    public void mostrarPiramide() {
        int nivel = 1;
        while (imprimirNivel(raiz, nivel)) {
            nivel++;
        }
    }
    private boolean imprimirNivel(Nodo x, int nivel) {
        if (x == null)
            return false;
        if (nivel == 1) {
            System.out.print("Llave: " + x.llave + ", Valor: " + x.valor + " \n");
            return true;
        } else {
            boolean izquierda = imprimirNivel(x.izquierda, nivel - 1);
            boolean derecha = imprimirNivel(x.derecha, nivel - 1);
            return izquierda || derecha;
        }
    }
    public int getAltura() {return getAltura(raiz);}
    private int getAltura(Nodo x) {
        if (x == null)
            return -1;
        int alturaIzquierda = getAltura(x.izquierda);
        int alturaDerecha = getAltura(x.derecha);
        return Math.max(alturaIzquierda, alturaDerecha) + 1;
    }
    public int getAlturaNodo(Nodo x) {return x == null ? -1 : x.altura;}
    public int getAlturaCampo() {return getAlturaNodo(raiz);}
    public double avgCompares() {
        return avgCompares(raiz, 0) / (double) get_tam() + 1;
    }
    private int avgCompares(Nodo nodo, int profundidad) {
        if (nodo == null) return 0;
        return profundidad + avgCompares(nodo.izquierda, profundidad + 1) + avgCompares(nodo.derecha, profundidad + 1);
    }
    public static double optCompares(int N) {
        int h = (int) (Math.log(N + 1) / Math.log(2)); // altura del árbol
        int nodosCompletos = (int) Math.pow(2, h) - 1; // número de nodos en los h - 1 niveles superiores
        int nodosUltimoNivel = N - nodosCompletos; // número de nodos en el último nivel

        int sumaProfundidadesCompletos = nodosCompletos * h; // suma de las profundidades de los nodos en los h - 1 niveles superiores
        int sumaProfundidadesUltimoNivel = nodosUltimoNivel * h; // suma de las profundidades de los nodos en el último nivel
        return ((double) (sumaProfundidadesCompletos + sumaProfundidadesUltimoNivel)) / N;
        //Implementar
    }
    //Ejercicio 5
    public void imprimirArbol() {
        imprimirArbol(raiz);
        System.out.println();
    }

    private void imprimirArbol(Nodo x) {
        if (x == null) {
            return;
        }
        imprimirArbol(x.izquierda);
        System.out.print(x.llave + " ");
        imprimirArbol(x.derecha);
    }
    //Ejercicio 6
    public void dfs(Llave l) {
        dfs(raiz, l);
    }

    private void dfs(Nodo x, Llave l) {
        if (x == null) {
            return;
        }
        System.out.print(x.llave + " ");
        if (x.llave.equals(l)) {
            System.out.println("\nEncontrado: " + l);
            return;
        }
        dfs(x.izquierda, l);
        dfs(x.derecha, l);
    }

    public void bfs(Llave l) {
        Queue<Nodo> queue = new LinkedList<>();
        queue.add(raiz);
        while (!queue.isEmpty()) {
            Nodo x = queue.poll();
            System.out.print(x.llave + " ");
            if (x.llave.equals(l)) {
                System.out.println("\nEncontrado: " + l);
                return;
            }
            if (x.izquierda != null) {
                queue.add(x.izquierda);
            }
            if (x.derecha != null) {
                queue.add(x.derecha);
            }
        }
    }

}