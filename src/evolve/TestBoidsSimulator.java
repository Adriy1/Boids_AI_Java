import gui.GUISimulator;
import gui.Rectangle;
import java.awt.Color;

public class TestBoidsSimulator {
  public static void main(String [] args) {
    GUISimulator gui = new GUISimulator(3000,3000,Color.BLACK);
    gui.setSimulable(new BoidsSimulator(gui,990,70,6));
    //gui.addGraphicalElement(new Rectangle(90, 40, Color.WHITE, Color.WHITE, 10));
  }
}
