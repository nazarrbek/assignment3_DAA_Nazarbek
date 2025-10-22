import java.util.*;

public class PrimAlgorithm {
    public static class Result {
        public List<Edge> mst = new ArrayList<>();
        public int totalCost = 0;
        public long ops = 0;
        public double timeMs = 0;
    }

    public static Result run(GraphData g){
        long start = System.nanoTime();
        Result res = new Result();
        List<String> nodes = g.nodes;
        int n = nodes.size();
        Map<String, Integer> key = new HashMap<>();
        Map<String, String> parent = new HashMap<>();
        Set<String> inMST = new HashSet<>();
        for(String v: nodes){ key.put(v, Integer.MAX_VALUE); parent.put(v, null); res.ops++; }
        String startNode = nodes.get(0); key.put(startNode, 0); res.ops++;
        List<Edge> edges = g.toEdges();
        for(int count=0; count<n; count++){
            String u = null; int min = Integer.MAX_VALUE;
            for(String v: nodes){
                res.ops++;
                if(!inMST.contains(v) && key.get(v) < min){
                    min = key.get(v); u = v; res.ops++;
                }
            }
            if(u==null) break;
            inMST.add(u); res.ops++;
            if(parent.get(u)!=null){
                res.mst.add(new Edge(parent.get(u), u, key.get(u)));
                res.totalCost += key.get(u);
                res.ops += 2;
            }
            for(Edge e: edges){
                String other = null;
                if(e.u.equals(u)) other = e.v;
                else if(e.v.equals(u)) other = e.u;
                if(other!=null){
                    res.ops++;
                    if(!inMST.contains(other) && e.w < key.get(other)){
                        key.put(other, e.w);
                        parent.put(other, u);
                        res.ops += 2;
                    }
                }
            }
        }
        long end = System.nanoTime();
        res.timeMs = (end - start) / 1_000_000.0;
        return res;
    }
}
