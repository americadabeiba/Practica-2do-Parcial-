import Lib.Arbol;

public class ArbolPrueba7 {
    public static void main(String[] args) {
        Arbol<String, Integer> arbol = new Arbol<>();

        String[] llaves = {"E", "A", "S", "Y", "Q", "U", "E", "S", "T", "I", "O", "N"};

        for (int i = 0; i < llaves.length; i++) {
            arbol.insertar(llaves[i], i);
        }

        System.out.println("Número promedio de comparaciones (recursivo): " + arbol.avgCompares());
        System.out.println("Número promedio de comparaciones (rápido): " + arbol.avgComparesFast());
    }
}

