
import java.util.*;
public class DijkstraSearch<V> implements Search<V> {
    private final Map<V, Double> distTo = new HashMap<>();
    private final Map<V, V> edgeTo = new HashMap<>();
    private final V start;

    public DijkstraSearch(WeightedGraph<V> graph, V start) {
        this.start = start;
        dijkstra(graph, start);
    }

    private void dijkstra(WeightedGraph<V> graph, V root) {
        Vertex<V> startVertex = graph.getVertex(root);
        if (startVertex == null) return;

        PriorityQueue<Vertex<V>> pq = new PriorityQueue<>(Comparator.comparingDouble(
                v -> distTo.getOrDefault(v.getData(), Double.POSITIVE_INFINITY)));

        distTo.put(root, 0.0);
        pq.add(startVertex);

        while (!pq.isEmpty()) {
            Vertex<V> current = pq.poll();
            V currentData = current.getData();

            for (Map.Entry<Vertex<V>, Double> entry : current.getAdjacentVertices().entrySet()) {
                Vertex<V> neighbor = entry.getKey();
                double weight = entry.getValue();
                double newDist = distTo.get(currentData) + weight;

                if (newDist < distTo.getOrDefault(neighbor.getData(), Double.POSITIVE_INFINITY)) {
                    distTo.put(neighbor.getData(), newDist);
                    edgeTo.put(neighbor.getData(), currentData);
                    pq.add(neighbor);
                }
            }
        }
    }

    @Override
    public List<V> pathTo(V goal) {
        LinkedList<V> path = new LinkedList<>();
        if (!distTo.containsKey(goal)) return path;
        for (V x = goal; !x.equals(start); x = edgeTo.get(x)) {
            path.addFirst(x);
        }
        path.addFirst(start);
        return path;
    }
}
