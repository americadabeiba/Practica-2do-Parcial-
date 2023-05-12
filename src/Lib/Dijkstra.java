package Lib;

import java.util.Stack;

public class Dijkstra {
    private double[] distancia_a;
    private Arista[] arista_a;
    private ColaPrioridad<Double> frontera;
    public Dijkstra(GrafoPeso G, int e0) {
        distancia_a = new double[G.getVertices()];
        arista_a = new Arista[G.getVertices()];
        for (int i = 0; i < G.getVertices(); i++)
            distancia_a[i] = Double.POSITIVE_INFINITY;
        distancia_a[e0] = 0.0;
        frontera = new ColaPrioridad<Double>(G.getVertices());
        frontera.insertar(e0,distancia_a[e0]);
        while(!frontera.isEmpty()){
            int nodo = frontera.delMin();
            for (Arista hijo : G.get_conexiones_by_vertice(nodo)){
                generar(hijo);
            }
        }
    }
    private void generar(Arista hijo) {
        int v = hijo.getOrigen();
        int w = hijo.getDestino();
        if(distancia_a[w] > distancia_a[v]+ hijo.getPeso()){
            distancia_a[w] = distancia_a[v]+ hijo.getPeso();
            arista_a[w] = hijo;
            if(frontera.contains(w))
                frontera.decrementar_llave(w,distancia_a[w]);
            else frontera.insertar(w,distancia_a[w]);
        }
    }
    public double get_distancia(int v){return distancia_a[v];}
    public boolean hay_camino_a(int v){return distancia_a[v] < Double.POSITIVE_INFINITY;}
    public Iterable<Arista> get_camino(int v){
        if(!hay_camino_a(v)) return null;
        Stack<Arista> camino = new Stack<>();
        for(Arista a = arista_a[v]; a!=null; a = arista_a[a.getOrigen()]){
            camino.push(a);
        }
        return camino;
    }
    //Ejercicio 7

}
