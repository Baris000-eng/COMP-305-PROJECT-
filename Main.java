import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        ArrayList <Tribe> tribes = getTribes();
        for (int i = 0; i < tribes.size(); i++) {
            System.out.println(tribes.get(i).getName() + " " + tribes.get(i).getElectoralVotes() + " " + tribes.get(i).getPopulationVotes()+ " "+ getPopVotes(tribes.get(i).getPopulationVotes()));
        }
    }
    


    public static int getPopVotes(int populationVotes) {
        if (populationVotes % 2 == 0) {
            return (populationVotes / 2) + 1;
        } else {
            return (populationVotes + 1) / 2;
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