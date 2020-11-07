package GradeAnalysisApp;

/*
Programmer: Robert Kacso
Student ID: B00123508
Note: Software Development 2, Assignment 1 - Grade analysis program
Due date: 23/03/2020
*/

import java.util.Scanner;
import java.util.*;

public class GradeAnalysisApp {
	
	//declaring and creating instance of Scanner object
    static Scanner in = new Scanner(System.in);
    public static void main(String[] args){
    	
        int element; //declare variable "element" which will be used to find String student
        String student; //declare variable String student

        System.out.println("         \uD83E\uDD13 \uD83E\uDD13 WELCOME \uD83E\uDD13 \uD83E\uDD13 "); //"nerd face" emoji unicode
        System.out.println("Please enter the number of students in this class.");
        System.out.print("A class can have a minimum of 2 and a maximum of 25 students. " +
                "\nPlease input the correct number of students: "); //prompting user to input the number of students in the class

        int input = in.nextInt(); //declaring a variable type int and defining it to be equal to the users input
        System.out.println(); // space of 1 row

        /*======================================== 1st ATTEMPT!!! ========================================*/
        // This works but it doesn't catch exceptions, like inputting a letter instead of a number
        // "while" loop to prompt user to input an integer between 2 and 25
        /*while(input<2 || input>25)
        {

            System.out.print("A class can have a minimum of 2 and a maximum of 25 students. Please input the correct number of students: ");
            input = in.nextInt();
            System.out.println();
        }*/
        /*================================================================================================*/

        /*======================================== 2nd ATTEMPT!!! ========================================*/
        //while researching how to introduce properly a try/catch in a "while" loop, I came over this method that was using a boolean
        //Ref: https://stackoverflow.com/questions/29490474/java-try-catch-statement-inside-while-loop
        //This works fine and the boolean stops the loop BUT...
        //... THIS METHOD doesn't loop back to take the details of a second student if you don't put in the correct grade details for the 1st student
        /*int number;
        boolean end = false;

        while (!end){
        
            System.out.print("A class can have a minimum of 2 and a maximum of 25 students. Please input the correct number of students: ");

            try{
                number = Integer.parseInt(in.next());
            }catch (Exception e){
                System.out.println("INCORRECT INPUT!!! IT'S NOT AN INTEGER!!!");
                continue; //skip loop
            }

            if ((number >= 2) && (number <= 25)){
            
                end = true;
            }
        }*/
        /*================================================================================================*/

        /*======================================== 3rd ATTEMPT!!! ========================================*/
        // This is the one. Works fine. A combination of the first 2 attempts.
        while(input<2 || input>25){

            System.out.print("A class can have a minimum of 2 and a maximum of 25 students. " +
                    "\nPlease input the correct number of students: ");

            try{
                input = Integer.parseInt(in.next());
            }catch (Exception e){
                System.out.println();
                System.out.println("#################################################################");
                System.out.println("         \uD83D\uDEAB INCORRECT INPUT!!! IT'S NOT AN INTEGER!!! \uD83D\uDEAB "); //"prohibited" emoji unicode
                System.out.println("#################################################################");
                continue; //skip loop
            }

            System.out.println();
        }

        String[] studentName = new String[input];  //declare, create & set size of array
        double[] grade = new double[input];    //declare, create & set size of array

        enterInfo(studentName, grade);  //method call

        //table of option for the user to choose from
        System.out.println("PLEASE CHOOSE FROM ONE OF THE FOLLOWING OPTIONS");
        System.out.println();
        System.out.println("##############################################################");
        System.out.println(" \u0031\u20E3 Press 1 to display average class grade."); // "key cap: 1" emoji unicode
        System.out.println(" \u0032\u20E3 Press 2 to display lowest class grade."); // "key cap: 2" emoji unicode
        System.out.println(" \u0033\u20E3 Press 3 to display highest class grade."); // "key cap: 3" emoji unicode
        System.out.println(" \u0034\u20E3 Press 4 to sort & display the grades in ascending order."); // "key cap: 4" emoji unicode
        System.out.println(" \u0035\u20E3 Press 5 to search for an individual student by name."); // "key cap: 5" emoji unicode
        System.out.println("##############################################################");
        System.out.println(" \u274C Otherwise press X to quit the program \u274C "); //"red X" emoji unicode
        System.out.println("##############################################################");
        System.out.println();

        //declare variable char to input an option of the switch case
        char option = in.next().toUpperCase().charAt(0);

        //"while" loop to run program until 'X' is selected to terminate program
        while (option != 'X'){
        	
            //"switch" statement to choose from the different options
            switch (option){
            
                case '1':
                    average(grade); //calling method in case 1
                    break;
                case '2':
                    lowestGrade(grade);//calling method in case 2
                    break;
                case '3':
                    highestGrade(grade);//calling method in case 3
                    break;
                case '4':
                    System.out.print("All the class grades in ascending order are: ");
                    sort(grade);//calling method in case 4
                    break;
                case '5':
                    System.out.print("Search for student: "); //prompting user to input student name
                    student = in.next().toUpperCase(); //declaring "string" student to be equal to users input
                    element = studentSearch(studentName, student); //calling method in case 5

                    // if/else statement to output the result of the "studentSearch" method in different cases
                    if (element != -1){
                    	
                        System.out.print("The student's name was FOUND \uD83D\uDC4D \nThe student is part of this class (array)");
                    
                    }else{
                    	
                        System.out.print("The student's name was NOT FOUND \uD83D\uDC4E \nThe student is NOT part of this class (array)");
                    }
                    System.out.println();
                    break;
                default:
                    System.out.println("###########################################################");
                    System.out.println("         \uD83D\uDEAB NO SUCH OPTION \uD83D\uDEAB ");
                    System.out.println("Please choose option 1-5 only, or press X to terminate the program: ");
                    System.out.println("###########################################################");
                    break;
            }
            //message to prompt user to choose a different option or to terminate the program
            System.out.println();
            System.out.println("##############################################################");
            System.out.println("Choose another option  \u0031\u20E3 - \u0035\u20E3 " +
                    "\nor press X to terminate program. \u274C "); //"red X" emoji unicode
            System.out.println("##############################################################");
            System.out.println();
            //input another option after executing a previous switch case
            option = in.next().toUpperCase().charAt(0);
        }
        //Goodbye message when terminating the program
        System.out.println("Thank you for using my program.");
        System.out.println();
        // unicode for waving hand * 4 with "GOODBYE" message in between
        System.out.println("         \uD83D\uDC4B \uD83D\uDC4B GOODBYE \uD83D\uDC4B \uD83D\uDC4B");
    }

