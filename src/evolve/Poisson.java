import gui.*;
import java.awt.*;
import java.util.*;

public class Poisson extends Boid {

  public Poisson(int x, int y,int taille) {
    super(x,y,taille);
    this.adn = new DNA(30,300,5);
    this.v = new Vector((int)((Math.random()-0.5)*this.adn.vmax),(int)((Math.random()-0.5)*this.adn.vmax));
    this.szz = adn.sz;
  }

  public Vector cohesion(ArrayList<Poisson> tabPoisson) {
    Vector v1 = new Vector(0,0);
    int nbVoisinvu = 0;
      for( Poisson e : tabPoisson) {
        if (this.equal(e) != true) {
          Vector w = new Vector( (int) (e.p.getX()-this.p.getX()), (int)(e.p.getY()-this.p.getY()));  //on crée un vecteur entre e et le boidj
          if(this.v.getAngle(w)<90 && w.norm()<adn.vision){ //on test si il est dans le champs de vision de longeur 200 et avec un angle de 180 degres
            Vector z = new Vector((int) e.p.getX(),(int)e.p.getY());
            v1.plus(z); //on somme tous les vecteurs positions pour les boids pour obtenir une moyenne percue
            nbVoisinvu++;
          }
        }
    }
    if(nbVoisinvu == 0){
      v1.x=0;
      v1.y=0;
      return v1;
    }
    v1.mult((double)1/(nbVoisinvu)); // on fait la moyenne
    v1.sous(new Vector((int)this.p.getX(),(int)this.p.getY())); // on calcule le vecteur qui va du boidj au point moyen percue
    v1.mult(0.05); //facteur pour limiter l'influence
    return v1;
  }

  public Vector separation(ArrayList<Poisson> tabPoisson) {
    Vector v2 = new Vector(0,0);
    Vector w = new Vector(0,0);
    int c=1;
    for( Poisson e : tabPoisson) {
      if (this.equal(e) != true) {
        w.x = (int) (e.p.getX() - this.p.getX());
        w.y = (int) (e.p.getY() - this.p.getY());  // w = position du boid e - position du boid j
        if (w.norm() < 3*(this.adn.sz+e.adn.sz) ) {
          v2.sous(w);
           // si la norme de w < a une valeur alors v2 = - w
          c++;
        }
      }
    }
    v2.mult((double) 1/c);
    return v2;
  }

  public Vector alignement(ArrayList<Poisson> tabPoisson) {
    Vector v3 = new Vector(0,0);
    int nbVoisinvu = 0;
    for( Boid e : tabPoisson) {
      if (this.equal(e) != true) {
        Vector w = new Vector((int) (e.p.getX()-this.p.getX()),(int) (e.p.getY()-this.p.getY()));  //on crée un vecteur entre e et le boidj
        if(this.v.getAngle(w)<90 && w.norm()<adn.vision){
          v3.plus(e.v); //on fait une moyenne ponderée des vitesses
          nbVoisinvu++;
        }
      }
    }
    v3.mult((double)1/(nbVoisinvu));
    v3.sous(this.v);
    v3.mult((double)1/4);
    return v3;
  }

  public Vector fuir(ArrayList<Requin> tabRequin) {
    Vector v6 = new Vector((int)this.p.getX(),(int)this.p.getY());
    int nbRequinvu = 0;
      for( Requin e : tabRequin) {
        if (this.equal(e) != true) {
          Vector w = new Vector( (int) (e.p.getX()-this.p.getX()), (int)(e.p.getY()-this.p.getY()));  //on crée un vecteur entre e et le boidj
          if(this.v.getAngle(w)<90 && w.norm()<adn.vision){ //on test si il est dans le champs de vision de longeur 200 et avec un angle de 180 degres
            Vector v = new Vector((int) e.p.getX(),(int)e.p.getY());
            v6.sous(v); //on somme tous les vecteurs positions pour les boids pour obtenir une moyenne percue
            nbRequinvu++;
          }
        }
    }
    if (nbRequinvu == 0) {
      v6.sous(new Vector((int)this.p.getX(),(int)this.p.getY()));
      return v6;
    }
    v6.mult((double)1/2*(nbRequinvu)); // on fait la moyenne
    return v6;
  }


  public void affichePoisson(GUISimulator gui){
    gui.addGraphicalElement(new Oval((int) p.getX(),(int) p.getY(),Color.white,Color.black,3*szz));
    gui.addGraphicalElement(new Oval((int) p.getX(),(int) p.getY(),Color.WHITE,Color.WHITE,szz));
    gui.addGraphicalElement(new gui.Rectangle((int) (p.getX()+v.x),(int) (p.getY()+v.y) ,Color.red,Color.red,szz)); // permet de representer le vecteur vitesse
  }
}
