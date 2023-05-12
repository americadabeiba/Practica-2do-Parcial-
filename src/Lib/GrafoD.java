package Lib;

import java.util.LinkedList;
import java.util.List;

public class GrafoD {
    private int vertices, aristas;
    private List<Integer>[] conexiones;
    private int[] grados;
    public GrafoD(int vertices) {
        this.vertices = vertices;
        this.aristas = 0;
        grados = new int[vertices];
        this.conexiones = new List[vertices];
        for(int i = 0; i<vertices; i++)
            this.conexiones[i] = new LinkedList<>();
    }
    public GrafoD(GrafoD original){
        setVertices(original.getVertices());
        setAristas(original.getAristas());
        for(int i = 0; i< original.getVertices(); i++){
            this.grados[i] = original.grados[i];
            for(int x : original.conexiones[i])
                conexiones[i].add(x);
            this.conexiones[i] = original.conexiones[i];
        }
    }
    public int getVertices() {return vertices;}
    public void setVertices(int vertices) {this.vertices = vertices;}
    public int getAristas() {return aristas;}
    public void setAristas(int aristas) {this.aristas = aristas;}
    public List<Integer>[] getConexiones() {return conexiones;}
    public void setConexiones(List<Integer>[] conexiones) {this.conexiones = conexiones;}
    public int[] getGrados() {return grados;}
    public void setGrados(int[] grados) {this.grados = grados;}
    public int get_num_salidas(int nodo){return this.conexiones[nodo].size();}
    public int get_num_entradas(int nodo){return this.grados[nodo];}
    public void conectar(int origen, int destino){
        this.conexiones[origen].add(destino);
        this.grados[destino]++;
        aristas++;
    }
}
