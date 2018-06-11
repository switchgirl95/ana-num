/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
public class CDSSparseMatrix implements ISparseMatrix{
    private double[] val_0;
    private double[] val_1;
    private double[] val_2;
    private double[] val_moins_1;
    private double[] val_moins_2;
    
    private HashMap<Integer,double[]> diagonales;
    
    private int size;
    
    /* Cree une matrice carree de taille n*/
    public CDSSparseMatrix (int n) {
        size = n;
        diagonales = new HashMap();
    }
    
    public double get(int i, int j) {
        int numero_diagonale = j-i;
        
        if (diagonales.get(numero_diagonale) == null)
            return 0;
        else {
            int position;
            if (numero_diagonale == 0)
                position = i;
            else if (numero_diagonale < 0)
                position = j;
            else
                position = i;
            return diagonales.get(numero_diagonale)[position];
        }
    }
    
    public void set(int i, int j, double x) {
        int numero_diagonale = j-i;
        
        if (diagonales.get(numero_diagonale) == null)
            diagonales.put(numero_diagonale, new double[size]);
        
        int position;
        if (numero_diagonale == 0)
            position = i;
        else if (numero_diagonale < 0)
            position = j;
        else
            position = i;
        diagonales.get(numero_diagonale)[position] = x;

    }
    
    public static void main(String[] args) {
        try {
            ISparseMatrix sp = new CDSSparseMatrix(5);
                        
            sp.set(0, 0, 1);
        sp.set(0, 1, 1);
        sp.set(0, 2, 0);
        sp.set(0, 3, 0);
        sp.set(0, 4, 0);
        
        sp.set(1, 0, 1);
        sp.set(1, 1, 1);
        sp.set(1, 2, 0);
        sp.set(1, 3, 0);
        sp.set(1, 4, 0);
        
        sp.set(2, 0, 0);
        sp.set(2, 1, 1);
        sp.set(2, 2, 2);
        sp.set(2, 3, -1);
        sp.set(2, 4, 0);
        
        sp.set(3, 0, 0);
        sp.set(3, 1, 0);
        sp.set(3, 2, 2);
        sp.set(3, 3, 3);
        sp.set(3, 4, -1);
        
        sp.set(4, 0, 0);
        sp.set(4, 1, 0);
        sp.set(4, 2, 0);
        sp.set(4, 3, 2);
        sp.set(4, 4, 4);
        
            for (int i = 0; i < 5; i++){
                for (int j = 0; j < 5; j++) 
                    System.out.print(sp.get(i,j) + " " );
                    System.out.println();
                }
            
            double[] b = new double[5];
            b[0] = 1;
        b[1] = 1;
        b[2] = 3;
        b[3] = -2;
        b[4] = 2;
            double[] x = GSSparseMatrix.resoudre(sp, b, 0.001);
        
        for (int i = 0; i < x.length; i++) {
            System.out.println(x[i]);
        }
            
        } catch (Exception ex) {
            Logger.getLogger(CDSSparseMatrix.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
