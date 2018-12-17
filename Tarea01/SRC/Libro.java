/**
 * Clase para el producto libro.
 */
public class Libro extends Producto{
    /* El autor creador del libro. */
    private String autor;
    /* El ISBN del libro. */
    private String ISBN;
    /* La editorial del libro. */
    private String editorial;

    /**
     * Método constructor para libros.
     * @param autor el autor del libro.
     * @param ISBN el ISBN del libro.
     * @param editorial la editorial del libro.
     * @param precio el precio del libro.
     * @param titulo el nombre del libro.
     * @param genero el genero del libro.
     */
    public Libro(String autor, String ISBN, String editorial,
                        double precio, String titulo, String genero,
                        int cantProducto){
        super(precio, titulo, genero, cantProducto);
        this.autor = autor;
        this.ISBN = ISBN;
        this.editorial = editorial;
    }

    /**
     * Método getter para el autor del libro.
     * @return autor el autor del libro.
     */
    public String getAutor(){
        return autor;
    }

    /**
     * Método getter para el ISBN.
     * @return ISBN el ISBN del libro.
     */
    public String getISBN(){
        return ISBN;
    }

    /**
     * Método getter para la editorial del libro.
     * @return editorial la editorial del libro.
     */
    public String getEditorial(){
        return editorial;
    }

    /**
     * Método toString para un libro.
     * @return cadena un String con los elementos de un libro.
     */
    @Override
    public String toString(){
        String cadena = String.format("Autor              : %s\n" +
                                      "Título             : %s\n" +
                                      "Género             : %s\n" +
                                      "Editorial          : %s\n" +
                                      "ISBN               : %s\n" +
                                      "Cantidad           : %d\n" +
                                      "Número de Producto : %d\n" +
                                      "Precio             : %f",
        autor, titulo, genero, editorial, ISBN, cantProducto, noProducto, precio);
        return cadena;
    }
}
