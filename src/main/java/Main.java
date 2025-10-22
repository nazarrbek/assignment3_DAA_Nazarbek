import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import com.google.gson.Gson;

public class Main {
    private static String buildOutput(List<GraphData> graphs,
                                      Map<Integer, PrimAlgorithm.Result> primResults,
                                      Map<Integer, KruskalAlgorithm.Result> kruskalResults) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n  \"results\": [\n");
        boolean firstG = true;

        for (GraphData g : graphs) {
            if (!firstG) sb.append(",\n");
            firstG = false;
            PrimAlgorithm.Result p = primResults.get(g.id);
            KruskalAlgorithm.Result k = kruskalResults.get(g.id);

            sb.append("    {\n");
            sb.append("      \"graph_id\": ").append(g.id).append(",\n");
            sb.append("      \"input_stats\": {\n");
            sb.append("        \"vertices\": ").append(g.nodes.size()).append(",\n");
            sb.append("        \"edges\": ").append(g.edges.size()).append("\n");
            sb.append("      },\n");

            // Prim section
            sb.append("      \"prim\": {\n");
            sb.append("        \"mst_edges\": [\n");
            for (int i = 0; i < p.mst.size(); i++) {
                sb.append("          ").append(p.mst.get(i).toString());
                if (i < p.mst.size() - 1) sb.append(",");
                sb.append("\n");
            }
            sb.append("        ],\n");
            sb.append("        \"total_cost\": ").append(p.totalCost).append(",\n");
            sb.append("        \"operations_count\": ").append(p.ops).append(",\n");
            sb.append("        \"execution_time_ms\": ")
                    .append(String.format(Locale.US, "%.2f", p.timeMs)).append("\n");
            sb.append("      },\n");

            // Kruskal section
            sb.append("      \"kruskal\": {\n");
            sb.append("        \"mst_edges\": [\n");
            for (int i = 0; i < k.mst.size(); i++) {
                sb.append("          ").append(k.mst.get(i).toString());
                if (i < k.mst.size() - 1) sb.append(",");
                sb.append("\n");
            }
            sb.append("        ],\n");
            sb.append("        \"total_cost\": ").append(k.totalCost).append(",\n");
            sb.append("        \"operations_count\": ").append(k.ops).append(",\n");
            sb.append("        \"execution_time_ms\": ")
                    .append(String.format(Locale.US, "%.2f", k.timeMs)).append("\n");
            sb.append("      }\n");
            sb.append("    }");
        }

        sb.append("\n  ]\n}\n");
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        String inputPath = "ass_3_input.json";
        if (args.length > 0) inputPath = args[0];

        String content = Files.readString(Paths.get(inputPath));
        Gson gson = new Gson();
        InputModel model = gson.fromJson(content, InputModel.class);
        List<GraphData> graphs = model.graphs;

        Map<Integer, PrimAlgorithm.Result> primResults = new LinkedHashMap<>();
        Map<Integer, KruskalAlgorithm.Result> kruskalResults = new LinkedHashMap<>();

        for (GraphData g : graphs) {
            PrimAlgorithm.Result pr = PrimAlgorithm.run(g);
            KruskalAlgorithm.Result kr = KruskalAlgorithm.run(g);
            primResults.put(g.id, pr);
            kruskalResults.put(g.id, kr);
        }

        String out = buildOutput(graphs, primResults, kruskalResults);
        Files.writeString(Paths.get("ass_3_output_generated.json"), out);
        System.out.println("✅ Output written to ass_3_output_generated.json");
    }

}