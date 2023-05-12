package Lib;
import java.util.*;
import java.util.Queue;

public class GrafoND {
    public int getVertices() {
        return vertices;
    }
    public void setVertices(int vertices) {
        this.vertices = vertices;
    }
    public int getAristas() {return aristas;}
    public void setAristas(int aristas) {
        this.aristas = aristas;
    }
    private int vertices, aristas;
    private List<Integer>[] conexiones;
    public GrafoND(int vertices) {
        this.aristas = 0;
        this.vertices = vertices;
        conexiones = new LinkedList[vertices];
        for (int i = 0; i < vertices; i++) {
            conexiones[i] = new LinkedList<>();
        }
    }
    public GrafoND(GrafoND original) {
        this.vertices = original.getVertices();
        this.aristas = original.getAristas();
        this.conexiones = (List<Integer>[]) new List[vertices];
        for (int i = 0; i < vertices; i++) {
            this.conexiones[i] = new LinkedList<>();
            for (Integer nodo : original.conexiones[i]) {
                this.conexiones[i].add(nodo);
            }
        }
    }
    public void add_arista(int n1, int n2){
        if (n1 < 0 || n1 >= vertices || n2 < 0 || n2 >= vertices) {
            System.out.println("Error: Nodo fuera de rango.");
            return;
        }
        conexiones[n1].add(n2);
        conexiones[n2].add(n1);
        aristas++;
    }
    public List<Integer>[] getConexiones() {
        return conexiones;
    }
    public List<Integer> caminoporProfundidad(int nodoX, int nodoY) {
        if (nodoX < 0 || nodoX >= vertices || nodoY < 0 || nodoY >= vertices) {
            System.out.println("Error: Nodo fuera de rango.");
            return null;
        }
        List<Integer> recorrido = new LinkedList<>();
        boolean[] visitado = new boolean[vertices];
        caminoAux(nodoX, nodoY, visitado, recorrido);
        return recorrido;
    }
    private boolean caminoAux(int nodoActual, int nodoDestino, boolean[] visitado, List<Integer> recorrido) {
        visitado[nodoActual] = true;
        recorrido.add(nodoActual);
        if (nodoActual == nodoDestino)
            return true;
        for (Integer vecino : conexiones[nodoActual]) {
            if (!visitado[vecino]) {
                if (caminoAux(vecino, nodoDestino, visitado, recorrido))
                    return true;
            }
        }
        recorrido.remove(recorrido.size() - 1);
        return false;
    }
    public List<Integer> caminoOptimo(int nodoX, int nodoY) {
        if (nodoX < 0 || nodoX >= vertices || nodoY < 0 || nodoY >= vertices) {
            System.out.println("Error: Nodo fuera de rango.");
            return null;
        }
        Queue<Integer> cola = new LinkedList<>();
        boolean[] visitado = new boolean[vertices];
        int[] distancia = new int[vertices];
        int[] predecesor = new int[vertices];
        cola.add(nodoX);
        visitado[nodoX] = true;
        distancia[nodoX] = 0;
        while (!cola.isEmpty()) {
            int nodoActual = cola.poll();
            for (Integer vecino : conexiones[nodoActual]) {
                if (!visitado[vecino]) {
                    visitado[vecino] = true;
                    distancia[vecino] = distancia[nodoActual] + 1;
                    predecesor[vecino] = nodoActual;
                    cola.add(vecino);
                    if (vecino == nodoY)
                        break;
                }
            }
        }
        if (!visitado[nodoY])
            return null;
        List<Integer> recorrido = new LinkedList<>();
        int nodoActual = nodoY;
        while (nodoActual != nodoX) {
            recorrido.add(nodoActual);
            nodoActual = predecesor[nodoActual];
        }
        recorrido.add(nodoX);
        Collections.reverse(recorrido);
        return recorrido;
    }
    //Ejercicio 1
    public void imprimirGrafo() {
        for (int i = 0; i < vertices; i++) {
            List<Integer> listaAdyacencia = conexiones[i];
            System.out.print(i + ": ");
            for (int nodo : listaAdyacencia) {
                System.out.print(nodo + " ");
            }
            System.out.println();
        }
    }
    //Ejercicio 2
    public boolean has_arista(int n1, int n2) {
        if (n1 < 0 || n1 >= vertices || n2 < 0 || n2 >= vertices) {
            System.out.println("Error: Nodo fuera de rango.");
            return false;
        }
        return conexiones[n1].contains(n2) && conexiones[n2].contains(n1);
    }
    //Ejercicio 3
    public boolean estanConectados(int origen, int destino) {
        if (origen < 0 || origen >= vertices || destino < 0 || destino >= vertices) {
            System.out.println("Error: Nodo fuera de rango.");
            return false;
        }
        boolean[] visitado = new boolean[vertices];
        return estanConectadosAux(origen, destino, visitado);
    }
    private boolean estanConectadosAux(int nodoActual, int nodoDestino, boolean[] visitado) {
        if (nodoActual == nodoDestino) {
            return true;
        }
        visitado[nodoActual] = true;
        for (Integer vecino : conexiones[nodoActual]) {
            if (!visitado[vecino]) {
                if (estanConectadosAux(vecino, nodoDestino, visitado)) {
                    return true;
                }
            }
        }
        return false;
    }
}










/*
    0------6
    | \  / |
    |   2  |
    |  / \ |
    | /   \|
    |/     3
    |     /
    5---10

    7---8
    |  /|\
    | / | 4
    |/  |/
    11--1

        9
 */

