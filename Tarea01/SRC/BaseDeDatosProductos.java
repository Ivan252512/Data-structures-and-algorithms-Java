import java.io.Serializable;

/**
 * <p> Interfaz para base de datos de productos </p> <p>Esta clase contiene las
 * operaciones elementales para una base de datos </p>
 * @author Carlos Iván Pineda Santiago <ivanpineda@ciencias.unam.mx>
 * @version 1.0
 */
public class BaseDeDatosProductos implements
             BaseDeDatos<Producto>, Serializable{
    /* Arreglo donde guardaremos los productos */
    private Producto [] productos;
    /* Cantidad de productos */
    private int numProductos;
    /* Ventas de discos. */
    private int ventasDiscos = 0;
    /* Ventas de libros. */
    private int ventasLibros = 0;
    /* Ventas de películas. */
    private int ventasPeliculas = 0;
    /* Ventas de videojuegos. */
    private int ventasVJ = 0;

    /**
     * Constructor por omisión de la base de datos.
     */
    public BaseDeDatosProductos(){
        numProductos = 0;
    }

    /**
     * Constructor de la base de datos.
     * @param producto el primero producto.
     */
    public BaseDeDatosProductos(Producto producto){
        productos = new Producto[1];
        productos[0] = producto;
        numProductos = 1;
    }

    /**
     * Constructor de la base de datos.
     * @param productos un arreglo de productos.
     */
    public BaseDeDatosProductos(Producto [] productos){
        this.productos = productos;
        numProductos = productos.length;
    }

    /**
     * Constructor de la base de datos.
     * @param bddProductos una bdd de productos.
     */
    public BaseDeDatosProductos(BaseDeDatosProductos bddProductos){
        this.productos = bddProductos.productos;
        numProductos = bddProductos.numProductos;
    }

    /**
     * Método que nos dice si la base de datos está vacía.
     * @return <code>true</code> si la bdd está vacía, <code>false</code>
     * en otro caso.
     */
    public boolean esVacio(){
       return numProductos == 0;
    }

    /**
     * Método para obtener el tamaño de la bdd.
     * @return número de registros en la bdd.
     */
    public int getTamanio(){
        return numProductos;
    }

    /**
     * Método para eliminar todos los elementos de una bdd.
     */
    public void vaciar(){
        productos = null;
        numProductos = 0;
    }

    /**
     * Método para agregar un elemento a la bdd.
     * @param producto Objeto que se incorporara a la bdd.
     */
    public void agregar(Producto producto){
        if (numProductos>0){
            Producto [] nuevo = new Producto[this.numProductos + 1];
            int cont=0;
            while (cont<productos.length){
              nuevo[cont] = this.productos[cont++];
            }
            nuevo[nuevo.length-1] = producto;
            this.productos = nuevo;
            this.numProductos = nuevo.length;
        }else{
          this.productos = new Producto[1];
          this.productos[0] = producto;
          this.numProductos = 1;
        }
    }

    /**
     * Método para obtener un producto con su número de producto.
     * @param id número del producto.
     * @return producto el producto a consultar, si no esta en la bdd regresa
     * null.
     */
    public Producto getProducto(int id){
        int cont = 0;
        while(cont<numProductos){
            if(productos[cont].getNoProducto() == id)
                return productos[cont];
            cont++;
        }
        throw new NullPointerException();
    }

    /**
     * Método para obtener la posicion de un producto con su número de producto.
     * @param id número del producto.
     * @return pos la posición en la bdd del producto a consultar.
     */
    public int getPosProducto(int id){
        int cont = 0;
        while(cont<numProductos){
            if(productos[cont].getNoProducto() == id)
                return cont;
            cont++;
        }
        return -1;
    }

    /**
     * Método para eliminar un <code>elemento</code> de la bdd
     * @param producto Objeto que se eliminara de la bdd.
     */
    public void eliminar(Producto producto){
        if (contiene(producto)){
            Producto [] eliminado = new Producto[numProductos - 1];
            int cont=0;
            while (cont<eliminado.length){
                if (!producto.equals(productos[cont]))
                   eliminado[cont] = productos[cont];
                cont++;
            }
            productos = eliminado;
            numProductos = eliminado.length;
        }
    }

    /**
     * Método para eliminar un elemento a la bdd.
     * @param id identificador del Objeto que se elimina de la bdd.
     */
    public void eliminar(int id){
        if (contiene(id)){
            Producto [] eliminado = new Producto[numProductos - 1];
            int cont=0;
            while (cont<eliminado.length){
                if (!(productos[cont].getNoProducto() == id))
                   eliminado[cont] = productos[cont];
                cont++;
            }
            productos = eliminado;
            numProductos = eliminado.length;
        }
    }

    /**
     * Método para ver si un elemento pertenece a la bdd.
     * @param elemento Objeto que se va a buscar en la bdd
     * @return <code>true</code> si el elemento esta en la bdd,
     * <code>false</code> en otro caso.
     */
    public boolean contiene(Producto producto){
          return contiene(producto.getNoProducto());
    }

    /**
     * Método para ver si un elemento pertenece a la bdd.
     * @param id idntificador del Objeto que se va a buscar en la bdd
     * @return <code>true</code> si el elemento esta en la bdd,
     * <code>false</code> en otro caso.
     */
    public boolean contiene(int id){
        if (!esVacio()){
            int cont = 0;
            while(cont<numProductos){
                if (productos[cont].getNoProducto() == id)
                    return true;
                cont++;
            }
        }
        return false;
    }

    /**
     * Método toString para la base de datos.
     * @return cadena un String con todos los elementos de la bdd.
     */
    @Override
    public String toString(){
        int cont = 0;
        String cadena = "----------------------------------------------" + "\n";
        while (cont<numProductos){
            cadena += productos[cont].toString() + "\n" +
                        "----------------------------------------------" + "\n";
            cont++;
        }
        return cadena;
    }

    /**
     * Método para vender un producto vía el número de producto.
     * @param cant cantidad vendida.
     * @param id número del producto.
     */
    public void venta(int cant,int id){
      if (getProducto(id) instanceof DiscoMusical)
          ventasDiscos+=cant;
      if (getProducto(id) instanceof Libro)
          ventasLibros+=cant;
      if (getProducto(id) instanceof Pelicula)
          ventasPeliculas+=cant;
      if (getProducto(id) instanceof Videojuego)
          ventasVJ+=cant;
      productos[getPosProducto(id)].setCantProducto(
      productos[getPosProducto(id)].getCantProducto() - cant);
    }

    /**
     *Modifica el número de productos en existencia via el número de producto.
     *@param cantProducto la nueva cantidad.
     *@param id número de Producto.
     */
    public void modificaCantProducto(int cantProducto, int id){
       productos[getPosProducto(id)].setCantProducto(cantProducto);
    }

    /**
     * Modifica el precio del producto vía el número del producto.
     * @param precio el nuevo precio.
     * @param id número de producto.
     */
    public void modificaPrecio(int precio, int id){
       productos[getPosProducto(id)].setPrecio(precio);
    }

    /**
     * Getter para ventasDiscos
     * @return ventasDiscos
     */
    public int getVentasDiscos(){
        return ventasDiscos;
    }

    /**
     * Getter para ventasLibros
     * @return ventasLibros
     */
    public int getVentasLibros(){
        return ventasLibros;
    }

    /**
     * Getter para ventasPeliculas
     * @return ventasPeliculas
     */
    public int getVentasPeliculas(){
        return ventasPeliculas;
    }

    /**
     * Getter para ventasVJ
     * @return ventasVJ
     */
    public int getVentasVJ(){
        return ventasVJ;
    }

    /**
     * Getter para ventasTotales
     * @return ventasTotales
     */
    public int getVentasTotales(){
        return ventasDiscos + ventasLibros + ventasPeliculas + ventasVJ;
    }
}
