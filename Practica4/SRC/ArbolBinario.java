import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * <p>Clase abstracta para modelar la estructura de datos Arbol Binario</p>
 * <p>Esta clase es genérica, es decir que es homogénea pero
 * puede tener elementos de cualquier tipo.</p>
 * <p>Puesto que todos los árboles binarios comparten algunas características similares,
 * esta clase sirve para modelarlas. Sin embargo no es lo suficientemente
 * específica para modelar algun árbol completamente. Por lo que la implementación
 * final depende de las clases concretas que hereden de ésta.</p>
 * @author Alejandro Hernández Mora <alejandrohmora@ciencias.unam.mx>
 * @version 1.0
 */

public abstract class ArbolBinario<T> implements Coleccionable<T> {
    /**
     * Clase interna protegida para nodos.
     */
    protected class Nodo<T> {

        /** El elemento del nodo. */
        public T elemento;
        /** Referencia a los nodos padre, e hijos. */
        public Nodo<T> padre, izquierdo, derecho;

        /**
         * Constructor único que recibe un elemento.
         * @param elemento el elemento del nodo.
         */
        public Nodo(T elemento) {
            //Aquí va su código
            this.elemento = elemento;
        }

        /**
         * Nos dice si el nodo tiene un padre.
         * @return <tt>true</tt> si el nodo tiene padre,
         *         <tt>false</tt> en otro caso.
         */
        public boolean hayPadre() {
            //Aquí va su código
            return this.padre != null;
        }

        /**
         * Nos dice si el nodo tiene un izquierdo.
         * @return <tt>true</tt> si el nodo tiene izquierdo,
         *         <tt>false</tt> en otro caso.
         */
        public boolean hayIzquierdo() {
            //Aquí va su código
            return this.izquierdo != null;
        }

        /**
         * Nos dice si el nodo tiene un derecho.
         * @return <tt>true</tt> si el nodo tiene derecho,
         *         <tt>false</tt> en otro caso.
         */
        public boolean hayDerecho() {
            //Aquí va su código
            return this.derecho != null;
        }

        /**
         * Regresa la altura del nodo.
         * @return la altura del nodo.
         */
        public int altura() {
            //Aquí va su código
            return altura(this,0);
        }

        private int altura(Nodo<T> n, int h) {
            if (n == null)
                return h-1;
            int izq = altura(n.izquierdo,h+1);
            int der = altura(n.derecho,h+1);
            return izq >= der ? izq : der;
        }


        /**
         * Regresa el elemento al que apunta el nodo.
         * @return el elemento al que apunta el nodo.
         */
        public T get() {
            //Aquí va su código
            return this.elemento;
        }

