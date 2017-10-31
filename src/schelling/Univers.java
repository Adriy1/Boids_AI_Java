import java.util.*;
import java.awt.*;




public class Univers {
  int[][] grille;
  int nbetat;
  int taille;
  LinkedList<int[]> ListeVacant;

  public Univers(int nbetat,int taille) {
    this.grille = new int[taille][taille];
    this.nbetat = nbetat;
    this.taille = taille;
    ListeVacant = new LinkedList<int[]>();
  }

  public void init() {
    for(int i=0; i<taille;i++) {
      for(int j=0; j<taille; j++) {
        if ( (int) (Math.random()*nbetat*2) < 1){
          grille[i][j]=0;
          //System.out.println("hello");
        }
        else {
           grille[i][j]=(int) ((Math.random()*(nbetat-1))+1);
        }
        if(grille[i][j]==0) {
          int[] t = {i,j};
          this.ListeVacant.add(t);

        }
      }
    }

  }

  public void suivant() {
    // modifie la grille pour le tour suivant
    int[][] tmp = new int[this.taille][this.taille];
    int i,j,k=0,c=0;
    for(i=0; i<this.taille;i++) {
      for(j=0; j<this.taille; j++) {
        tmp[i][j] = this.grille[i][j];
      }
    }
    int lim = 5;
    int l = this.ListeVacant.size();
    for(i=0; i<this.taille;i++) {
      for(j=0; j<this.taille; j++) {
        if(this.grille[i][j] != 0) {
          Iterator<int[]> it = ListeVacant.iterator();
          int[][] t = {{1,1},{0,1},{-1,1},{-1,0},{-1,-1},{0,-1},{1,-1},{1,0}};
          k=c=0;
          if (i==0) {
            t[2][0]=t[3][0]=t[4][0]=this.taille-1;
          }
          if (j==0){
            t[4][1]=t[5][1]=t[6][1]=this.taille-1;
          }
          while(c<lim && k<8){
            if(this.grille[(i+t[k][0])%this.taille][(j+t[k][1])%this.taille] != (this.grille[i][j])) {
              c++;
            }
            k++;
          }
          if(c >= lim) {
            int coord[] = it.next();
            this.ListeVacant.remove(0);
            tmp[coord[0]][coord[1]] = tmp[i][j];
            int[] a = {i,j};
            this.ListeVacant.add( (int)(Math.random()*l),a);
            tmp[i][j]=0;
          }
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
