import Lib.Arbol;

public class ArbolDemo {
    public static void main(String[] args) {
        Arbol<String, Integer> arbol = new Arbol<>();
        String[] llaves = {"E", "A", "S", "Y", "Q", "U", "E", "S", "T", "I", "O", "N"};
        for (String llave : llaves) {
            arbol.insertar(llave, 1);
        }
        arbol.mostrarPiramide();
        System.out.println("Número de comparaciones: " + arbol.getComparaciones());
    }
}
