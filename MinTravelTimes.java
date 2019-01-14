/**
 * Will Dennis - 100157542
 * Date: 21/11/18
 * Version: 1.0
 */
package mintraveltimes;

//import packages
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class MinTravelTimes {

    //generate random array using Random function
    public static int[][] randomArray(int n) {
        Random random = new Random();
        //initialise new array of size n
        int[][] array = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int x = random.nextInt(1001);   //ints from 0-1000
                //condition to ensure 0's diagonally
                if (i == j) {
                    array[i][j] = 0;        //make value to 0
                } else {                    //assign randomInt to symmetrical cells
                    array[i][j] = x;
                    array[j][i] = x;
                }

            }
        }
        return array;       //return array of random ints
    }

    //first function to sort matrix
    public void compareMatrix(int[][] matrix) {
        //initialise ArrayList 'output'
        ArrayList<Integer> output = new ArrayList<>();
        for (int[] matrix1 : matrix) {          //for loop for incrementing matrix positions
            int minValue = Integer.MAX_VALUE;   //initialise minValue
            for (int j = 0; j < matrix1.length; j++) {
                if (matrix1[j] > 0 && matrix1[j] < minValue) {      //comparing values
                    minValue = matrix1[j];
                }
            }
            output.add(minValue);   //add minValue to output ArrayList
        }
        System.out.println(output); //System.out.println for checking
    }

    //refined sorting function
    public void compareMatrixRefinded(int[][] matrix) {
        //initialise ArrayList 'output'
        ArrayList<Integer> output = new ArrayList<>();
        int counter = 0;  //initialise a counter for 1/0

        for (int j = matrix.length - 1; j >= 0; --j) {
            int minValue = Integer.MAX_VALUE;   //minValue varibale
            for (int i = matrix.length - 1; i >= 0; --i) {
                if (matrix[i][j] == 0) {
                    counter = 1;
                } else if (counter == 1 && matrix[i][j] < minValue) {
                    minValue = matrix[i][j];
                    //System.out.println(minValue);
                } else if (counter == 0 && matrix[j][i] < minValue) {
                    minValue = matrix[j][i];
                    //System.out.println(minValue);
                }
            }
            output.add(minValue);
        }
        
        Collections.reverse(output);     //https://stackoverflow.com/questions/10766492/what-is-the-simplest-way-to-reverse-an-arraylist
        System.out.println(output);     //output arraylist 'output'
    }

    //function to output/print matrix
    public void printMatrix(int[][] matrix) {
        for (int[] matrixNew : matrix) {    //loop for printing index's
            for (int j = 0; j < matrixNew.length; j++) {        //loop through matrix length
                System.out.print(matrixNew[j] + "\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {   //file not found exception
        //TestData from CW sheet
        /*int[][] matrix = {{0,58,184,271,378,379},{58,0,167,199,351,382},
            {184,167,0,43,374,370},{271,199,43,0,394,390},{378,351,374,394,0,47},
                {379,382,370,390,47,0}	
        };*/
        //Enter Number of cities (n) 
        //scan in user input using scanner
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the number of cities: ");
        int n = input.nextInt();

        //timing experiment start //print nanotimings to file "timingsTravelTimesImproved"
        PrintWriter pw = new PrintWriter(new File("timingsTravelTimesImproved.csv"));
        //while loop to cap number of matrix at 1000 for timing experiment
        while (n <= 1000) {
            //Print 'n' to the csv file
            pw.println(n);
            int runs = 1; //intialise runs to 1
            //while loop to produce multiple runs
            while (runs < 10) {
                //generate new class
                MinTravelTimes matrixClass = new MinTravelTimes();
                                                    
                int array[][] = randomArray(n);     //generate matrix of size n

                matrixClass.printMatrix(array);   //print matrix to check

                //1st method timing experiment
                //time start
                //long nano_startTime = System.nanoTime();
                //run algorithm on array
                matrixClass.compareMatrix(array);
                //time end
                //long nano_endTime = System.nanoTime();

                //Print the time taken by subtracting the end-time from the start-time 
                //System.out.println("\nTime taken in nano seconds: " + (nano_endTime - nano_startTime));
                //pw.println(nano_endTime - nano_startTime);
                //refinded algorithm timing experiment
                long nano_startTime2 = System.nanoTime();   //start timings
                //run algorithm function with randomly generated matrix (2D Arrays)
                matrixClass.compareMatrixRefinded(array);
                long nano_endTime2 = System.nanoTime();     //end timings
                // Print the time taken by subtracting the end-time from the start-time 
                System.out.println("\nTime taken in nano seconds: "
                        + (nano_endTime2 - nano_startTime2));
                //printwrite the results to .csv
                pw.println(nano_endTime2 - nano_startTime2);
                runs++;     //increment runs by 1
            }
            n += 50;  //increase runs by 50
        }
        pw.close();   //close printwriter
    }

}
