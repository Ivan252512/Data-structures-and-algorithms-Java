import java.util.Scanner;

public class SaberSiEsMayusculaoMinuscula{

public static void main (String args[]){
     Scanner sc = new Scanner(System.in);
     System.out.print ("Ingrese un caracter: ");
     char letra = sc.nextLine().charAt(0);

     if(Character.isUpperCase(letra))
          letra = Character.toLowerCase(letra);
     else
          letra = Character.toUpperCase(letra);

     System.out.println(letra);
}
}

//Use el metodo isLowerCase para saber si un caracter esta en minuscula
//Use el metodo isUpperCase para saber si un caracter esta en mayuscula
//Saludos ;)
