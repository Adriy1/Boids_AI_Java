public class DNA {
  int vmax;
  int vision;
  int sz;
  int score = 0;
  Perceptron brain;

  public DNA(int vmax,int vision,int sz,Perceptron brain){
    this.vmax=vmax;
    this.vision=vision;
    this.sz = sz;
    this.brain = brain;
  }

 public void mutation(double mutationRate) {
   if(Math.random()<mutationRate) {             // ajout d'une mutation aléatoire
     vision += (int)(Math.random()-0.5)*50;
   }
   if(Math.random()<mutationRate) {
     sz += (int)(Math.random()-0.5)*10;
   }
   if(Math.random()<mutationRate) {
     vmax += (int)(Math.random()-0.5)*10;
   }
 }

}
