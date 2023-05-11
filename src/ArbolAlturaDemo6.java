import Lib.Arbol;

public class ArbolAlturaDemo6 {
    public static void main(String[] args) {
        Arbol<String, Integer> arbol = new Arbol<>();

        String[] llaves = {"E", "A", "S", "Y", "Q", "U", "E", "S", "T", "I", "O", "N"};

        for (String llave : llaves) {
            arbol.insertar(llave, 1);
        }

        int alturaRecursiva = arbol.getAltura();
        int alturaCampo = arbol.getAlturaCampo();

        System.out.println("Altura del árbol (método recursivo): " + alturaRecursiva);
        System.out.println("Altura del árbol (método con campo): " + alturaCampo);
    }
}
