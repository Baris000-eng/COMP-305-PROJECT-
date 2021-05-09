import Tribe;


public static void main(String[] args){

Tribe tribe= new Tribe();

List<List<String>> records = new ArrayList<>();
try (BufferedReader br = new BufferedReader(new FileReader("tribal-king-selection-test1.csv"))) {
    String line;
    while ((line = br.readLine()) != null) {
        String[] values = line.split(COMMA_DELIMITER);
        records.add(Arrays.asList(values));
    }
}
}











private int getPopularVote(int populationVotes){
  if(populationVotes%2==0){
    return (populationVotes/2)+1;
  } else {
    return (populationVotes+1)/2;
  }
}
