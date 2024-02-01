package projectpackage;
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
     */
    public void addClass(FitnessClass newClass){
        classes[numClasses] = newClass;
        numClasses += 1;
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
     * Method to display class schedules.
     * @return String, returns all fitness classes.
     */
    public String displaySchedule(){
        if(numClasses == 0){
            return "Fitness class schedule is empty.";
        }
        return ("\n-Fitness classes-\n" + this.toString() + "\n-end of class list-\n");
    }

}
