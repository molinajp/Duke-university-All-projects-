
public class CaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    public CaesarCipher(int key){
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0,key);
        mainKey = key;
    }
    public String encrypt(String input){
        StringBuilder sb = new StringBuilder(input);
        for(int i=0;i<sb.length();i++){
            char c = input.charAt(i);
            boolean what = false;
            if(Character.isUpperCase(c)){
                what = true;
            }
            c = Character.toUpperCase(c);
            int index = alphabet.indexOf(c);
            if(index != -1){
               if(what==true){
                  char newc = shiftedAlphabet.charAt(index);
                  sb.setCharAt(i,newc);
               }
               if(what==false){
                   char newc = shiftedAlphabet.charAt(index);
                   newc = Character.toLowerCase(newc);
                   sb.setCharAt(i,newc);
               }
            }
        }
        String result = sb.toString();
        return result;
    }
    public String decrypt(String input){
        CaesarCipher cc = new CaesarCipher(26-mainKey);
        return cc.encrypt(input);
    }
}