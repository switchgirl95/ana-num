/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices;

import static java.lang.Math.abs;

public class GSSparseMatrix {
    
    public static double[] resoudre (ISparseMatrix A, double[] b, double precision) {
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
                                    t = t - A.get(i,j)*x_n[j] ;
                            }
                    }
                    x_n[i] = t/A.get(i,i) ;
            }

            while (erreur(x_n, x_n_moins_1) > precision) {
                    for (i = 0; i < b.length; i++) {
                            x_n_moins_1[i] = x_n[i];
                            double t = b[i];	
                            for (j = 0; j < b.length; j++) {	
                                    if (j != i) {
                                            t = t - A.get(i,j)*x_n[j] ;
                                    }
                            }
                            x_n[i] = t/A.get(i,i) ;
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
