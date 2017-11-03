import gui.*;
import java.awt.*;
import java.util.*;

public class Boids {
  ArrayList<Poisson> ListeProie;  //tableau pour stoker les boid
  ArrayList<Requin> ListePreda;



  public Boids(int taille, int nbpoisson,int nbpreda) {
    ListeProie = new ArrayList<Poisson>();
    ListePreda = new ArrayList<Requin>();
    for(int i=0;i<nbpoisson;i++) {
      ListeProie.add(new Poisson((int)(Math.random()*taille), (int)(Math.random()*taille),taille));
    }
    for(int i=0;i<nbpreda;i++) {
      ListePreda.add(new Requin((int)(Math.random()*taille), (int)(Math.random()*taille),taille));
    }
  }

  public void nextPoisson(int taille){
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
      b.v.plus(v1);
      b.v.plus(v2);
      b.v.plus(v4);
      b.limit_speed();
      b.v.plus(v5);
      b.setGoodLocation((int)b.p.getX()+b.v.x ,(int)b.p.getY()+b.v.y,taille);
      b.manger(ListeProie);
    }
    for(int i=0;i<l;i++) {
      ListePreda.set(i,tmp.get(i));  //on copie le tableau temporaire
    }
    for(int i=0;i<l;i++) {
      tmp.remove(l-1-i);    //on supprime le tableau temporaire
    }
  }

  public void affichetouslesRequins(GUISimulator gui){
    for (Requin b : ListePreda) { //System.out.println(b.p.toString());
      b.afficheRequin(gui);
    }
  }



}
