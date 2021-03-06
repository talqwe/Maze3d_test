package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

import controller.Command;

/**
 * @author Tal Mishaan 203908652 And Guy Binyamin 200958098
 *
 */
public class CLI{
	private HashMap<String,Command> hm;
	BufferedReader in;
	PrintWriter out;
	
	/**
	 * Constructor
	 * @param in BufferedReader The object to read input from
	 * @param out PrintWriter The object to write output to
	 * @param hms HashMap The String Command hash map
	 */
	public CLI(BufferedReader in, PrintWriter out, HashMap<String,Command> hms) {
		super();
		this.in = in;
		this.out = out;
		this.hm = new HashMap<String,Command>();
		this.hm.putAll(hms);
	}
	
	/**
	 * Start working
	 */
	public void start(){
		new Thread(new Runnable(){
			@Override
			public void run()
			{
				Scanner s;
				String [] choice = new String[10];//all the command values and names don't go to 10 values
				int i = 0;
				
				do{
					System.out.println("\nEnter The commnad you want and its parameters (separated with spaces)");
					System.out.println("1) Dir: dir <path>");//2
					System.out.println("2) Generate3DMaze: generate_3d_maze <name> <levels> <rows> <columns> <Simple/Growing>");//6
					System.out.println("3) Display: display <name>");//2
					System.out.println("4) DisplayCrossSectionBy: display_cross_section <cross (X/Y/Z)> <index> <name>");//4
					System.out.println("5) SaveMaze: save_maze <name> <file name>");//3
					System.out.println("6) LoadMaze: load_maze <file name> <name> ");//3
					System.out.println("7) Solve: solve <name> <BFS/DFS>");//3
					System.out.println("8) DisplaySolution: display_solution <name>");//2
					System.out.println("9) Exit: Bye!");//1
					
//					dir c:\
//					generate_3d_maze tal 10 10 10 Growing
//					generate_3d_maze tal2 10 10 10 Simple
//					display tal
//					display_cross_section x 6 tal
//					save_maze tal c:\tal_maze
//					load_maze c:\tal_maze tal
//					solve tal BFS
//					solve tal2 DFS
//					display_solution tal
//					display_solution tal2
					
					try{
						s= new Scanner(in.readLine());
						s.useDelimiter(" ");
						while(s.hasNext())
						{
							choice[i] = s.next();
							i++;
						}
						
						if(hm.containsKey(choice[0]))
					    {
							hm.get(choice[0]).doCommand(choice);
					    }
					}catch(IOException e)
					{
						out.println("Enter valid Command");
					}
					
					i=0;
					
				}while(!choice[0].equals("exit"));
			 
			}
		}).start();
	}
}
