import java.lang.Math.*;

// céation d'une classe vector pour definir la vitesse d'un boid

public class Vector {
  int x;
  int y;

  public Vector(int x, int y){
    this.x=x;
    this.y=y;
  }

  public void plus(Vector v) {
    this.x = this.x + v.x;
    this.y = this.y + v.y;
  }

  public void sous(Vector v) {
    this.x = this.x - v.x;
    this.y = this.y - v.y;
  }

  public void mult(double l) {
    this.x = (int) (this.x*l);
    this.y = (int) (this.y*l);
  }

  public int norm() {
    return (int)(Math.sqrt(this.x*this.x+this.y*this.y));
  }

}
