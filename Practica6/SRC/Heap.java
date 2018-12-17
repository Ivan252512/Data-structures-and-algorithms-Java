import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Clase para heaps mínimos (<i>min heaps</i>). Podemos crear un heap
 * mínimo con <em>n</em> elementos en tiempo <em>O</em>(<em>n</em>), y podemos
 * agregar y actualizar elementos en tiempo <em>O</em>(log <em>n</em>). Eliminar
 * el elemento mínimo también nos toma tiempo <em>O</em>(log <em>n</em>).
 */
public abstract class Heap<T extends Comparable<T>> implements Coleccionable<T> {

    /* Clase privada para iteradores de heaps. */
    private class Iterador<T extends Comparable<T>> implements Iterator<T> {

        /* Índice del iterador. */
        private int actual;
        /* El heap mínimo. */
        private Heap<T> heap;

        /* Construye un nuevo iterador, auxiliándose del heap mínimo. */
        public Iterador(Heap<T> heap) {
            // Aquí va su código.
            this.heap = heap;
            actual = 0;
        }

        /* Nos dice si hay un siguiente elemento. */
        @Override public boolean hasNext() {
            // Aquí va su código.
            return actual != siguiente;
        }

        /* Regresa el siguiente elemento. */
        @Override public T next() {
            // Aquí va su código.
            if (actual == siguiente)
                throw new NoSuchElementException();
            return (T) arreglo[actual++];
        }

        /* No lo implementamos: siempre lanza una excepción. */
        @Override public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /* El siguiente índice dónde agregar un elemento. */
    private int siguiente;
    /* El arreglo que guarda los datos con la estructura de árbol ue requiere el heap.*/
    private T[] arreglo;

    /* Truco para crear arreglos genéricos. Es necesario hacerlo así por cómo
       Java implementa sus genéricos; de otra forma obtenemos advertencias del
       compilador. */
    @SuppressWarnings("unchecked") private T[] creaArregloGenerico(int n) {
        return (T[])(new Comparable[n]);
    }

    /**
     * Constructor sin parámetros. Es más eficiente usar {@link
     * #HeapMinimo(Lista)}, pero se ofrece este constructor por completez.
     */
    public Heap() {
        // Aquí va su código.
        arreglo = creaArregloGenerico(1000);
        siguiente = 0;
    }

    /**
     * Constructor para heap mínimo que recibe una lista. Es más barato
     * construir un heap con todos sus elementos de antemano (tiempo
     * <i>O</i>(<i>n</i>)), que el insertándolos uno por uno (tiempo
     * <i>O</i>(<i>n</i> log <i>n</i>)).
     * @param lista la lista a partir de la cuál queremos construir el
     *              heap.
     */
    public Heap(Lista<T> lista) {
	     // Aquí va su código.
       int n = lista.longitud();
       arreglo = creaArregloGenerico(n);
       for (T e : lista) {
           arreglo[siguiente++] = e;
       }
       for (int i = n / 2; i >= 0 ; i--)
           reordena(i);
    }

    /*
     * Métodos auxiliares.
     */
    private void intercambia(int i, int j){
	     // Aquí va su código.
       T e = arreglo[i];
       arreglo[i] = arreglo[j];
       arreglo[j] = e;
    }

    private int izquierdo(int i) {
      return i * 2 + 1;
    }

    private int derecho(int i) {
      return i * 2 + 2;
    }

    private int padre(int i){
    	// Aquí va su código.
      if (0 < i && i < siguiente)
          return (i-1) / 2;
      return -1;
    }

    /**
     * Agrega un nuevo elemento en el heap.
     * @param elemento el elemento a agregar en el heap.
     */
    @Override public void agrega(T elemento) {
        // Aquí va su código.
        if (arreglo.length <= siguiente) {
          T[] array = creaArregloGenerico(2 * siguiente);
          for (int i = 0; i < siguiente; i++)
            array[i] = arreglo[i];
          arreglo = array;
        }
        arreglo[siguiente] = elemento;
        reordena(siguiente);
        siguiente += 1;
    }

