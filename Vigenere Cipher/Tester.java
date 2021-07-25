
import edu.duke.*;
import java.util.*;

public class Tester {
    private String readFile(){
        FileResource fr = new FileResource();
        return fr.asString();
    }
    public void testCaesarCipher(){
        String message = readFile();
        CaesarCipher cc = new CaesarCipher(5);
        String encrypted = cc.encrypt(message);
        String decrypted = cc.decrypt(encrypted);
        System.out.println(message);
        System.out.println(encrypted);
        System.out.println(decrypted);
    }
    public void testCaesarCracker(){
        //en ingles
        String message = readFile();
        CaesarCracker C = new CaesarCracker();
        String decrypt = C.decrypt(message);
        System.out.println(C.getKey(message));
        System.out.println(decrypt);
        //en portugues
        String message2 = readFile();
        CaesarCracker C2 = new CaesarCracker('a');
        String decrypt2 = C2.decrypt(message2);
        System.out.println(C2.getKey(message2));
        System.out.println(decrypt2);
    }
    public void testVignereCipher(){
        String message = readFile();
        VigenereBreaker vb = new VigenereBreaker();
        int[] key = vb.tryKeyLength(message,38,'e');
        //for(int k=0;k<key.length;k++){
        //   System.out.println(key[k]);
        //}
        VigenereCipher vc = new VigenereCipher(key);
        //String encrypted = vc.encrypt(message);
        String decrypted = vc.decrypt(message);
        //System.out.println(message);
        //System.out.println(encrypted);
        System.out.println(decrypted);
    }
    
}