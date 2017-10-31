import java.awt.*;
import java.util.*;

public class Boid {
  Point p;
  Vector v;

  public Boid(double x,double y, Vector v) {
    this.p = new Point();
    this.p.setLocation(x,y);
    this.v = new Vector(v.x,v.y);
  }

  public void limit_speed() { //permet de limiter la vitesse 
    int vlim = 20;
    if(this.v.norm()>vlim) {
      this.v.mult(vlim/v.norm());
    }
  }

}
