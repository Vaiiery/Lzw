import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.*;

public class main {
    public static void main(final String[] args) throws IOException {
        String inputFile, outputFile;
        Scanner user, scan;
        System.out.println("Digite 1 para comprimir y 2 para descomprimir");
        Scanner op = new Scanner(System.in);
        int opt = op.nextInt();
        op.close();

        switch(opt) {
            case 1:
                System.out.println("Compresion");
                System.out.println("Directorio del archivo a comprimir:");
                user = new Scanner(System.in);
                inputFile = user.nextLine().trim();
                File input = new File(inputFile);
                scan = new Scanner(input);
                System.out.println("Directorio del archivo resultante:");
                outputFile = user.nextLine().trim();
                user.close();
                scan.close();
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
                user = new Scanner(System.in);
                inputFile = user.nextLine().trim();
                File input1 = new File(inputFile);
                scan = new Scanner(input1);
                System.out.println("Directorio de archivo resultante:");
                outputFile = user.nextLine().trim();
                user.close();
                scan.close();
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
                System.out.println("Nada se hizo.");
        }
    }
}