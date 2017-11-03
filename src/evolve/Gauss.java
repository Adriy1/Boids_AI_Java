import java.lang.Math.*;

public class Gauss {
  double[] TAB;
  int max;
  int min;
  int L = 11;

  public Gauss(int min, int max, double sigma) {
    this.min = min;
    this.max = max;
    TAB = new double[L];
    double larg = (double)(max-min)/(L+1);
    for(int j=0;j<L;j++){
      double a = min+(2.0*(j+1))*larg/2.0-(min+max)/2.0;
      TAB[j] = 1 / (sigma*Math.sqrt(2.0*Math.PI))*Math.exp(-a*a/(2.0*sigma*sigma)); //  System.out.println(a + "-----" + TAB[j] +" " + j );
    }
    double S=0;
    for(double e : TAB) {
      S += e;
    }
    for(int j=0; j<L;j++) {
      TAB[j]=TAB[j]/S;
    }
    for(int j=1; j<L;j++) {
      TAB[j]+=TAB[j-1];
    }
  }

  public int rechercheElement(double[] tab,int i, int j,double r) {
      if (tab[0] > r){
        return 0;
      }
      if (tab[j-1] <r ){
        return j-1;
      }
      int k = (i+j)/2;
      if (tab[k]<r && tab[k+1]>r)
        return k;
      else
        if (r<tab[k])
          return rechercheElement(tab,i,k,r);
        else
          return rechercheElement(tab, k, j, r);
      }


    public int tirage() {
      double r = Math.random();  //System.out.println(r);
      int i = rechercheElement(TAB,0,L,r);
      double y = min + (2*i+1)*(max-min)/(L+1)/2;
      return (int) y;
    }

}
