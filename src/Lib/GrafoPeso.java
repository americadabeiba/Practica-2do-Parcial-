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
}

//Encuentra el camino mas corto, En dijxtra, grafo y estado inicial instancial el dixtra.
//Deberiamos ver los caminos que hay a de a a F todos los nodos.