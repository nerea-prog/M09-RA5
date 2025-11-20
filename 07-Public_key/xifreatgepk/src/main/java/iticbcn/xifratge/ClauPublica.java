package iticbcn.xifratge;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;

import javax.crypto.Cipher;

public class ClauPublica {

    private static final String algorisme = "RSA";


    public KeyPair generaParellClausRSA() throws Exception{
        KeyPairGenerator genClaus = KeyPairGenerator.getInstance(algorisme);
        SecureRandom random = new SecureRandom();
        genClaus.initialize(2048, random);
        return genClaus.generateKeyPair();
    }

    public byte[] xifraRSA(String msg, PublicKey clauPublica) throws Exception{
        Cipher xifrador = Cipher.getInstance(algorisme);
        xifrador.init(Cipher.ENCRYPT_MODE, clauPublica);
        byte[] msgXifrat = xifrador.doFinal(msg.getBytes()); // Convertimos el cifrado de string a byte
        return msgXifrat;
    }

    public String desxifraRSA(byte[] msgXifrat, PrivateKey ClauPrivada) throws Exception{
        Cipher xifrador = Cipher.getInstance(algorisme);
        xifrador.init(Cipher.DECRYPT_MODE, ClauPrivada);
        byte[] msgDesxifrat = xifrador.doFinal(msgXifrat);
        return new String(msgDesxifrat);
    }  
}
