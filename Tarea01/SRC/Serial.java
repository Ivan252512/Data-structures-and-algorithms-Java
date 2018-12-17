import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.File;
import javax.swing.JOptionPane;

public class Serial {

    public static void main(String[] args) {

        File file;
        FileOutputStream fos = null;
        ObjectOutputStream salida = null;
        BaseDeDatosUsuarios bddu = new BaseDeDatosUsuarios();
        BaseDeDatosProductos bddp = new BaseDeDatosProductos();
        bddu.agregar(new Administrador("Juan Pérez", "123"));
        bddu.agregar(new Vendedor("Pepe Pecas", "456"));
        bddu.agregar(new Vendedor("Iván Pineda", "789"));

        bddp.agregar(new DiscoMusical("Curco Bein", 1994, "Shark Dj",
                     500, "La chona", "Banda", 100));
        bddp.agregar(new DiscoMusical("Metallica", 1984, "Bar sinso productions",
                     5000, "Vamos México", "Metal en transición", 1000));
        bddp.agregar(new DiscoMusical("El Recodo", 1521, "AMLO",
                     645, "Movimiento Naranja", "ASMR", 200));
        bddp.agregar(new Libro("Enrique Peña Nieto", "98ufth8unun5u",
                     "Editorial libro vaquero", 250, "México vs Cruz Azul",
                      "Romántico", 100));
        bddp.agregar(new Libro("Isaac Asimov", "jnrijninnuincf",
                     "Jet", 200, "Yo Robot en Ecatepec", "Suspenso", 120));
        bddp.agregar(new Libro("Yo", "jnomocrfmikcmi",
                     "Porrua", 20, "Mi vida a lado de Jenny Rivera",
                     "Agropecuario", 110));
        bddp.agregar(new Pelicula(1994, "Xochimilco", 60, "Titanic", "Novela",
                     50));
        bddp.agregar(new Pelicula(2048, "Trump Paramount Disney", 600,
                     "El día que México recuperó Texas", "Ciencia ficción", 5));
        bddp.agregar(new Videojuego("Mi corazón", "Ella", 200,
                     "Nuestro amor", "Tristeza", 120));
        bddp.agregar(new Videojuego("Xbox 64 Alienware", "Chespirito", 20000000,
                     "La vecindad maldita 666", "Terror", 110));

        System.out.println("Ingresa tus datos para esocger el usuario");
        System.out.println(bddu.toString());

        Usuario us;

        while (true){
            String id =JOptionPane.showInputDialog("Introduce tu id" +
                               "(En la terminal se muestran los datos)");
            String contrasenia=JOptionPane.showInputDialog("Introduce contraseña" +
                               "(En la terminal se muestran los datos)");
            if (bddu.getUsuario(Integer.parseInt(id)).getContrasenia().equals(contrasenia)){
                System.out.println("Has ingresado como: ");
                System.out.println(bddu.getUsuario(Integer.parseInt(id)).toString());
                us = bddu.getUsuario(Integer.parseInt(id));
                break;
            } else {
                System.out.println("Contraseña y/o usuario incorrecto.");
            }
         }
         int cont = 0;
         while (true){
            if (us.getTipoDeUsuario().equals("Vendedor")){
                Vendedor vend = (Vendedor) us;
                if (cont == 0){
                    System.out.println("-------------Productos-------------");
                    System.out.println(bddp.toString());
                    cont++;
                }
                System.out.println("Introduce un número con la acción a realizar.");
                System.out.println("1) Vender un producto.");
                System.out.println("2) Obtener información de un producto.)");
                System.out.println("3) Salir del sistema.)");
                String des =JOptionPane.showInputDialog("Introduce un número" +
                                   " con la acción a realizar.");
                switch (des){
                    case "1":
                        String nop =JOptionPane.showInputDialog("Introduce " +
                                       " el número de producto a vender.");
                        String prod =JOptionPane.showInputDialog("Introduce " +
                                       " la cantidad de productos a vender.");
                        vend.vender(Integer.parseInt(prod),
                                    Integer.parseInt(nop), bddp);
                        break;
                    case "2":
                        String nop2 =JOptionPane.showInputDialog("Introduce " +
                                       " el número de producto consultar.");
                        System.out.println(bddp.getProducto
                                  (Integer.parseInt(nop2)).toString());
                        break;
                    case "3":
                        try {
                            //Se crea el fichero
                            file = new File("productos.dat");
                            fos = new FileOutputStream(file);
                            salida = new ObjectOutputStream(fos);
                            //Se escribe el objeto en el fichero
                            salida.writeObject(bddu);
                            salida.writeObject(bddp);

                        } catch (FileNotFoundException e) {
                            System.out.println("1"+e.getMessage());
                        } catch (IOException e) {
                            System.out.println("2"+e.getMessage());
                        } finally {
                            try {
                                if(fos!=null) fos.close();
                                if(salida!=null) salida.close();
                            } catch (IOException e) {
                                System.out.println("3"+e.getMessage());
                            }
                        }
                        vend.salir();

                }

            }

            if (us.getTipoDeUsuario().equals("Administrador")){
                Administrador adm = (Administrador) us;
                if (cont == 0){
                    System.out.println("-------------Productos-------------");
                    System.out.println(bddp.toString());
                    cont++;
                }
                System.out.println("Introduce un número con la acción a realizar.");
                System.out.println("1) Ver el inventario completo.");
                System.out.println("2) Dar de alta nuevos productos.");
                System.out.println("3) Quitar productos del inventario.");
                System.out.println("4) Modificar el número de productos existentes.");
                System.out.println("5) Modificar el precio de un producto.");
                System.out.println("6) Obtener el total de ventas de un día.");
                System.out.println("7) Obtener el total de ventas de un día por producto.");
                System.out.println("8) Salir del sistema.)");
                String des =JOptionPane.showInputDialog("Introduce un número" +
                                   " con la acción a realizar.");
                switch (des){
                    case "1":
                        System.out.println(bddp.toString());
                        break;
                    case "2":
                        System.out.println("Introduce un número con el tipo de producto a agregar.");
                        System.out.println("1) Disco.");
                        System.out.println("2) Libro.");
                        System.out.println("3) Película.");
                        System.out.println("4) Videojuego.");
                        String nop9 =JOptionPane.showInputDialog("Introduce " +
                                       " el número de la opción");
                        switch (nop9){
                        //Agregue un disco ya hecho, nadie se iba a poner a llenar
                        //linea por linea un disco :v.
                            case "1":
                                bddp.agregar(new DiscoMusical("Blur", 1994, "Shark Dj",
                                             500, "Lalala", "Pop", 100));
                                break;
                            case "2":
                                bddp.agregar(new Libro("Octavio Paz", "990439043th8unun5u",
                                             "Editorial Porrua", 250, "kjrfjn4r",
                                              "Novela", 100));
                                break;
                            case "3":
                                bddp.agregar(new Pelicula(1994,
                                            "Hola mundo", 20, "C++", "Pollo",
                                             500));
                                break;
                            case "4":
                                bddp.agregar(new Videojuego("Xbox 360", "EA", 200000,
                                             "Minecraft", "Simulador", 110));
                                break;
                        }

                        break;
                    case "3":
                        String nop2 =JOptionPane.showInputDialog("Introduce " +
                                       " el número de producto a quitar.");
                        adm.quitarDelInventario(Integer.parseInt(nop2), bddp);
                        break;
                    case "4":
                        String nop4 =JOptionPane.showInputDialog("Introduce " +
                                       " el número de producto.");
                        String nop3 =JOptionPane.showInputDialog("Introduce " +
                                 " el número de producto del cual quieres " +
                                 " la cantidad.");
                        adm.modificarCantProducto(Integer.parseInt(nop3),
                                                  Integer.parseInt(nop4),
                                                  bddp);
                        break;
                    case "5":
                        String nop5 =JOptionPane.showInputDialog("Introduce " +
                                       " el número de producto a modificar.");
                        String nop6 =JOptionPane.showInputDialog("Introduce " +
                                       " el nuevo precio.");
                        adm.modificarPrecio(Integer.parseInt(nop6),
                                            Integer.parseInt(nop5),
                                            bddp);
                        break;
                    case "6":
                        System.out.println("-----Total de ventas-----");
                        System.out.println(adm.totalVentas(bddp));
                        break;
                    case "7":
                        System.out.println("-----Ventas por productos-----");
                        System.out.println("Discos: " + adm.ventasDiscos(bddp));
                        System.out.println("Libros: " + adm.ventasLibros(bddp));
                        System.out.println("Películas: " + adm.ventasPeliculas(bddp));
                        System.out.println("Videojuegos: " + adm.ventasVJ(bddp));
                        break;
                    case "8":
                        try {
                            //Se crea el fichero
                            file = new File("productos.dat");
                            fos = new FileOutputStream(file);
                            salida = new ObjectOutputStream(fos);
                            //Se escribe el objeto en el fichero
                            salida.writeObject(bddu);
                            salida.writeObject(bddp);

                        } catch (FileNotFoundException e) {
                            System.out.println("1"+e.getMessage());
                        } catch (IOException e) {
                            System.out.println("2"+e.getMessage());
                        } finally {
                            try {
                                if(fos!=null) fos.close();
                                if(salida!=null) salida.close();
                            } catch (IOException e) {
                                System.out.println("3"+e.getMessage());
                            }
                        }
                        adm.salir();

                }

            }

         }


    }
}
