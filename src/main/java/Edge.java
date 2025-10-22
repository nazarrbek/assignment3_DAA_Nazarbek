public class Edge implements Comparable<Edge> {
    public final String u;
    public final String v;
    public final int w;
    public Edge(String u, String v, int w) { this.u = u; this.v = v; this.w = w; }
    @Override
    public int compareTo(Edge o) { return Integer.compare(this.w, o.w); }
    @Override
    public String toString() {
        return String.format("{\"from\": \"%s\", \"to\": \"%s\", \"weight\": %d}", u, v, w);
    }
}
