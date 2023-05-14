package Practica2;

import Lib.Arista;
import Lib.Dijkstra;
import Lib.GrafoPeso;

public class GrafoDirigidoDemo7 {
    //EJERCICIO 7
    public static void main(String[] args) {
        GrafoPeso grafo = new GrafoPeso(5);
        grafo.insertar_arista(new Arista(0, 1, 10));
        grafo.insertar_arista(new Arista(1, 2, 10));
        grafo.insertar_arista(new Arista(2, 3, 10));
        grafo.insertar_arista(new Arista(3, 4, 40));

        Dijkstra dijkstra = new Dijkstra(grafo, 0);
        Iterable<Arista> ruta = dijkstra.get_camino(4);
        System.out.println("Ruta más corta original: " + ruta);

        grafo.aumentarPesoAristas(100);

        dijkstra = new Dijkstra(grafo, 0);
        Iterable<Arista> nuevaRuta = dijkstra.get_camino(4);
        System.out.println("Ruta más corta después de aumentar los pesos: " + nuevaRuta);

        System.out.println("Las rutas son iguales: " + ruta.equals(nuevaRuta));
    }
}
