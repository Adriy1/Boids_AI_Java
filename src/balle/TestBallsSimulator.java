import gui.GUISimulator;
import gui.Rectangle;
import java.awt.Color;

public class TestBallsSimulator {
  public static void main(String [] args) {
    GUISimulator gui = new GUISimulator(100,100,Color.BLACK);
    gui.setSimulable(new BallsSimulator());
    gui.addGraphicalElement(new Rectangle(90, 40, Color.WHITE, Color.WHITE, 10));
  }
}
