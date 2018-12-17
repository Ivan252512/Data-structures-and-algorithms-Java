import java.util.Iterator;
/**
 * Clase para Colas genéricas.
 */
public class Cola<T> implements Coleccionable<T>{

	private class Nodo<T> {
        /** El elemento del nodo. */
        public T elemento;
        /** El siguiente nodo. */
        public Nodo<T> siguiente;

        /**
         * Construye un nodo con un elemento.
         * @param elemento el elemento del nodo.
         */
        public Nodo(T elemento) {
            // Aquí va su código.
						this.elemento = elemento;
        }
    }

		private class IteradorCola<T> implements Iterator<T> {
        public Nodo<T> siguiente;

        public IteradorCola() {
					//Aqui va tu codigo
					siguiente = (Nodo <T>)tope;
        }

        /* Nos dice si hay un elemento siguiente. */
				@Override
        public boolean hasNext() {
            //Aqui va tu codigo
						return siguiente!=null;
        }

        /* Nos da el elemento siguiente. */
				@Override
        public T next() {
            //Aqui vatu codigo
            T e = siguiente.elemento;
            siguiente=siguiente.siguiente;
            return e;
        }
				@Override
				public void remove() {
            Iterator.super.remove();
        }

    }
		private Nodo<T> tope;
		private Nodo<T> rabo;

		private int elementos;

		public Cola(){//Aqui no hay que hacer nada
		}

		public Cola(T[] elementos){
			Cola<T> c = new Cola<T>();
			for (int i=0; i<elementos.length;i++ ){
					c.agrega(elementos[i]);
			}
		}
		public Cola(Coleccionable<T> elementos){
			//Aqui va tu codigo
			Cola<T> c = new Cola<T>();
			for (T elem : elementos) {
				c.agrega(elem);
			}
		}

    /**
     * Agrega un elemento al tope de la Cola.
     * @param elemento el elemento a agregar.
     */
    public void push(T elemento) {
      //aqui va tu codigo
			if(elemento==null)
					throw new IllegalArgumentException();
			Nodo<T> n=new Nodo<T>(elemento);
			if (!esVacio()){
				n.siguiente=tope;
				tope=n;
				elementos++;
			}else{
				tope=n;
				rabo=n;
				elementos++;
			}
    }
    /**
     * Elimina el elemento del tope de la Cola y lo regresa.
     * @return el elemento en el tope de la Cola.
     */
    public T pop() {
		//aqui va tu codigo
			if (elementos<1)
				return null;
			T aux = tope.elemento;
			if (elementos==1){
				tope = null;
				rabo = null;
				elementos--;
				return aux;
			}
			tope = tope.siguiente;
			elementos--;
			return aux;
    }
		/**
     * Nos permite ver el elemento en el tope de la Cola
     * @return el elemento en un extremo de la estructura.
     */
		public T peek(){
			//Aqui va tu codigo
			if (elementos<1)
				return null;
			return tope.elemento;
		}

		/**
     * Agrega un elemento a la Cola.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void agrega(T elemento){
			if(elemento==null)
					throw new IllegalArgumentException();
			Nodo<T> n=new Nodo<T>(elemento);
			if (!esVacio()){
				rabo.siguiente=n;
				rabo=n;
				elementos++;
			}else{
				tope=n;
				rabo=n;
				elementos++;
			}
		}
		/**
     * Nos dice si un elemento está contenido en la Cola.
     * @param elemento el elemento que queremos verificar si está contenido en
     *                 la Cola.
     * @return <code>true</code> si el elemento está contenido en la Cola,
     *         <code>false</code> en otro caso.
     */
		@Override
    public boolean contiene(T elemento){
			Nodo<T> n = this.tope;
			while(n!=null){
				if(n.elemento.equals(elemento))
					return true;
				n = n.siguiente;
			}
			return false;
		}

		/**
     * Elimina un elemento de la Cola.
     * @param elemento el elemento a eliminar.
     */
    public void elimina(T elemento){
			if(!esVacio() && contiene(elemento)){
				if(tope.siguiente==null){
					tope=null;
					rabo=null;
					elementos--;
				}else{
					Nodo<T> n=tope;
					Nodo<T> ant=null;
					while(n.siguiente!=null && !n.elemento.equals(elemento)){
						ant = n;
						n=n.siguiente;
					}
					if(ant==null){
						pop();
					}else if (n.siguiente==null){
						rabo = ant;
						ant.siguiente = null;
						elementos--;
					}else{
						ant.siguiente = n.siguiente;
						elementos--;
					}
				}
			}
		}
		/**
     * Nos dice si la Cola está vacía.
     * @return <tt>true</tt> si la Cola no tiene elementos,
     *         <tt>false</tt> en otro caso.
     */
		@Override
    public boolean esVacio() {
        // Aquí va su código.
				return tope==null;
    }

		/**
     * Regresa el número de elementos en la Cola.
     * @return el número de elementos en la Cola.
     */
		@Override
    public int getTamanio(){
			//Aqui vatu codigo.
			return elementos;
		}

		@Override
		public Iterator<T> iterator(){
			//Aqui va tu codigo
			return new IteradorCola<T>();
		}

		/**
     * Regresa una representación en cadena de la cola.
     * @return una representación en cadena de la cola.
     */
    @Override public String toString() {
        // Aquí va su código.
        if (elementos==0){
          return "[]";
        }
        String s = "[";
				for (T elem : this) {
						s += String.format("%s, ", elem);
				}
				s += String.format("]");
				return s;
			}
}
