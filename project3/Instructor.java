package projectpackage;
/**
 * This class is for the instructor type, which has name attribute.
 * @author Thomas Shea, James Artuso
 */
public enum Instructor {
    JENNIFER ("Jennifer"),
    KIM ("Kim"),
    DENISE ("Denise"),
    DAVIS ("Davis"),
    EMMA ("Emma");

    private final String name;

    /**
     * Constructor to make instructor that has name attribute.
     * @param name, name of instructor given.
     */
    Instructor(String name){
        this.name = name;
    }

    /**
     * Method to check if the given instructor name is an
     * actual instructor in the system.
     * @param instructorName, user inputted location name.
     * @return boolean, true if location name is valid.
     */
    public static boolean isValid(String instructorName){
        Instructor[] instructors = Instructor.values();
        for(int i = 0; i < instructors.length; i++){
            if(instructors[i].name.equalsIgnoreCase(instructorName)){
                return true;
            }
        }
        return false;
    }
}
