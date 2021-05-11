import java.io.*;
import java.util.*;


public class Main {

    public static int delegatesToWin=0;
     public static int populationVotesNeeded=0;
     public static int total = 0;
     
    public static void main(String[] args) {
        int delegateCount=0;
        ArrayList <Tribe> tribes = getTribes();
        PriorityQueue<Tribe> prioTribes = new PriorityQueue<Tribe>(tribes);
        System.out.println(tribes.size()+" "+prioTribes.size());
        
        for (int i = 0; i < tribes.size(); i++) {
            //System.out.println(tribes.get(i).getName() + " " + tribes.get(i).getElectoralVotes() + " " + tribes.get(i).getPopulationVotes()+ " "+ getPopVotes(tribes.get(i).getPopulationVotes()));
          delegateCount+=tribes.get(i).getElectoralVotes();
          total+=tribes.get(i).getPopulationVotes();
        }
        System.out.println("Total: "+total);
        
       
        delegatesToWin = minToWin(delegateCount);
        
      int size= prioTribes.size();
      /*for (int i = 0; i < size; i++) {
        Tribe tribe = prioTribes.poll();
            System.out.println(tribe.getName() + " " + tribe.getElectoralVotes() + " " + tribe.getPopulationVotes()+ " "+tribe.getElectorValue());
          delegateCount+=tribe.getElectoralVotes();
          
        }*/
     
        int currentDelegates = 0;
        while (delegatesToWin > currentDelegates){
          Tribe currentTribe = prioTribes.poll();
          System.out.println(currentTribe.getName() + " " + currentTribe.getElectoralVotes() + " " + currentTribe.getPopulationVotes()+ " "+currentTribe.getElectorValue());
          currentDelegates += currentTribe.getElectoralVotes();
          populationVotesNeeded += currentTribe.getPopVotesNeeded();
        }
        System.out.println("Total delegates: " + delegateCount);
        System.out.println("Delegates to win: "+delegatesToWin);
        System.out.println("Min population votes needed: "+populationVotesNeeded);
        
    }
    
   


    public static int minToWin(int voters) {
        if (voters % 2 == 0) {
            return (voters / 2) + 1;
        } else {
            return (voters + 1) / 2;
        }
    }



    public static ArrayList <Tribe> getTribes() {
        ArrayList <Tribe> tribes = new ArrayList <Tribe>();
        try (BufferedReader br = new BufferedReader(new FileReader("tribal-king-selection-test1.csv"))) {
            String line;
            line = br.readLine();
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                Tribe tribe = new Tribe(values[0], Integer.parseInt(values[2]), Integer.parseInt(values[3]));
                tribes.add(tribe);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tribes;
    }

    
   
}
