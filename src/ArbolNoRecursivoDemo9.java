import Lib.ArbolNoRecursivo;

public class ArbolNoRecursivoDemo9 {
    public static void main(String[] args) {
        ArbolNoRecursivo<String, Integer> arbol = new ArbolNoRecursivo<>();

        String[] llaves = {"E", "A", "S", "Y", "Q", "U", "E", "S", "T", "I", "O", "N"};

        for (String llave : llaves) {
            arbol.insertar(llave, 1);
        }

        System.out.println("Valor por llave 'E': " + arbol.getValorByLlave("E"));
        System.out.println("Valor por llave 'T': " + arbol.getValorByLlave("T"));
    }
}
