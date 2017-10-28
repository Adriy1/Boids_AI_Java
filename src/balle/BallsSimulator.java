import gui.*;

public class BallsSimulator implements Simulable {
  Balls Balles;

@Override
public void next () {
  this.Balles.translate(10,10);
  }

@Override
public void restart () {
  this.Balles.reInit();
  }

}
