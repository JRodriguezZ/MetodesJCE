package com.company;

import javax.crypto.SecretKey;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.*;
import java.security.cert.Certificate;
import java.util.Enumeration;
import java.util.Scanner;

public class PobramaA5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Exercici 1");
        System.out.println("1. ------------");


        System.out.println("Generant claus...");
        KeyPair keys1 = Xifrar.randomGenerate(1024);

        System.out.println("Escriu el missatge a xifrar:");
        String secret1 = scanner.next();

        PrivateKey clauPrivada1 = keys1.getPrivate();
        PublicKey clauPublica1 = keys1.getPublic();

        System.out.println("\nClau publica: ");
        System.out.println(clauPublica1);

        System.out.println("Clau privada: ");
        System.out.println(clauPrivada1);



        System.out.println("\n2. ------------");

        Path keyStorePath = Paths.get("ksJoan.ks");
        String keyStoreFile = keyStorePath.toString();

        String password = "puig.2020";
        try {
            KeyStore keyStore1 = Xifrar.loadKeyStore(keyStoreFile,password);

            System.out.print("1. Tipus de keystore: ");
            String typeKS = keyStore1.getType();
            System.out.println(typeKS);

            System.out.print("2. Tamany d'entrades al magatzem: ");
            int sizeKS = keyStore1.size();
            System.out.println(sizeKS);

            System.out.print("3. Àlies de totes les claus emmagatzemades: ");
            Enumeration<String> aliasKS = keyStore1.aliases();
            String alias = aliasKS.nextElement();
            System.out.println(alias);

            System.out.print("4. Certificat de un alias concret: ");
            // Certificate certificat = keyStore1.getCertificate(alias);
            // System.out.println(certificat);

            System.out.print("5. Algorisme de xifrat de la clau: ");
            Key clauKS = keyStore1.getKey(alias, password.toCharArray());
            System.out.println(clauKS.getAlgorithm());

//            1.2.ii
//            String passwd = "pass";
//
//            SecretKey secretKey1 = Xifrar.passwordKeyGenerator(passwd,256);
//
//            KeyStore.SecretKeyEntry secretKeyEntry = new KeyStore.SecretKeyEntry(secretKey1);
//            KeyStore.ProtectionParameter protParam = new KeyStore.PasswordProtection(password.toCharArray());
//            keyStore1.setEntry(alias,secretKeyEntry,protParam);


        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("\n3---------------------");

        System.out.println("\n4---------------------");

        System.out.println("\n5---------------------");

        String secreto1 = "Hola mundo";

        System.out.println("Generant claus...");
        KeyPair keys2 = Xifrar.randomGenerate(1024);

        PrivateKey clauPrivada2 = keys2.getPrivate();

        System.out.println("Signant secret...");
        byte[] signatura1 = Xifrar.signData(secreto1.getBytes(),clauPrivada2);

        try {
            System.out.println("Signatura: " + new String(signatura1,"UTF8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        System.out.println("\n6---------------------");

        String secreto2 = "Em dic Joan";

        System.out.println("Generant claus...");
        KeyPair keys3 = Xifrar.randomGenerate(1024);

        PrivateKey clauPrivada3 = keys3.getPrivate();

        System.out.println("Signant secret...");
        byte[] signatura2 = Xifrar.signData(secreto2.getBytes(), clauPrivada3);

        PublicKey clauPublica3 = keys3.getPublic();

        System.out.println("Validant signatura...");
        boolean signaturaValida = Xifrar.validateSignature(secreto2.getBytes(), signatura2, clauPublica3);

        System.out.println("Signatura valida? " + signaturaValida);


    }
}
