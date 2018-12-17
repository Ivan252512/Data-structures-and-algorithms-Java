import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * <p> Clase concreta para modelar la estructura de datos Lista</p>
 * <p>Esta clase implementa una Lista genérica, es decir que es homogénea pero
 * puede tener elementos de cualquier tipo.
 * @author Alejandro Hernández Mora <alejandrohmora@ciencias.unam.mx>
 * @version 1.0
 */
public class Lista<T> implements Listable<T>, Iterable{

    /* Clase interna para construir la estructura */
    private class Nodo{
  	/* Referencias a los nodos anterior y siguiente */
  	public Nodo anterior, siguiente;
  	/* El elemento que almacena un nodo */
  	public T elemento;

  	/* Unico constructor de la clase */
  	public Nodo(T elemento){
	    //aqui va tu codigo
	    this.elemento = elemento;
	  }

        public boolean equals(Nodo n){
            //aqui va tu codigo
            return elemento.equals(n.elemento);
        }
    }

    private class IteradorLista implements Iterator{
        /* La lista a recorrer*/
        private Lista<T> lista;
        /* Elementos del centinela que recorre la lista*/
        private Lista<T>.Nodo anterior, siguiente;


        public IteradorLista(Lista<T> lista){
            siguiente = cabeza;
        }
        @Override
        public boolean hasNext() {
            //aqui va tu codigo
            return siguiente!=null;
        }

        @Override
        public T next() {
            //aqui va tu codigo
            if(siguiente==null){
              throw new NoSuchElementException();
            }
            T e= siguiente.elemento;
            anterior=siguiente;
            siguiente=siguiente.siguiente;
            return e;
            }

        @Override
        public void remove() {
            Iterator.super.remove(); //To change body of generated methods, choose Tools | Templates.
        }
    }

    /* Atributos de la lista */
    private Nodo cabeza, cola;
    private int longitud;

    /**
     * Método que nos dice si las lista está vacía.
     * @return <code>true</code> si el conjunto está vacío, <code>false</code>
     * en otro caso.
     */
    public boolean esVacia(){
        //aqui va tu codigo
        return cabeza==null;
    }
    /**
     * Método para eliminar todos los elementos de una lista
     */
    public void vaciar(){
        //aqui va tu codigo
        while(longitud!=0){
            eliminaPrimero();
        }
    }
    /**
     * Método para obtener el tamaño de la lista
     * @return tamanio Número de elementos de la lista.
     **/
    public int longitud(){
        //aqui va tu codigo
        return longitud;
    }
    /**
     * Método para agregar un elemento a la lista.
     * @param elemento Objeto que se agregará a la lista.
     */
    public void agregar(T elemento){
        //aqui va tu codigo
        agregarAlFinal(elemento);
    }
        /**
     * Método para agregar al inicio un elemento a la lista.
     * @param elemento Objeto que se agregará al inicio de la lista.
     */
    public void agregarAlInicio(T elemento){
    	//aqui va tu codigo
      if(elemento==null)
          throw new IllegalArgumentException();
      Nodo n=new Nodo(elemento);
      if (!esVacia()){
        cabeza.anterior=n;
        cabeza.anterior.siguiente=cabeza;
        cabeza=n;
        longitud++;
      }else{
        cabeza=n;
        cola=n;
        longitud++;
      }
    }
    /**
     * Método para agregar al final un elemento a la lista.
     * @param elemento Objeto que se agregará al inicio de la lista.
     */
    public void agregarAlFinal(T elemento){
        //aqui va tu codigo
        if(elemento==null)
            throw new IllegalArgumentException();
        Nodo n=new Nodo(elemento);
        if (!esVacia()){
          cola.siguiente=n;
          cola.siguiente.anterior=cola;
          cola=n;
          longitud++;
        }else{
          cabeza=n;
          cola=n;
          longitud++;
        }
    }
    /**
     * Método para verificar si un elemento pertenece a la lista.
     * @param elemento Objeto que se va a buscar en la lista.
     * @return <code>true</code> si el elemento esta en el lista y false en otro caso.
     */
    public boolean contiene(T elemento){
        //aqui va tu codigo
        Nodo aux=cabeza;
        for (int i=1;i<=longitud;i++){
          if(aux.elemento.equals(elemento)){
            return true;
          }
          aux=aux.siguiente;
        }
        return false;
    }
    /**
     * Método para eliminar un elemento de la lista.
     * @param elemento Objeto que se eliminara de la lista.
     */
    public void eliminar(T elemento){
        //aqui va tu codigo
        if(!esVacia() && contiene(elemento)){
          if(cabeza.siguiente==null){
            cabeza=null;
            cola=null;
            longitud--;
          }else{
            Nodo n=cabeza;
            while(n.siguiente!=null && !n.elemento.equals(elemento)){
              n=n.siguiente;
            }
            if(n.anterior==null){
              eliminaPrimero();
            }else if (n.siguiente==null){
              eliminaUltimo();
            }else{
              n.siguiente.anterior=n.anterior;
              n.anterior.siguiente=n.siguiente;
              n.siguiente=null;
              n.anterior=null;
              longitud--;
            }
          }
        }
    }

