/**
 * Clase para el producto pelicula.
 */
public class Pelicula extends Producto{
    /* El año de lanzamiento de la película. */
    private int anio;
    /* La casa productora de la película. */
    private String casa;

    /**
     * Método constructor para discos músicales.
     * @param anio el año de lanzamiento de la película.
     * @param casa la casa productora de la película.
     * @param precio el precio de la película.
     * @param titulo el nombre de la película.
     * @param genero el genero de la película.
     */
    public Pelicula(int anio, String casa, double precio, String titulo,
                    String genero, int cantProducto){
        super(precio, titulo, genero, cantProducto);
        this.anio = anio;
        this.casa = casa;
    }

    /**
     * Método getter para el año de lanzamiento de la película.
     * @return anio el año de lanzamiento de la película.
     */
    public int getAnio(){
        return anio;
    }

    /**
     * Método getter para la casa productora.
     * @return casa la casa pruductora.
     */
    public String getcasa(){
        return casa;
    }

    /**
     * Método toString para una Pelicula.
     * @return cadena un String con los elementos de una pelicula.
     */
    @Override
    public String toString(){
        String cadena = String.format("Título             : %s\n" +
                                      "Género             : %s\n" +
                                      "Casa               : %s\n" +
                                      "Año                : %d\n" +
                                      "Cantidad           : %d\n" +
                                      "Número de Producto : %d\n" +
                                      "Precio             : %f",
        titulo, genero, casa, anio, cantProducto, noProducto, precio);
        return cadena;
    }
}
