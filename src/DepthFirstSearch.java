import java.util.*;
public class DepthFirstSearch<V> implements Search<V> {
    private final Map<V, V> edgeTo = new HashMap<>();
    private final Set<V> marked = new HashSet<>();
    private final V start;

    public DepthFirstSearch(UnweightedGraph<V> graph, V start) {
        this.start = start;
        dfs(graph, start);
    }

    private void dfs(UnweightedGraph<V> graph, V v) {
        marked.add(v);
        for (V neighbor : graph.getMap().getOrDefault(v, new ArrayList<>())) {
            if (!marked.contains(neighbor)) {
                edgeTo.put(neighbor, v);
                dfs(graph, neighbor);
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
