import java.util.*;

public class BreadthFirstSearch<V> implements Search<V> {
    private final Map<V, V> edgeTo = new HashMap<>();
    private final Set<V> marked = new HashSet<>();
    private final V start;

    public BreadthFirstSearch(UnweightedGraph<V> graph, V start) {
        this.start = start;
        bfs(graph, start);
    }

    private void bfs(UnweightedGraph<V> graph, V root) {
        Queue<V> queue = new LinkedList<>();
        marked.add(root);
        queue.add(root);

        while (!queue.isEmpty()) {
            V v = queue.poll();
            for (V neighbor : graph.getMap().getOrDefault(v, new ArrayList<>())) {
                if (!marked.contains(neighbor)) {
                    marked.add(neighbor);
                    edgeTo.put(neighbor, v);
                    queue.add(neighbor);
                }
            }
        }
    }

    @Override
    public List<V> pathTo(V goal) {
        LinkedList<V> path = new LinkedList<>();
        if (!marked.contains(goal)) return path;
        for (V x = goal; !x.equals(start); x = edgeTo.get(x)) {
            path.addFirst(x);
        }
        path.addFirst(start);
        return path;
    }
}
