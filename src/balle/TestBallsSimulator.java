import gui.GUISimulator;
import gui.Rectangle;
import java.awt.Color;

public class TestBallsSimulator {
  public static void main(String [] args) {
    GUISimulator gui = new GUISimulator(1000,1000,Color.BLACK);
    gui.setSimulable(new BallsSimulator(gui));
    //gui.addGraphicalElement(new Rectangle(90, 40, Color.WHITE, Color.WHITE, 10));
  }
}
