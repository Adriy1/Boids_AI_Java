import java.awt.*;

public class TestBalls {


  public static void main(String[] args) {
    int dx=5,dy=5;
    Balls bb = new Balls(100);
    int i;
    for(i=0;i<100;i++) {
      bb.translate(dx,dy);
      System.out.println(bb.balle1.toString());
      dx= bb.DX;
      dy =bb.DY;
    }




  }
}
