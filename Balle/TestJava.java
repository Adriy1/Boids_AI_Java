import java.awt.*;

public class TestJava {


  public static void main(String[] args) {
    int DX,DY;
    int dx=5,dy=5;
    Point test = new Point(5,4);
    Balls bb = new Balls(test,100);
    int i;
    for(i=0;i<100;i++) {
      bb.translate(dx,dy);
      System.out.println(bb.balle1.toString() + "DY: " + bb.DY + "et DX: " + bb.DX);
      dx= bb.DX;
      dy =bb.DY;
    }




  }
}
