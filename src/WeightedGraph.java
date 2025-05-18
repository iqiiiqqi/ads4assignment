import java.util.*;
public class WeightedGraph<V> {
    private final Map<Vertex<V>, List<Vertex<V>>> map = new HashMap<>();
    private final boolean directed;

    public WeightedGraph(boolean directed) {
        this.directed = directed;
    }

    public void addEdge(V source, V dest, double weight) {
        Vertex<V> sourceVertex = new Vertex<>(source);
        Vertex<V> destVertex = new Vertex<>(dest);

        map.putIfAbsent(sourceVertex, new ArrayList<>());
        map.get(sourceVertex).add(destVertex);
        sourceVertex.addAdjacentVertex(destVertex, weight);

        if (!directed) {
            map.putIfAbsent(destVertex, new ArrayList<>());
            map.get(destVertex).add(sourceVertex);
            destVertex.addAdjacentVertex(sourceVertex, weight);
        }
    }

    public Map<Vertex<V>, List<Vertex<V>>> getMap() {
        return map;
    }

    public Vertex<V> getVertex(V data) {
        for (Vertex<V> vertex : map.keySet()) {
            if (vertex.getData().equals(data)) {
                return vertex;
            }
        }
        return null;
    }
}