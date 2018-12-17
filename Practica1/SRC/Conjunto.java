/**
 * <p> Clase que modela conjuntos </p> <p>Esta clase sirve para crear
 * conjuntos y hacer operaciones entre ellos</p>
 * @author Alejandro Hernández Mora <alejandrohmora@ciencias.unam.mx>
 * @version 1.0
 */
import java.util.Iterator;

public class Conjunto <T> implements Iterable, Conjuntable<T> {
    /* Arreglo donde guardaremos los elementos de un conjunto */
    private T [] conjunto;
    /* Número de elementos que tiene el conjunto*/
    private int elementos;

    private class IteradorConjunto implements Iterator{
        /* Variable para contar cuantos elementos hay en el conjunto
        no debe tener repeticiones, ni espacios en blanco para que funcione */
      	int contador;
      	public IteradorConjunto(){
      	    //Aqui va tu codigo.
      	    contador = -1;
      	}

      	public boolean hasNext(){
      	    //Aqui va tu codigo.
      	    return ++contador<conjunto.length;
      	}

      	public Object next(){
      	    //Aqui va tu codigo.
            return conjunto[contador];
      	}
    }

    public Conjunto(){
        //Aqui va tu codigo.
        conjunto = null;
        elementos = 0;
    }

    public Conjunto(Conjunto<T> c){
        //Aqui va tu codigo.
        conjunto = c.conjunto;
        elementos = c.elementos;
    }

    public Conjunto(T[] elemento){
        //Aqui va tu codigo.
        conjunto = elemento;
        elementos = elemento.length;
    }

    /**
     * Método que nos dice si el conjunto está vacío.
     * @return <code>true</code> si el conjunto está vacío, <code>false</code>
     * en otro caso.
     */
    @Override
    public boolean esVacio(){
	       //Aqui va tu codigo.
	       return elementos==0;
    }

    /**
     * Método para obtener el tamaño de un conjunto
     * @return número de elementos en el conjunto
     */
    @Override
    public int cardinalidad(){
	       //Aqui va tu codigo.
         return elementos;
    }

    /**
     * Método para eliminar todos los elementos de un conjunto
     */
    @Override

    public void vaciar(){
	       //Aqui va tu codigo.
	       if (elementos>0){
            this.conjunto = null;
            this.elementos = 0;
         }
    }

    /**
     * Método para agregar un elemento al conjunto
     * @param elemento Objeto que se incorporara al conjunto
     */
    @Override
    public void agregar(T elemento){
        //Aqui va tu codigo.
        if (contiene(elemento))
            return;

        T [] nuevo = (T[]) new Object[elementos + 1];
        if (elementos>0){
            int cont=0;
            while (cont<elementos){
                nuevo[cont] = this.conjunto[cont];
                cont++;
            }
            nuevo[nuevo.length - 1] = elemento;
            conjunto = nuevo;
            elementos = nuevo.length;
        } else {
            nuevo[0] = elemento;
            conjunto = nuevo;
            elementos = 1;
        }
    }

    /**
     * Método para eliminar un <code>elemento</code> del conjunto
     * @param elemento Objeto que se eliminara del conjunto
     */
    @Override
    public void eliminar(T elemento){
	       //Aqui va tu codigo.
	       if (contiene(elemento)){
             T [] nuevo = (T[]) new Object[this.elementos - 1];
             int cont=0;
             int dif=0;
             while (dif<elementos - 1){
                 if (!elemento.equals(this.conjunto[cont]))
                    nuevo[dif] = this.conjunto[cont];
                 else{
                    nuevo[dif] = this.conjunto[++cont];
                 }
                 dif++;
                 cont++;
             }
             this.conjunto = nuevo;
             this.elementos = nuevo.length;
        }
    }

    /**
     * Método para ver si un elemento pertenece al conjunto
     * @param elemento Objeto que se va a buscar en el conjunto
     * @return <code>true</code> si el elemento esta en el conjunto,
     * <code>false</code> en otro caso.
     */
    @Override
    public boolean contiene (T elemento){
	       //Aqui va tu codigo.
	       int cont=0;
         while (cont<this.elementos){
            if (conjunto[cont].equals(elemento))
                return true;
            cont++;
         }

         return false;
    }

