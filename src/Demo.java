import Lib.Arista;
import Lib.Dijkstra;
import Lib.GrafoPeso;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        GrafoPeso gp = new GrafoPeso(6);
        gp.insertar_arista(new Arista(0, 3, 23.6));
        gp.insertar_arista(new Arista(2, 3, 10.0));
        gp.insertar_arista(new Arista(4, 1, 23.6));
        gp.insertar_arista(new Arista(2, 3, 23.6));
        gp.insertar_arista(new Arista(0, 2, 4.0));
        gp.insertar_arista(new Arista(2, 4, 15.0));

        System.out.println(gp);

        int nodoInicio = 0;
        int nodoDestino = 4;

        Dijkstra dijkstra = new Dijkstra(gp, nodoInicio);
        List<Arista> caminoMasCorto = (List<Arista>) dijkstra.get_camino(nodoDestino);

        if (caminoMasCorto != null) {
            System.out.println("Camino más corto desde " + nodoInicio + " a " + nodoDestino + ":");
            for (Arista a : caminoMasCorto)
                System.out.println(a);
        }
        else
            System.out.println("No hay camino desde " + nodoInicio + " a " + nodoDestino);
    }
}
