
// Matthew Lutz, CS340, Project One

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

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
    	//System.out.println("Adding vertex: " + s);
        //insert a new vertex so the vertex list remains sorted in ascending order
    	System.out.println("Adding vertex: " + s);
        VertexNode prev = vertices;
        VertexNode curr = vertices.nextV;
        
        while (curr != null && s.compareTo(curr.name) > 0) {
            prev = curr;
            curr = curr.nextV;
        }
        
        // Check for duplicate vertices
        if (curr != null && s.equals(curr.name)) {
            return; // Don't add duplicate vertices
        }
        
        VertexNode newNode = new VertexNode(s, curr);
        prev.nextV = newNode;
        numVertices++;

    }


    public void addEdge(String n1, String n2) {
    	//System.out.println("Adding edge: " + n1 + " -> " + n2);
        //PRE: the vertices n1 and n2 have already be added
        //insert the new edge so the edge list remains sorted in ascending order

        VertexNode vertex1 = null;
        VertexNode vertex2 = null;        
        VertexNode current = vertices.nextV;
        VertexNode previous = vertices;

        while (current != null){
        	//System.out.println("Checking vertex: " + current.name);
            if (current.name.equals(n1)){
                vertex1 = current;
            }else if (current.name.equals(n2)){
                vertex2 = current;
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

        EdgeNode currentEdge = vertex1.edges;
        EdgeNode prevEdge = null;
        while(currentEdge != null && n2.compareTo(currentEdge.vertex2.name) > 0){
            prevEdge = currentEdge;
            currentEdge = currentEdge.nextE;
            
        }

        //Insertion
        EdgeNode newEdge = new EdgeNode(vertex1, vertex2, currentEdge);

        if (prevEdge == null){
            vertex1.edges = newEdge;
        }else{
            prevEdge.nextE = newEdge;
        }

    }


    public void printGraph(){
         //print the graph in the format shown in class
    	VertexNode currentV = vertices.nextV;
    	System.out.println("Vertex\t\tAdjacent Vertices");
    	while (currentV != null) {
    		System.out.print(currentV.name + "\t\t");
    		EdgeNode currentE = currentV.edges;
    		if(currentE == null) {
    			System.out.println("");
    		}else {
    			while(currentE != null) {
        			System.out.print(currentE.vertex2.name +" ");
        			currentE = currentE.nextE;
    			}
    		}
    		System.out.println();
    		currentV= currentV.nextV;
    	}
    }


    public String topoSort(){
        //return a string with the vertex names in a topological order
        //if no topological order exists return the empty string
    	String topoString = "";
        Queue<VertexNode> topoQueue = new LinkedList<>();
        int topoCount = 0;
        int vCount = 0;
        
        
        VertexNode currentV = vertices.nextV;
        while (currentV != null) {
        	vCount++;
        	EdgeNode newE = currentV.edges;
        	while(newE != null) {
        		newE.vertex2.indegree++;
        		newE =newE.nextE;
        	}
        	currentV = currentV.nextV;
        }
        
        
        currentV = vertices.nextV;
        while (currentV != null) {
        	if(currentV.indegree == 0){
        		topoQueue.add(currentV);
        	}
        	currentV = currentV.nextV;
        }
        

        while(!topoQueue.isEmpty()){
        	VertexNode i = topoQueue.poll();
        	topoString = topoString + i.name + " ";
        	
        	EdgeNode edge = i.edges;
        	while (edge != null) {
        		edge.vertex2.indegree--;
        		if(edge.vertex2.indegree == 0){
        		    topoQueue.add(edge.vertex2);
        		}
        		edge = edge.nextE;
        	}
        	topoCount++;
        }
        
        
        if (vCount == topoCount) {
        	return topoString;
        }else {
        	return "Graph contains a cycle";
        }

    }


    //Tester
    public static void main(String args[]) throws IOException{
    //Simple Test Driver
    //This driver assumes the input file is formatted correctly
    	//String filePath = "C:\\Users\\matth\\projectOne340\\testerProjectOne";

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
    		if (scan.hasNext()) {
    	        String n1 = scan.next();
    	        if (scan.hasNext()) {
    	            String n2 = scan.next();
    	            g.addEdge(n1, n2);
    	        }
    	    }
    		line = b.readLine();
    	}
    	g.printGraph();
    	System.out.println("\nTopological Order\n" + g.topoSort());
    	b.close();
    	scan.close();
    }
}
