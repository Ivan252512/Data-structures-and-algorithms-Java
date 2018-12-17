/**
 * <p> Interfaz para administrador </p> <p>Esta clase contiene las
 * operaciones elementales para implementar un usuario como administrador </p>
 * @author Carlos Iván Pineda Santiago <ivanpineda@ciencias.unam.mx>
 * @version 1.0
 */
public class Administrador extends Usuario{
    /**
     * Método constructor para administrador.
     * @param nombre el nombre del administrador.
     * @param contrasenia la contraseña del administrador.
     */
    public Administrador(String nombre, String contrasenia){
        super(nombre, contrasenia);
    }

    /**
     *Método getter para el tipo de usuario.
     *@return tipo el tipo del usaurio.
     */
    @Override
    public String getTipoDeUsuario(){
        return "Administrador";
    }

    /**
     * Método para ver el inventario.
     * @param bddProductos la base de datos.
     * @return inventario el inventario.
     */
    public String verInventario(BaseDeDatosProductos bddProductos){
        return bddProductos.toString();
    }

    /**
     * Método para dar de alta nuevos discos.
     * @param artista el artista creador del disco.
     * @param anio el año de lanzamiento del disco.
     * @param sello el sello discográfico del disco.
     * @param precio el precio del disco.
     * @param titulo el nombre del disco.
     * @param genero el genero del disco.
     * @param bdd la base de datos donde se agregará el disco.
     */
    public void agregaDisco(String artista, int anio, String sello,
                            double precio, String titulo, String genero,
                            int cantProducto, BaseDeDatosProductos bdd){
        bdd.agregar(
                  new DiscoMusical(artista, anio, sello, precio, titulo, genero,
                                   cantProducto));
    }

    /**
     * Método para dar de alta libros.
     * @param autor el autor del libro.
     * @param ISBN el ISBN del libro.
     * @param editorial la editorial del libro.
     * @param precio el precio del libro.
     * @param titulo el nombre del libro.
     * @param genero el genero del libro.
     */
    public void agregaLibro(String autor, String ISBN, String editorial,
                            double precio, String titulo, String genero,
                            int cantProducto, BaseDeDatosProductos bdd){
        bdd.agregar(new
                        Libro(autor, ISBN, editorial, precio, titulo, genero,
                        cantProducto));
    }

    /**
     * Método para dar de alta Película.
     * @param anio el año de lanzamiento de la película.
     * @param casa la casa productora de la película.
     * @param precio el precio de la película.
     * @param titulo el nombre de la película.
     * @param genero el genero de la película.
     */
    public void agregaPelicula(int anio, String casa,
                               double precio, String titulo, String genero,
                               int cantProducto, BaseDeDatosProductos bdd){
        bdd.agregar(new Pelicula(anio, casa, precio, titulo, genero,
                                 cantProducto));
    }

    /**
     * Método para dar de alta Videojuego.
     * @param plataforma La plataforma en la que se puede correr el videojuego.
     * @param publisher El publisher del videojuego.
     * @param precio el precio del videojuego.
     * @param titulo el nombre del videojuego.
     * @param genero el genero del videojuego.
     */
     public void agregaVideojuego(String plataforma, String publisher,
                                  double precio, String titulo, String genero,
                                  int cantProducto, BaseDeDatosProductos bdd){
        bdd.agregar(new
                    Videojuego(plataforma, publisher, precio, titulo, genero,
                               cantProducto));
     }

     /**
      * Método para quitar productos del inventario.
      * @param id número del producto.
      * @param bdd la base de datos de donde se quiere eliminar el producto.
      */
     public void quitarDelInventario(int id, BaseDeDatosProductos bdd){
        bdd.eliminar(id);
     }

     /**
      *Modifica el número de productos en existencia via el número de producto.
      *@param cantProducto la nueva cantidad.
      *@param id número de Producto.
      *@param bdd la base de datos.
      */
     public void modificarCantProducto(int cantProducto, int id,
                                       BaseDeDatosProductos bdd){
        bdd.modificaCantProducto(cantProducto, id);
     }

     /**
      * Modifica el precio del producto vía el número del producto.
      * @param precio el nuevo precio.
      * @param id número de producto.
      * @param bdd la base de datos del producto.
      */
     public void modificarPrecio(int precio, int id, BaseDeDatosProductos bdd){
        bdd.modificaPrecio(precio, id);
     }

     /**
      * Obtener el total de ventas en un día.
      * @return ventas el total de ventas.
      */
     public int totalVentas(BaseDeDatosProductos bdd){
          return bdd.getVentasTotales();
     }

     /**
      * Getter para ventasDiscos
      * @param bdd Base de datos a consultar.
      * @return ventasDiscos
      */
     public int ventasDiscos(BaseDeDatosProductos bdd){
         return bdd.getVentasDiscos();
     }

     /**
      * Getter para ventasLibros
      * @param bdd Base de datos a consultar.
      * @return ventasLibros
      */
     public int ventasLibros(BaseDeDatosProductos bdd){
         return bdd.getVentasLibros();
     }

     /**
      * Getter para ventasPeliculas
      * @param bdd Base de datos a consultar.
      * @return ventasPeliculas
      */
     public int ventasPeliculas(BaseDeDatosProductos bdd){
         return bdd.getVentasPeliculas();
     }

     /**
      * Getter para ventasVJ
      * @param bdd Base de datos a consultar.
      * @return ventasVJ
      */
     public int ventasVJ(BaseDeDatosProductos bdd){
         return bdd.getVentasVJ();
     }

     /**
      * Método toString para los usuarios.
      * @return cadena la información de los usuarios.
      */
     @Override
     public String toString(){
       String cadena = String.format("Nombre     : %s\n" +
                                     "Contraseña : %s\n" +
                                     "Tipo       : %s\n" +
                                     "id         : %d",
       nombre, contrasenia, "Administrador", id);
       return cadena;
     }

     /**
      * Método para salir del sistema.
      */
     public void salir(){
         System.exit(0);
     }
}
