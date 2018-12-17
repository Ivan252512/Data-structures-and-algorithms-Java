public class pruebaSubstring{
    public static void main(String[] args) {
        //Primero que nada, declaramos las variables.
        String cadena_a,cadena_b,cadena_c="GeekyTheory";
        //Borramos los últimos caracteres a partir del sexto carácter.
        cadena_a = cadena_c.substring(0,5);
        //Borramos los primeros cinco caracteres.
        cadena_b = cadena_c.substring(5);
        //Imprimimos el resultado.
        System.out.println(cadena_a+"\n"+cadena_b);
	System.out.println((char) 101);
    }
}
