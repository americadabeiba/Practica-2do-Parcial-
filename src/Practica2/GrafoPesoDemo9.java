package Practica2;

import Lib.Arista;
import Lib.GrafoPeso;

public class GrafoPesoDemo9 {
    public static void main(String[] args) {
        GrafoPeso grafo = new GrafoPeso(5);
        grafo.insertar_arista(new Arista(0, 1, 2));
        grafo.insertar_arista(new Arista(1, 2, 3));
        grafo.insertar_arista(new Arista(2, 3, 4));
        grafo.insertar_arista(new Arista(3, 4, 5));
        grafo.insertar_arista(new Arista(4, 0, 6));

        System.out.println(grafo.diametro());
    }
}
