import Lib.Arbol;

public class ArbolMejorCaso {
    public static void main(String[] args) {
        String[][] ordenamientos = {
                {"E", "C", "A", "H", "S", "R", "X"},
                {"E", "S", "R", "X", "C", "A", "H"},
                {"E", "C", "H", "A", "S", "X", "R"},
                {"E", "S", "X", "R", "C", "H", "A"},
                {"E", "C", "A", "H", "S", "X", "R"}
        };

        for (int i = 0; i < ordenamientos.length; i++) {
            Arbol<String, Integer> arbol = new Arbol<>();
            for (String llave : ordenamientos[i]) {
                arbol.insertar(llave, 1);
            }
            System.out.println("Ordenamiento " + (i + 1) + ":");
            arbol.mostrarPiramide();
            System.out.println();
        }
    }
}

