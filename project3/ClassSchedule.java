package projectpackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class is for the class schedules data type.
 * Contains the loaded fitness classes.
 * @author Thomas Shea, James Artuso
 */
public class ClassSchedule {
    private FitnessClass[] classes;
    private int numClasses;

    /**
     * Constructor which holds number of classes
     * and the classes themselves in Fitness class array.
     */
    public ClassSchedule(){
        numClasses = 0;
        classes = new FitnessClass[15];
    }

    /**
     * Method to add classes to the array.
     * @param newClass, the class being added.
     * @return , return true if class was added.
     */
    public boolean addClass(FitnessClass newClass){
        for(int i = 0; i < numClasses; i++){
            if(newClass.equals(classes[i])){
                return false;
            }
        }

        if(numClasses == classes.length){
            grow();
        }

        classes[numClasses] = newClass;
        numClasses += 1;
        return true;
    }



    /**
     * Method to get the fitness class from classes array.
     * @param testClass, the class being searched for.
     * @return FitnessClass, return the class if found, null otherwise.
     */
    public FitnessClass getFitnessClass(FitnessClass testClass){
        for(int i = 0; i < numClasses; i++){
            if(testClass.equals(classes[i])){
                return classes[i];
            }
        }
        return null;
    }

    /**
     * Method to get all classes from array.
     * @return returns all fitness classes.
     */
    public FitnessClass[] getAllClasses(){
        return classes;
    }

    /**
     * Method to print all classes within the array.
     * @return String, returns the string containing all classes.
     */
    @Override
    public String toString(){
        String returnString = "";
        for(int i = 0; i < numClasses; i++){
            returnString = returnString + classes[i].toString() + "\n" +
                classes[i].displayClass();
        }

        return returnString.trim();
    }

    /**
     * Method to load in classes from file.
     * @param file, the user selected file.
     * @return String, returns class string if executed properly. Otherwise, return rejection message
     */
    public String classLoop(File file) throws FileNotFoundException {
        Scanner fileScanner = new Scanner(file);
        
        while (fileScanner.hasNextLine()) {
            String[] inputArray = fileScanner.nextLine().split(" ");
            if (inputArray.length == 4) {
                ClassType classType = ClassType.valueOf(inputArray[0].toUpperCase());
                Instructor instructor = Instructor.valueOf(inputArray[1].toUpperCase());
                Time classTime = Time.valueOf(inputArray[2].toUpperCase());
                Location loc = Location.valueOf(inputArray[3].toUpperCase());
                FitnessClass addedClass = new FitnessClass(classTime, instructor, loc, classType);
                addClass(addedClass);
            }
        }
        if(numClasses == 0){
            return "No classes added! Check file format!";
        }
        return this.toString();
    }

    /**
     * Method to display class schedules.
     * @return String, returns all fitness classes.
     */
    public String displaySchedule(){
        if(numClasses == 0){
            return "Fitness class schedule is empty.";
        }
        return ("\n-Fitness classes-\n" + this.toString() + "\n-end of class list-\n");
    }

    /**
     * Method to completely remove a member and their guests from all classes
     * @param member Member to be removed
     */
    public void removeAllInstancesOf(Member member){
        for(int i = 0; i < numClasses; i++){
            if( member instanceof Family) {
                boolean drop = true;
                while (drop){
                    drop = classes[i].dropGuest(member);
                }
            }
            classes[i].drop(member);
        }
    }

    /**
     * Helper method for the add class method. This method will copy
     * all elements from the old array into a new one that is 4 elements
     * larger than the old array.
     */
    private void grow(){
        FitnessClass[] tmpArray = new FitnessClass[classes.length + 4];
        for(int i = 0; i < classes.length; i++){
            tmpArray[i] = classes[i];
        }
        classes = tmpArray;
    }

}
