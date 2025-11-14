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
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance(algorisme);
        SecureRandom random = new SecureRandom();
        keyGen.initialize(2048, random);
        return keyGen.generateKeyPair();
    }

    public byte[] xifraRSA(String msg, PublicKey clauPublica) throws Exception{
        Cipher cipher = Cipher.getInstance(algorisme);
        cipher.init(Cipher.ENCRYPT_MODE, clauPublica);
        byte[] msgXifrat = cipher.doFinal(msg.getBytes()); // Convertimos el cifrado de string a byte
        return msgXifrat;
    }

    public String desxifraRSA(byte[] msgXifrat, PrivateKey ClauPrivada) throws Exception{
        Cipher cipher = Cipher.getInstance(algorisme);
        cipher.init(Cipher.DECRYPT_MODE, ClauPrivada);
        byte[] msgDesxifrat = cipher.doFinal(msgXifrat);
        return new String(msgDesxifrat);
    }  
}