        /**
         * Compara el nodo con otro objeto. La comparación es
         * <em>recursiva</em>. Las clases que extiendan {@link Nodo} deben
         * sobrecargar el método {@link Nodo#equals}.
         * @param o el objeto con el cual se comparará el nodo.
         * @return <code>true</code> si el objeto es instancia de la clase
         *         {@link Nodo}, su elemento es igual al elemento de éste
         *         nodo, y los descendientes de ambos son recursivamente
         *         iguales; <code>false</code> en otro caso.
         */
        @Override
	      public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass())
                return false;
            @SuppressWarnings("unchecked") Nodo<T> nodo = (Nodo)o;
            return elemento.equals(nodo.elemento);
        }


        /**
         * Regresa una representación en cadena del nodo.
         * @return una representación en cadena del nodo.
         */
        public String toString() {
	        return elemento.toString();
        }
    }

    /** La raíz del árbol. */
    protected Nodo<T> raiz;
    /** El número de elementos */
    protected int tamanio;

    /**
     * Constructor sin parámetros.
     */
    public ArbolBinario() {}

    /**
     * Construye un árbol binario a partir de una colección. El árbol binario
     * tendrá los mismos elementos que la colección recibida.
     * @param coleccion la colección a partir de la cual creamos el árbol
     *        binario.
     */
    public ArbolBinario(Coleccionable<T> coleccion) {
        //Aquí va su código
        for (T elemento : coleccion)
            agrega(elemento);
    }

    /**
     * Construye un nuevo nodo, usando una instancia de {@link Nodo}. Para
     * crear nodos se debe utilizar este método en lugar del operador
     * <code>new</code>, para que las clases herederas de ésta puedan
     * sobrecargarlo y permitir que cada estructura de árbol binario utilice
     * distintos tipos de nodos.
     * @param elemento el elemento dentro del nodo.
     * @return un nuevo nodo con el elemento recibido dentro del mismo.
     */
    protected Nodo<T> nuevoNodo(T elemento) {
        //Aquí va su código
        return new Nodo<T>(elemento);
    }


    /**
     * Regresa la altura del árbol. La altura de un árbol es la altura de su
     * raíz.
     * @return la altura del árbol.
     */
    public int altura() {
        //Aquí va su código
        return raiz.altura();
    }

    /**
     * Regresa el número de elementos que se han agregado al árbol.
     * @return el número de elementos en el árbol.
     */
    @Override
    public int getTamanio() {
        //Aquí va su código
        return this.tamanio;
    }

    /**
     * Nos dice si un elemento está en el árbol binario.
     * @param elemento el elemento que queremos comprobar si está en el árbol.
     * @return <code>true</code> si el elemento está en el árbol;
     *         <code>false</code> en otro caso.
     */
    @Override
    public boolean contiene(T elemento) {
        //Aquí va su código
        return busca(elemento) != null;
    }

    /**
     * Busca el nodo de un elemento en el árbol. Si no lo encuentra regresa
     * <tt>null</tt>.
     * @param elemento el elemento para buscar el nodo.
     * @return un nodo que contiene el elemento buscado si lo encuentra;
     *         <tt>null</tt> en otro caso.
     */
    public Nodo<T> busca(T elemento) {
        //Aquí va su código
        return busca(raiz, elemento);
    }

    private Nodo<T> busca(Nodo<T> n, T elemento){
        if (n == null)
            return null;
        if (n.elemento.equals(elemento))
            return n;
        Nodo<T> ni = busca(n.izquierdo,elemento);
        return (ni != null) ? ni : busca(n.derecho,elemento);
    }


    /**
     * Regresa el nodo que contiene la raíz del árbol.
     * @return el nodo que contiene la raíz del árbol.
     * @throws NoSuchElementException si el árbol es vacío.
     */
    public Nodo<T> raiz() {
        //Aquí va su código
        return raiz;
    }

    /**
     * Nos dice si el árbol es vacío.
     * @return <code>true</code> si el árbol es vacío, <code>false</code> en
     *         otro caso.
     */
    @Override
    public boolean esVacio() {
        return raiz == null;
    }

    /**
     * Limpia el árbol de elementos, dejándolo vacío.
     */
    public void limpia() {
        //Aquí va su código
        raiz.izquierdo = null;
        raiz.derecho = null;
        raiz = null;
    }


    /**
     * Regresa una Lista con el los elementos en inorden del árbol.
     * @return Lista con los elementos del arbol.
     */
    public Lista<T> inOrden(){
        //Aquí va su código
        if (esVacio())
            return null;
        Lista<T> l = new Lista<T>();
        inordenAux(raiz, l);
        return l;
    }

    /**
     * Regresa una Lista con el los elementos en inorden del árbol.
     * @return Lista con los elementos del arbol.
     */

    public Lista<T> preOrden(){
        //Aquí va su código
        if (esVacio())
            return null;
        Lista<T> l = new Lista<T>();
        preordenAux(raiz, l);
        return l;
    }

    /**
     * Regresa una Lista con el los elementos en inorden del árbol.
     * @return Lista con los elementos del arbol.
     */
    public Lista<T> postOrden(){
        //Aquí va su código
        if (esVacio())
            return null;
        Lista<T> l = new Lista<T>();
        postordenAux(raiz, l);
        return l;
    }

    private void inordenAux(Nodo<T> n,Lista<T> l){
        //Aquí va su código
        if (n.hayIzquierdo())
            inordenAux(n.izquierdo, l);
        l.agrega(n.elemento);
        if (n.hayDerecho())
            inordenAux(n.derecho, l);

    }

    private void preordenAux(Nodo<T> n, Lista<T> l){
        //Aquí va su código
        l.agrega(n.elemento);
        if (n.hayIzquierdo())
            preordenAux(n.izquierdo, l);
        if (n.hayDerecho())
            preordenAux(n.derecho, l);
    }

    private void postordenAux(Nodo<T> n, Lista<T> l){
        //Aquí va su código
        if (n.hayIzquierdo())
            postordenAux(n.izquierdo, l);
        if (n.hayDerecho())
            postordenAux(n.derecho, l);
        l.agrega(n.elemento);
    }


    /**
     * Compara el árbol con un objeto.
     * @param o el objeto con el que queremos comparar el árbol.
     * @return <code>true</code> si el objeto recibido es un árbol binario y los
     *         árboles son iguales; <code>false</code> en otro caso.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;
        @SuppressWarnings("unchecked")
            ArbolBinario<T> arbol = (ArbolBinario<T>)o;
	      return arbol.inOrden().equals(this.inOrden());
    }

    /**
     * Regresa una representación en cadena del árbol.
     * @return una representación en cadena del árbol.
     */
    @Override public String toString() {
	if (raiz == null)
            return "";

        boolean[] r = new boolean[altura()+1];
        for (int i = 0; i < altura()+1; i++)
            r[i] = false;
        return cadena(raiz, 0, r);
    }

    private String cadena(Nodo<T> v, int n, boolean[] r) {
        String s = v + "\n";
        r[n] = true;
        if (v.izquierdo != null && v.derecho != null) {
            s += dibujaEspacios(n, r);
            s += "├─›";
            s += cadena(v.izquierdo, n+1, r);
            s += dibujaEspacios(n, r);
            s += "└─»";
            r[n] = false;
            s += cadena(v.derecho, n+1, r);
        } else if (v.izquierdo != null) {
            s += dibujaEspacios(n, r);
            s += "└─›";
            r[n] = false;
            s += cadena(v.izquierdo, n+1, r);
        } else if (v.derecho != null) {
            s += dibujaEspacios(n, r);
            s += "└─»";
            r[n] = false;
            s += cadena(v.derecho, n+1, r);
        }
        return s;
    }


  private String dibujaEspacios(int n, boolean[] r) {
  	String s = "";
  	for (int i = 0; i < n; i++)
  	    if (r[i])
                  s += "│  ";
              else
                  s += "   ";
  	return s;
  }
}
