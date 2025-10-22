import java.util.*;

public class UnionFind {
    private final Map<String, String> parent = new HashMap<>();
    private final Map<String, Integer> rank = new HashMap<>();

    public UnionFind(Collection<String> elems){
        for(String e: elems){ parent.put(e,e); rank.put(e,0); }
    }
    public String find(String x){
        String p = parent.get(x);
        if(p.equals(x)) return x;
        String r = find(p);
        parent.put(x, r);
        return r;
    }
    public boolean union(String a, String b){
        String ra = find(a), rb = find(b);
        if(ra.equals(rb)) return false;
        int raRank = rank.get(ra), rbRank = rank.get(rb);
        if(raRank < rbRank) parent.put(ra, rb);
        else if(raRank > rbRank) parent.put(rb, ra);
        else { parent.put(rb, ra); rank.put(ra, raRank+1); }
        return true;
    }
}
