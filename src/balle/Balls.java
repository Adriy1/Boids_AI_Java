import java.awt.*;
import java.util.LinkedList;
import java.util.*;

public class Balls {
  int taille;
  LinkedList<Ball> ListeBalle;

  public Balls(int taille) {
    ListeBalle = new LinkedList<Ball>();
    this.taille=taille;
  }

  void translate(){
    for (Ball b : ListeBalle ) {
      int dx = b.dx;
      int dy = b.dy;
      if (b.p.getX() + dx > 0 && b.p.getX() + dx < this.taille) {
        if (b.p.getY() + dy >= 0 && b.p.getY() + dy < this.taille) {
          b.p.setLocation(b.p.getX() + dx,b.p.getY()+dy);
        }
        else {
          if(b.p.getY() + dy < 0){
            // x bon y negatif
            b.p.setLocation(b.p.getX() + dx,-dy-b.p.getY());
            b.dy = -dy;
          }
          else {
            //x bon y > taille
            b.p.setLocation(b.p.getX() + dx,taille-(dy+b.p.getY()-taille));
            b.dy = -dy;
          }
        }
      }
      else {
        if (b.p.getY() + dy >= 0 && b.p.getY() + dy < this.taille){
          if (b.p.getX() + dx <= 0) {
            //x <0 et y bon
            b.p.setLocation(-dx-b.p.getX(),b.p.getY()+dy);
            b.dx = -dx;
          }
          else {
            // x > 100 et y bon
            b.p.setLocation(taille-(dx+b.p.getX()-taille),b.p.getY()+dy);
            b.dx = -dx;
          }
        }
        else {
          // x et y pas bon
          if (b.p.getY() + dy < 0 && b.p.getX() + dx < 0) {
            b.p.setLocation(dx-b.p.getX(),dy-b.p.getY());
            b.dx = -dx;
            b.dy = -dy;
            //x <0 et y aussi
          }
          else if (b.p.getY() + dy < 0 && b.p.getX() + dx > taille) {
            b.p.setLocation(taille-(dx+b.p.getX()-taille),dy-b.p.getY());
            b.dx = -dx;
            b.dy = -dy;
          }
          else if (b.p.getY() + dy > taille && b.p.getX() + dx < 0) {
            b.p.setLocation(dx-b.p.getX(),taille-(dy+b.p.getY()-taille));
            b.dx = -dx;
            b.dy = -dy;
          }
          else {
            b.p.setLocation(taille-(dx+b.p.getX()-taille),taille-(dy+b.p.getY()-taille));
            b.dx = -dx;
            b.dy = -dy;
          }

        }
      }
    }
}

  public void reInit() {
    for (Ball b : ListeBalle) {
      b.p.setLocation(Math.random()*500,Math.random()*500);
    }


}

}
