package com.example.service;

import com.example.exception.NoSquareException;
import com.example.model.Matrix;

public class MatrixMathematics {

	/**
	 * This class a matrix utility class and cannot be instantiated.
	 */
	private MatrixMathematics(){}
	
	
	/**
	 * Determinant of a square matrix
	 * The following function find the determinant in a recursively. 
	 * @param matrix
	 * @return
	 * @throws NoSquareException
	 */
	public static double determinant(Matrix matrix) throws NoSquareException {
		if (!matrix.isSquare())
			throw new NoSquareException("matrix need to be square.");
		if (matrix.size() == 1){
			return matrix.getValueAt(0, 0);
		}
			
		if (matrix.size()==2) {
			return (matrix.getValueAt(0, 0) * matrix.getValueAt(1, 1)) - ( matrix.getValueAt(0, 1) * matrix.getValueAt(1, 0));
		}
		double sum = 0.0;
		for (int i=0; i<matrix.getNcols(); i++) {
			sum += changeSign(i) * matrix.getValueAt(0, i) * determinant(createSubMatrix(matrix, 0, i));
		}
		return sum;
	}

	/**
	 * Determine the sign; i.e. even numbers have sign + and odds -
	 * @param i
	 * @return
	 */
	private static int changeSign(int i) {
		if (i%2==0)
			return 1;
		return -1;
	}
	/**
	 * Creates a submatrix excluding the given row and column
	 * @param matrix
	 * @param excluding_row
	 * @param excluding_col
	 * @return
	 */
	public static Matrix createSubMatrix(Matrix matrix, int excluding_row, int excluding_col) {
		Matrix mat = new Matrix(matrix.getNrows()-1, matrix.getNcols()-1);
		int r = -1;
		for (int i=0;i<matrix.getNrows();i++) {
			if (i==excluding_row)
				continue;
				r++;
				int c = -1;
			for (int j=0;j<matrix.getNcols();j++) {
				if (j==excluding_col)
					continue;
				mat.setValueAt(r, ++c, matrix.getValueAt(i, j));
			}
		}
		return mat;
	}
	
	/**
	 * The cofactor of a matrix
	 * @param matrix
	 * @return
	 * @throws NoSquareException
	 */
	public static Matrix cofactor(Matrix matrix) throws NoSquareException {
		Matrix mat = new Matrix(matrix.getNrows(), matrix.getNcols());
		for (int i=0;i<matrix.getNrows();i++) {
			for (int j=0; j<matrix.getNcols();j++) {
				mat.setValueAt(i, j, changeSign(i) * changeSign(j) * determinant(createSubMatrix(matrix, i, j)));
			}
		}
		
		return mat;
	}



	/**
	 * Transpose of a matrix - Swap the columns with rows
	 * @param matrix
	 * @return
	 */
	public static Matrix transpose(Matrix matrix) {
		Matrix transposedMatrix = new Matrix(matrix.getNcols(), matrix.getNrows());
		for (int i=0;i<matrix.getNrows();i++) {
			for (int j=0;j<matrix.getNcols();j++) {
				transposedMatrix.setValueAt(j, i, matrix.getValueAt(i, j));
			}
		}
		return transposedMatrix;
	}



	/**
	 * Inverse of a matrix - A-1 * A = I where I is the identity matrix
	 * A matrix that have inverse is called non-singular or invertible. If the matrix does not have inverse it is called singular.
	 * For a singular matrix the values of the inverted matrix are either NAN or Infinity
	 * Only square matrices have inverse and the following method will throw exception if the matrix is not square.
	 * @param matrix
	 * @return
	 * @throws NoSquareException
	 */
	public static Matrix inverse(Matrix matrix) throws NoSquareException {
		double d=determinant(matrix);
		if(d==0)
		{
			return null;
		}
		return (transpose(cofactor(matrix)).multiplyByConstant(1.0/d));

	}


	public  void test() {

	}




	
	
}
