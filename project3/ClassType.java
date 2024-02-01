package projectpackage;
/**
 * This class is for the classType enum class.
 * Has three classes, Pilates, Spinning and Cardio.
 * @author Thomas Shea, James Artuso
 */
public enum ClassType {

    PILATES(0),
    SPINNING(1),
    CARDIO(2);


    private final int id;

    ClassType(int id){
        this.id = id;
    }
    /**
     * Method to check if the given class is provided.
     * @param className, user inputted class name.
     * @return boolean, true if class is valid.
     */
    public static boolean isValid(String className){
        try {
            ClassType.valueOf(className.toUpperCase());
            return true;
        }

        catch(Exception IllegalArgumentException){
            return false;
        }
    }
}