    /**
     * Elimina el primer elemento de la lista y lo regresa.
     * @return el primer elemento de la lista antes de eliminarlo.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T eliminaPrimero() {
        // Aquí va su código.
        if(longitud==0)
            throw new NoSuchElementException();
        if(!esVacia()){
          Nodo n=cabeza;
          if(cabeza.siguiente==null){
            cabeza=null;
            cola=null;
          }else{
            cabeza=cabeza.siguiente;
            cabeza.anterior.siguiente=null;
            cabeza.anterior=null;
          }
          longitud--;
          return n.elemento;
        }
        return null;
    }

    /**
     * Elimina el último elemento de la lista y lo regresa.
     * @return el último elemento de la lista antes de eliminarlo.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T eliminaUltimo() {
        // Aquí va su código.
        if(longitud==0)
            throw new NoSuchElementException();
        if(!esVacia()){
          Nodo n=cola;
          if(cola.anterior==null){
            cabeza=null;
            cola=null;
          }else{
            cola=cola.anterior;
            cola.siguiente.anterior=null;
            cola.siguiente=null;
          }
          longitud--;
          return n.elemento;
        }
        return null;
    }

    /**
     * Método que devuelve la posición en la lista que tiene la primera
     * aparición del <code> elemento</code>.
     * @param elemento El elemnto del cuál queremos saber su posición.
     * @return i la posición del elemento en la lista, -1, si no se encuentra en ésta.
     */
    public int indiceDe(T elemento){
        //aqui va tu codigo
        if(contiene(elemento)){
          int contador=0;
          Nodo aux=cabeza;
          while(!aux.elemento.equals(elemento)){
            aux=aux.siguiente;
            contador++;
          }
          return contador;
        }
        return -1;
    }

    /**
     * Método que nos dice en qué posición está un elemento en la lista
     * @param i La posición cuyo elemento deseamos conocer.
     * @return <code> elemento </code> El elemento que contiene la lista,
     * <code>null</code> si no se encuentra
     * @throws IndexOutOfBoundsException Si el índice es < 0 o >longitud()
     */
    public T getElemento(int i)throws IndexOutOfBoundsException{
        //aqui va tu codigo
        if(0<=i && i<longitud){
          Nodo n=cabeza;
          int cont=0;
          while(n.siguiente!=null && cont<i){
            n=n.siguiente;
            cont++;
          }
          return n.elemento;
        }
        throw new IndexOutOfBoundsException();
    }

    /**
     * Método que devuelve una copia de la lista, pero en orden inverso
     * @return Una copia con la lista l revés.
     */
    public Lista<T> reversa(){
        //aqui va tu codigo
        Lista <T> rev=new Lista<T>();
        Nodo n=cola;
        for(int i=1;i<=longitud;i++){
          rev.agregarAlFinal(n.elemento);
          n=n.anterior;
        }
        return rev;
    }

