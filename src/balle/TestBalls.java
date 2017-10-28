import java.awt.*;

public class TestBalls {


  public static void main(String[] args) {
    Balls bb = new Balls(100);
    bb.ListeBalle.add(new Ball(new Point(30,50),10,10));
    int i;
    for(i=0;i<100;i++) {
      bb.translate();
      System.out.println(bb.ListeBalle.toString());
    }




  }
}
