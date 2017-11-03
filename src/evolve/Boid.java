import java.awt.*;
import java.util.*;

public class Boid {
  Point p;
  Vector v;
  int vmax;
  int sz;
  int vision;

  public Boid(int x,int y,int taille) {
    this.p = new Point();
    this.setGoodLocation(x,y,taille);
  }

  public void setGoodLocation(int x, int y, int taille) {
    if(x < 0 ) {
      p.setLocation(-x,y);
    }
    else if(x > taille) {
      p.setLocation(2*taille-x,y);
    }
    else if(y < 0 ) {
      p.setLocation(x,-y);
    }
    else if(y > taille) {
      p.setLocation(x,2*taille-y);
    }
    else{
      p.setLocation(x,y);
    }

  }

  public Boolean equal(Boid b) {
    if(p.getX() == b.p.getX() && p.getY() == b.p.getY()) {
      return true;
    }
    return false;
  }

  public Vector bounding_position(int taille) { //on empeche de faire partir les boid en modifiant leur vitesse aux abords des bords
    Vector v4 = new Vector(0,0);
    int a = v.x; //previus version
    int b = v.y;
    if (p.getX() < -2*a && a <0) {
      v4.x = (int )-(2*a);
    }
    if(p.getX() > taille -2*a && a > 0) {
      v4.x = (int )-(2*a);
    }
    if(p.getY() < -2*b && b<0) {
      v4.y = (int )-(2*b);
    }
    if(p.getY() > taille - 2*b && b>0) {
      v4.y = (int )-(2*b);
    }
    return v4;
  }

  public void limit_speed() { //permet de limiter la vitesse
    if(this.v.norm()>this.vmax) {
      double k = vmax/v.norm();
      this.v.mult(k);
    }
  }


  public Vector alea(){   // on ajoute un coté aléatoire dans 0.5% des cas au mouvement de l'oiseau ou alors quand il ralenti trop
    Vector v5 = new Vector(0,0);
    if(this.v.norm()<1 || Math.random()<0.0005) {
      v5.x=(int)((Math.random()-0.5)*this.vmax/4);
      v5.y=(int)((Math.random()-0.5)*this.vmax/4);
    }
    return v5;
  }
}
