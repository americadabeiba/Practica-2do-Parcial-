package Practica2;

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

    }
}
