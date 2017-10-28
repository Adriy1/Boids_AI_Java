import gui.*;
import java.awt.*;


public class BallsSimulator implements Simulable {
  Balls bb;
  GUISimulator gui = new GUISimulator(500,500,Color.BLACK);




  public BallsSimulator() {
    this.bb = new Balls(500);
    this.bb.ListeBalle.add(new Ball(new Point(30,50),10,18));
    this.bb.ListeBalle.add(new Ball(new Point(230,150),-5,13));
    this.bb.ListeBalle.add(new Ball(new Point(130,110),23,-3));
    this.gui.addGraphicalElement(new gui.Rectangle(-1,-1,Color.RED,Color.RED,1004,4));
    this.gui.addGraphicalElement(new gui.Rectangle( -1,0,Color.RED,Color.RED,4,1000));
    this.gui.addGraphicalElement(new gui.Rectangle( -1,501,Color.RED,Color.RED,1004,4));
    this.gui.addGraphicalElement(new gui.Rectangle(501,0,Color.RED,Color.RED,4,1000));


  }


@Override
public void next () {
  for (Ball b : this.bb.ListeBalle) {
    System.out.println("before " +b.p.toString());
    this.gui.addGraphicalElement(new Oval((int) b.p.getX(),(int) b.p.getY(),Color.BLACK,Color.BLACK,10));
  }
this.bb.translate();
  for (Ball b : this.bb.ListeBalle) {
    //System.out.println(b.p.toString());
    System.out.println("NEXT: " + b.p.toString());
    this.gui.addGraphicalElement(new Oval((int) b.p.getX(),(int) b.p.getY(),Color.WHITE,Color.WHITE,5));
  }
}

@Override
public void restart () {
  this.bb.reInit();
  System.out.println("RESTART");
  //this.gui.reset();
  }

}
