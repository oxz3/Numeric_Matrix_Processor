package processor;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        String menu = String.format("1. Add matrices\n2. Multiply matrix by a constant\n3. Multiply matrices\n4. " +
                "Transpose Matrix\n5. Calculate a determinant\n6. Inverse matrix\n0. Exit");
        int input;
        // Menu loop
        do {
            System.out.println(menu);
            System.out.print("Your choice: ");
            input = keyboard.nextInt();

            switch (input) {
                case 1:
                    AddMatrixInput();
                    break;
                case 2:
                    MultConstInput();
                    break;
                case 3:
                    MultMatrixInput();
                    break;
                case 4:
                    MatrixMenu();
                    break;
                case 5:
                    DeterminantInput();
                    break;
                case 6:
                    InverseInput();
                    break;
                default:
                    break;
            }

        } while (input != 0);
    }

    public static void MatrixMenu() {
        Scanner keyboard = new Scanner(System.in);
        String menu = String.format("1. Main diagonal\n2. Side diagonal\n3. Vertical line\n4. Horizontal line");
        System.out.println(menu);
        System.out.print("Your choice: ");
        int input = keyboard.nextInt();

        switch (input) {
            case 1:
                MainDiagonalInput();
                break;
            case 2:
                SideDiagonalInput();
                break;
            case 3:
                VerticalLineInput();
                break;
            case 4:
                HorizontalLineInput();
                break;
            default:
                break;
        }
    }

    public static void InverseInput() {
        double[][] matrixA = MenuInputSingle();
        int row = matrixA.length;
        int col = matrixA[0].length;

        if (CalcDeterminant(matrixA) == 0) {
            System.out.println("This matrix doesn't have an inverse");
        } else {
            System.out.println("The result is:");
            double[][] matrixAdj = Adjugate(CalcMinors(matrixA));
            double constant = 1 / CalcDeterminant(matrixA);
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    System.out.print(matrixAdj[i][j] * constant + " ");
                }
                System.out.print("\n");
            }
        }
    }

    public static double[][] Adjugate(double[][] matrixInput) {
        int row = matrixInput.length;
        int col = matrixInput[0].length;

        double[][] matrixCof = new double[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if ((i + j) % 2 == 0) {
                    matrixCof[i][j] = matrixInput[i][j];
                } else {
                    matrixCof[i][j] = -matrixInput[i][j];
                }
            }
        }

        double[][] matrixAdj = new double[row][col];
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                matrixAdj[i][j] = matrixCof[j][i];
            }
        }

        return matrixAdj;
    }

    public static void DeterminantInput() {
        double[][] matrixA = MenuInputSingle();

        System.out.println("The result is:");
        System.out.println(CalcDeterminant(matrixA));
    }

    public static double[][] CalcMinors(double[][] matrixInput) {
        int row = matrixInput.length;
        int col = matrixInput[0].length;
        double[][] matrixMinors = new double[row][col];

        // dr and dc are determinant rows and columns for the purpose of populating the array
        for (int dr = 0; dr < row; dr++) {
            for (int dc = 0; dc < col; dc++) {
                double[][] matrixMinDet = new double[row - 1][col - 1];
                for (int i = 0; i < row; i++) {
                    for (int j = 0; j < col; j++) {
                        if (j < dc && i < dr) {
                            matrixMinDet[i][j] = matrixInput[i][j];
                        } else if (j > dc && i < dr) {
                            matrixMinDet[i][j - 1] = matrixInput[i][j];
                        } else if (j < dc && i > dr) {
                            matrixMinDet[i - 1][j] = matrixInput[i][j];
                        } else if (j > dc && i > dr) {
                            matrixMinDet[i - 1][j - 1] = matrixInput[i][j];
                        } else {}
                    }
                }

                matrixMinors[dr][dc] = CalcDeterminant(matrixMinDet);
            }
        }

        return matrixMinors;
    }

    public static double CalcDeterminant(double[][] matrixInput) {
        int row = matrixInput.length;
        int col = matrixInput[0].length;

        // For 2D matrices just solve it and return the number
        if (row == 2) {
            return matrixInput[0][0] * matrixInput[1][1] - matrixInput[0][1] * matrixInput[1][0];
        } else {
            // For all matrices larger, calc the determinant by following the formula, that reduces the matrix size
            double solution = 0;

            for (int d = 0; d < col; d++) { // This is the determinant counter
                double[][] matrixDet = new double[row - 1][col - 1];
                // Populate the row - 1 sized matrix for use in the recursive method call below
                for (int i = 1; i < row; i++) {
                    for (int j = 0; j < col; j++) {
                        if (j < d) {
                            matrixDet[i - 1][j] = matrixInput[i][j];
                        } else if (j > d) {
                            matrixDet[i - 1][j - 1] = matrixInput[i][j];
                        } else if (j == d) { // skip this column
                        }
                    }
                }

                if (d % 2 == 0) {
                    solution += matrixInput[0][d] * CalcDeterminant(matrixDet);
                } else {
                    solution -= matrixInput[0][d] * CalcDeterminant(matrixDet);
                }
            }
        return solution;
        }
    }

    public static double[][] MenuInputSingle() {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter size of the matrix: ");
        int rowA = keyboard.nextInt();
        int colA = keyboard.nextInt();

        System.out.println("Enter matrix:");
        double[][] matrixA = new double[rowA][colA];
        for (int i = 0; i < rowA; i++) {
            for (int j = 0; j < colA; j++) {
                matrixA[i][j] = keyboard.nextDouble();
            }
        }
        return matrixA;
    }

    public static void MainDiagonalInput() {
        double[][] matrixA = MenuInputSingle();
        int rowA = matrixA.length;
        int colA = matrixA[0].length;

        System.out.println("The result is:");
        for (int i = 0; i < colA; i++) {
            for (int j = 0; j < rowA; j++) {
                System.out.print(matrixA[j][i] + " ");
            }
            System.out.print("\n");
        }
    }

    public static void SideDiagonalInput() {
        double[][] matrixA = MenuInputSingle();
        int rowA = matrixA.length;
        int colA = matrixA[0].length;

        System.out.println("The result is:");
        for (int i = 0; i < colA; i++) {
            for (int j = 0; j < rowA; j++) {
                System.out.print(matrixA[rowA - 1 - j][colA - 1 - i] + " ");
            }
            System.out.print("\n");
        }
    }

    public static void VerticalLineInput() {
        double[][] matrixA = MenuInputSingle();
        int rowA = matrixA.length;
        int colA = matrixA[0].length;

        System.out.println("The result is:");
        for (int i = 0; i < rowA; i++) {
            for (int j = 0; j < colA; j++) {
                System.out.print(matrixA[i][colA - 1 - j] + " ");
            }
            System.out.print("\n");
        }
    }

    public static void HorizontalLineInput() {
        double[][] matrixA = MenuInputSingle();
        int rowA = matrixA.length;
        int colA = matrixA[0].length;

        System.out.println("The result is:");
        for (int i = 0; i < rowA; i++) {
            for (int j = 0; j < colA; j++) {
                System.out.print(matrixA[rowA - 1 - i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    public static void AddMatrixInput() {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter size of the first matrix: ");
        int rowA = keyboard.nextInt();
        int colA = keyboard.nextInt();

        System.out.println("Enter first matrix:");
        double[][] matrixA = new double[rowA][colA];
        for (int i = 0; i < rowA; i++) {
            for (int j = 0; j < colA; j++) {
                matrixA[i][j] = keyboard.nextDouble();
            }
        }

        System.out.print("Enter size of the second matrix: ");
        int rowB = keyboard.nextInt();
        int colB = keyboard.nextInt();

        System.out.println("Enter second matrix:");
        double[][] matrixB = new double[rowA][colA];
        for (int i = 0; i < rowB; i++) {
            for (int j = 0; j < colB; j++) {
                matrixB[i][j] = keyboard.nextDouble();
            }
        }

        System.out.println("The addition result is: ");
        for (int i = 0; i < rowA; i++) {
            for (int j = 0; j < colA; j++) {
                System.out.print(matrixA[i][j] + matrixB[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    public static void MultConstInput() {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter size of the first matrix: ");
        int rowA = keyboard.nextInt();
        int colA = keyboard.nextInt();

        System.out.println("Enter the matrix:");
        double[][] matrixA = new double[rowA][colA];
        for (int i = 0; i < rowA; i++) {
            for (int j = 0; j < colA; j++) {
                matrixA[i][j] = keyboard.nextDouble();
            }
        }

        System.out.print("Enter the constant: ");
        double constant = keyboard.nextDouble();

        System.out.println("The multiplication result is:");
        for (int i = 0; i < rowA; i++) {
            for (int j = 0; j < colA; j++) {
                System.out.print(matrixA[i][j] * constant + " ");
            }
            System.out.print("\n");
        }
    }

    public static void MultMatrixInput() {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter size of the first matrix: ");
        int rowA = keyboard.nextInt();
        int colA = keyboard.nextInt();

        System.out.println("Enter first matrix:");
        double[][] matrixA = new double[rowA][colA];
        for (int i = 0; i < rowA; i++) {
            for (int j = 0; j < colA; j++) {
                matrixA[i][j] = keyboard.nextDouble();
            }
        }

        System.out.print("Enter size of the second matrix: ");
        int rowB = keyboard.nextInt();
        int colB = keyboard.nextInt();

        System.out.println("Enter second matrix:");
        double[][] matrixB = new double[rowB][colB];
        for (int i = 0; i < rowB; i++) {
            for (int j = 0; j < colB; j++) {
                matrixB[i][j] = keyboard.nextDouble();
            }
        }

        System.out.println("The multiplication result is: ");
        for (int i = 0; i < rowA; i++) {
            for (int j = 0; j < colB; j++) {
                double value = 0;
                for (int k = 0; k < colA; k++) {
                    value += matrixA[i][k] * matrixB[k][j];
                }
                System.out.print(value + " ");
            }
            System.out.print("\n");
        }
    }
}
