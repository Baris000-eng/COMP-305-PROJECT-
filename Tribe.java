public class Tribe implements Comparable<Tribe>{



  public String name;
  public int electoralVotes;
  public int populationVotes;
  public int popVotesNeeded;
  public double electorValue;
  

  public Tribe(String name,int electoralVotes,int populationVotes){
    this.popVotesNeeded = minToWin(populationVotes);
    this.name=name;
    this.electoralVotes=electoralVotes;
    this.populationVotes=populationVotes;
    this.electorValue=((double)populationVotes/(double)electoralVotes);

  }


  public int compareTo(Tribe t){  
    if(electorValue==t.electorValue){  
      return 0;  
    }else if(electorValue>t.electorValue){
      return 1;  
    }  
    return -1;  
  }  

  public String getName(){
    return name;
  }

  public double getElectorValue(){
    return electorValue;
  }

  public void setName(String name){
    this.name=name;
  }

  public int getElectoralVotes(){
    return electoralVotes;
  }

  public void setElectoralVotes(int electoralVotes){
    this.electoralVotes=electoralVotes;
  }

  public int getPopulationVotes(){
    return populationVotes;
  }

  public void setPopulationVotes(int populationVotes){
    this.populationVotes=populationVotes;
  }


  public int getPopVotesNeeded(){
    return popVotesNeeded;
  }

 public static int minToWin(int voters) {
        if (voters % 2 == 0) {
            return (voters / 2) + 1;
        } else {
            return (voters + 1) / 2;
        }
    }

  /*@Override
  public String toString(){
    return "Name: "+getName()+" Electoral Votes: "+getElectoralVotes()+" PopulationVotes: "+getPopulationVotes+" Priority "+this.electorValue;
  }*/

}