import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * <p>Clase para modelar árboles binarios de búsqueda genéricos.</p>
 * <p>Un árbol instancia de esta clase siempre cumple que:</p>
 * <ul>
 *   <li>Cualquier elemento en el árbol es mayor o igual que todos sus
 *       descendientes por la izquierda.</li>
 *   <li>Cualquier elemento en el árbol es menor o igual que todos sus
 *       descendientes por la derecha.</li>
 * </ul>
 * @author Alejandro Hernández Mora <alejandrohmora@ciencias.unam.mx>
 * @version 1.0
 */
public class ArbolBinarioBusqueda<T extends Comparable<T>>
    extends ArbolBinario<T> {

    /* Clase privada para iteradores de árboles binarios ordenados. */
    private class Iterador implements Iterator<T> {

        /* Pila para recorrer los nodos en DFS in-order. */
        private Pila<Nodo<T>> pila;

        /* Construye un iterador con el nodo recibido. */
        public Iterador() {
            pila = new Pila<Nodo<T>>();
            if (raiz != null)
                pila.push(raiz);
        }

        /* Nos dice si hay un elemento siguiente. */
        @Override public boolean hasNext() {
            // Aquí va su código
            try {
                return pila.peek() != null;
            } catch (NoSuchElementException e) {
                return false;
            }
        }

        /* Regresa el siguiente elemento en orden DFS in-order. */
        @Override public T next() {
            // Aquí va su código.
            Nodo<T> n = pila.pop();
            if (n.izquierdo != null)
                pila.push(n.izquierdo);
            if (n.derecho != null)
                pila.push(n.derecho);
            return n.elemento;
        }
    }


    /**
     * Constructor que no recibe parámeteros. {@link ArbolBinario}.
     */
    public ArbolBinarioBusqueda() {
	     // Aquí va su código.
       super();
    }

    /**
     * Construye un árbol binario ordenado a partir de una colección. El árbol
     * binario ordenado tiene los mismos elementos que la colección recibida.
     * @param coleccion la colección a partir de la cual creamos el árbol
     *        binario ordenado.
     */
    public ArbolBinarioBusqueda(Coleccionable<T> coleccion) {
        // Aquí va su código.
        super(coleccion);
    }

    /**
     * Agrega un nuevo elemento al árbol. El árbol conserva su orden in-order.
     * @param elemento el elemento a agregar.
     */
    @Override public void agrega(T elemento) {
        // Aquí va su código.
        agrega(nuevoNodo(elemento),raiz);
    }

    public void agrega(Nodo<T> e, Nodo<T> n) {
        if (e.elemento == null)
            throw new IllegalArgumentException();
        if (raiz == null) {
            raiz = e;
            tamanio++;
            return;
        }
        if (e.elemento.compareTo(n.elemento) <= 0 ) {
            if (n.izquierdo == null) {
                n.izquierdo = e;
                n.izquierdo.padre = n;
                tamanio++;
                return;
            }
            agrega(e,n.izquierdo);
        } else {
            if(n.derecho == null) {
                n.derecho = e;
                n.derecho.padre = n;
                tamanio++;
                return;
            }
            agrega(e,n.derecho);
        }
    }

    /**
     * Elimina un elemento. Si el elemento no está en el árbol, no hace nada; si
     * está varias veces, elimina el primero que encuentre (in-order). El árbol
     * conserva su orden in-order.
     * @param elemento el elemento a eliminar.
     */
    @Override public void elimina(T elemento) {
        // Aquí va su código.
        Nodo<T> e = busca(elemento);
        if(e == null)
            return;
        if (e.izquierdo == null && e.derecho == null)
            eliminaHoja(e);
        else if(e.izquierdo == null && e.derecho != null)
            eliminarConHijoDerecho(e);
        else if (e.izquierdo != null && e.derecho == null)
            eliminarConHijoIzquierdo(e);
        else if (e.izquierdo != null && e.derecho != null)
            eliminarConDosHijos(e);
        return;
    }

    /**
    * Método auxiliar para eliminar una hoja
    * @param el nodo a eliminar
    */
    private void eliminaHoja(Nodo<T> n) {
        if (n == raiz) {
            raiz = null;
            tamanio--;
            return;
        }
        if(n.padre.izquierdo == n)
            n.padre.izquierdo = null;
        else
            n.padre.derecho = null;
        n = null;
        tamanio--;
    }

    /**
    * Método auxiliar para eliminar un vértice con un hijo izquierdo.
    * @param el vértice a eliminar
    */
    private void eliminarConHijoIzquierdo(Nodo<T> n) {
        if (n == raiz) {
            raiz = n.izquierdo;
            n.izquierdo.padre = null;
            tamanio--;
            return;
        }

        if (n.padre.izquierdo == n) {
        n.padre.izquierdo = n.izquierdo;
        n.izquierdo.padre = n.padre;
        n = null;
        } else {
            n.padre.derecho = n.izquierdo;
            n.izquierdo.padre = n.padre;
            n = null;
        }
        tamanio--;
    }

    /**
    * Método auxiliar para eliminar un vértice con hijo derecho.
    * @param el vértice a eliminar
    */
    private void eliminarConHijoDerecho(Nodo<T> n) {
        if (n == raiz) {
            raiz = n.derecho;
            n.derecho.padre = null;
            tamanio--;
            return;
        }
        if (n.padre.derecho == n) {
        n.padre.derecho = n.derecho;
        n.derecho.padre = n.padre;
        n = null;
        } else {
            n.padre.izquierdo = n.derecho;
            n.derecho.padre = n.padre;
            n = null;
        }
        tamanio--;
    }

    /**
    * Método auxiliar para eliminar un vértice con dos hijos.
    * @param el vértice a eliminar
    */
    private void eliminarConDosHijos(Nodo<T> n) {
        Nodo<T> maximoIzq = maximoEnSubarbol(n.izquierdo);
        n.elemento = maximoIzq.elemento;
        if (maximoIzq.izquierdo == null)
            eliminaHoja(maximoIzq);
        else eliminarConHijoIzquierdo(maximoIzq);
    }

    /**
     * Regresa el vértice máximo en el subárbol cuya raíz es el vértice que
     * recibe.
     * @param vertice el vértice raíz del subárbol del que queremos encontrar el
     *                máximo.
     * @return el vértice máximo el subárbol cuya raíz es el vértice que recibe.
     */
    protected Nodo<T> maximoEnSubarbol(Nodo<T> n) {
        while (n.derecho != null) {
            n = n.derecho;
        }
        return n;
    }

    /**
     * Nos dice si un elemento está contenido en el arbol.
     * @param elemento el elemento que queremos verificar si está contenido en
     *                 la arbol.
     * @return <code>true</code> si el elemento está contenido en el arbol,
     *         <code>false</code> en otro caso.
     */
    @Override public boolean contiene(T elemento){
        // Aquí va su código.
        return busca(elemento) != null;
    }

    /**
     * Gira el árbol a la derecha sobre el nodo recibido. Si el nodo no
     * tiene hijo izquierdo, el método no hace nada.
     * @param nodo el nodo sobre el que vamos a girar.
     */
    public void giraDerecha(Nodo<T> nodo) {
        // Aquí va su código.
        if (nodo.izquierdo == null)
            return;
        Nodo<T> ni = nodo.izquierdo;
        Nodo<T> derechoIzquierdo = ni.derecho;
        ni.derecho = nodo;
        ni.padre = nodo.padre;
        nodo.izquierdo = derechoIzquierdo;
        if (nodo.izquierdo != null)
            nodo.izquierdo.padre = nodo;
        if (ni.padre == null) {
            raiz = ni;
            nodo.padre = ni;
            return;
        }
        if (esDerecho(nodo))
            nodo.padre.derecho = ni;
        else nodo.padre.izquierdo = ni;
        nodo.padre = ni;
    }

    protected boolean esDerecho(Nodo<T> n) {
        if (n.padre.derecho == n)
            return true;
        else return false;
    }

    /**
     * Gira el árbol a la izquierda sobre el nodo recibido. Si el nodo no
     * tiene hijo derecho, el método no hace nada.
     * @param nodo el nodo sobre el que vamos a girar.
     */
    public void giraIzquierda(Nodo<T> nodo) {
        // Aquí va su código.
        if (nodo.derecho == null)
            return;
        Nodo<T> nodod = nodo.derecho;
        Nodo<T> izquierdoDerecho = nodod.izquierdo;
        nodo.derecho = izquierdoDerecho;
        nodod.izquierdo = nodo;
        nodod.padre = nodo.padre;
        if (nodo.derecho != null)
            nodo.derecho.padre = nodo;
        if (nodod.padre == null) {
            raiz = nodod;
            nodo.padre = nodod;
            return;
        }
        if (esDerecho(nodo))
            nodo.padre.derecho = nodod;
        else nodo.padre.izquierdo = nodod;
        nodo.padre = nodod;
    }


    /**
     * Regresa un iterador para iterar el árbol. El árbol se itera en orden.
     * @return un iterador para iterar el árbol.
     */
    @Override public Iterator<T> iterator() {
        return new Iterador();
    }

}
