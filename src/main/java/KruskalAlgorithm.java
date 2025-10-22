import java.util.*;

public class KruskalAlgorithm {
    public static class Result {
        public List<Edge> mst = new ArrayList<>();
        public int totalCost = 0;
        public long ops = 0;
        public double timeMs = 0;
    }

    public static Result run(GraphData g){
        long start = System.nanoTime();
        Result res = new Result();
        List<Edge> edges = g.toEdges();
        // insertion sort to count comparisons (fine for small inputs)
        for(int i=1;i<edges.size();i++){
            Edge key = edges.get(i);
            int j = i-1;
            while(j>=0){
                res.ops++;
                if(edges.get(j).w > key.w){
                    edges.set(j+1, edges.get(j));
                    res.ops++;
                    j--;
                } else break;
            }
            edges.set(j+1, key); res.ops++;
        }
        UnionFind uf = new UnionFind(g.nodes);
        for(Edge e: edges){
            res.ops++;
            if(uf.find(e.u).equals(uf.find(e.v))){ res.ops++; continue; }
            boolean merged = uf.union(e.u, e.v); res.ops++;
            if(merged){
                res.mst.add(e);
                res.totalCost += e.w;
                res.ops += 2;
            }
            if(res.mst.size() == g.nodes.size()-1) break;
        }
        long end = System.nanoTime();
        res.timeMs = (end - start)/1_000_000.0;
        return res;
    }
}
