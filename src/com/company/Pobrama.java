package com.company;

import javax.crypto.SecretKey;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Pobrama {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Exercici 1");
        System.out.println("5. ------------");

        System.out.println("Generando clave...");
        SecretKey key1 = Xifrar.keygenKeyGenerator(256);

        String secreto1 = "Hola mundo";

        byte[] encriptado1 = Xifrar.encryptData(key1, secreto1.getBytes());
        System.out.println("Se ha encriptado");

        byte[] desencriptado1 = Xifrar.decryptData(key1, encriptado1);
        System.out.println("Se ha desencriptado");

        String secretoDesencriptado1 = new String(desencriptado1);
        System.out.println("Resultado: " + secretoDesencriptado1);

        System.out.println();
        System.out.println();
        System.out.println();


        System.out.println("6. -------------------");

        String passwd = "pass";

        System.out.println("Generando clave...");
        SecretKey key2 = Xifrar.passwordKeyGenerator(passwd,256);

        String secreto2 = "hola mundo";

        byte[] encriptado2 = Xifrar.encryptData(key2, secreto2.getBytes());
        System.out.println("Se ha encriptado");

        byte[] desencriptado2 = Xifrar.decryptData(key2, encriptado2);
        System.out.println("Se ha desencriptado");

        String secretoDesencriptado2 = new String(desencriptado2);
        System.out.println("Resultado: " + secretoDesencriptado1);


        System.out.println();
        System.out.println();
        System.out.println();


        System.out.println("7. -------------------");

        System.out.println("getAlgorithm()");
        System.out.println(key2.getAlgorithm());
        System.out.println("getEncoded()");
        System.out.println(key2.getEncoded());
        System.out.println("getFormat()");
        System.out.println(key2.getFormat());

        System.out.println();
        System.out.println();
        System.out.println();


        System.out.println("8. -------------------");

        passwd = "contra";

        key2 = Xifrar.passwordKeyGenerator(passwd,256);

        secreto2 = "hola mundo";

        encriptado2 = Xifrar.encryptData(key2, secreto2.getBytes());

//        desencriptado2 = Xifrar.decryptData(key1, encriptado2);
//
//        secretoDesencriptado2 = new String(desencriptado2);
//        System.out.println(secretoDesencriptado2);

        System.out.println("Intenta desencriptar pero lanza un BadPaddingException ya que le ha enviado una contraseña que no corresponde con la que deberia ser");

        System.out.println("Exercici 2");

        Path claus = Paths.get("clausA4.txt");
        Path textAmagat = Paths.get("textamagat");
        try {
            byte[] textEnBytes = Files.readAllBytes(textAmagat);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            List<String> clausList = Files.readAllLines(claus);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String passwd2 = "pass";

        System.out.println("Generando clave...");
        SecretKey key3 = Xifrar.passwordKeyGenerator(passwd2,256);

        byte[] textEncriptat = textAmagat.;

        byte[] desencriptat = Xifrar.decryptData(key3, textEncriptat);

    }


}
