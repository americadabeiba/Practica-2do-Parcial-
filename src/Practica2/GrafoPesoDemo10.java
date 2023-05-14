package Practica2;

import Lib.Arista;
import Lib.GrafoPeso;

public class GrafoPesoDemo10 {
    public static void main(String[] args) {
        GrafoPeso grafo = new GrafoPeso(5);

        grafo.insertar_arista(new Arista(0, 1, 10));
        grafo.insertar_arista(new Arista(0, 2, 3));
        grafo.insertar_arista(new Arista(1, 2, 1));
        grafo.insertar_arista(new Arista(1, 3, 2));
        grafo.insertar_arista(new Arista(2, 1, 4));
        grafo.insertar_arista(new Arista(2, 3, 8));
        grafo.insertar_arista(new Arista(2, 4, 2));
        grafo.insertar_arista(new Arista(3, 4, 7));
        grafo.insertar_arista(new Arista(4, 3, 9));

        Iterable<Arista> camino = grafo.obtenerCaminoMasCorto(0, 3);
        System.out.println("Camino maas corto de 0 a 3:");
        for (Arista arista : camino) {
            System.out.println(arista);
        }
    }
}
