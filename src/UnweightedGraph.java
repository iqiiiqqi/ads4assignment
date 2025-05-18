
import java.util.*;
public class UnweightedGraph<V> {
    private final Map<V, List<V>> map = new HashMap<>();
    private final boolean directed;

    public UnweightedGraph(boolean directed) {
        this.directed = directed;
    }

    public void addEdge(V src, V dest) {
        map.computeIfAbsent(src, k -> new ArrayList<>()).add(dest);
        if (!directed) {
            map.computeIfAbsent(dest, k -> new ArrayList<>()).add(src);
        }
    }

    public Map<V, List<V>> getMap() {
        return map;
    }
}
