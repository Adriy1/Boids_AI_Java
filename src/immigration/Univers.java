import java.util.*;




public class Univers {
  int[][] grille;
  int nbetat;
  int taille;

  public Univers(int nbetat,int taille) {
    this.grille = new int[taille][taille];
    this.nbetat = nbetat;
    this.taille = taille;
  }

  public void init() {
    for(int i=0; i<taille;i++) {
      for(int j=0; j<taille; j++) {
        grille[i][j]=(int) (Math.random()*nbetat);
      }
    }

  }

  public void suivant() {
    // modifie la grille pour le tour suivant
    int[][] tmp = grille;
    int i,j,k=0,c=0;
    for(i=0; i<this.taille;i++) {
      for(j=0; j<this.taille; j++) {
        int[][] t = {{1,1},{0,1},{-1,1},{-1,0},{-1,-1},{0,-1},{1,-1},{1,0}};
        k=c=0;
        if (i==0) {
          t[2][0]=t[3][0]=t[4][0]=this.taille-1;
        }
        if (j==0){
          t[4][1]=t[5][1]=t[6][1]=this.taille-1;
        }
        while(c<3 && k<8){
          if(this.grille[(i+t[k][0])%this.taille][(j+t[k][1])%this.taille] == (this.grille[i][j]+1)%this.nbetat) {
            c++;
          }
          k++;
        }
        if(c >= 3) {
          tmp[i][j] = (this.grille[i][j]+1)%this.nbetat;
        }
        else {
          tmp[i][j] = this.grille[i][j];
        }
      }
    }
    for(i=0; i<this.taille;i++) { //boucle sur grille w/o bord
      for(j=0; j<this.taille; j++) {
        this.grille[i][j] = tmp[i][j];
      }
    }
  }


  @Override
  public String toString() {
    String s,sb;
    int i,j;
    s=sb="";
    for(i=0; i<this.taille;i++) {
      s="";
      for(j=0; j<this.taille; j++) {
        s += this.grille[i][j] + " ; ";
      }
     sb += s + "\n" ;
  }

  return sb;
  }







}
