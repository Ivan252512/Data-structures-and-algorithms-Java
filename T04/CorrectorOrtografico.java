import java.io.*;

public class CorrectorOrtografico{

    public static void cargaContenido(String archivo, Coleccionable<Palabra> a) throws FileNotFoundException, IOException {
        String cadena;
        FileReader f = new FileReader(archivo);
        BufferedReader b = new BufferedReader(f);
        while((cadena = b.readLine())!=null) {
            a.agrega(new Palabra(cadena));
        }
        b.close();
    }
    
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

    public static void main(String[] args) throws IOException {
        Cola<Palabra> c = new Cola<Palabra>();
        cargaContenido("diccionario.txt", c);
    
        ArbolAVL<Palabra> a = new ArbolAVL<Palabra>(c);
    
        Lista<Palabra> lp = a.inOrden();

        File f;
        f = new File("Nuevo");

        String dic = "";
        for (Palabra p : lp) {
            dic += p.toString()+'\n';
        }

        escribir(f, dic);
    }

}
