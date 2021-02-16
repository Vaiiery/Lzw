import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.*;

public class main {
    public static void main(final String[] args) throws IOException {
        String inputFile, outputFile;
        System.out.println("Digite 1 para comprimir y 2 para descomprimir");
        Scanner op = new Scanner(System.in);
        int opt = op.nextInt();

        switch(opt) {
            case 1:
                System.out.println("Compresion");
                System.out.println("Directorio del archivo a comprimir:");
                Scanner user = new Scanner(System.in);
                inputFile = user.nextLine().trim();
                File input = new File(inputFile);
                Scanner scan = new Scanner(input);
                System.out.println("Directorio del archivo resultante:");
                outputFile = user.nextLine().trim();
                try {
                    FileInputStream in = new FileInputStream(inputFile);
                    FileOutputStream out = new FileOutputStream(outputFile);
                    LZW lzw = new LZW();
                    lzw.compress(in, out);
                }
                catch(Exception e) {
                    System.out.println("El archivo a comprimir no se encuentra.");
                }
            case 2:
                System.out.println("Decompresion");
                System.out.println("Escriba el directorio del archivo a descomprimir:");
                Scanner user1 = new Scanner(System.in);
                inputFile = user1.nextLine().trim();
                File input1 = new File(inputFile);
                Scanner scan1 = new Scanner(input1);
                System.out.println("Directorio de archivo resultante:");
                outputFile = user1.nextLine().trim();
                try {
                    FileInputStream in = new FileInputStream(inputFile);
                    FileOutputStream out = new FileOutputStream(outputFile);
                    LZW lzw = new LZW();
                    lzw.decompress(in, out);
                }
                catch(Exception e) {
                    System.out.println("No se encuentra el archivo a descomprimir.");
                }
            default:
                System.out.println("No se comprimio/descomprimio nada distinguido.");
        }
    }
}