    //method to enter info
    private static void enterInfo(String[] tempName, double[] tempGrade){
    	
        for(int i = 0; i < tempName.length; i++) {  //"for" loop to populate arrays
            
        	if (in.hasNextLine()){
        		
                in.nextLine();
            }
            System.out.print((i+1)  +". Student's name: " );
            tempName[i] = in.nextLine();

            boolean num = false;
            while (!num){
            	
                System.out.print((i+1)+ ". " + tempName[i]+"'s" + " grade: ");
                try{
                	
                    tempGrade[i]=in.nextDouble();
                }catch (Exception  e) {
                	
                    in.nextLine();
                }
                
                if (tempGrade[i] >= 0 && tempGrade[i] <= 100){
                	
                    num=true;
                } else{
                	
                        System.out.println("###########################################################");
                        System.out.println("         \uD83D\uDEAB INVALID ENTRY!!! " +
                                "           \nPlease input a number between 1 and 100");
                        System.out.println("###########################################################");
                    }
            }

            System.out.println();
        }

    }

    //method to calculate average
    private static void average(double[] tempGrade){
    	
        double total = 0;
        //enhanced "for" loop recommended by the IDE instead of
        for (double i : tempGrade){
        	
            total = total + i;
        }
        //normal "for" loop left in to be aware of the options
         /*for (int i=0; i<grade.length; i++){
          * 
            total = total + grade[i];
        }*/
        double average = total / tempGrade.length;
        System.out.println("The average class grade is: " + average);
    }

    //method to find lowest grade
    private static void lowestGrade(double[] tempGrade){
    	
        /*================================================================================================*/
        //sorting the array as we learned in class
        /*int hold; //temporary holding area for swap

        for(int pass = 1; pass < grade.length; pass++) //passes as many times as necessary to sort array
            for(int i = 0; i < grade.length-1; i++) //one pass

                if(grade[i] > grade[i+1]) //one comparison{
                
                    hold = (int) grade[i];
                    grade[i] = grade[i+1];
                    grade[i+1] = hold;
                }*/
        /*================================================================================================*/

        // sorting the array using the util.Arrays library as I learned from http://zparacha.com/minimum-maximum-array-value-java
        Arrays.sort(tempGrade);
        System.out.println("The lowest grade in the class is: " +tempGrade[0]);
    }

    //method to find highest grade
    private static void highestGrade(double[] tempGrade){
    	
        // sorting the array using the util.Arrays library as I learned from http://zparacha.com/minimum-maximum-array-value-java
        Arrays.sort(tempGrade);
        System.out.println("The highest grade in the class is: " +tempGrade[tempGrade.length - 1]);
    }

    //method to sort grade array in ascending order
    public static void sort(double[] tempGrade){
    	
        // sorting the array using the util.Arrays library as I learned from http://zparacha.com/minimum-maximum-array-value-java
        Arrays.sort(tempGrade);
        //enhanced for loop recommended by IDE
        for (double i : tempGrade){
        	
            System.out.print(i + ", ");
        }
        System.out.println();
    }

    //method to perform a binary search for an individual student's name
    public static int studentSearch(String[] studentName, String key){
    	
        Arrays.sort(studentName);
        int first = 0;
        int last  = studentName.length;

        while (first < last){
        	
            int mid = first + ((last - first) / 2);
            if (key.compareTo(studentName[mid].toUpperCase()) < 0){
            	
                last = mid;
            } else if (key.compareTo(studentName[mid].toUpperCase()) > 0){
            	
                first = mid + 1;
            }
            else {
            	
                return mid;
            }
        }
        return - 1;
    }
    /*================================================================================================*/
    // a different way to search for string in an array, showed to me by John Haughton (student number B00123655)
    // John's way, another method for displayStudent is required
    /*
    private static String studentSearch(String[] studentName, double[] grade){
    
        //Method find Names Matching input string
        //Going to return the formatted string of any matching results
        //Going to change all to upper case to make searching easier
        String SearchStr,OutStr="";

        System.out.println("What name would you like to search for");
        SearchStr=in.next().toUpperCase();
        for (int x = 0; x < studentName.length; x++ ){
        
            if (studentName[x].toUpperCase().contains(SearchStr)){
            
                //if there is a match create or append to OutStr
                OutStr=OutStr+displayStudent(studentName[x],grade[x]);
            }
        }

        return OutStr;
    }*/

}
