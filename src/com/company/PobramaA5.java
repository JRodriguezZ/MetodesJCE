package com.company;

import javax.crypto.SecretKey;
import java.io.File;
import java.io.FileOutputStream;
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
        KeyStore keyStore1 = null;

        String password = "puig.2020";
        try {
            System.out.println("i...........................");
            keyStore1 = Xifrar.loadKeyStore(keyStoreFile,password);

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
            Certificate certificat = keyStore1.getCertificate(alias);
            System.out.println(certificat);

            System.out.print("5. Algorisme de xifrat de la clau: ");
            Key clauKS = keyStore1.getKey(alias, password.toCharArray());
            System.out.println(clauKS.getAlgorithm());


            System.out.println("ii...........................");
            System.out.println("Generant clau simetrica...");
            SecretKey clauSecreta = Xifrar.keygenKeyGenerator(256);

            KeyStore.SecretKeyEntry skEntry = new KeyStore.SecretKeyEntry(clauSecreta);
            KeyStore.ProtectionParameter protParam = new KeyStore.PasswordProtection(password.toCharArray());

            System.out.println("Desant clau al keystore...");
            keyStore1.setEntry("clau1", skEntry, protParam);
            FileOutputStream fos = new FileOutputStream(keyStoreFile);
            keyStore1.store(fos, password.toCharArray());

            System.out.println("S'ha desat la clau al keystore.");
            fos.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("\n3---------------------");

        Path jordiCerPath = Paths.get("jordi.cer");
        String jordiCerFile = jordiCerPath.toString();

        System.out.println("Obtenint clau publica del arxiu...");
        PublicKey jordiCerClauPublica = Xifrar.getPublicKey(jordiCerFile);

        System.out.println("Clau publica: " + jordiCerClauPublica);


        System.out.println("\n4---------------------");
        System.out.println("Extraient clau publica del alias...");
        PublicKey jordiCerClauPublica2 = Xifrar.getPublicKey(keyStore1,"clau2","passwd");

        System.out.println("Clau Publica: " + jordiCerClauPublica2);

        System.out.println("\n5---------------------");

        String secret2 = "Hola mundo";

        System.out.println("Generant claus...");
        KeyPair keys2 = Xifrar.randomGenerate(1024);

        PrivateKey clauPrivada2 = keys2.getPrivate();

        System.out.println("Signant secret...");
        byte[] signatura1 = Xifrar.signData(secret2.getBytes(),clauPrivada2);

        try {
            System.out.println("Signatura: " + new String(signatura1,"UTF8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        System.out.println("\n6---------------------");

        String secret3 = "Em dic Joan";

        System.out.println("Generant claus...");
        KeyPair keys3 = Xifrar.randomGenerate(1024);

        PrivateKey clauPrivada3 = keys3.getPrivate();

        System.out.println("Signant secret...");
        byte[] signatura2 = Xifrar.signData(secret3.getBytes(), clauPrivada3);

        PublicKey clauPublica3 = keys3.getPublic();

        System.out.println("Validant signatura...");
        boolean signaturaValida = Xifrar.validateSignature(secret3.getBytes(), signatura2, clauPublica3);

        System.out.println("Signatura valida? " + signaturaValida);


        System.out.println("\nExercici 2");
        System.out.println("2-------------------------");

        String secret4 = "secrreto";

        System.out.println("Generant claus...");
        KeyPair keys4 = Xifrar.randomGenerate(1024);

        PrivateKey clauPrivada4 = keys4.getPrivate();

        PublicKey clauPublica4 = keys4.getPublic();

        System.out.println("Encriptant secret...");
        byte[][] encriptat = Xifrar.encryptWrappedData(secret4.getBytes(),clauPublica4);


        System.out.println("Desencriptat secret...");
        byte[] decriptat = Xifrar.decryptWrappedData(encriptat, clauPrivada4);

        try {
            System.out.println(new String(decriptat, "UTF8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
