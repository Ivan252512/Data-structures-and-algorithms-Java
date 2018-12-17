/**
 * Clase para el producto disco musical.
 */
public class DiscoMusical extends Producto{
    /* El artista creador del disco. */
    private String artista;
    /* El año de lanzamiento del disco. */
    private int anio;
    /* El sello discográfico del disco. */
    private String sello;

    /**
     * Método constructor para discos músicales.
     * @param artista el artista creador del disco.
     * @param anio el año de lanzamiento del disco.
     * @param sello el sello discográfico del disco.
     * @param precio el precio del disco.
     * @param titulo el nombre del disco.
     * @param genero el genero del disco.
     */
    public DiscoMusical(String artista, int anio, String sello,
                        double precio, String titulo, String genero,
                        int cantProducto){
        super(precio, titulo, genero, cantProducto);
        this.artista = artista;
        this.anio = anio;
        this.sello = sello;
    }

    /**
     * Método getter para el artista.
     * @return artista el artista.
     */
    public String getArtista(){
        return artista;
    }

    /**
     * Método getter para el año de lanzamiento del disco.
     * @return anio el año de lanzamiento del disco.
     */
    public int getAnio(){
        return anio;
    }

    /**
     * Método getter para el sello discográfico.
     * @return sello el sello discográfico.
     */
    public String getSello(){
        return sello;
    }

    /**
     * Método toString para un disco.
     * @return cadena un String con los elementos de un disco.
     */
    @Override
    public String toString(){
        String cadena = String.format("Artista            : %s\n" +
                                      "Título             : %s\n" +
                                      "Género             : %s\n" +
                                      "Año                : %d\n" +
                                      "Sello Discográfico : %s\n" +
                                      "Cantidad           : %d\n" +
                                      "Número de Producto : %d\n" +
                                      "Precio             : %f",
        artista, titulo, genero, anio, sello, cantProducto, noProducto, precio);
        return cadena;
    }
}
