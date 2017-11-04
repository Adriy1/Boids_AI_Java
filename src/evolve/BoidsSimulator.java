import gui.*;
import java.awt.*;
import java.util.*;


public class BoidsSimulator implements Simulable {
  Boids bb;
  GUISimulator gui;
  int taille;
  int nbpoisson;
  int nbpreda;

public BoidsSimulator(GUISimulator gui,int taille,int nbpoisson,int nbpreda){
  this.gui=gui;
  this.taille=taille;
  this.nbpoisson=nbpoisson;
  this.nbpreda=nbpreda;
  this.bb = new Boids(taille,nbpoisson,nbpreda);
  this.box();
  this.affiche();
}

public void efface() {
  this.gui.reset();
  this.box();
}

public void affiche() {
  this.efface();
  bb.affichetouslesPoissons(this.gui);
  bb.affichetouslesRequins(this.gui);
}

@Override
public void next() {
  bb.nextPoisson(taille,nbpoisson);
  bb.nextRequin(taille);
  affiche();
}

@Override
public void restart() {
  System.out.println("RESTART");
  this.gui.reset();
  this.bb = new Boids(taille,nbpoisson,nbpreda);
  this.box();
  this.affiche();
}




public void box() {  //creation d'un contour rouge
  this.gui.addGraphicalElement(new gui.Rectangle(-1,-1,Color.RED,Color.RED,2*this.taille+10,3));
  this.gui.addGraphicalElement(new gui.Rectangle( -1,0,Color.RED,Color.RED,3,2*this.taille+10));
  this.gui.addGraphicalElement(new gui.Rectangle( -1,this.taille+10,Color.RED,Color.RED,2*this.taille+10,3));
  this.gui.addGraphicalElement(new gui.Rectangle(this.taille+10,0,Color.RED,Color.RED,3,2*this.taille+10));
}

}
