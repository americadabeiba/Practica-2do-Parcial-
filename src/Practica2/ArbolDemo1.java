package Practica2;

import Lib.Arbolrb2;

public class ArbolDemo1 {
    public static void main(String[] args) {
        Arbolrb2<Integer, Integer> arbol = new Arbolrb2<>();
        int[] llaves = {5, 6, 1, 8, 3, 2, 10, 12, 13, 7};

        for (int llave : llaves) {
            arbol.insertar(llave, 1);
            System.out.println("Después de insertar: " + llave);
            arbol.imprimir();
            System.out.println("---------------------");
        }
    }
}