    /**
     * Elimina el elemento hasta arriba del heap.
     * @return el elemento mínimo o máximo del heap.
     * @throws IllegalStateException si el heap es vacío.
     */
    public T elimina() {
        // Aquí va su código.
        if (siguiente == 0)
          throw new IllegalStateException("El montículo es vacio.");
        T e = arreglo[0];
        intercambia(0, --siguiente);
        arreglo[siguiente] = null;
        if (siguiente != 0)
          reordena(0);
        return e;
    }

    /**
     * Puesto que no deberíamos eliminar un elemento que
     * no sea el mínimo, lanzamos una excepción. En una
     * implementación más aplicada es posible implementar
     * esta operación con los métodos ya implementados sin mucho
     * esfuerzo.
     * @param elemento a eliminar del heap.
     * @throws IllegalStateException. Esta operación no debería ser posible
     * en un Heap
     */
    @Override public void elimina(T elemento) {
	     throw new IllegalStateException("Esta operación no debería ser válida para Heaps");
    }

    /**
     * Nos dice si un elemento está contenido en el heap.
     * @param elemento el elemento que queremos saber si está contenido.
     * @return <code>true</code> si el elemento está contenido,
     *         <code>false</code> en otro caso.
     */
    @Override public boolean contiene(T elemento) {
        // Aquí va su código.
        for (T e : arreglo)
            if (elemento.equals(e))
                return true;
        return false;
    }

    /**
     * Nos dice si el heap es vacío.
     * @return <tt>true</tt> si ya no hay elementos en el heap,
     *         <tt>false</tt> en otro caso.
     */
    public boolean esVacio() {
	     // Aquí va su código.
       return siguiente == 0;
    }

    /**
     * Reordena un elemento en el árbol.
     * La implementación se deja a la clase concreta, pues el reordenamiento
     * depende de si es un heap mínimo o máximo.
     * @param elemento el elemento que hay que reordenar.
     */
    public abstract void reordena(int elemento);

    /**
     * Este método hace la chamba, dependiendo de si
     * el heap es mínimo o máximo.
     */
    protected void reordena(int elemento, boolean esMin){
	     // Aquí va su código.
       int i = izquierdo(elemento);int j = derecho(elemento);
       int k; if (esMin) {
         k = getMenor(elemento, i, j);
         if (k != elemento) {
           intercambia(elemento, k);
           reordena(k, esMin);
         }
       } else {
         k = getMayor(elemento, i, j);
         if (k != elemento) {
           intercambia(elemento, k);
           reordena(k, esMin);
         }
       }
    }

    /* Método auxiliar que regresa el índice en el arreglo que
     * tiene al elemento menor de tres.
     */
    private int getMenor(int elemento, int i, int d){
	     // Aquí va su código.
       T a = arreglo[elemento];
       T o = i < siguiente ? arreglo[i] : null;
       T u = d < siguiente ? arreglo[d] : null;
       T b = o.compareTo(a) < 0 ? o : o == null ? a : a;
       b = u.compareTo(b) < 0 ? u : u == null ? b : b;
       int r = b.equals(o) ? i : b.equals(a) ? elemento : d;
       return r;
    }

    /* Método auxiliar que regresa el índice en el arreglo que
     * tiene al elemento menor de tres.
     */
    private int getMayor(int elemento, int i, int d){
	     // Aquí va su código.
       T a = arreglo[elemento];
       T o = i < siguiente ? arreglo[i] : null;
       T u = d < siguiente ? arreglo[d] : null;
       T b = o.compareTo(a) > 0 ? o : o == null ? a : a;
       b = u.compareTo(b) > 0 ? u : u == null ? b : b;
       int r = b.equals(o) ? i : b.equals(a) ? elemento : d;
       return r;
    }

    /**
     * Regresa el número de elementos en el heap.
     * @return el número de elementos en el heap.
     */
    @Override public int getTamanio() {
        // Aquí va su código.
        return siguiente;
    }

    @Override
    /**
     * Regresa la representación en cadena del heap.
     * @return la representación en cadena del árbol.
     */
    public String toString(){
	       String s = "[";
	       for(int i =0; i<siguiente;i++){
	          s+= i==siguiente-1? arreglo[i]: arreglo[i]+"|";
	        }
	        return s+="]";
    }

    /**
     * Regresa un iterador para recorrer el heap.
     * @return un iterador para recorrer el heap.
     */
    @Override public Iterator<T> iterator() {
        return new Iterador<T>(this);
    }
}
