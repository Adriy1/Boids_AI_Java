import java.awt.Color;

public class TestUnivers {
  public static void main(String [] args) {

    Univers test = new Univers(4,5);
    test.init();
    System.out.println(test.toString());
    test.suivant();
    System.out.println(test.toString());
    Color c = new Color(123,45,12);
    System.out.println(Color.decode("#DEC451").toString());//-Color.decode("#DEC451").toString());

  }
}
