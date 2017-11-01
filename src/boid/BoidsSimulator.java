import gui.*;
import java.awt.*;


public class BoidsSimulator implements Simulable {
  Boids bb;
  GUISimulator gui; // = new GUISimulator(500,500,Color.BLACK);



public BoidsSimulator(GUISimulator gui){
  this.gui=gui;
  //this.bb= new Boids(500,20);

//  this.box();

}

public void efface() {
  this.gui.reset();
  this.box();
}

public void affiche() {
  this.efface();
  int x=0,y=0;
  for (Boid b : this.bb.tabBoid) {
    //System.out.println(b.p.toString());
    this.gui.addGraphicalElement(new Oval((int) b.p.getX(),(int) b.p.getY(),Color.white,Color.black,15));
    this.gui.addGraphicalElement(new Oval((int) b.p.getX(),(int) b.p.getY(),Color.WHITE,Color.WHITE,5));
    this.gui.addGraphicalElement(new gui.Rectangle((int) (b.p.getX()+b.v.x),(int) (b.p.getY()+b.v.y) ,Color.red,Color.red,5)); // permet de representer le vecteur vitesse
    x += (int) b.p.getX();y += (int) b.p.getY(); // calcul utile our le centre de gravité

  }
  //this.gui.addGraphicalElement(new gui.Rectangle((int) (x/this.bb.nbBoid),(int) (y/this.bb.nbBoid) ,Color.green,Color.green,10));
  // correspond au centre de gravité
}

@Override
public void next() {
  int l = this.bb.nbBoid;
  Boid[] tmp = new Boid[l];
  for(int i=0;i<l;i++) {
    tmp[i] = this.bb.tabBoid[i];
  }
  Vector v1 = new Vector(0,0);
  Vector v2 = new Vector(0,0);
  Vector v3 = new Vector(0,0);
  Vector v4 = new Vector(0,0);
  for(int i=0;i<l;i++) {
    v1=this.bb.center(i); //on calcul les vecteurs qui définissent nos règles
    v2=this.bb.repulsion(i);
    v3=this.bb.attraction(i);
    v4=this.bb.bounding_position(i);
    tmp[i].v.plus(v1); // on les ajoute au vecteur vitesse du boid i
    tmp[i].v.plus(v2);
    tmp[i].v.plus(v3);
    tmp[i].v.plus(v4);
    //System.out.println("V av : "+tmp[i].v.toString());
    tmp[i].limit_speed();
    //System.out.println("V f : "+tmp[i].v.toString());
    //System.out.println("V: x / y / i " + this.bb.tabBoid[i].v.x + " " + this.bb.tabBoid[i].v.y + " "+ i);
    tmp[i].p.setLocation(this.bb.tabBoid[i].p.getX()+this.bb.tabBoid[i].v.x ,this.bb.tabBoid[i].p.getY()+this.bb.tabBoid[i].v.y); //on calcul la nouvelle position
  }
  for(int i=0;i<l;i++) {
     this.bb.tabBoid[i]=tmp[i];
  }
  affiche();
}

@Override
public void restart() {
  System.out.println("RESTART");
  this.gui.reset();
  this.bb = new Boids(900,50);
  this.box();
  this.affiche();
}




public void box() {
  this.gui.addGraphicalElement(new gui.Rectangle(-1,-1,Color.RED,Color.RED,2*this.bb.taille,3));
  this.gui.addGraphicalElement(new gui.Rectangle( -1,0,Color.RED,Color.RED,3,2*this.bb.taille));
  this.gui.addGraphicalElement(new gui.Rectangle( -1,this.bb.taille,Color.RED,Color.RED,2*this.bb.taille,3));
  this.gui.addGraphicalElement(new gui.Rectangle(this.bb.taille,0,Color.RED,Color.RED,3,2*this.bb.taille));
}

}
