// A generic Matrix class where T extends Number to allow numeric operations
public class Matrix<T extends Number> {
    private T[][] matrix; // 2D array to store matrix elements
    private int rows;     // Number of rows in the matrix
    private int columns;  // Number of columns in the matrix

    // Constructor to initialize a matrix of given dimensions
    @SuppressWarnings("unchecked") // Suppress unchecked warnings for generic array creation
    public Matrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        matrix = (T[][]) new Number[rows][columns]; // Create a 2D array for the matrix
    }

    // Method to set the value of a specific matrix element
    public void setElement(int row, int column, T value) {
        // Check if the specified row and column are within bounds
        if (row >= rows || column >= columns || row < 0 || column < 0) {
            throw new IndexOutOfBoundsException("Invalid index for the matrix.");
        }
        matrix[row][column] = value; // Set the value at the specified location
    }

    // Method to retrieve the value of a specific matrix element
    public T getElement(int row, int column) {
        // Check if the specified row and column are within bounds
        if (row >= rows || column >= columns || row < 0 || column < 0) {
            throw new IndexOutOfBoundsException("Invalid index for the matrix.");
        }
        return matrix[row][column]; // Return the value at the specified location
    }

    // Method to add two matrices
    public Matrix<T> add(Matrix<T> other) {
        // Check if the dimensions of both matrices match
        if (this.rows != other.rows || this.columns != other.columns) {
            throw new IllegalArgumentException("Matrices must have the same dimensions for addition.");
        }

        // Create a new matrix to store the result
        Matrix<T> result = new Matrix<>(this.rows, this.columns);

        // Iterate through each element, compute the sum, and set it in the result matrix
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                // Add the corresponding elements of the two matrices
                T sum = (T) Double.valueOf(
                        this.matrix[i][j].doubleValue() + other.matrix[i][j].doubleValue()
                );
                result.setElement(i, j, sum); // Store the sum in the result matrix
            }
        }
        return result; // Return the resulting matrix
    }

    // Getter for the number of rows
    public int getRows() {
        return rows;
    }

    // Getter for the number of columns
    public int getColumns() {
        return columns;
    }

    // Method to print the matrix in a readable format
    public void printMatrix() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(matrix[i][j] + " "); // Print each element
            }
            System.out.println(); // Move to the next line after each row
        }
    }
    
    public static void main(String[] args) {
        // Create two 2x2 matrices
        System.out.println("Creating a 2x2 matrix...");
        Matrix<Integer> matrixA = new Matrix<>(2, 2);
        Matrix<Integer> matrixB = new Matrix<>(2, 2);

        // Set elements for the first matrix (Matrix A)
        System.out.println("Setting elements...");
        matrixA.setElement(0, 0, 1);
        matrixA.setElement(0, 1, 2);
        matrixA.setElement(1, 0, 3);
        matrixA.setElement(1, 1, 4);

        // Set elements for the second matrix (Matrix B)
        matrixB.setElement(0, 0, 5);
        matrixB.setElement(0, 1, 6);
        matrixB.setElement(1, 0, 7);
        matrixB.setElement(1, 1, 8);

        // Display Matrix A
        System.out.println("Matrix A:");
        matrixA.printMatrix();

        // Display Matrix B
        System.out.println("Matrix B:");
        matrixB.printMatrix();

        // Add the two matrices and display the result
        System.out.println("Adding the matrices...");
        try {
            Matrix<Integer> matrixC = matrixA.add(matrixB); // Add Matrix A and B
            System.out.println("Result of A + B:");
            matrixC.printMatrix(); // Print the result
        } catch (IllegalArgumentException e) {
            System.out.println("Exception: " + e.getMessage()); // Handle invalid dimensions
        }

        // Demonstrate error handling for incompatible matrix dimensions
        System.out.println("Trying to add matrices of incompatible dimensions...");
        Matrix<Integer> matrixD = new Matrix<>(3, 3); // Create a 3x3 matrix
        try {
            Matrix<Integer> matrixE = matrixA.add(matrixD); // Attempt to add incompatible matrices
        } catch (IllegalArgumentException e) {
            System.out.println("Exception: " + e.getMessage()); // Catch and print the exception
        }
    }
}


