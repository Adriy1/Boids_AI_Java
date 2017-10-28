import gui.*;

public class BallsSimulator implements Simulable {
  Balls bb;
  int dx;
  int dy;

  public BallsSimulator() {
    this.bb = new Balls(100);
    this.dx = 10;
    this.dy = 10;
  }


@Override
public void next () {
  this.bb.translate(this.dx,this.dy);
  System.out.println("NEXT: " + bb.balle1.toString());
  this.dx= bb.DX;
  this.dy =bb.DY;
  }

@Override
public void restart () {
  this.bb.reInit();
  System.out.println("RESTART");
  this.dx=10;
  this.dy=10;
  }

}
