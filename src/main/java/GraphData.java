import java.util.*;

public class GraphData {
    public int id;
    public List<String> nodes = new ArrayList<>();
    public List<GraphEdge> edges = new ArrayList<>();

    public static class GraphEdge {
        public String from;
        public String to;
        public int weight;
    }

    // Convert to Edge list for algorithms
    public List<Edge> toEdges() {
        List<Edge> list = new ArrayList<>();
        for(GraphEdge ge: edges){
            list.add(new Edge(ge.from, ge.to, ge.weight));
        }
        return list;
    }
}
