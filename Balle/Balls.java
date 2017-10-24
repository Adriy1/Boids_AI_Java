import java.awt.*;

public class Balls {
  Point balle1;
  int taille;
  public int DX;
  public int DY;

  public Balls(Point balle1, int taille) {this.balle1=balle1; this.taille=taille;  }

  void translate( int dx,  int dy){
    this.DX = dx;
    this.DY = dy;
    if (this.balle1.getX() + dx > 0 && this.balle1.getX() + dx < this.taille) {
      if (this.balle1.getY() + dy >= 0 && this.balle1.getY() + dy < this.taille) {
        this.balle1.setLocation(this.balle1.getX() + dx,this.balle1.getY()+dy);
      }
      else {
        if(this.balle1.getY() + dy < 0){
          // x bon y negatif
          this.balle1.setLocation(this.balle1.getX() + dx,-dy-this.balle1.getY());
          this.DY = -dy;
        }
        else {
          //x bon y > taille
          this.balle1.setLocation(this.balle1.getX() + dx,taille-(dy+this.balle1.getY()-taille));
          this.DY = -dy;
        }
      }
    }
    else {
      if (this.balle1.getY() + dy >= 0 && this.balle1.getY() + dy < this.taille){
        if (this.balle1.getX() + dx <= 0) {
          //x <0 et y bon
          this.balle1.setLocation(-dx-this.balle1.getX(),this.balle1.getY()+dy);
          this.DX = -dx;
        }
        else {
          // x > 100 et y bon
          this.balle1.setLocation(taille-(dx+this.balle1.getX()-taille),this.balle1.getY()+dy);
          this.DX = -dx;
        }
      }
      else {
        // x et y pas bon
        if (this.balle1.getY() + dy < 0 && this.balle1.getX() + dx < 0) {
          this.balle1.setLocation(dx-this.balle1.getX(),dy-this.balle1.getY());
          this.DX = -dx;
          this.DY = -dy;
          //x <0 et y aussi
        }
        else if (this.balle1.getY() + dy < 0 && this.balle1.getX() + dx > taille) {
          this.balle1.setLocation(taille-(dx+this.balle1.getX()-taille),dy-this.balle1.getY());
          this.DX = -dx;
          this.DY = -dy;
        }
        else if (this.balle1.getY() + dy > taille && this.balle1.getX() + dx < 0) {
          this.balle1.setLocation(dx-this.balle1.getX(),taille-(dy+this.balle1.getY()-taille));
          this.DX = -dx;
          this.DY = -dy;
        }
        else {
          this.balle1.setLocation(taille-(dx+this.balle1.getX()-taille),taille-(dy+this.balle1.getY()-taille));
          this.DX = -dx;
          this.DY = -dy;
        }

      }
    }

  }

  public void reInit() {
    this.balle1.setLocation(50,50);

}

}
