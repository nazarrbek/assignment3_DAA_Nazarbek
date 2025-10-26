# Assignment 3 — Optimization of a City Transportation Network (Minimum Spanning Tree)

## Objective
The purpose of this assignment is to apply Prim’s and Kruskal’s algorithms to optimize a city’s transportation network by determining the minimum set of roads that connect all city districts with the lowest total construction cost.

---

## Problem Description
The city is represented as a weighted undirected graph, where:

- **Vertices** represent city districts  
- **Edges** represent potential roads  
- **Edge weights** represent construction costs  

The goal is to find a **Minimum Spanning Tree (MST)** — a subset of edges that connects all districts with minimal total cost and no cycles.

---

## Implementation Details
This project is implemented in **Java 11** using **Maven** for dependency management.  
The program uses the **Gson** library to read graph data from a JSON file.

---

## Key Components

| Class | Description |
|-------|-------------|
| `Edge` | Represents a road (graph edge) |
| `GraphData` | Holds nodes and edges for one graph |
| `UnionFind` | Disjoint Set data structure for Kruskal’s algorithm |
| `PrimAlgorithm` | Implements Prim’s algorithm (O(V²) version) |
| `KruskalAlgorithm` | Implements Kruskal’s algorithm |
| `Main` | Entry point; runs both algorithms and generates the report |
| `InputModel` | Container for parsing JSON input with Gson |

---

## Input and Output

### Input File (`ass_3_input.json`)
Example structure (contains three graphs of different sizes):

```json
{
  "graphs": [
    {
      "id": 1,
      "nodes": ["P", "Q", "R", "S"],
      "edges": [
        {"from": "P", "to": "Q", "weight": 5},
        {"from": "P", "to": "R", "weight": 7},
        {"from": "Q", "to": "R", "weight": 3},
        {"from": "Q", "to": "S", "weight": 6},
        {"from": "R", "to": "S", "weight": 4}
      ]
    },
    {
      "id": 2,
      "nodes": ["A", "B", "C", "D", "E", "F"],
      "edges": [
        {"from": "A", "to": "B", "weight": 2},
        {"from": "A", "to": "C", "weight": 5},
        {"from": "B", "to": "C", "weight": 1},
        {"from": "B", "to": "D", "weight": 4},
        {"from": "C", "to": "E", "weight": 6},
        {"from": "D", "to": "E", "weight": 3},
        {"from": "D", "to": "F", "weight": 7},
        {"from": "E", "to": "F", "weight": 2}
      ]
    },
    {
      "id": 3,
      "nodes": ["L", "M", "N", "O", "P", "Q", "R"],
      "edges": [
        {"from": "L", "to": "M", "weight": 3},
        {"from": "L", "to": "N", "weight": 8},
        {"from": "M", "to": "N", "weight": 2},
        {"from": "M", "to": "O", "weight": 4},
        {"from": "N", "to": "O", "weight": 5},
        {"from": "O", "to": "P", "weight": 7},
        {"from": "P", "to": "Q", "weight": 6},
        {"from": "Q", "to": "R", "weight": 3},
        {"from": "O", "to": "R", "weight": 9},
        {"from": "N", "to": "Q", "weight": 5}
      ]
    }
  ]
}
```

---

### Output File (`ass_3_output_generated.json`)
Example result:

```json
{
  "results": [
    {
      "graph_id": 1,
      "input_stats": {
        "vertices": 4,
        "edges": 5
      },
      "prim": {
        "mst_edges": [
          {"from": "P", "to": "Q", "weight": 5},
          {"from": "Q", "to": "R", "weight": 3},
          {"from": "R", "to": "S", "weight": 4}
        ],
        "total_cost": 12,
        "operations_count": 55,
        "execution_time_ms": 2.16
      },
      "kruskal": {
        "mst_edges": [
          {"from": "Q", "to": "R", "weight": 3},
          {"from": "R", "to": "S", "weight": 4},
          {"from": "P", "to": "Q", "weight": 5}
        ],
        "total_cost": 12,
        "operations_count": 31,
        "execution_time_ms": 1.71
      }
    }
  ]
}
```

---

## Results Summary

Both algorithms produce identical total costs for the MST.

| Algorithm | MST Cost | Execution Time (ms) | Operations Count |
|------------|-----------|---------------------|------------------|
| Prim’s | 12 | 2.16 | 55 |
| Kruskal’s | 12 | 1.71 | 31 |

The MST cost is the same for both algorithms, confirming correctness.  
Kruskal’s algorithm was slightly faster and required fewer operations for this dataset.

---

## Analysis and Comparison

| Aspect | Prim’s Algorithm | Kruskal’s Algorithm |
|--------|------------------|---------------------|
| Approach | Expands tree vertex by vertex | Builds MST by adding the smallest edges |
| Best for | Dense graphs (many edges) | Sparse graphs (few edges) |
| Data Structures | Priority Queue | Disjoint Set (Union-Find) |
| Complexity | O(V²) or O(E log V) with heap | O(E log E) |
| Implementation difficulty | Moderate | Easy with Union-Find |
| Output | Always one MST | Same MST cost, possibly different structure |

---

## Conclusion

Both algorithms correctly construct MSTs with the same total cost.  
Kruskal’s performs fewer operations and is slightly faster on most sparse graphs.

**In general:**
- Use **Prim’s** for dense graphs.  
- Use **Kruskal’s** for sparse graphs or when edges are already sorted.

---

## How to Run

### Using Maven
```bash
mvn package
java -jar target/mst-assignment-1.0-SNAPSHOT-jar-with-dependencies.jar ass_3_input.json
```

### Using IDE
1. Open the Maven project in IntelliJ IDEA or Eclipse.  
2. Set `Main` as the entry class.  
3. Run the project.

---

## References
- Cormen, Leiserson, Rivest, Stein. *Introduction to Algorithms*, 3rd ed. MIT Press.  
- GeeksforGeeks: *Prim’s and Kruskal’s Algorithm for MST*  
- Oracle Java Documentation (JDK 11)  
- Gson Library: [https://github.com/google/gson](https://github.com/google/gson)
