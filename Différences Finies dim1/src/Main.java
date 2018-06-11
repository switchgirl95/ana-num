import java.util.Scanner;

import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.Function;

import matrices.GS;


public class Main {

	static Function funct;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner scanner = new Scanner(System.in);
		int n; // Nombre de points du maillage
		System.out.println("Entrez l'expression de la fonction f en focntion de x:");
		String str = scanner.nextLine();
		funct = new Function("f",str,"x");
		
		System.out.println("Entrez la valeur de X_0 :"); //borne gauche
		final double x_0 = scanner.nextDouble();
		System.out.println("Entrez la valeur de X_final :"); //borne droite
		final double x_final = scanner.nextDouble();
		
		System.out.println("Entrez le nombre de points du maillage (n): ");
		n = scanner.nextInt();
		double pas = 1.0/(n+1);
		double maille[] = new double[n+2]; //Tableau des éléments du maillage
		maille[0] = x_0;
		maille[n+1] = x_final;
		double f[] = new double[n]; // Tableau des fi3
		for(int i=1;i<=n;i++){
			
			maille[i] = i*pas;
			f[i-1] = f(maille[i]);
		}
		double U[] = new double[n+2]; //Tableau des Ui (ce que l'on recherche)
		U[0] = 0;
		U[n+1] = 0;
		double A[][] = new double[n][n]; //Tableau représentant la matrice A telle que A.U = f
		A[0][0] = 2;
		A[0][1] = -1;
		A[n-1][n-1] = 2;
		A[n-1][n-2] = -1;
		for(int i = 1;i<n-1;i++){
			
			A[i][i-1] = -1;
			A[i][i] = 2;
			A[i][i+1] = -1;
		}
		/*
		Matrix matrice = new Matrix(A,n);
		Matrix inv_A = matrice.Inverse();
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				System.out.print(inv_A.getElt(i,j)/inv_A.determinant()+" ");
				U[i+1] = U[i+1] + (inv_A.getElt(i,j)/inv_A.determinant())*f[j];
			}
			U[i+1] = U[i+1]*pas*pas;
			System.out.println();
		}
		*/
		double[] X = new double[n];
		X = GS.resoudre(A, f, 5);
		System.out.println("********************************************\nRésultat final: ");
		for(int i=0;i<n;i++){
			
			System.out.println(U[i]); // Affichage des Ui			
		}
		scanner.close();
	}
	
	public static double f(double x){
		
		String str = "funct("+x+")";
		Expression e = new Expression(str,funct);
		return e.calculate();
	}
	public void U_second(double x){
		
		
	}

}
