import Tribe;


public static void main(String[] args){

Tribe tribe= new Tribe();


}











private int getPopularVote(int populationVotes){
  if(populationVotes%2==0){
    return (populationVotes/2)+1;
  } else {
    return (populationVotes+1)/2;
  }
}