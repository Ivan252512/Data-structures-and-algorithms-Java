import java.util.Random;

public class Prueba{
    public static void main(String[] args) {
        Lista<Integer> prueba = new Lista<Integer>();
        Random rand = new Random();

        for(int i=0; i<rand.nextInt(50); i++){
            prueba.agregar(rand.nextInt(50));
        }

        System.out.println("-----------Agregar--------------");
        System.out.println(prueba.toString());
        System.out.println(prueba.longitud());

        System.out.println("-----------Eliminar--------------");
        int elim;
        for(int i=0; i<rand.nextInt(50); i++){
            elim = rand.nextInt(50);
            prueba.eliminar(elim);
            System.out.println(prueba.toString() + " ... Se elimino: " + elim);
        }
        System.out.println("Longitud: " + prueba.longitud());

        System.out.println("-----------Contiene--------------");
        int cont;
        for (int i=0; i<rand.nextInt(50); i++){
            cont = rand.nextInt(50);
            System.out.println(prueba.contiene(cont) + " ... Contiene: " + cont);
        }
        System.out.println("-----------Reversa--------------");
        System.out.println(prueba.reversa().toString());

        System.out.println("-----------Merge Sort--------------");
        System.out.println(Lista.mergesort(prueba).toString());

        // Prueba iterator.
        System.out.println("----------Iterator-------------");
        for (Object a : prueba) {
            System.out.println(a.toString());
        }
    }
}