    /**
     * Método para calcular la union de dos conjuntos
     * @param c1 conjunto con el que se calculará la unión
     * @return Conjuntable conjunto que contiene la unión
     */
    @Override
    public Conjuntable union(Conjuntable c){
	       //Aqui va tu codigo.
         if (!(c instanceof Conjunto))
            throw new ClassCastException();

         Conjunto<T> union = new Conjunto<T>((Conjunto<T>) c);

         int cont=0;
         while (cont<elementos){
             union.agregar(conjunto[cont++]);
         }


         return union;
    }

    /**
     * Método para calcular la intersección de dos conjuntos
     * @param c conjunto con el que se calculará la intersección
     * @return Conjuntable que con tiene la intersección
     */
    @Override
    public Conjuntable interseccion(Conjuntable c){
	        //Aqui va tu codigo.
          if (!(c instanceof Conjunto))
             throw new ClassCastException();
          Conjunto<T> cc = (Conjunto<T>) c;
          Conjunto<T> in = new Conjunto<T>();
          int cont = 0;
          while (cont<elementos){
              if (cc.contiene(conjunto[cont]))
                  in.agregar(conjunto[cont]);
              cont++;
          }
          return in;
    }

    /**
     * Método para calcular la diferencia de dos conjuntos
     * @param c conjunto con el que se va a calcular la diferencia
     * @return Conjuntable con la diferencia
     */
    @Override
    public Conjuntable diferencia(Conjuntable c){
	       //Aqui va tu codigo.
         if (!(c instanceof Conjunto))
            throw new ClassCastException();
	       Conjunto<T> dif = (Conjunto<T>) c;
         T [] d = this.conjunto;
         Conjunto<T> diferencia = new Conjunto<T>(d);
	       int cont = 0;
         while (cont<dif.elementos){
              if (contiene(dif.conjunto[cont])){
                    diferencia.eliminar(dif.conjunto[cont]);
              }
              cont++;
         }
         return diferencia;
    }

    /**
     * Método para calcular la diferencia simétrica de dos conjuntos
     * @param c conjunto con el que se va a calcular la diferencia simétrica
     * @return Conjuntable con la diferencia simétrica
     */
    @Override
    public Conjuntable diferenciaSimetrica(Conjuntable c){
	       //Aqui va tu codigo.
         if (!(c instanceof Conjunto))
            throw new ClassCastException();
	       Conjunto<T> dc = (Conjunto<T>) c;
	       Conjunto<T> d1 = (Conjunto<T>) diferencia(dc);
         Conjunto<T> d2 = (Conjunto<T>) dc.diferencia(this);
	       return d1.union(d2);
    }

    /**
     * Método para determinar si un conjunto esta contenido en otro
     * @param c conjunto en se va a probar si el que llama es subconjunto
     * @return boolean true si el conjunto que llama a este metodo es
     *         subconjunto del parametro y false en otro caso
     */
    @Override
    public boolean subconjunto(Conjuntable c){
	       //Aqui va tu codigo.
         if (!(c instanceof Conjunto))
            throw new ClassCastException();
	       Conjunto<T> cc = (Conjunto<T>) c;
         int cont = 0;
         if (this.elementos>cc.elementos)
            return false;
         while(cont<elementos){
            if (!cc.contiene(this.conjunto[cont]))
                return false;
            cont++;
         }
	       return true;
    }

    @Override
    public String toString(){
	       //Aqui va tu codigo.
	       if (esVacio())
            return "{}";

	       int cont = 0;
         String sa = "";
         while (elementos>0 && cont<elementos-1){
            sa += conjunto[cont++] + ",";
         }
	       return String.format("{%s}", sa + conjunto[elementos - 1]);
    }

    /**
     * Método para crear un iterador sobre un conjunto
     * @return Iterator iterador sobre el conjunto.
     */
    @Override
    public java.util.Iterator iterator(){
	       //Aqui va tu codigo.
	       return new IteradorConjunto();
    }
}
