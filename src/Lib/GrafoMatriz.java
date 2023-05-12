package Lib;

import java.util.ArrayList;
import java.util.List;

public class GrafoMatriz {
    private int[][] matriz;
    private int vertices;

    public GrafoMatriz(int vertices) {
        this.vertices = vertices;
        matriz = new int[vertices][vertices];
    }

    public void insertarArista(int origen, int destino, int peso) {
        matriz[origen][destino] = peso;
    }

    public void eliminarArista(int origen, int destino) {
        matriz[origen][destino] = 0;
    }

    public int obtenerPesoArista(int origen, int destino) {
        return matriz[origen][destino];
    }

    public boolean existeArista(int origen, int destino) {
        return matriz[origen][destino] != 0;
    }

    public List<Integer> obtenerVecinos(int vertice) {
        List<Integer> vecinos = new ArrayList<>();
        for(int i = 0; i < vertices; i++) {
            if(matriz[vertice][i] != 0) {
                vecinos.add(i);
            }
        }
        return vecinos;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < vertices; i++) {
            for(int j = 0; j < vertices; j++) {
                sb.append(matriz[i][j]);
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
