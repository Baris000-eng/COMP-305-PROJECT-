public class Tribe{



  public String name;
  public int electoralVotes;
  public int populationVotes;
  public Tribe(String name,int electoralVotes,int populationVotes){
    this.name=name;
    this.electoralVotes=electoralVotes;
    this.populationVotes=populationVotes;
  }






  public String getName(String name){
    return name;
  }

  public void setName(String name){
    this.name=name;
  }

  public void getElectoralVotes(int electoralVotes){
    return electoralVotes;
  }

  public void setElectoralVotes(int electoralVotes){
    this.electoralVotes=electoralVotes;
  }

  public void getPopulationVotes(int populationVotes){
    return populationVotes;
  }

  public void setPopulationVotes(int populationVotes){
    this.populationVotes=populationVotes;
  }

}