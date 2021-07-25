
import java.util.*;

public class MatchAllFilter implements Filter {
    private ArrayList<Filter> filters;
    public MatchAllFilter(){
        filters = new ArrayList<Filter>();
    }
    public void addFilter(Filter f){
        filters.add(f);
    }
    public boolean satisfies(QuakeEntry qe){
        for(Filter f : filters){
            if(! f.satisfies(qe)){
                return false;
            }
        }
        return true;
    }
    public String getName(){
        StringBuilder sb = new StringBuilder();
        for(int k=0;k<filters.size();k++){
            Filter f = filters.get(k);
            sb.append(f.getName() + ", ");
        }
        String answer = sb.toString();
        return answer;
    }
}
