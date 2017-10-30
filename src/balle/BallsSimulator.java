import gui.*;
import java.awt.*;


public class BallsSimulator implements Simulable {
  Balls bb;
  GUISimulator gui; // = new GUISimulator(500,500,Color.BLACK);



public BallsSimulator(GUISimulator gui){
    this.bb = new Balls(500);
    this.bb.ListeBalle.add(new Ball(new Point((int) (Math.random()*500),(int) (Math.random()*500)),(int) ((Math.random() -0.5) * 40),(int) ((Math.random() -0.5) * 40)));
    this.bb.ListeBalle.add(new Ball(new Point((int) (Math.random()*500),(int) (Math.random()*500)),(int) ((Math.random() -0.5) * 40),(int) ((Math.random() -0.5) * 40)));
    this.bb.ListeBalle.add(new Ball(new Point((int) (Math.random()*500),(int) (Math.random()*500)),(int) ((Math.random() -0.5) * 40),(int) ((Math.random() -0.5) * 40)));
    this.gui = gui;
    this.box();



  }

public void box() {
  this.gui.addGraphicalElement(new gui.Rectangle(-1,-1,Color.RED,Color.RED,1007,3));
  this.gui.addGraphicalElement(new gui.Rectangle( -1,0,Color.RED,Color.RED,3,1003));
  this.gui.addGraphicalElement(new gui.Rectangle( -1,504,Color.RED,Color.RED,1007,3));
  this.gui.addGraphicalElement(new gui.Rectangle(504,0,Color.RED,Color.RED,3,1003));
}
public void affiche() {
  for (Ball b : this.bb.ListeBalle) {
    //System.out.println(b.p.toString());
    System.out.println("NEXT: " + b.p.toString());
    this.gui.addGraphicalElement(new Oval((int) b.p.getX(),(int) b.p.getY(),Color.WHITE,Color.WHITE,10));
  }
}

public void efface() {
  this.gui.reset();
  this.box();
}
/*  for (Ball b : this.bb.ListeBalle) {
    System.out.println("before " +b.p.toString());
    this.gui.addGraphicalElement(new Oval((int) b.p.getX(),(int) b.p.getY(),Color.BLACK,Color.BLACK,7));
  }
}*/


@Override
public void next () {
this.efface();
this.bb.translate();
this.affiche();
}

@Override
public void restart () {
  this.bb.reInit();
  System.out.println("RESTART");
  this.gui.reset();
  this.box();
  }

}
