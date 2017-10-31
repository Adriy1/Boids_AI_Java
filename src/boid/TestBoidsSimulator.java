import gui.GUISimulator;
import gui.Rectangle;
import java.awt.Color;

public class TestBoidsSimulator {
  public static void main(String [] args) {
    GUISimulator gui = new GUISimulator(2000,2000,Color.BLACK);
    gui.setSimulable(new BoidsSimulator(gui));
    //gui.addGraphicalElement(new Rectangle(90, 40, Color.WHITE, Color.WHITE, 10));
  }
}
