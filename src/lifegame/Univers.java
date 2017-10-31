import java.util.*;

public class Univers {
    int[][] grille;
    int nbetat; //0 morte , 1 vivante
    int taille;
    
    public Univers(int nbetat,int taille) {
	this.grille = new int[taille][taille];
	this.nbetat = nbetat;
	this.taille = taille;
    }

    public void init() {
	for(int i=0; i<taille;i++) {
	    for(int j=0; j<taille; j++) {
		//grille[i][j] = 0; //pattern du sujet
		grille[i][j]=(int) (Math.random()*nbetat); //0 morte , 1 vivante
	    }
	}
	//grille[1][1]=grille[1][2]=grille[2][1]=grille[2][3]=grille[4][4]=1; //pattern du sujet
    }

    public void suivant() {
	// modifie la grille pour le tour suivant
	int[][] tmp = grille;
	int i,j,c=0,k=0; //c compte le nb de voisins de la meme espece vivant
	for(i=0; i<this.taille;i++) { 
	    for(j=0; j<this.taille; j++) {
		k=c=0; //reinitialisation
		int[][] t = {{1,1},{0,1},{-1,1},{-1,0},{-1,-1},{0,-1},{1,-1},{1,0}};
		
		if (i==0) { //la grille est circulaire 
		    t[2][0]=t[3][0]=t[4][0]=this.taille-1;
		}
		if (j==0){ //la grille est circulaire 
		    t[4][1]=t[5][1]=t[6][1]=this.taille-1;
		}
		while(k<8){ //tant qu'on a pas tester tous les voisins k 
		    if(this.grille[(i+t[k][0])%this.taille][(j+t[k][1])%this.taille] == 1) { //vivant
			c++;
			//System.out.println(c);
		    }
		    k++;
		}
		//System.out.println("grille[" +i+"]["+j+"] = "+grille[i][j] +" a "+c+" voisin(s)");
		if(c == 3 && this.grille[i][j] == 0) { //cellule actuelle morte avec 3 voisins vivants
		    tmp[i][j] = 1; //etat a t+1
		}
		if((c == 2 || c == 3)&&this.grille[i][j] == 1) { //cellule vivante avec 2 ou 3 voisins vivants
		    tmp[i][j] = 1;
		}
		else {
		    tmp[i][j] = 0;
		}
	    }
	}
	for(i=0; i<this.taille;i++) { //boucle sur grille w/ bord
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
