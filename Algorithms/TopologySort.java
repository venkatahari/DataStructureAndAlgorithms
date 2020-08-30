import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/***
 * Topological sort
 * @author VenkataHari Shankar
 */
public class TopologySort<T> {

    Graph<T> graph;

    Stack<T> result = new Stack<>();
    Set<T> visited = new HashSet<>();

    public TopologySort(Graph<T> graph){
        this.graph= graph;
    }

    public Stack<T> sort(){
        result.clear();
        for (T vertex:graph.getAllVertex()){
            if(visited.contains(vertex))
                continue;
            sortUtil(vertex);
        }
        return result;
    }

    public void sortUtil(T currVertex){
        visited.add(currVertex);
        if(graph.getAllEdges().containsKey(currVertex)) {
            for (T vertex :graph.getAllEdges().get(currVertex)) {
                if (visited.contains(vertex))
                    continue;
                sortUtil(vertex);
            }
        }
        result.push(currVertex);
    }

    public static void main(String[] args) {
        Graph<String> graph = new Graph<>(true);
        /*  A -> E
        *   A -> B -> C
        *        D -> C
        */
        graph.addEdges("A","B");
        graph.addEdges("B","C");
        graph.addEdges("D","C");
        graph.addEdges("A","E");
        TopologySort<String> stringTopologySort = new TopologySort<>(graph);

        System.out.println(stringTopologySort.sort());
    }

}
