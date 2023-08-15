import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;

class SRTF {
	public static void GetTurnAroundTime(process proc[], int n, int wait[], int total[]) {
		   for (int i = 0; i < n; i++)
			   total[i] = proc[i].get_burst()+ wait[i];
		}
	public static void findWaitingTime(process proc[], int n, int wait[],int Context) {
		   int store[]=new int[n];
		  
		   String store2[]=new String[n];
		    Queue<String> execute= new LinkedList<>();;

		    //store all the process's burst time in new array
		   for (int i = 0; i < n; i++)
		    {
		        store[i] = proc[i].burst;
		        store2[i] = proc[i].pid;

		    }
		    //t for time 1 by 1 ,shortest for index & min for value of that index
		   int complete = 0, t = 0, minm = 1000;
		   int shortest = 0, finish_time;
		   //bool check = false;

		   while (complete != n) { //1 turn completed when 1 process terminated
		      for (int j = 0; j < n; j++) { //for loop to get the smallest process every 1 unit of time
		         if ((proc[j].arrival <= t) && (store[j] < minm) && store[j] > 0) {

		            minm = store[j];
		            //cout<<minm<<" try"<<endl;
		            shortest = j;
		            //check = true;
		         }
		      }
		      /*if (check == false) {
		         t++;
		         continue;
		      }*/
		      //crementing the remaining time
		      store[shortest]--;
		      minm = store[shortest];

		      if (minm == 0) //DOES BURST TIME FINISHED?
		         {minm = 1000;} //reset minm

		         // If a process gets completely
		         // executed
		         if (store[shortest] == 0) { //process terminated
		            {   complete++; //reset the steps
		                //check = false;
		                finish_time = t + 1; //finish time is the last value of t

		            // Calculate waiting time
		                wait[shortest] = finish_time - proc[shortest].burst -proc[shortest].arrival;

		            /*if (wt[shortest] < 0)
		               wt[shortest] = 0;*/
		            }
		         }
		         // Increment time
		         execute.add(proc[shortest].pid);
		         t++;

		   }
		   String temp1,temp2;
		   int counter=0;
		   String arr_exe[]=new String[10];
		    while(!execute.isEmpty())
		            {
		                 temp1=execute.peek();
		                    execute.remove();
		                if(!execute.isEmpty())
		                    {
		                         temp2=execute.peek();
		                         if(temp1==temp2)
		                         {
		                             continue;
		                         }
		                         else{
		                                arr_exe[counter]=temp1;
		                                counter++;		                           
		                              System.out.print(temp1+ " ");

		                            }

		                    }
		                else{
		                        arr_exe[counter]=temp1;		                        
		                        System.out.print(temp1+ " ");
		                        counter++;
		                }

		        }
		    	System.out.println();
		    	
		        int array_cs[]=new int[counter];
		        int result[]=new int [n];
		        for(int i=0;i<counter;i++)
		        {
		            array_cs[i]=Context*(i+1);
		        }
		        for(int a=0;a<n;a++)
		        {
		            for(int j=0;j<counter ;j++)
		            {
		                if(store2[a]==arr_exe[j])
		                {
		                    result[a]+=array_cs[j];
		                }
		            }
		        }

		        for(int b =0;b<n;b++)
		        {
		            wait[b]+=result[b];

		        }

		}
	
	public static void findavgTime(process proc[], int n,int Context) {
		   int wt[]=new int [n], tat[]=new int [n], total_wt = 0,
		   total_tat = 0;
		   
		   // Function to find waiting time of all processes
		   
		   findWaitingTime(proc, n, wt,Context);
		   // Function to find turn around time for
		   // all processes
		   GetTurnAroundTime(proc, n, wt, tat);
		   System.out.println("Processes " +" Burst time " + " Waiting time " + " Turn around time"); 
		   for (int i = 0; i < n; i++) {
		      total_wt = total_wt + wt[i];
		      total_tat = total_tat + tat[i];
		      
		     
		      System.out.println( " " + proc[i].pid +"\t\t" + proc[i].burst + "\t\t " + wt[i] + "\t\t " + tat[i] );
		   }
		   System.out.println ("Average waiting time = " + (float)total_wt / (float)n);
		   System.out.println( "Average turn around time = " + (float)total_tat / (float)n);
		}
	
public static void main(String[] args) {
   
    try (Scanner in = new Scanner (System.in)) {
		System.out.println("Enter number of processes"); 
		int Size=in.nextInt();
		
		System.out.println("Enter value of the Context switch");
		int context=in.nextInt();
		System.out.println("Enter Process_name Arrival_time Burst_time");
		 
		process[] p=new process[Size];
   
		for(int i=0;i<Size;i++)
		{
			String pid=in.next();
			int a=in.nextInt();
			int b=in.nextInt();
			p[i]=new process(pid,a,b);
			
		}
		
		System.out.print("the process execution order are :");
		findavgTime(p,Size,context);
	}
}

}
class process{
	 String pid;
   int burst; // Burst Time
   int arrival; // Arrival Time
  process(String pid ,int arrival,int burst)
  {
	   this.pid= pid;
	   this.burst=burst;
	   this.arrival=arrival;
	   
  }
  public void set_pid(String pid)
	{
		this.pid= pid;
		
		
	}
  public void set_burst(int burst)
	{
		this.burst=burst;
		
	}
  public void set_arrival(int arrival)
	{
		this.arrival=arrival;
		
	}
  public String get_pid()
  {
	   return pid;
  }
  public int get_burst()
  {
	   return burst;
  }
  public int get_arrival()
  {
	   return arrival;
  }
};

