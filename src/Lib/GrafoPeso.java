package Lib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GrafoPeso {
    private int vertices;
    private int aristas;
    private List<Arista>[] conexiones;
    private int[] entradas;
    public GrafoPeso(int vertices) {
        this.vertices = vertices;
        this.aristas = 0;
        this.entradas = new int [vertices];
        conexiones = new List[vertices];
        for (int i = 0; i<vertices ; i++)
            conexiones[i] = new ArrayList<>();
    }

    public GrafoPeso(GrafoPeso G){
        this.setVertices(G.getVertices());
        this.setAristas(G.getAristas());
        for(int i = 0; i<G.getVertices();i++){
            this.entradas[i] = G.entradas[i];
        }
        for(int i = 0; i<G.getVertices(); i++){
            for(Arista e: G.conexiones[i])
                conexiones[i].add(e);
        }
    }

    public int getVertices() {return vertices;}
    public int getAristas() {return aristas;}
    public List<Arista>[] getConexiones() {return conexiones;}
    public void setVertices(int vertices) {this.vertices = vertices;}
    public void insertar_arista(Arista a){
        int v = a.getOrigen();
        int w = a.getDestino();
        conexiones[v].add(a);
        entradas[w]++;
        aristas++;
    }
    public Iterable<Arista> get_conexiones_by_vertice(int v){ return conexiones[v];}
    public int get_salidas(int v){return conexiones[v].size();}
    public int getEntradas(int v) {
        return entradas[v];
    }
    public Iterable<Arista> listar_aristas(){
        List<Arista> aux = new ArrayList<>();
        for(int i = 0; i< vertices; i++){
            for(Arista a : get_conexiones_by_vertice(i))
                aux.add(a);
        }
        return aux;
    }
    public void setAristas(int aristas) {
        this.aristas = aristas;
    }
    @Override
    public String toString() {
        StringBuilder cad = new StringBuilder();
        cad.append(vertices + " " + aristas + "\n");
        for (int i = 0; i < vertices; i++){
            cad.append(i + " : ");
            for (Object e : conexiones[i])
                cad.append(e + " ");
            cad.append("\n");
        }
        return cad.toString();
    }
    //EJERCICIO 7
    public void aumentarPesoAristas(double constante) {
        for (int i = 0; i < vertices; i++) {
            for (Arista a : conexiones[i]) {
                a.setPeso(a.getPeso() + constante);
            }
        }
    }
    //EJERCICIO 9
    public double diametro() {
        double[][] dist = new double[vertices][vertices];
        for (int i = 0; i < vertices; i++)
            Arrays.fill(dist[i], Double.POSITIVE_INFINITY);
        for (int v = 0; v < vertices; v++) {
            for (Arista a : conexiones[v]) {
                dist[a.getOrigen()][a.getDestino()] = a.getPeso();
            }
        }
        for (int v = 0; v < vertices; v++) dist[v][v] = 0;
        for (int k = 0; k < vertices; k++)
            for (int i = 0; i < vertices; i++)
                for (int j = 0; j < vertices; j++)
                    if (dist[i][k] < Double.POSITIVE_INFINITY && dist[k][j] < Double.POSITIVE_INFINITY)
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
        double diametro = 0;
        for (int i = 0; i < vertices; i++)
            for (int j = 0; j < vertices; j++)
                if (dist[i][j] != Double.POSITIVE_INFINITY)
                    diametro = Math.max(diametro, dist[i][j]);
        return diametro;
    }
}

