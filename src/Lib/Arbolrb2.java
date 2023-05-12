package Lib;

import java.util.LinkedList;
import java.util.Queue;

public class Arbolrb2 <Llave extends Comparable<Llave>, Valor>{
    private static boolean ROJO = true;
    private static boolean NEGRO = false;
    private Nodo raiz;
    private class Nodo{
        private Llave llave;
        private Valor valor;
        private Nodo izquierda,derecha;
        private boolean color;
        private int tam;
        public Nodo(Llave llave, Valor valor, boolean color, int tam) {
            this.llave = llave;
            this.valor = valor;
            this.tam = tam;
            this.color = color;
        }
    }
    public boolean esta_vacio(){
        return raiz == null;
    }
    public int get_tam(){
        return get_tam(raiz);
    }
    public int get_tam(Nodo x){
        if (x == null)
            return 0;
        return x.tam;
    }
    public Valor getValorByLlave(Llave l){return getValorByLlave(raiz, l);}
    public Valor getValorByLlave(Nodo x, Llave l){
        if (x == null)
            return null;
        int comp = l.compareTo(x.llave);
        if (comp < 0)
            return getValorByLlave(x.izquierda, l);
        else if (comp > 0)
            return getValorByLlave(x.derecha, l);
        else return x.valor;
    }
    public void insertar(Llave l, Valor v){
        raiz = insertar(raiz, l, v);
        raiz.color = NEGRO;
    }
    public boolean es_rojo(Nodo x){
        if (x == null) return false;
        return x.color == ROJO;
    }
    public Nodo insertar(Nodo x, Llave l, Valor v){
        if (x == null)
            return new Nodo(l, v, ROJO, 1);
        int cmp = l.compareTo(x.llave);
        if (cmp < 0) x.izquierda = insertar(x.izquierda, l, v);
        else if (cmp > 0) x.derecha = insertar(x.derecha, l, v);
        else x.valor = v;

        if (es_rojo(x.derecha) && es_rojo(x.izquierda))
            cambiar_color(x);
        if (es_rojo(x.izquierda) && es_rojo(x.izquierda.izquierda))
            x = rotar_derecha(x);
        if (!es_rojo(x.izquierda)&& es_rojo(x.derecha)){
            x = rotar_izquierda(x);
        }
        x.tam = 1 + get_tam(x.izquierda) + get_tam(x.derecha);
        return x;
    }
    private Nodo rotar_derecha(Nodo x) {
        Nodo aux = x.izquierda;
        x.izquierda = aux.derecha;
        aux.derecha = x;
        aux.color = aux.derecha.color;
        aux.derecha.color = ROJO;
        aux.tam = x.tam;
        x.tam = get_tam(x.izquierda) + get_tam(x.derecha) +1;
        return aux;

    }
    private Nodo rotar_izquierda(Nodo x){
        if (x.derecha == null) {
            return x; // No se puede rotar si no hay hijo derecho
        }
        Nodo aux = x.derecha;
        x.derecha = aux.izquierda;
        aux.izquierda = x;
        aux.color = aux.izquierda.color;
        aux.izquierda.color= ROJO;
        aux.tam = x.tam;
        x.tam = get_tam(x.izquierda) + get_tam(x.derecha) + 1;
        return aux;
    }
    private void cambiar_color(Nodo x) {
        x.color = !x.color;
        x.izquierda.color = !x.izquierda.color;
        x.derecha.color = !x.derecha.color;
    }

    public Iterable<Llave> get_llaves(){
        if (esta_vacio()) return new LinkedList<>();
        return get_llaves(get_min(), get_max());
    }

    public Iterable<Llave> get_llaves(Llave mas_bajo, Llave mas_alto){
        Queue<Llave> cola = new LinkedList<>();
        get_llaves(raiz, cola, mas_bajo, mas_alto);
        return cola;
    }

