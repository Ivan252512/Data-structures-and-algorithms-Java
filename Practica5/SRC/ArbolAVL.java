/**
 * <p>Clase para árboles AVL.</p>
 *
 * <p>Un árbol AVL cumple que para cada uno de sus nodos, la diferencia entre
 * la áltura de sus subárboles izquierdo y derecho está entre -1 y 1.</p>
 */
public class ArbolAVL<T extends Comparable<T>>
    extends ArbolBinarioBusqueda<T> {

    /**
     * Clase interna protegida para nodos de árboles AVL. La única diferencia
     * con los nodos de árbol binario, es que tienen una variable de clase
     * para la altura del nodo.
     */
    protected class NodoAVL extends ArbolBinario<T>.Nodo<T> {

        /** La altura del nodo. */
        public int altura;

        /**
         * Constructor único que recibe un elemento.
         * @param elemento el elemento del nodo.
         */
        public NodoAVL(T elemento) {
            // Aquí va tu código.
            super(elemento);
        }

        private boolean equals(NodoAVL v, NodoAVL v2){
            //Aquí va tu código
            if (v == null && v2 != null)
                return false;
            if (v != null && v2 == null)
                return false;
            if (v == null & v2 == null)
                return true;
            if (v.altura != v2.altura)
                return false;
            NodoAVL vizq = (NodoAVL) v.izquierdo;
            NodoAVL vder = (NodoAVL) v.derecho;
            NodoAVL v2izq = (NodoAVL) v2.izquierdo;
            NodoAVL v2der = (NodoAVL) v2.derecho;

            return (equals(vizq,v2izq) && equals(vder,v2der));
        }

        /**
         * Compara el nodo con otro objeto. La comparación es
         * <em>recursiva</em>.
         * @param o el objeto con el cual se comparará el nodo.
         * @return <code>true</code> si el objeto es instancia de la clase
         *         {@link NodoAVL}, su elemento es igual al elemento de éste
         *         nodo, los descendientes de ambos son recursivamente
         *         iguales, y las alturas son iguales; <code>false</code> en
         *         otro caso.
         */
        @Override public boolean equals(Object o) {
            if (o == null)
                return false;
            if (getClass() != o.getClass())
                return false;
            @SuppressWarnings("unchecked") NodoAVL nodo = (NodoAVL)o;
            // Aquí va tu código.
            return equals(this, nodo);
        }

    }

    public ArbolAVL(){
      super();
    }

    public ArbolAVL(Coleccionable<T> coleccion){
        for (T elemento : coleccion){
            agrega(elemento);
        }
    }

    private void actualizaAltura(NodoAVL v){
	     // Aquí va tu código.
       if (v.izquierdo == null && v.derecho == null) {
           v.altura = 0;
           return;
       }
       v.altura = Math.max(getAltura(v.izquierdo),getAltura(v.derecho)) + 1;
    }

    private void rebalancea(NodoAVL v){
	     // Aquí va tu código.
       if (v == null)
           return;
       actualizaAltura(v);
       int e = getAltura(v.izquierdo) - getAltura(v.derecho);
       if (e == -2) {
           if (getAltura(v.derecho.izquierdo) - getAltura(v.derecho.derecho) == 1)
               giraDerechaAVL(v.derecho);
           giraIzquierdaAVL(v);
       } else if (e == 2) {
           if (getAltura(v.izquierdo.izquierdo) - getAltura(v.izquierdo.derecho) == -1)
               giraIzquierdaAVL(v.izquierdo);
           giraDerechaAVL(v);
       }
       rebalancea((NodoAVL) v.padre);
    }

    private void giraDerechaAVL(Nodo<T> n) {
        super.giraDerecha(n);
        actualizaAltura((NodoAVL) n);
    }

    private void giraIzquierdaAVL(Nodo<T> n) {
        super.giraIzquierda(n);
        actualizaAltura((NodoAVL) n);
    }

    /**
     * Agrega un nuevo elemento al árbol. El método invoca al método {@link
     * ArbolBinarioBusqueda#agrega}, y después balancea el árbol girándolo como
     * sea necesario. La complejidad en tiempo del método es <i>O</i>(log
     * <i>n</i>) garantizado.
     * @param elemento el elemento a agregar.
     */
    @Override public void agrega(T elemento) {
    	 Nodo<T> nuevo = new NodoAVL(elemento);
    	 super.agrega(nuevo,raiz);
	     // Aquí va tu código.
      rebalancea((NodoAVL) nuevo);
    }

    /**
     * Elimina un elemento del árbol. El método elimina el nodo que contiene
     * el elemento, y gira el árbol como sea necesario para rebalancearlo. La
     * complejidad en tiempo del método es <i>O</i>(log <i>n</i>) garantizado.
     * @param elemento el elemento a eliminar del árbol.
     */
    @Override public void elimina(T elemento) {
        Nodo<T> busca = super.busca(elemento);
	      // Aquí va tu código.
        if (busca==null)
            return;
        if (busca.hayPadre()){
            Nodo<T> papa = busca.padre;
            super.elimina(elemento);
            rebalancea((NodoAVL) papa);
            return;
        }
        super.elimina(elemento);
    }

    /**
     * Regresa la altura del nodo AVL.
     * @param nodo el nodo del que queremos la altura.
     * @return la altura del nodo AVL.
     * @throws ClassCastException si el nodo no es instancia de {@link
     *         NodoAVL}.
     */
    public int getAltura(Nodo<T> nodo) {
	     // Aquí va tu código.
       if (nodo == null)
           return -1;
       if (!(nodo instanceof ArbolAVL<?>.NodoAVL))
           throw new ClassCastException();
       return nodoAVL(nodo).altura;
    }


    /**
     * Convierte el nodo (visto como instancia de {@link
     * Nodo}) en nodo (visto como instancia de {@link
     * NodoAVL}). Método auxililar para hacer este cast en un único
     * lugar.
     * @param nodo el nodo de árbol binario que queremos como nodo AVL.
     * @return el nodo recibido visto como nodo AVL.
     * @throws ClassCastException si el nodo no es instancia de {@link
     *         NodoAVL}.
     */
    protected NodoAVL nodoAVL(Nodo<T> nodo) {
        return (NodoAVL) nodo;
    }
}