    /**
     * Método que devuelve una copi exacta de la lista
     * @return la copia de la lista.
     */
    public Lista<T> copia(){
        //aqui va tu codigo
        Lista <T>l=new Lista<T>();
        Nodo n=cabeza;
        for(int i=1;i<=longitud;i++){
          l.agregarAlFinal(n.elemento);
          n=n.siguiente;
        }
        return l;
    }

    /**
     * Método que nos dice si una lista es igual que otra.
     * @param o objeto a comparar con la lista.
     * @return <code>true</code> si son iguales, <code>false</code> en otro caso.
     */
    @Override
    public boolean equals(Object o){
        //aqui va tu codigo
        if (o == null || getClass() != o.getClass())
            return false;
        @SuppressWarnings("unchecked") Lista<T> lista = (Lista<T>)o;
        // Aquí va su código.
        if(longitud!=lista.longitud())
            return false;
        Nodo aux1=this.cabeza;
        Nodo aux2=lista.cabeza;
        for(int i=0;i<longitud;i++){
            if(!aux1.elemento.equals(aux2.elemento))
                return false;
            aux1=aux1.siguiente;
            aux2=aux2.siguiente;
        }
        return true;
    }

    /**
     * Método que devuelve un iterador sobre la lista
     * @return java.util.Iterador -- iterador sobre la lista
     */
    @Override
    public java.util.Iterator iterator(){
        return new IteradorLista(this);
    }

    /**
     * Método que devuelve una copia de la lista.
     * @param <T> Debe ser un tipo que extienda Comparable, para poder distinguir
     * el orden de los elementos en la lista.
     * @param l La lista de elementos comparables.
     * @return copia de la lista ordenada.
     */
    public static <T extends Comparable<T>> Lista <T> mergesort(Lista<T>l){
        //aqui va tu codigo
        Lista<T> ilist = l.copia();
        if(ilist.longitud() <= 1) {
        			return ilist;
    		} else {
    			Lista<T> izquierda = new Lista<T>();
    			Lista<T> derecha = new Lista<T>();

    			int mitad = ilist.longitud() / 2;
    			for(int i=0;i<mitad;i++) {
    				izquierda.agregarAlFinal(ilist.getElemento(i));
    			}
    			for(int i=mitad;i<ilist.longitud();i++) {
    				derecha.agregarAlFinal(ilist.getElemento(i));
    			}
    			return merge(mergesort(izquierda), mergesort(derecha));
    		}
    }

    private static <T extends Comparable<T>> Lista<T> merge(Lista<T> a, Lista<T> b) {
        Lista<T> ret = new Lista<T>();
        int a_idx = 0, b_idx = 0;
     		while(a_idx+1 <= a.longitud() || b_idx+1 <= b.longitud()) {
     			if(a_idx+1 <= a.longitud() && b_idx+1 <= b.longitud()) {
     				if(a.getElemento(a_idx).compareTo(b.getElemento(b_idx))<=0) {
     					ret.agregarAlFinal(a.getElemento(a_idx));
     					a_idx++;
     				} else {
     					ret.agregarAlFinal(b.getElemento(b_idx));
     					b_idx++;
     				}
     			} else if(a_idx+1 <= a.longitud()) {
     				ret.agregarAlFinal(a.getElemento(a_idx));
     				a_idx++;
     			} else if(b_idx+1 <= b.longitud()) {
     				ret.agregarAlFinal(b.getElemento(b_idx));
     				b_idx++;
     			}
     		}
        return ret;
    }

    /**
     * Regresa una representación en cadena de la lista.
     * @return una representación en cadena de la lista.
     */
    @Override public String toString() {
        // Aquí va su código.
        if (longitud()==0){
          return "[]";
        }
        String s = "[";
        for (int i = 0; i < longitud()-1; i++)
            s += String.format("%s, ", getElemento(i));
        s += String.format("%s]", getElemento(longitud()-1));
        return s;
    }

}
