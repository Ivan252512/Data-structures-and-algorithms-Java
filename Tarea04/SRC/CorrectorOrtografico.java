import java.io.*;
import java.util.Scanner;

public class CorrectorOrtografico{

    /**
     * Carga un archivo en un coleccionable.
     * @param archivo
     * @param a
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void cargaContenido(String archivo, Coleccionable<Palabra> a) throws FileNotFoundException, IOException {
        String cadena;
        FileReader f = new FileReader(archivo);
        BufferedReader b = new BufferedReader(f);
        while((cadena = b.readLine())!=null) {
            a.agrega(new Palabra(cadena));
        }
        b.close();
    }
    
    /**
     * Escribe un String en un documento.
     * @param f
     * @param linea
     */
    public static void escribir(File f, String linea) {        
        //Escritura
        try{
            FileWriter w = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(w);
            PrintWriter wr = new PrintWriter(bw);	
            wr.write(linea);//escribimos en el archivo 
            wr.close();
            bw.close();
        }catch(IOException e){};
    }

    public static MaxHeap<Sugerencia> sugerencias(Palabra p,  ArbolAVL<Palabra> a) {
        Lista<Sugerencia> ls = new Lista<Sugerencia>();
        Lista<Palabra> lp = a.inOrden();

        for (Palabra ap : lp) {
            ls.agregar(new Sugerencia(ap.getPalabra(), p.getPalabra()));
        }

        MaxHeap<Sugerencia> mhp = new MaxHeap<Sugerencia>(ls);

        return mhp;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
       
        //Cargamos el diccionario en un árbol AVL.    
        ArbolAVL<Palabra> a = new ArbolAVL<Palabra>(new Cola<Palabra>());

        cargaContenido("diccionario.txt", a);

        boolean consultar = true;
        while(consultar){
            System.out.println("Escribe la palabra de la cual quieres verficar la ortografía:");
            String pins = sc.nextLine();
            Palabra pin = new Palabra(pins);

            if (a.contiene(pin))
                System.out.println("La palabra " + pins + " está escrita correctamente.");
            else{
                Lista<String> masParecidos = new Lista<String>();
                MaxHeap<Sugerencia> mps = sugerencias(pin, a);
                int ind = 0;
                for (Sugerencia s : mps) {
                    masParecidos.agregar(s.getOriginal());
                    ind++;
                    if(pin.normaliza().equals(s.getOriginal())){
                        System.out.println("La palabra " + pins + " está escrita correctamente.");
                        break;
                    }
                    if(ind>15){
                        System.out.println("La palabra " + pins + " NO está escrita correctamente.");
                        System.out.println("Sugerencias: " + masParecidos.toString());
                        System.out.println("Si su palabra no está en las sugerencias, ¿desea agregarla? s/n");
                        String respuesta = sc.nextLine();
                        boolean resag = true;
                        while(resag){
                            if(respuesta.equals("s")){
                                System.out.println("La palabra " + pins + " ha sido agregada.");
                                a.agrega(pin);
                                resag = false;
                            }else if (respuesta.equals("n")){
                                System.out.println("La palabra " + pins + " NO ha sido agregada.");
                                resag = false;
                            }
                            else
                                System.out.println("Ingrese una opción válida.");
                        }
                        break;
                    }
                }
            }
            System.out.println("¿Desea consultar otra palabra? s/n");
            boolean consultarer = true;
            while(consultarer){
                String respuesta2 = sc.nextLine();
                if(respuesta2.equals("n")){
                    consultar = false;
                    consultarer = false;
                }
                else if(respuesta2.equals("s")){
                    consultarer = false;
                }else{
                    System.out.println("Opción no válida, intenta de nuevo."); 
                }
            }
        }

        System.out.println("Saliendo y guardando cambios."); 

        Lista<Palabra> lp = a.inOrden();

        File f;
        f = new File("diccionario.txt");

        String dic = "";
        for (Palabra p : lp) {
            dic += p.toString()+'\n';
        }

        escribir(f, dic);
        System.out.println("Finalizado."); 
    }
}
