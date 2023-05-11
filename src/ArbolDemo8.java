import Lib.Arbol;
public class ArbolDemo8 {
    public static void main(String[] args) {
        int N = 127;
        System.out.println("Número óptimo de comparaciones para un árbol de tamaño " + N + ": " + Arbol.optCompares(N));
    }
}
