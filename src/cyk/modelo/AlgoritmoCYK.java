package cyk.modelo;

import cyk.entidades.Variable;

public class AlgoritmoCYK {

	/**
	 * @Descripcion Este metodo genera una matriz CYK a partir de las variables y la
	 *              cadena que recibe como parametro.
	 * @param variables variables que hacen parte de la gramatica
	 * @param cadena    cadena sobre la cual se construira la matriz CYK
	 * @return matrizCYK resultado de algoritmo CYK aplicado sobre las variables y
	 *         cadena de entrada, null si la cadena de entrada es vacia
	 */
	public static String[][] generaMatriz(Variable[] variables, String cadena) {
		// Generar matriz CYK
		String[][] matrizCYK = new String[cadena.length()][cadena.length()];
		// Validar que la cadena tenga por lo menos un caracter
		if (cadena.length() == 0)
			return null;
		// LLenar la primera columna de la matriz
		llenarPrimeraColumna(matrizCYK, variables, cadena);
		// llenar columnas posteriores
		llenarColumnas(matrizCYK, variables, cadena);
		return matrizCYK;
	}

	private static void llenarPrimeraColumna(String[][] matrizCYK, Variable[] variables, String cadena) {
		// Recuperar caracteres
		char[] caracteres = cadena.toCharArray();
		// Iterar sobre los caracteres
		for (int i = 0; i < caracteres.length; i++) {
			// Iterar sobre las variables
			matrizCYK[i][0] = "";
			for (Variable v : variables) {
				// Crear variable para verificar que no se agregue una variable dos veces
				boolean seAgregoLaVariable = false;
				// Iterar sobre las producciones de la variable
				for (String produccion : v.getProducciones()) {
					// Si la produccion es igual a el caracter que se esta revisando, se agrega la
					// variable en la posicion adecuada
					if (produccion.equals(caracteres[i] + "") && !seAgregoLaVariable) {
						matrizCYK[i][0] += v.getVariable() + ",";
						seAgregoLaVariable = true;
					}
				}
			}
			// Eliminar ultima coma en caso de que se agregue por lo menos una variable
			if (matrizCYK[i][0] != null && !matrizCYK[i][0].trim().equals("")) {
				matrizCYK[i][0] = matrizCYK[i][0].substring(0, matrizCYK[i][0].length() - 1);
			}
		}
	}

	private static void llenarColumnas(String[][] matrizCYK, Variable[] variables, String cadena) {
		// Iterar sobre las columnas de la matriz
		for (int j = 2; j <= cadena.length(); j++) {
			// Iterar sobre las filas de la matriz
			for (int i = 1; i <= cadena.length() - j +1; i++) {
				matrizCYK[i-1][j-1] = "";
				// Iterar sobre el K
				for (int k = 1; k <j; k++) {
					// Generar producto
					matrizCYK[i-1][j-1] += producto(matrizCYK[i-1][k-1], matrizCYK[i+k-1][j-1-k]) + ",";
				}
				// Eliminar ultima coma en caso de que se agregue por lo menos una variable
				if (matrizCYK[i-1][j-1] != null && !matrizCYK[i-1][j-1].trim().equals("")) {
					matrizCYK[i-1][j-1] = matrizCYK[i-1][j-1].substring(0, matrizCYK[i-1][j-1].length() - 1);
				}
				// Ver que variables meten las variables encontradas
				reemplazarVariables(matrizCYK, variables, i-1, j-1);
			}
		}
	}

	private static void reemplazarVariables(String[][] matrizCYK, Variable[] variables, int fila, int columna) {
		// Recuperar producciones nuevas
		String[] prod = matrizCYK[fila][columna].split(",");
		// Limpiar matriz CYK
		matrizCYK[fila][columna] = "";
		// Iterar sobre la producciones nuevas
		for (int i = 0; i < prod.length; i++) {
			// Iterar sobre las variables
			for (Variable v : variables) {
				// Crear variable para verificar que no se agregue una variable dos veces
				boolean seAgregoLaVariable = false;
				// Iterar sobre las producciones de la variable
				for (String produccion : v.getProducciones()) {
					// Si la produccion es igual a el caracter que se esta revisando, se agrega la
					// variable en la posicion adecuada
					if (produccion.equals(prod[i] + "") && !seAgregoLaVariable) {
						matrizCYK[fila][columna] += v.getVariable() + ",";
						seAgregoLaVariable = true;
					}
				}
			}
		}
		// Eliminar ultima coma en caso de que se agregue por lo menos una variable
		if (matrizCYK[fila][columna] != null && !matrizCYK[fila][columna].trim().equals("")) {
			matrizCYK[fila][columna] = matrizCYK[fila][columna].substring(0, matrizCYK[fila][columna].length() - 1);
		}
	}

	private static String producto(String a, String b) {
		String producto = "";
		// Verificar si el string a, o el string b tiene mas de dos variables
		if (a.split(",").length > 1 || b.split(",").length > 1) {
			// Separar variables
			String[] aux_a = a.split(",");
			String[] aux_b = b.split(",");
			for (String a1 : aux_a) {
				for (String b1 : aux_b) {
					if(!a1.equals(b1))
						producto += a1 + b1 + ",";
					else
						producto += a1 + ",";
				}
			}
			// Eliminar ultima coma
			producto = producto.substring(0, producto.length() - 1);
		}
		// Si no las tiene operar normal
		else {
			if(!a.equals(b))
				producto = a + b;
			else
				producto = a;
		}
		return producto;
	}
}
