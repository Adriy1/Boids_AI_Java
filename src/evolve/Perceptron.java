import gui.*;
import java.awt.*;
import java.util.*;



public class Perceptron{
    double[] weights;
    double learningconstant;  //learningconstant permet de ralentir l'apprentissage

    public Perceptron(int n, double c){     // Perceptron = cerveau
      weights = new double[n];
      for(int i=0;i<n;i++) {
        weights[i] = Math.random();
      }
      learningconstant = c;
    }

    public Vector feedforward(Vector[] forces,Boid r) {
      // Sum is a Vector.
      Vector sum = new Vector(0,0);
      for (int i = 0; i < weights.length; i++) {
        // Vector addition and multiplication
        forces[i].mult(weights[i]);
        sum.plus(forces[i]);
        sum.mult(r.adn.vmax/sum.norm());    //on calcule ici la resultante des forces
      }
      // No activation function
      return sum;
    }

    public void train(Vector[] forces, Vector error) {  //entrainement en fontion des forces et de l'erreur
      for (int i = 0; i < weights.length; i++) {
          weights[i] += learningconstant*error.x*forces[i].x;
          weights[i] += learningconstant*error.y*forces[i].y;
          if(weights[i] < 0){
            weights[i] = 0;
          }
        }
      }


}
