import gui.*;
import java.awt.*;



public class UniversSimulator implements Simulable {
  Univers univ;
  GUISimulator gui;


  public UniversSimulator(GUISimulator gui){
      this.univ = new Univers(2,100);
      this.gui = gui;
    }

    public void affiche() {
      int i,j,val,c;
      int n = this.univ.nbetat;
      for(i=0; i<this.univ.taille;i++) {
        for(j=0; j<this.univ.taille; j++) {
          val = this.univ.grille[i][j];
          c = (int) 255 - 255/(n-1)*val;
          this.gui.addGraphicalElement(new gui.Rectangle(10*i,10*j,new Color(c,c,c),new Color(c,c,c),10));
        }
      }
    }

    @Override
    public void next(){
      this.gui.reset();
      this.univ.suivant();
      this.affiche();
    }

    @Override
    public void restart() {
      this.gui.reset();
      this.univ.init();
      this.affiche();
    }






}
