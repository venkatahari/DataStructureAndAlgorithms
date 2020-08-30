import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * Simple Graph Implementation for Other Graph Problem and Solution Algorithms
 * @param <T> Generic Vertex value
 */
public class Graph<T> {

    List<T> vertexes;
    Map<T, List<T>> edges;
    boolean isDirected;

    public Graph(){
        vertexes = new ArrayList<>();
        edges  = new HashMap<>();
    }

    public Graph(boolean isDirected){
        this();
        this.isDirected = isDirected;
    }

    public void addEdges(T vertex1, T vertex2){
        if(!vertexes.contains(vertex1)){
            vertexes.add(vertex1);
        }
        if(!vertexes.contains(vertex2)){
            vertexes.add(vertex2);
        }
        if(!isDirected) {
            addEdgeList(vertex2, vertex1);
        }
        addEdgeList(vertex1, vertex2);
    }

    private void addEdgeList(T vertex1, T vertex2){
        if(!edges.containsKey(vertex1)){
            List<T> list = new ArrayList<>();
            list.add(vertex2);
            edges.put(vertex1, list);
        }else{
            edges.get(vertex1).add(vertex2);
        }
    }

    public List<T> getAllVertex(){
        return vertexes;
    }

    public Map<T,List<T>> getAllEdges(){
        return edges;
    }

    public static void main(String[] args) {
        //Directed
        Graph<Integer> graph = new Graph<>(true);
        graph.addEdges(1,2);
        graph.addEdges(2,3);
        graph.addEdges(4,5);
        graph.addEdges(2,5);

        System.out.println(graph.getAllVertex());
        System.out.println(graph.getAllEdges());

        //Un-Directed
        Graph<String> graphAlpha = new Graph<>();
        graphAlpha.addEdges("A","B");
        graphAlpha.addEdges("B","C");

        System.out.println(graphAlpha.getAllVertex());
        System.out.println(graphAlpha.getAllEdges());

    }
}
