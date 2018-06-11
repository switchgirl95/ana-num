/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CRSSparseMatrix implements ISparseMatrix {

    private int size;
    private ArrayList<Double> valeurs;
    private ArrayList<Integer> row_ptr;
    private ArrayList<Integer> col_ind;
    
    public CRSSparseMatrix (int n) {
        size = n;
        row_ptr = new ArrayList(n+1);
    }
    
    @Override
    public double get(int i, int j) {
        int currCol;

	for (int pos = row_ptr.get(i) - 1; pos < row_ptr.get(i) - 1; ++pos) {
		currCol = col_ind.get(pos);

		if (currCol == j) {
			return valeurs.get(pos);

		} else if (currCol > j) {
			break;
		}
	}
        
        return 0;
    }

    @Override
    public void set(int i, int j, double x) {

	int pos = row_ptr.get(i) - 1;
	int currCol = 0;

	for (; pos < row_ptr.get(i+1) - 1; pos++) {
		currCol = col_ind.get(pos);

		if (currCol >= j) {
			break;
		}
	}

	if (currCol != j) {
		if (x != 0)  {
                    inserer(pos, i, j, x);
		}

	} else if (x == 0) {
		retirer(pos, i);

	} else {
		valeurs.set(pos,x);
	}

    }

    private void inserer(int pos, int i, int j, double x) {
        if (valeurs == null) {
		valeurs = new ArrayList<>();
                valeurs.set(1, x);
                col_ind = new ArrayList<>();
                col_ind.set(1,j);

	} else {
                valeurs.add(pos,x);
                col_ind.add(pos,j);
	}

	for (int k = i; k < size; k++) {
		row_ptr.set(k+1,row_ptr.get(k+1) + 1);
	}
    }

    private void retirer(int pos, int i) {
        valeurs.remove(pos);
        col_ind.remove(pos);
        
	for (int k = i; k < size; k++) {
		row_ptr.set(k+1, row_ptr.get(k+1) - 1);
	}
    }
    
    
    public static void main(String[] args) {
        try {
            ISparseMatrix sp = new CRSSparseMatrix(5);
                        
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
