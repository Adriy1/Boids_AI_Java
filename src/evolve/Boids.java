import gui.*;
import java.awt.*;
import java.util.*;

public class Boids {
  ArrayList<Poisson> ListeProie;  //tableau pour stoker les boid
  ArrayList<Requin> ListePreda;

static int iter =0;

  public Boids(int taille, int nbpoisson,int nbpreda) {
    ListeProie = new ArrayList<Poisson>();
    ListePreda = new ArrayList<Requin>();
    for(int i=0;i<nbpoisson;i++) {
      ListeProie.add(new Poisson((int)(Math.random()*taille), (int)(Math.random()*taille),taille));
    }
    for(int i=0;i<nbpreda;i++) {
      int w = (new Gauss(5,60,20)).tirage();
      DNA adn = new DNA(w,(new Gauss(100,500,100)).tirage(),(int)(27-w/3),new Perceptron(1,0.0000001));
      ListePreda.add(new Requin((int)(Math.random()*taille), (int)(Math.random()*taille),taille,adn));
    }
  }

  public void nextPoisson(int taille){
    iter++;
    int l = ListeProie.size();
    ArrayList<Poisson> tmp = new ArrayList<Poisson>();
    for(int i=0;i<l;i++) {
      tmp.add(i,ListeProie.get(i));
    }
    Vector v1 = new Vector(0,0);
    Vector v2 = new Vector(0,0);
    Vector v3 = new Vector(0,0);
    Vector v4 = new Vector(0,0);
    Vector v5 = new Vector(0,0);
    Vector v6 = new Vector(0,0);
    for(Poisson b : tmp) {
      v1=b.cohesion(ListeProie); //on calcul les vecteurs qui définissent nos règles
      v2=b.separation(ListeProie);
      v3=b.alignement(ListeProie);
      v4=b.bounding_position(taille);  //System.out.println("V: " +b.v.toString()+ " V1: " +v1.toString()+ " V2: " +v2.toString()+ " V3: " +v3.toString()+ " V4: " +v4.toString());
      v5=b.alea();
      v6=b.fuir(ListePreda);
      b.v.plus(v1);
      b.v.plus(v2);
      b.v.plus(v3);
      b.v.plus(v6);
      b.v.plus(v4);
      b.limit_speed();
      b.v.plus(v5);
      b.setGoodLocation((int)b.p.getX()+b.v.x ,(int)b.p.getY()+b.v.y,taille); //on calcul la nouvelle position
    }
    for(int i=0;i<l;i++) {
      ListeProie.set(i,tmp.get(i));  //on copie le tableau temporaire
    }
    for(int i=0;i<l;i++) {
      tmp.remove(l-1-i);    //on supprime le tableau temporaire
    }
    if(iter%50==0){
      reproductionPoisson(taille);
    }
  }

  public void affichetouslesPoissons(GUISimulator gui){
    //int x=0,y=0;
    int l = ListeProie.size();
    if(l !=0 ) {
      for (Poisson b : ListeProie) { //System.out.println(b.p.toString());
        b.affichePoisson(gui);
      //  x += (int) b.p.getX();y += (int) b.p.getY(); // calcul utile our le centre de gravité
      }
    //gui.addGraphicalElement(new gui.Rectangle((int) (x/l),(int) (y/l) ,Color.green,Color.green,10)); // correspond au centre de gravité
   }
  }

  public void nextRequin(int taille) {
    int l = ListePreda.size();
    ArrayList<Requin> tmp = new ArrayList<Requin>();
    for(int i=0;i<l;i++) {
      tmp.add(i,ListePreda.get(i));
    }
    Vector v1 = new Vector(0,0);
    Vector v2 = new Vector(0,0);
    Vector v4 = new Vector(0,0);
    Vector v5 = new Vector(0,0);
    for(Requin b : tmp) {
      v1 = b.chasser(ListeProie); //System.out.println("V1: " + v1.toString());
      v2 = b.separation(ListePreda);
      v4 = b.bounding_position(taille);
      v5 = b.alea();//System.out.println("V: " +b.v.toString()+ " V1: " +v1.toString()+ " V2: " +v2.toString()+ " V3: " +v5.toString()+ " V4: " +v4.toString());
      Vector[] forces = {v1};
      Vector result = b.adn.brain.feedforward(forces,b); //brain process
  //    b.v.plus(v1);
      b.v.plus(v2);
      b.v.plus(result);
      b.limit_speed();
      b.v.plus(v4);
      b.v.plus(v5);
      b.setGoodLocation((int)b.p.getX()+b.v.x ,(int)b.p.getY()+b.v.y,taille);
      b.manger(ListeProie);
      Vector error = b.getPoissonplusProche(ListeProie);
      b.adn.brain.train(forces,error);
      System.out.println(b + " " +b.adn.brain.weights[0]);
    }
    System.out.println("\n");
    double vv=0,ss=0,vi=0,in=0,al=0;
    for(Requin b : tmp){
      vv += b.adn.vmax;
      ss += b.adn.sz;
      vi += b.adn.vision;
      in += b.adn.brain.weights[0];
    }
    System.out.println("Vmoy="+vv/tmp.size()+" Taille Moyenne="+ss/tmp.size()+" Vision moyenne="+vi/tmp.size()+" Intelligence Moy="+in/tmp.size());
    System.out.println("\n");
    for(int i=0;i<l;i++) {
      ListePreda.set(i,tmp.get(i));  //on copie le tableau temporaire
    }
    for(int i=0;i<l;i++) {
      tmp.remove(l-1-i);    //on supprime le tableau temporaire
    }
    if(iter%100 == 0) {
      ArrayList<Requin> ListeBebeRequin = new ArrayList<Requin>();
      reproductionRequin(ListePreda,ListeBebeRequin,taille);
      mortRequin();
      naissanceRequin(ListeBebeRequin);
      initScore();
    }
  }

