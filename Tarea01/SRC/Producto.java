import java.io.Serializable;
/**
 * <p> Clase abstracta para productos </p> <p>Esta clase contiene los métodos y
 * atributos principales para los productos de algun tipo de tienda. </p>
 * @author Carlos Iván Pineda Santiago <ivanpineda@ciencias.unam.mx>
 * @version 1.0
 */
public abstract class Producto implements Serializable{
    /* Número para identificar el producto. */
    protected static int no = 0;
    protected int noProducto = no;
    /* Cantidad de productos de un tipo. */
    protected int cantProducto;
    /* Precio para cada producto. */
    protected double precio;
    /* Nombre de cada producto (título). */
    protected String titulo;
    /* Genero del producto. */
    protected String genero;

    /**
     * Método constructor de algún producto para ser utilizado en los productos
     * que hereden de esta clase.
     * @param precio el precio del producto.
     * @param titulo el nombre del producto.
     * @param genero el genero del producto.
     */
    public Producto(double precio, String titulo, String genero,
                    int cantProducto){
        this.precio = precio;
        this.titulo = titulo;
        this.genero = genero;
        this.cantProducto = cantProducto;
        no++;
    }

    /**
     * Método getter para el número de producto.
     * @return noProducto el número del producto.
     */
    public int getNoProducto(){
        return noProducto;
    }

    /**
     * Método getter para la cantidad de un producto.
     * @return cantProducto la cantidad de un producto.
     */
    public int getCantProducto(){
        return cantProducto;
    }

    /**
     * Método setter para la cantidad de un producto.
     * @param cantProducto la nueva cantidad de producto de un tipo.
     */
    public void setCantProducto(int cantProducto){
        this.cantProducto = cantProducto;
    }

    /**
     * Método getter para el precio de producto.
     * @return precio el precio del producto.
     */
    public double getPrecio(){
        return precio;
    }

    /**
     * Método setter para el precio de producto.
     * @param precio el precio del producto.
     */
    public void setPrecio(double precio){
        this.precio = precio;
    }

    /**
     * Método getter para el género del producto.
     * @return genero el género del producto.
     */
    public String getGenero(){
        return genero;
    }

    /**
     * Método getter para el título/nombre del producto.
     * @return titulo el título/nombre del producto.
     */
    public String getTitulo(){
        return titulo;
    }

    /**
     * Método equals para un producto.
     * @param o el objeto con el que se va a comparar
     * @return <tt>true</tt> sí los productos a comparar son iguales.
     *         <tt>false</tt> en otro caso.
     */
     @Override
     public boolean equals(Object o){
        if (!(o instanceof Producto))
            return false;
        Producto prod = (Producto) o;
        return noProducto == prod.getNoProducto();
     }
}
