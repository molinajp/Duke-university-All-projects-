

public class CaesarCipherTwo {
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int mainKey1;
    private int mainKey2;
    public CaesarCipherTwo(int key1,int key2){
        alphabet = "abcdefghijklmnopqrstuvwxyz";
        shiftedAlphabet1 = alphabet.substring(key1) + 
                           alphabet.substring(0,key1);
        shiftedAlphabet2 = alphabet.substring(key2) +
                           alphabet.substring(0,key2);
        mainKey1 = key1;
        mainKey2 = key2;
    }
    public String encryptTwoKeys(String input){
        StringBuilder sb = new StringBuilder(input);
        for(int i=0;i<sb.length();i=i+2){
            char c = input.charAt(i);
            boolean what = false;
            if(Character.isLowerCase(c)){
                what = true;
            }
            c = Character.toLowerCase(c);
            int index = alphabet.indexOf(c);
            if(index != -1){
                if (what == false){
                    char newc = shiftedAlphabet1.charAt(index);
                    newc = Character.toUpperCase(newc);
                    sb.setCharAt(i,newc);
                }
                if (what == true){
                    char newc = shiftedAlphabet1.charAt(index);
                    
                    sb.setCharAt(i,newc);
                }
            }
        }
        for(int i=1;i<sb.length();i=i+2){
            char c = input.charAt(i);
            boolean what = false;
            if(Character.isLowerCase(c)){
                what = true;
            }
            c = Character.toLowerCase(c);
            int index = alphabet.indexOf(c);
            if(index != -1){
                if (what == false){
                    char newc = shiftedAlphabet2.charAt(index);
                    newc = Character.toUpperCase(newc);
                    sb.setCharAt(i,newc);
                }
                if (what == true){
                    char newc = shiftedAlphabet2.charAt(index);
                    
                    sb.setCharAt(i,newc);
                }
            }
        }
        String result = sb.toString();
        return result;
    }
    public String decrypt(String input){
        CaesarCipherTwo cc = new CaesarCipherTwo(26-mainKey1,26-mainKey2);
        return cc.encryptTwoKeys(input);
    }
} 
