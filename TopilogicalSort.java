// Matthew Lutz, CS340, Project One
import java.io;
import java.util;
import java.util.Queue;

public class TopologicalSort {
    private class VertexNode {
        private String name;
        private VertexNode nextV;   //reference to the next vertex
        private EdgeNode edges;     //head of the edge list for the vertex
                                    //the edge lists are sorted in ascending order
                                    //based of the name of the second vertex in the edge
        private int indegree;
        private VertexNode(String n, VertexNode v) {
        name = n;
        nextV = v;
        edges = null;
        indegree = 0;
        }
    }


    private class EdgeNode {
        private VertexNode vertex1; //the edge (vertex1, vertex2)
        private VertexNode vertex2;
        private EdgeNode nextE; //reference to the next edge in the edge list
        private EdgeNode(VertexNode v1, VertexNode v2,EdgeNode e) {
        vertex1 = v1;
        vertex2 = v2;
        nextE = e;
        }
    }

    private VertexNode vertices;
    private int numVertices;

    public TopologicalSort() {
        vertices = new VertexNode(null, null); //sentinel node
        numVertices = 0;
    }


    public void addVertex(String s){
        //insert a new vertex so the vertex list remains sorted in ascending order
        if(vertices.nextV == null){
            VertexNode newVertex = new VertexNode(s, null);
            vertices.nextV = newVertex;
            numVertices ++;
            return;
        }
        VertexNode current = vertices;
        VertexNode previous = null;
        while(current.nextV != null){
            int compareResult = s.compareTo(current.name);
            if(compareResult < 0){
                VertexNode newVertex = new VertexNode(s, current);
                previous.nextV = newVertex;
                numVertices ++;
                return;
            }
            previous = current;
            current = current.nextV;
            }
        
        VertexNode newVertex = new VertexNode(s, null);
        previous.nextV = newVertex;
        numVertices++;
    }

    }


    public void addEdge(String n1, String n2) {
        //PRE: the vertices n1 and n2 have already be added
        //insert the new edge so the edge list remains sorted in ascending order

        VertexNode vertex1 = null;
        VertexNode vertex2 = null;        
        VertexNode current = vertices.nextV;

        while (current != null){
            if (current.name.equals(n1)){
                VertexNode vertex1 = n1;
            }else if (current.name.equals(n2)){
                VertexNode vertex2 = n2;
            }
            if(vertex1 != null && vertex2 != null) {
                break;
            }
            previous = current;
            current = current.nextV;
        }

        //For testing
        if (vertex1 == null || vertex2 == null) {
            System.out.println("One or both vertices not found!");
            return;
        }        

        EdgeNode currentEdge = vertex1.edge;
        EdgeNode prevEdge = null;
        while(currentedge != null && n2.compareTo(currentEdge.vertex2.name)){
            prevEdge = currentEdge;
            currentEdge = currentedge.nextE;
            
        }

        //Insertion
        EdgeNode newEdge = new EdgeNode(vertex1, vertex2, currentEdge);

        if (prevEdge == null){
            vertex1.edge = vertices;
        }else{
            prevEdge.nextE = newEdge;
        }

    }


    public void printGraph(){
         //print the graph in the format shown in class


    }


    public String topoSort(VertexNode v){
        //return a string with the vertex names in a topological order
        //if no topological order exists return the empty string
        Queue<VertexNode> topoQueue = new LinkedList<>();


        while(!queue.isEmpty()){

        }

    }


    private int inDegree(VertexNode v){

    }


    //Tester
    public static void main(String args[]) throws IOException{
    //Simple Test Driver
    //This driver assumes the input file is formatted correctly
    BufferedReader b = new BufferedReader(new FileReader(args[0]));
    TopologicalSort g = new TopologicalSort();
    String line = b.readLine();
    Scanner scan = new Scanner(line);
    while (scan.hasNext()) {
        g.addVertex(scan.next());
    }
    line = b.readLine();
    while (line != null) {
        scan = new Scanner(line);
        g.addEdge(scan.next(), scan.next());
        line = b.readLine();
    }
    g.printGraph();
    System.out.println(“\nTopological Order\n”+g.topoSort());
    
    }
}
