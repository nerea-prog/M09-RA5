
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import java.security.*;

public class AES {
    
    
    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";
    
    private static final int MIDA_IV = 16;
    private static byte[] iv = new byte[MIDA_IV];
    private static final String CLAU = "1234";

    public static void main(String[] args) {
        String msgs[] = {"Lorem ipsum dicet",
                        "Hola Andrés cómo está tu cuñado",
                        "Agora ïlla Ôtto"};
        
        for (int i = 0; i < msgs.length; i++) {
            String msg = msgs[i];

            byte[] bXifrats = null;
            String desxifrat = "";
            try {
                bXifrats = XifraAES(msg, CLAU);
                desxifrat = desxifraAES(bXifrats, CLAU);
            } catch (Exception e) {
                System.err.println("Error de xifrat: " 
                + e.getLocalizedMessage() );
            }
            System.out.println("--------------------");
            System.out.println("Msg: " + msg);
            System.out.println("Enc: " + new String(bXifrats));
            System.out.println("DEC: " + desxifrat);
        }
    }

    public static byte[] XifraAES(String msg, String clau) throws Exception{
        
        //Obtenir els bytes de L'String
        byte[] byteString = msg.getBytes();
        //Genera IvParameterSpec
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        //Generar hash
        MessageDigest messageDigest = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] hashKey = messageDigest.digest(CLAU.getBytes());
        SecretKeySpec clauSecreta = new SecretKeySpec(hashKey, ALGORISME_XIFRAT);
        //Encrypt
        Cipher cifrado = Cipher.getInstance(FORMAT_AES);
        cifrado.init(cifrado.ENCRYPT_MODE, clauSecreta, ivParameterSpec);
        byte[] msgXifrat = cifrado.doFinal(byteString);
        //Combinar IV i part xifrada
        byte[] resultat = new byte[iv.length + msgXifrat.length];
        System.arraycopy(iv, 0, resultat, 0, iv.length);
        System.arraycopy(msgXifrat, 0, resultat, iv.length, msgXifrat.length);
        //return iv+msgxifrat
        return resultat;
    }

    public static String desxifraAES(byte[] bIvIMsgXifrat, String clau) throws Exception{
        //Extreure l'IV
        byte[] iv = new byte[MIDA_IV];
        System.arraycopy(bIvIMsgXifrat, 0, iv, 0, MIDA_IV);
        //Extreure la part xifrada
        byte[] msgXifrat = new byte[bIvIMsgXifrat.length - MIDA_IV];
        System.arraycopy(bIvIMsgXifrat, MIDA_IV, msgXifrat, 0, msgXifrat.length);
        //Fer hash de la clau
        MessageDigest messageDigest = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] hashKey = messageDigest.digest(clau.getBytes());
        SecretKey clauSecreta = new SecretKeySpec(hashKey, ALGORISME_XIFRAT);
        //Desxifrar
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        Cipher descrifrado = Cipher.getInstance(FORMAT_AES);
        descrifrado.init(Cipher.DECRYPT_MODE, clauSecreta, ivParameterSpec);
        byte[] msgDesxifrat = descrifrado.doFinal(msgXifrat);
        //return String desifrat
        return new String(msgDesxifrat);
    }
}
