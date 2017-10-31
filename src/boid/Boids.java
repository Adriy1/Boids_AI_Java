import java.awt.*;
import java.util.*;

public class Boids {
  Boid[] tabBoid; //tableau pour stoker les boid
  int nbBoid;
  int taille; //terrain de jeu du boid


  public Boids(int taille, int nb) {
    this.taille=taille;
    this.nbBoid=nb;
    int vlim = 30;
    this.tabBoid=new Boid[nb];
    for(int i=0;i<nb;i++) {
      Vector v = new Vector((int)((Math.random()-0.5)*vlim),(int)((Math.random()-0.5)*vlim)); // initialisation de la vitesse
      tabBoid[i] = new Boid((Math.random()*taille), (Math.random()*taille),v); // initialisation position
    }
  }

  public Vector center(int j) {
    Vector v1 = new Vector(0,0);
      for( Boid e : this.tabBoid) {
        if (e != this.tabBoid[j]) {
          Vector v = new Vector((int) e.p.getX(),(int)e.p.getY());
          v1.plus(v); //on somme tous les vecteurs positions pour les boids pour obtenir une moyenne percue
        }
    }
    v1.mult(1/(this.nbBoid-1)); // on fait la moyenne
    v1.sous(new Vector((int)this.tabBoid[j].p.getX(),(int)this.tabBoid[j].p.getY())); // on calcule le vecteur qui va du boidj au point moyen percue
    v1.mult(0.001); //facteur pour limiter l'influence
    return v1;
  }

  public Vector repulsion(int j) {
    Vector v2 = new Vector(0,0);
    Vector w = v2;
    for( Boid e : this.tabBoid) {
      if (e != this.tabBoid[j]) {
        w.x = (int) (e.p.getX() - this.tabBoid[j].p.getX());
        w.y = (int) (e.p.getY() - this.tabBoid[j].p.getY());  // w = position du boid e - position du boid j
        if (w.norm() < 40 ) {
          v2.sous(w); // si la norme de w < a une valeur alors v2 = - w
        }
      }
    }
    v2.mult(0.01);
    return v2;
  }

  public Vector attraction(int j) {
    Vector v3 = new Vector(0,0);
    for( Boid e : this.tabBoid) {
      if (e != this.tabBoid[j]) {
        v3.plus(e.v); //on fait une moyenne ponderée des vitesses
      }
    }
    v3.mult(1/(this.nbBoid-1));
    v3.sous(this.tabBoid[j].v);
    v3.mult(1/8);
    return v3;
  }


  public Vector bounding_position(int j) { //on empeche de faire partir les boid en modifiant leur vitesse aux abors des bords
    Vector v1 = new Vector(0,0);
    int a = this.tabBoid[j].v.x;
    int b = this.tabBoid[j].v.y;
    if (this.tabBoid[j].p.getX() < 20 && a <0) {
      v1.x = (int )-(2*a);
    }
    if(this.tabBoid[j].p.getX() > this.taille -20 && a > 0) {
      v1.x = (int )-(2*a);
    }
    if(this.tabBoid[j].p.getY() < 20 && b<0) {
      v1.y = (int )-(2*b);
    }
    if(this.tabBoid[j].p.getY() > this.taille -20 && b>0) {
      v1.y = (int )-(2*b);
    }
    return v1;
  }



}
