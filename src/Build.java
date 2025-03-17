import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Build {

  /**
   * Prints words that are reachable from the given vertex and are strictly
   * shorter than k characters.
   * If the vertex is null or no reachable words meet the criteria, prints
   * nothing.
   *
   * @param vertex the starting vertex
   * @param k      the maximum word length (exclusive)
   */
  public static void printShortWords(Vertex<String> vertex, int k) {
    // create helper method using set to track visited
    // vertex == null return ""
    // add vertex to set
    // for each loop on neighbors

    printShortWordsHelper(vertex, k, new HashSet<>());
  }

  public static void printShortWordsHelper(Vertex<String> vertex, int k, Set<Vertex<String>> visited) {
    if (vertex == null) {
      System.out.println("");
      return;
    }

    visited.add(vertex);

    if (!visited.contains(vertex)) {
      System.out.println("");
      return;
    }

      if (vertex.data.length() < k) {
        System.out.println(vertex.data);
      }


    for (var neighbor : vertex.neighbors) {
      if (!visited.contains(neighbor)) {
        printShortWordsHelper(neighbor, k, visited);
      }
    }

  }

  /**
   * Returns the longest word reachable from the given vertex, including its own
   * value.
   *
   * @param vertex the starting vertex
   * @return the longest reachable word, or an empty string if the vertex is null
   */
  public static String longestWord(Vertex<String> vertex) {
    // make the starting vertex the longest
    // add vertex to set.
    // logic for checking if neighbors are longer
    return longestWordHelper(vertex, new HashSet<>());
  }

  public static String longestWordHelper(Vertex<String> vertex, Set<Vertex<String>> visited){
    if(vertex == null){
      return "";
    }

    String longest = vertex.data;

    visited.add(vertex);

    for(var neighbor : vertex.neighbors){
      if(!visited.contains(neighbor)){
        if(neighbor.data.length() > longest.length()){
          longest = neighbor.data;
        }

        String recurseLongest = longestWordHelper(neighbor, visited);

        if(recurseLongest.length() > longest.length()){
          return recurseLongest;
        }
      }
    }
    return longest;
  }

  /**
   * Prints the values of all vertices that are reachable from the given vertex
   * and
   * have themself as a neighbor.
   *
   * @param vertex the starting vertex
   * @param <T>    the type of values stored in the vertices
   */
  public static <T> void printSelfLoopers(Vertex<T> vertex) {
    printSelfLoopersHelper(vertex, new HashSet<>());
  }

  public static <T> void printSelfLoopersHelper(Vertex<T> vertex, Set<Vertex<T>> visited){
    if(vertex == null){
      return;
    }


    if(vertex.neighbors.contains(vertex)){
      System.out.println(vertex.data);
    }

    visited.add(vertex);

    for(var neighbor : vertex.neighbors){
      if(!visited.contains(neighbor)){
        
          printSelfLoopersHelper(neighbor, visited);
        
      }
    }

  }

  /**
   * Determines whether it is possible to reach the destination airport through a
   * series of flights
   * starting from the given airport. If the start and destination airports are
   * the same, returns true.
   *
   * @param start       the starting airport
   * @param destination the destination airport
   * @return true if the destination is reachable from the start, false otherwise
   */
  public static boolean canReach(Airport start, Airport destination) {
    // if start == null return false;
    //if destination == null return false
    // create helper method for using set to track visited airports
    //
    return canReachHelper(start, destination, new HashSet<>());
  }

  public static boolean canReachHelper(Airport start, Airport destination, Set<Airport> visited){
    if(start == null || destination == null) return false;
    if(start == destination) return true;

    visited.add(start);

    for(Airport neighbor : start.getOutboundFlights()){
      if(!visited.contains(neighbor)){
        if(canReachHelper(neighbor, destination, visited)){
          return true;
        }
      }
    }

    return false; 
  }

  /**
   * Returns the set of all values in the graph that cannot be reached from the
   * given starting value.
   * The graph is represented as a map where each vertex is associated with a list
   * of its neighboring values.
   *
   * @param graph    the graph represented as a map of vertices to neighbors
   * @param starting the starting value
   * @param <T>      the type of values stored in the graph
   * @return a set of values that cannot be reached from the starting value
   */
  public static <T> Set<T> unreachable(Map<T, List<T>> graph, T starting) {
    return unreachableHelper(graph, starting, new HashSet<>(), new HashSet<>(graph.keySet()));
  }

  public static <T> Set<T> unreachableHelper(Map<T, List<T>> graph, T starting, Set<T> visited,
                            Set<T> allNodes) {
    if(!graph.containsKey(starting)){
       return allNodes;
    }

    visited.add(starting);

    for(var neighbor : graph.get(starting)){
      if(!visited.contains(neighbor))
      unreachableHelper(graph, neighbor, visited, allNodes);
    }

    //subtract visited nodes from allNodes
    allNodes.removeAll(visited);

    return allNodes;


  }
}
