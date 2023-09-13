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


    }


    public void addEdge(String n1, String n2) {
        //PRE: the vertices n1 and n2 have already be added
        //insert the new edge so the edge list remains sorted in ascending order


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
}
