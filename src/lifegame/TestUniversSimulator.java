import gui.GUISimulator;
import gui.Rectangle;
import java.awt.Color;

public class TestUniversSimulator {
  public static void main(String [] args) {
    GUISimulator gui = new GUISimulator(500,500,Color.BLACK);
    gui.setSimulable(new UniversSimulator(gui));
    //gui.addGraphicalElement(new Rectangle(90, 40, Color.WHITE, Color.WHITE, 10));
  }
}
