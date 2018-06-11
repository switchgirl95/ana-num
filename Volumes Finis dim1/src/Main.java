import java.util.Scanner;

import matrices.GS;


public class Main {

	final static double x_0 = 0;
	final static double x_final = 1;
	static double[][] maille;
	static double[] f;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner scanner = new Scanner(System.in);
		int n; //Nombre de volumes
		
		System.out.println("Entrez le nombre de points du maillage :"); 
		n = scanner.nextInt();		
		double pas = 1.0/(n);
		
		f = new double [n]; 
		f = chargementF(n, pas);		
			
		maille = new double[n][n];
		chargementMaille(maille, n, pas); // Stockage des éléments de la matrice A grace au CDS
		
		double[] U = new double[n]; // définition du tableau des inconnus
		U = GS.resoudre(maille, f, n);
		show(U);
		//resolution de l'equation A.U=f
		
		
		
		// Erreur de la methode
		System.out.println();
		for(int i=0;i<n;i++){
			
			double x1 = pas/2;
			if(i==0){
				System.out.println(((-x1*x1*x1/3)-U[i])/(-x1*x1*x1/3));
				continue;
			}
			double x = x1 + i*pas;
			System.out.println(((-x*x*x/3)-U[i])/(-x*x*x/3));
			
		}
		
		scanner.close();
	}
	
	private static void show(double[] u) {
		// TODO Auto-generated method stub
		System.out.println("Liste des éléments qu'on cherchait :");
		for(int i=0;i<u.length;i++){
			System.out.println(u[i]);
		}
	}

	private static void chargementMaille(double[][] maille,int n,double pas){
		
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++) maille[i][j]=0;
		}
		maille [0][0] = 3.0/(pas);
		maille[0][1] = -1.0/(pas);
		maille [n-1][n-1] = 3.0/(pas);
		maille[n-1][n-2] = 1.0/(pas);
	
		for(int i=1; i<n-1;i++){
			
			maille[i][i] = 2.0/(pas);
			maille[i][i+1] = -1.0/(pas);
			maille[i][i-1] = 1.0/(pas);
		}
		
	}
	
	private static double[] chargementF(int n, double pas){
		
		double[] f = new double[n];
		for(int i=0;i<n-1;i++){
			
			f[i] = pas * (fct(i+1.5)+fct(i+0.5)+fct(i+1))/6;
		}
		return f;
	}

	private static double fct(double x0) {
		// TODO Auto-generated method stub
		return 2*x0;
	}

}