    public void get_llaves(Nodo x, Queue<Llave> cola, Llave bajo, Llave alto) {
        if (x == null) return;
        int cmp_b = bajo.compareTo(x.llave);
        int cmp_a = alto.compareTo(x.llave);

        if (cmp_b < 0) get_llaves(x.izquierda, cola, bajo, alto);
        if (cmp_b <= 0 && cmp_a >= 0) cola.add(x.llave);
        if (cmp_a > 0) get_llaves(x.derecha, cola, bajo, alto);
    }
    public Llave get_min(){return get_min(raiz).llave;}
    public Nodo get_min(Nodo x){
        if (x.izquierda == null)
            return x;
        return get_min(x.izquierda);
    }
    public Llave get_max(){return get_max(raiz).llave;}
    public Nodo get_max(Nodo x){
        if (x.derecha == null)
            return x;
        return get_max(x.derecha);
    }
    public void eliminarMayor() {
        if (!esta_vacio()) {
            raiz = eliminarMayor(raiz);
            if (raiz != null)
                raiz.color = NEGRO;
        }
    }
    private Nodo eliminarMayor(Nodo x)
    {   if (x.derecha == null)
            return x.izquierda;
        x.derecha = eliminarMayor(x.derecha);
        x.tam = 1 + get_tam(x.izquierda) + get_tam(x.derecha);
        if (es_rojo(x.izquierda) && !es_rojo(x.derecha))
            x = rotar_izquierda(x);
        if (!es_rojo(x.izquierda) && es_rojo(x.izquierda.izquierda))
            x = rotar_derecha(x);
        if (es_rojo(x.izquierda) && es_rojo(x.derecha))
            cambiar_color(x);
        return x;
    }
    //metodo balancear()
    private Nodo balancear(Nodo h){
        if(es_rojo(h.derecha))
            h = rotar_izquierda(h);
        if(es_rojo(h.izquierda) && es_rojo(h.izquierda))
            h = rotar_derecha(h);
        if(es_rojo(h.izquierda) && es_rojo(h.derecha))
            cambiar_color(h);
        h.tam = get_tam(h.izquierda) + get_tam(h.derecha) + 1;
        return h;
    }
    private Nodo mover_rojo_izquierda(Nodo h){
        cambiar_color(h);
        if(es_rojo(h.derecha.izquierda)){
            h.derecha = rotar_derecha(h.derecha);
            h = rotar_izquierda(h);
            cambiar_color(h);
        }
        return h;
    }
    private Nodo mover_derecha(Nodo h){
        cambiar_color(h);
        if(es_rojo(h.izquierda.izquierda))
            h.izquierda = rotar_derecha(h);
            cambiar_color(h);
        return h;
    }
    public void eliminar_min(){
        if(!es_rojo(raiz.izquierda) && !es_rojo(raiz.derecha))
            raiz.color = ROJO;
        raiz = eliminar_min(raiz);
        if(!esta_vacio())
            raiz.color= NEGRO;
    }
    private Nodo eliminar_min(Nodo h) {
        if(h.izquierda == null)
            return null;
        if(!es_rojo(h.izquierda)&& !es_rojo(h.izquierda.izquierda))
            h = mover_rojo_izquierda(h);
        h.izquierda = eliminar_min(h.izquierda);
        return balancear(h);
    }
    public void imprimir() {
        imprimir(this.raiz, "", true);
    }

    private void imprimir(Nodo nodo, String indentacion, boolean ultimo) {
        if (nodo != null) {
            System.out.print(indentacion);
            if (ultimo) {
                System.out.print("└── ");
                indentacion += "    ";
            } else {
                System.out.print("├── ");
                indentacion += "│   ";
            }

            String colorNodo = nodo.color == ROJO ? "R" : "N";
            System.out.println(nodo.llave + "(" + colorNodo + ")");

            imprimir(nodo.izquierda, indentacion, false);
            imprimir(nodo.derecha, indentacion, true);
        }
    }
}
