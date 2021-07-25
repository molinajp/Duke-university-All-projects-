

public class PhraseFilter implements Filter {
    private String where;
    private String phrase;
    private String name;
    public PhraseFilter(String whereIsItInPhrase,String thePhrase,
                        String realName){
        where = whereIsItInPhrase;
        phrase = thePhrase;
        name = realName;
    }
    public boolean satisfies(QuakeEntry qe){
        if(where.equals("any")){
            if(qe.getInfo().contains(phrase)){
                return true;    
            }
        }
        if(where.equals("end")){
            if(qe.getInfo().endsWith(phrase)){
                return true;
            }
        }
        if(where.equals("start")){
            if(qe.getInfo().startsWith(phrase)){
                return true;
            }
        }
        return false;
    }
    public String getName(){
        return name;
    }
}  
