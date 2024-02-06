package com.example.crud_backend;

import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class KeysGenerator {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        var keyPair = keyPairGenerator.generateKeyPair() ;
        byte[] publicKey = keyPair.getPublic().getEncoded();
        byte[] privateKey = keyPair.getPrivate().getEncoded();
        PemWriter pemWriter = new PemWriter(new OutputStreamWriter(new FileOutputStream("public.pem")));
        PemObject pemObject = new PemObject("PUBLIC KEY" , publicKey);
        pemWriter.writeObject(pemObject);
        pemWriter.close();
        PemWriter pemWriter1 = new PemWriter(new OutputStreamWriter(new FileOutputStream("private.pem")));
        PemObject pemObject1 = new PemObject("PRIVATE KEY" , privateKey);
        pemWriter1.writeObject(pemObject1);
        pemWriter1.close();
    }

}
