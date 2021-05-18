import java.io.*;
import java.util.*;

public class Main {

  public static int delegatesToWin = 0;
  public static int populationVotesNeeded = 0;
  public static int total = 0;

  public static void main(String[] args) {
    int delegateCount = 0;
    ArrayList<Tribe> tribes = getTribes();
    PriorityQueue<Tribe> prioTribes = new PriorityQueue<Tribe> (tribes.size(), new ElectorComparator());
    PriorityQueue<Tribe> populoTribes = new PriorityQueue<Tribe> (tribes.size(), new PopulationComparator());

    for(int i = 0; i < tribes.size(); i++){
      prioTribes.add(tribes.get(i));
      populoTribes.add(tribes.get(i));
    }
    //PriorityQueue<Tribe> delegoTribes = new PriorityQueue<Tribes> (tribes);
    System.out.println(tribes.size() + " " + prioTribes.size());
    for (int i = 0; i < tribes.size(); i++) {
      delegateCount += tribes.get(i).getElectoralVotes();
      total += tribes.get(i).getPopulationVotes();
    }
    System.out.println("Total: " + total);
    delegatesToWin = minToWin(delegateCount);
    int size = prioTribes.size();
    int currentDelegates = 0;
    while (delegatesToWin > currentDelegates) {
      Tribe currentPrioTribe = prioTribes.poll();
      System.out.println(currentPrioTribe.getName() + " " + currentPrioTribe.getElectoralVotes() + " " + currentPrioTribe.getPopulationVotes() + " " + currentPrioTribe.getElectorValue());
      int delegatesRemaining= delegatesToWin - currentDelegates;
      if( delegatesRemaining < currentPrioTribe.getElectoralVotes()){
        int populoDelegateCount = 0;
        int populoPopuloCount = 0;
        Tribe currentPopuloTribe = currentPrioTribe;
        while (delegatesRemaining > populoDelegateCount){
          currentPopuloTribe = populoTribes.poll();
          populoDelegateCount += currentPopuloTribe.getElectoralVotes();
          populoPopuloCount += currentPopuloTribe.getPopVotesNeeded();
        }
        if (populoPopuloCount < currentPrioTribe.getPopVotesNeeded()){
          currentDelegates += currentPopuloTribe.getElectoralVotes();
          populationVotesNeeded += currentPopuloTribe.getPopVotesNeeded();
          prioTribes.remove(currentPopuloTribe);
          System.out.println("*From Population List* " + currentPopuloTribe.getName() +" " + currentPopuloTribe.getElectoralVotes() + " " + currentPopuloTribe.getPopulationVotes() + " " + currentPopuloTribe.getElectorValue());
        } else {
           currentDelegates += currentPrioTribe.getElectoralVotes();
           populationVotesNeeded += currentPrioTribe.getPopVotesNeeded();
           populoTribes.remove(currentPrioTribe);
        }
         
      } else {
      currentDelegates += currentPrioTribe.getElectoralVotes();
      populationVotesNeeded += currentPrioTribe.getPopVotesNeeded();
      populoTribes.remove(currentPrioTribe);
      }
    }
    System.out.println("Our delegates: " + currentDelegates);
    System.out.println("Total delegates: " + delegateCount);
    System.out.println("Delegates to win: " + delegatesToWin);
    System.out.println("Min population votes needed: " + populationVotesNeeded);
  }
  public static int minToWin(int voters) {
    if (voters % 2 == 0) {
      return (voters / 2) + 1;
    } else {
      return (voters + 1) / 2;
    }
  }
  public static ArrayList < Tribe > getTribes() {
    ArrayList < Tribe > tribes = new ArrayList < Tribe > ();
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