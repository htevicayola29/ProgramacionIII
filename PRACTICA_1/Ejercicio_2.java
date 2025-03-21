package PRACTICA_1;

import java.util.Scanner;

public class Ejercicio_2 {
    String[]original;
    String[]inverso;
    Scanner scanner;
    public Ejercicio_2(){
        original=new String[5];
        inverso=new String[5];
        scanner=new Scanner(System.in);
    }
    public void ejecutar(){
        for (int i=0; i <original.length;i++){
            System.out.println("original["+i+"]:");
            String cadena=scanner.nextLine();
            original[i]=cadena;
        }
        int indiceOriginal=0;
        int indiceIverso=4;
        while (indiceOriginal< original.length){
            inverso[indiceIverso]=original[indiceOriginal];
             indiceOriginal++;
             indiceIverso--;
        }
        for(int i=0;i< inverso.length;i++){
            System.out.println("inverso["+i+"]:"+inverso[i]);
        }
    }
}
