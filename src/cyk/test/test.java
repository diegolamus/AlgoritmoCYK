package cyk.test;

import cyk.entidades.Variable;
import cyk.modelo.AlgoritmoCYK;

public class test {

	
	public static void main(String[] args) {
		Variable[] var = new Variable[4];
		
		String[] prod = {"BA","AC"};
		var[0] = new Variable("S", prod);
		
		String[] prod2 = {"CC","b"};
		var[1] = new Variable("A", prod2);

		String[] prod3 = {"AB","a"};
		var[2] = new Variable("B", prod3);

		String[] prod4 = {"BA","a"};
		var[3] = new Variable("C", prod4);
		
		String[][] CYK = AlgoritmoCYK.generaMatriz(var, "bbab");
		for (int i = 0; i < CYK.length; i++) {
			for (int j = 0; j < CYK[0].length; j++) {
				System.out.print(CYK[i][j]+ " ");
			}
			System.out.println(" ");
		}
		
		
	}
}
