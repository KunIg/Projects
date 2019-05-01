import java.util.*;
import java.lang.*;
import java.io.*;

/* Examples
INPUT
3 3 4
1 3 2 1
1 2 1
2 3 -1
3 1 2
OUTPUT
0
INPUT
4 3 4
1 2 3 4
1 2 10
2 3 1
3 4 10
OUTPUT
11
*/
class Main
{
	public static void main (String[] args)
	{
	System.out.println("Enter number of nodes, number of edges and number of nodes on a pass through the graph.");
	Scanner s = new Scanner(System.in);
	int crossings=s.nextInt();
	int roads=s.nextInt();
	int stops=s.nextInt();
	s.nextLine();
    ArrayList<Integer> stop_list=new ArrayList<Integer>();
    ArrayList<road> road_list=new ArrayList<road>();
    ArrayList<crossing> cross_list=new ArrayList<crossing>();
    System.out.println("Enter the node numbers in the order in which they will be passed.");
    for (int i=0;i<stops;i++){
    	stop_list.add(s.nextInt());
    }
    System.out.println("Enter the node numbers in the order in which they will be passed.");
    s.nextLine();
    System.out.println("Enter the start and end node of an edge and the cost of traversing it for the "+roads+" edges in this graph.");
    for (int i=0;i<roads;i++){
    	int f_cr=s.nextInt();
    	int n_cr=s.nextInt();
    	int pr=s.nextInt();
    	road temp= new road(f_cr,n_cr,pr);
    	road_list.add(i, temp);
    	s.nextLine();
    }
    s.close();
    for (int i=0;i<crossings;i++){
		cross_list.add(i, new crossing());
    }
    for (road r : road_list){
    	cross_list.get(r.first_cross-1).cost_next_crossing=r.price;
    	cross_list.get(r.next_cross-1).cost_prev_crossing=r.price;
    }
    int min_cost=100;
    int node_nr=0;
    for (int i=1;i<stops-1;i++){
    	int curr_cross=cross_list.get(stop_list.get(i)-1).sum();
    	if (curr_cross<min_cost){
    		min_cost=curr_cross;
    		node_nr=cross_list.get(stop_list.get(i)-1).cross_n;
    }
	}
	System.out.println("The cheapest node to stop at costs "+min_cost+" and is node number "+node_nr+".");
}
}
class road{
	int first_cross;
	int next_cross;
	int price;
	road(int f,int n, int p){
		first_cross=f;
		next_cross=n;
		price=p;
	}
}
class crossing{
	static int number_of_crossings=0;
	int cost_next_crossing;
	int cost_prev_crossing;
	int cross_n;
	crossing(){
	    number_of_crossings++;
	    cross_n=number_of_crossings;
	}
	int sum(){
		return cost_next_crossing+cost_prev_crossing;
	}
}
