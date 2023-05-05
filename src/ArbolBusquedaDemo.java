import Lib.Arbol;

public class ArbolBusquedaDemo {
    public static void main(String[] args) {
        Arbol<Integer, Integer> arbol = new Arbol<>();

        for (int i = 1; i <= 10; i++)
            arbol.insertar(i, 1);

        int[][] secuencias = {
                {10, 9, 8, 7, 6, 5},
                {4, 10, 8, 7, 53},
                {1, 10, 2, 9, 3, 8, 4, 7, 6, 5},
                {2, 7, 3, 8, 4, 5},
                {1, 2, 10, 4, 8, 5}
        };

        for (int i = 0; i < secuencias.length; i++) {
            if (!puedeSerSecuencia(arbol, secuencias[i], 5))
                System.out.println("La secuencia " + (i + 1) + " no puede ser la secuencia de teclas examinada.");
        }
    }

    public static boolean puedeSerSecuencia(Arbol<Integer, Integer> arbol, int[] secuencia, int objetivo) {
        Integer valorActual = null;
        for (int llave : secuencia) {
            valorActual = arbol.getValorByLlave(llave);
            if (valorActual != null && llave == objetivo) {
                return true;
            }
        }
        return false;
    }
}