  public void affichetouslesRequins(GUISimulator gui){
    for (Requin b : ListePreda) { //System.out.println(b.p.toString());
      b.afficheRequin(gui);
    }
  }

  public void reproductionPoisson(int taille){
    ArrayList<Poisson> matingPool = new ArrayList<Poisson>();
    for(Poisson p : ListeProie){
      matingPool.add(p);
    }
    while (matingPool.size()>1) {
      int a = (int) (Math.random()*matingPool.size());
      Poisson male = matingPool.get(a);
      Poisson femelle = matingPool.get(a);
      double normmini = 999999999;
      for (Poisson b : ListeProie) {
        if((new Vector((int)(b.p.getX()-male.p.getX()),(int)(b.p.getY()-male.p.getY()))).norm() < normmini && male.equal(b) == false){
          normmini = (new Vector((int)(b.p.getX()-male.p.getX()),(int)(b.p.getY()-male.p.getY()))).norm();
          femelle = b;
        }
      }
      Poisson enfant1 = new Poisson((int)((male.p.getX()+femelle.p.getX())/2.0),(int)((male.p.getY()+femelle.p.getY())/2.0),taille);
      Poisson enfant2 = new Poisson((int)((male.p.getX()+femelle.p.getX())/2.0),(int)((male.p.getY()+femelle.p.getY())/2.0),taille);
      ListeProie.add(enfant2);
      ListeProie.add(enfant1);
      matingPool.remove(male);
      matingPool.remove(femelle);
    }

  }

  public ArrayList<Requin> createMatingPool(ArrayList<Requin> tabRequin) { // on cree un tableau en fonction des scores des requins
    ArrayList<Requin> matingPool = new ArrayList<Requin>();
    for(Requin e : tabRequin ) {
      for(int i=0;i<e.adn.score+1;i++) {
        matingPool.add(e);
      }
    }
    return matingPool;
  }

  public void reproductionRequin(ArrayList<Requin> tabRequin, ArrayList<Requin> tabEnfant,int taille){ // on tire dans ce tableau deux parents aléatoirement
    ArrayList<Requin> matingPool = createMatingPool((ArrayList<Requin>)(tabRequin));
    while (matingPool.size()>1 && matingPool.get(0).equal(matingPool.get(matingPool.size()-1)) == false) {
      int a = (int) (Math.random()*matingPool.size());
      int b = (int) (Math.random()*matingPool.size());
      while(matingPool.get(a).equal(matingPool.get(b))) { //on verifie que les parents sont différent on essaye plusisuers
        b = (int) (Math.random()*matingPool.size());
      }
      Requin male = matingPool.get(a);
      Requin femelle = matingPool.get(b);
      DNA child = new DNA(male.adn.vmax,male.adn.vision,male.adn.sz,male.adn.brain); //on crée un adn enfant qui sera soit celui de la mere soit du pere
      if(Math.random()<0.5){
        child.vision=femelle.adn.vision;
      }
      if(Math.random()<0.5){
        child.sz=femelle.adn.sz;
      }
      if(Math.random()<0.5){
        child.vmax=femelle.adn.vmax;
      }
      if(Math.random()<0.5){
        child.brain=femelle.adn.brain;
      }
      child.mutation(0.1);
      Requin enfant = new Requin((int)((male.p.getX()+femelle.p.getX())/2.0),(int)((male.p.getY()+femelle.p.getY())/2.0),taille,child); //on creer le requin avec le bon adn
      tabEnfant.add(enfant);
      int indice = matingPool.indexOf(male);
      matingPool.subList(indice,indice+male.adn.score+1).clear(); //on supprime les deux parents de la matingpool
      indice = matingPool.indexOf(femelle);
      matingPool.subList(indice,indice+femelle.adn.score+1).clear();
    }
  }

  public void mortRequin(){
    int S = 0;
    ArrayList<Integer> tabMediane = new ArrayList<Integer>();
    ArrayList<Requin> indice = new ArrayList<Requin>();
    int j =0;
    if(ListePreda.size() != 0) {
      for(Requin r : ListePreda){
        tabMediane.add(r.adn.score);
        j++;
      }
      Collections.sort(tabMediane);
      for(Requin r : ListePreda) {
        if(tabMediane.get((tabMediane.size()-1)/2) >= r.adn.score && S < (tabMediane.size()/2)) {
          indice.add(r);
          S++;
        }
      }
    }
      for(Requin r : indice) {
        ListePreda.remove(r);//System.out.println("mort");
      }
    }

  public void naissanceRequin(ArrayList<Requin> tabEnfant){
    for(Requin r : tabEnfant){
      ListePreda.add(r);//System.out.println("naissance");
    }
  }

  public void initScore(){
    for(int i=0;i<ListePreda.size();i++){
      ListePreda.get(i).adn.score = 0;
    }
  }

}
