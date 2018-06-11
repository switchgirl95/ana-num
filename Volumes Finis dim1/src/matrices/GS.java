/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices;

import static java.lang.Math.abs;

/**
 */
public class GS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        double[][] A = new double[5][5];
        double[] b = new double[5];
        
        A[0][0] = 1;
        A[0][1] = 1;
        A[0][2] = 0;
        A[0][3] = 0;
        A[0][4] = 0;
        
        A[1][0] = 1;
        A[1][1] = 1;
        A[1][2] = 0;
        A[1][3] = 0;
        A[1][4] = 0;
        
        A[2][0] = 0;
        A[2][1] = 1;
        A[2][2] = 2;
        A[2][3] = -1;
        A[2][4] = 0;
        
        A[3][0] = 0;
        A[3][1] = 0;
        A[3][2] = 2;
        A[3][3] = 3;
        A[3][4] = -1;
        
        A[4][0] = 0;
        A[4][1] = 0;
        A[4][2] = 0;
        A[4][3] = 2;
        A[4][4] = 4;
        
        b[0] = 1;
        b[1] = 1;
        b[2] = 3;
        b[3] = -2;
        b[4] = 2;
        
        double[] x = resoudre(A,b,0.001);
        
        for (int i = 0; i < x.length; i++) {
            System.out.println(x[i]);
        }
    }
    
    
    public static double[] resoudre (double[][] A, double[] b, double precision) {
            double[] x_n_moins_1 = new double[b.length];
            double[] x_n = new double[b.length] ;
            int i, j;
            int nbIterations =1;

            for (i = 0; i < b.length; i++) {
                    x_n_moins_1[i] = 0;
                    x_n[i] = 0;
            }

            // première iteration 
            for (i = 0; i < b.length; i++) {
                    double t = b[i];
                    for (j = 0; j < b.length; j++) {
                            if (j != i) {
                                    t = t - A[i][j]*x_n[j] ;
                            }
                    }
                    x_n[i] = t/A[i][i] ;
            }

            while (erreur(x_n, x_n_moins_1) > precision) {
                    for (i = 0; i < b.length; i++) {
                            x_n_moins_1[i] = x_n[i];
                            double t = b[i];	
                            for (j = 0; j < b.length; j++) {	
                                    if (j != i) {
                                            t = t - A[i][j]*x_n[j] ;
                                    }
                            }
                            x_n[i] = t/A[i][i] ;
                    }
                    nbIterations++;
            }
            
            System.out.println(nbIterations + " iterations pour la precision : " + precision+"%\n");
            return x_n;
    }

    public static double erreur(double x_n[], double x_n_moins_1[]) {
            double e = 0;
            int i;

            for (i = 0; i < x_n.length; i++) {
                    double temp;
                    if (x_n[i] != 0) {
                            temp = abs(100*(x_n[i] - x_n_moins_1[i])/x_n[i]) ;
                            e = e > temp ? e : temp ;
                    }
                    if (x_n[i] == 0 && x_n_moins_1[i] != 0) {
                            temp = 100 ;
                            e = e > temp ? e : temp ;
                    }
            }
            return e;
    }
}
