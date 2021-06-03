import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.Math;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;




public class Main {
	static ArrayList<Tribe> tribes;
	static PriorityQueue<Tribe> prioTribes;
	static PriorityQueue<Tribe> populoTribes;
	public static int delegatesToWin = 0;
	public static int populationVotesNeeded = 0;
	public static int total = 0;
	public static String test;
	public static int currentDelegates=0;
	public static int delegateCount=0;
	public static int populoDelegateCount=0;
	public static int populoPopuloCount=0;
	public static int delegatesRemaining=0;


	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter test name: ");
		test = scanner.nextLine();
		scanner.close();
		double start = System.nanoTime();
		createLists();
		System.out.println("Total: " + total);
		delegatesToWin = minToWin(delegateCount);

		while (delegatesToWin > currentDelegates) {
			Tribe currentPrioTribe = prioTribes.poll();
			System.out.println(currentPrioTribe.getName() + " " + currentPrioTribe.getElectoralVotes() + " " + currentPrioTribe.getPopulationVotes() + " " + currentPrioTribe.getElectorValue());
			delegatesRemaining= delegatesToWin - currentDelegates;

			if( delegatesRemaining < currentPrioTribe.getElectoralVotes()){
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

		printStats();
		double end = System.nanoTime();
		double execution = end - start;
		double power= Math.pow(10.0,9);
		execution=execution/power;
		System.out.println("The Execution Time: " + execution + " seconds");
	}


	public static int minToWin(int voters) {
		if (voters % 2 == 0) {
			return (voters / 2) + 1;
		} else {
			return (voters + 1) / 2;
		}
	}
	public static ArrayList <Tribe> getTribes() {
		ArrayList <Tribe> tribes = new ArrayList <Tribe> ();
		try (BufferedReader br = new BufferedReader(new FileReader(test))) {
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

	public static void createLists() {
		tribes = getTribes();
		prioTribes = new PriorityQueue<Tribe> (tribes.size(), new ElectorComparator());
		populoTribes = new PriorityQueue<Tribe> (tribes.size(), new PopulationComparator());

		for(int i = 0; i < tribes.size(); i++){
			prioTribes.add(tribes.get(i));
			populoTribes.add(tribes.get(i));

			delegateCount += tribes.get(i).getElectoralVotes();
			total += tribes.get(i).getPopulationVotes();
		}
		System.out.println(tribes.size() + " " + prioTribes.size());
	}

	public static void printStats(){
		System.out.println("Our delegates: " + currentDelegates);
		System.out.println("Total delegates: " + delegateCount);
		System.out.println("Delegates to win: " + delegatesToWin);
		System.out.println("Min population votes needed: " + populationVotesNeeded);
	}

}