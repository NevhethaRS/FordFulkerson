//=============================================================================================================================================================================================//
//	  							*** Driver:This program reads the input graph and calculates the maximum flow from a given source to the given sink in the graph ***    												   //
//=============================================================================================================================================================================================//
/*
 	 *  @dateCreated:		-September-25-2016
	 *  @dateLastModified:	-September-29-1016
	 *  @author: 			-Nevhetha
 */
//=============================================================================================================================================================================================//
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Driver {

    public static void main(String[] args) throws FileNotFoundException {
	Scanner in;
        if (args.length > 0) {
            File inputFile = new File(args[0]);
            in = new Scanner(inputFile);
        } else {
            in = new Scanner(System.in);
        }
      //Reading the input graph
        Graph g = Graph.readGraph(in,true);
        //printing the input graph
        System.out.println("Input Graph:");
        for(Vertex u: g) {
        	System.out.print(u + ": ");
        	for(Edge e: u.adj) {
        		System.out.print(e + " ");
        	}
        System.out.println();
        }
        FordFulkerson ff=new FordFulkerson();
        int flow=ff.fordFulkerson(g,in);
        System.out.println();
        
        //printing the output
        System.out.println("Flow along the edges of the given network: ");
    	System.out.println("============================================================");
    	for(Vertex u: g) {
    	    for(Edge e: u.adj) {
    	    	if(!e.rev) {
    	    		System.out.println();
    	    		System.out.print(" "+e + "\t");
    	    		System.out.print(" Capacity: "+e.weight+"\t"+"Flow: "+e.flow);
    	    	}
    	    }
    	}
    	System.out.println();
    	System.out.println("============================================================");
    	System.out.println("Maximum flow for the given network is: "+ flow);
    	System.out.println("============================================================");

   }
}
