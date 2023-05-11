import Lib.Arbol;

public class ArbolPeorCaso3 {
    public static void main(String[] args) {
        String[][] ordenamientos = {
                {"A", "X", "C", "S", "E", "R", "H"},
                {"A", "C", "E", "H", "R", "S", "X"},
                {"X", "S", "R", "H", "E", "C", "A"},
                {"A", "X", "C", "E", "H", "S", "R"},
                {"R", "S", "X", "H", "C", "A", "E"},
                {"A", "H", "E", "S", "R", "C", "X"}
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
