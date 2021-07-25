
import edu.duke.*;

public class TestCaesarCipher {
    public int[] countLetters(String message){
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int[] counts = new int[26];
        for(int i=0;i<message.length();i++){
            char ch = Character.toUpperCase(message.charAt(i));
            int index = alphabet.indexOf(ch);
            if(index != -1){
                counts[index] += 1;
            }
        }
        return counts;
    }
    public int maxIndex(int[] freq){
        int result = 0;
        for(int i=0;i<freq.length;i++){
            int currIndex = freq[i];
            if(currIndex>freq[result]){
                result = i;
            }
        }
        return result;
    }
    public String breakCaesarCipher(String input){
        int[] countL = countLetters(input);
        int maxL = maxIndex(countL);
        int dkey = maxL - 4;
        if(maxL<4){
            dkey = 26 - (4 - maxL);
        }
        CaesarCipher cc = new CaesarCipher(dkey);
        return cc.decrypt(input);
    }
    public void simpleTests(){
        //FileResource fr = new FileResource();
        String message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        System.out.println("Message: " + message);
        CaesarCipher cc = new CaesarCipher(15);
        String encrypt = cc.encrypt(message);
        System.out.println("Message encrypted: " + encrypt);
        String decrypt = cc.decrypt(encrypt);
        System.out.println("Message decrypted 1: " + decrypt);
        String decrypt2 = breakCaesarCipher(encrypt);
        System.out.println("Message decrypted 2: " + decrypt2);
    }
}
