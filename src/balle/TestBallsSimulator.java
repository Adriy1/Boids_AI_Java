import gui.GUISimulator;

import java.awt.Color;

public class TestBallsSimulator {
  public static void main(String [] args) {
    GUISimulator gui = new GUISimulator(100,100,Color.BLACK);
    gui.setSimulable(new BallsSimulator());
  }
}
