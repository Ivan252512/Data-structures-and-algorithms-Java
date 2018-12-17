/**
 * Clase para el producto videojuego.
 */
public class Videojuego extends Producto{
    /* La plataforma en la que se puede correr el videojuego. */
    private String plataforma;
    /* El publisher del videojuego. */
    private String publisher;

    /**
     * Método constructor para videojuegos.
     * @param plataforma La plataforma en la que se puede correr el videojuego.
     * @param publisher El publisher del videojuego.
     * @param precio el precio del videojuego.
     * @param titulo el nombre del videojuego.
     * @param genero el genero del videojuego.
     */
    public Videojuego(String plataforma, String publisher,
                      double precio, String titulo, String genero,
                      int cantProducto){
        super(precio, titulo, genero, cantProducto);
        this.plataforma = plataforma;
        this.publisher = publisher;
    }

    /**
     * Método getter para la plataforma del videojuego.
     * @return plataforma la plataforma del videojuego.
     */
    public String getPlataforma(){
        return plataforma;
    }

    /**
     * Método getter para el publisher del videojuego.
     * @return publisher el publisher del videojuego.
     */
    public String getPublisher(){
        return publisher;
    }

    /**
     * Método toString para un videojuego.
     * @return cadena un String con los elementos de un Videojuego.
     */
    @Override
    public String toString(){
        String cadena = String.format("Título             : %s\n" +
                                      "Género             : %s\n" +
                                      "Publisher          : %s\n" +
                                      "Plataforma         : %s\n" +
                                      "Cantidad           : %d\n" +
                                      "Número de Producto : %d\n" +
                                      "Precio             : %f",
        titulo, genero, publisher, plataforma, cantProducto, noProducto, precio);
        return cadena;
    }
}
