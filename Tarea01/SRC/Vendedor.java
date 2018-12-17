/**
 * <p> Interfaz para vendedor </p> <p>Esta clase contiene las
 * operaciones elementales para implementar un usuario como vendedor </p>
 * @author Carlos Iván Pineda Santiago <ivanpineda@ciencias.unam.mx>
 * @version 1.0
 */
public class Vendedor extends Usuario{
    /**
     * Método constructor para administrador.
     * @param nombre el nombre del administrador.
     * @param contrasenia la contraseña del administrador.
     */
    public Vendedor(String nombre, String contrasenia){
        super(nombre, contrasenia);
    }

    /**
     *Método getter para el tipo de usuario.
     *@return tipo el tipo del usaurio.
     */
    @Override
    public String getTipoDeUsuario(){
        return "Vendedor";
    }

    /**
     * Método para vender un producto vía el número de producto.
     * @param cant cantidad vendida.
     * @param id número del producto.
     * @param bddProductos la base de datos donde están los productos.
     */
    public void vender(int cant,int id, BaseDeDatosProductos bddProductos){
        bddProductos.venta(cant, id);
    }

    /**
     * Método para obtener la información del producto via el número de
     * producto.
     * @param id número del producto.
     * @param bddProductos la base de datos donde están los productos.
     */
    public String informacion(int id, BaseDeDatosProductos bddProductos){
        return bddProductos.getProducto(id).toString();
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
      nombre, contrasenia, "Vendedor", id);
      return cadena;
    }

    /**
     * Método para salir del sistema.
     */
    public void salir(){
        System.exit(0);
    }
}
