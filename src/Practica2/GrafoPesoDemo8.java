package Practica2;

import Lib.Arista;
import Lib.Dijkstra;
import Lib.GrafoMatriz;

public class GrafoPesoDemo8 {
    public static void main(String[] args) {
        GrafoMatriz grafo = new GrafoMatriz(5);
        // Insertar aristas
        grafo.insertarArista(0, 1, 2); // Arista del vértice 0 al vértice 1 con peso 2
        grafo.insertarArista(1, 2, 3); // Arista del vértice 1 al vértice 2 con peso 3
        grafo.insertarArista(2, 3, 4); // Arista del vértice 2 al vértice 3 con peso 4
        grafo.insertarArista(3, 4, 5); // Arista del vértice 3 al vértice 4 con peso 5
        grafo.insertarArista(4, 0, 6); // Arista del vértice 4 al vértice 0 con peso 6

        System.out.println(grafo.toString());

        System.out.println(grafo.existeArista(0, 1)); // Debe imprimir true
        System.out.println(grafo.existeArista(0, 2)); // Debe imprimir false
        //peso arista
        System.out.println(grafo.obtenerPesoArista(0, 1));
        System.out.println(grafo.obtenerVecinos(1)); // Debe imprimir [0, 2]
    }
}
