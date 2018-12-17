import java.util.Random;

public class Prueba{
  public static void main(String[] args) {

    System.out.println("############### ArbolBinarioBusqueda ################");

    ArbolBinarioBusqueda<Integer> prueba = new ArbolBinarioBusqueda<Integer>();
    Random rand = new Random();

    int agrega;
    for(int i=0; i<rand.nextInt(100); i++){
        agrega = rand.nextInt(100);
        prueba.agrega(agrega);
        System.out.println(prueba.toString() + " ... Se agregó: " + agrega);
        System.out.println(" ... altura: " + prueba.altura());
    }

    System.out.println("-----------Agregar--------------");
    System.out.println(prueba.toString());
    System.out.println("Tamaño: " + prueba.getTamanio());

    System.out.println("-----------inOrden--------------");
    System.out.println(prueba.inOrden().toString());

    System.out.println("-----------postOrden--------------");
    System.out.println(prueba.postOrden().toString());

    System.out.println("-----------preOrden--------------");
    System.out.println(prueba.preOrden().toString());

    System.out.println("-----------Eliminar--------------");
    int elim;
    for(int i=0; i<rand.nextInt(100); i++){
        elim = rand.nextInt(100);
        prueba.elimina(elim);
        System.out.println(prueba.toString() + " ... Se elimino: " + elim);
    }
    System.out.println("Longitud: " + prueba.getTamanio());

    System.out.println("-----------Contiene--------------");
    int cont;
    for (int i=0; i<rand.nextInt(100); i++){
        cont = rand.nextInt(100);
        System.out.println(prueba.contiene(cont) + " ... Contiene: " + cont);
    }
  }
}
