package Practica2;

import Lib.Arbol;
import Lib.GrafoND;

public class GrafoDemo {
    public static void main(String[] args) {
        GrafoND miGrafo = new GrafoND(12); // Dado que tenemos nodos desde 0 hasta 11.
        miGrafo.add_arista(0, 6);
        miGrafo.add_arista(0, 2);
        miGrafo.add_arista(0, 5);
        miGrafo.add_arista(6, 2);
        miGrafo.add_arista(2, 3);
        miGrafo.add_arista(5, 10);
        miGrafo.add_arista(10, 3);
        miGrafo.add_arista(7, 8);
        miGrafo.add_arista(7, 11);
        miGrafo.add_arista(8, 11);
        miGrafo.add_arista(8, 1);
        miGrafo.add_arista(8, 4);
        miGrafo.add_arista(11, 1);
        miGrafo.add_arista(1, 4);
        miGrafo.imprimirGrafo();

        //Ejercicio 2
        boolean existeArista = miGrafo.has_arista(1, 4);
        System.out.println("¿Existe una arista entre 1 y 4?: " + existeArista);

        //Ejercicio 3
        boolean resultado = miGrafo.estanConectados(0, 6);
        if(resultado)
            System.out.println("Los nodos 0 y 6 están conectados");
        else
            System.out.println("Los nodos 0 y 6 no están conectados");

        //Ejercicio 4
        Arbol<Integer, String> miArbol = new Arbol<>();

        miArbol.insertar(0, "Nodo 0");
        miArbol.insertar(6, "Nodo 6");
        miArbol.insertar(2, "Nodo 2");
        miArbol.insertar(5, "Nodo 5");
        miArbol.insertar(10, "Nodo 10");
        miArbol.insertar(3, "Nodo 3");
        miArbol.insertar(7, "Nodo 7");
        miArbol.insertar(8, "Nodo 8");
        miArbol.insertar(11, "Nodo 11");
        miArbol.insertar(1, "Nodo 1");
        miArbol.insertar(4, "Nodo 4");

        miArbol.imprimirArbol();

        //Ejericicio 5
        miArbol.dfs(3);  // Búsqueda en profundidad (DFS) de la llave 3
        miArbol.bfs(3);  // Búsqueda en amplitud (BFS) de la llave 3


    }
}
