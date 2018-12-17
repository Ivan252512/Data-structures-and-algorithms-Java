import java.util.Random;

public class Prueba{
  public static void main(String[] args) {

    System.out.println("############### ArbolAVL ################");

    ArbolAVL<Integer> prueba = new ArbolAVL<Integer>();
    Random rand = new Random();

    int agrega;
    for(int i=0; i<1000; i++){
        agrega = rand.nextInt(100);
        prueba.agrega(agrega);
        System.out.println(prueba.toString() + " ... Se agregó: " + agrega);
    }

    System.out.println("-----------Agregar--------------");
    System.out.println(prueba.toString());
    System.out.println("Tamaño: " + prueba.getTamanio());

    int elim;
    for(int i=0; i<1000; i++){
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
