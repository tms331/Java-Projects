package projectpackage;
/**
 This Time enum class is for the time data type, gives time, instructor and name of the fitness classes.
 Time objects have class name, class instructor and time of the class.
 Class is used by another class the "FitnessClass" class to check members into classes.
 There is a constructor to create the time data type and a toString method to create a printable string.
 @author Thomas Shea, James Artuso
 */
public enum Time {
    EMPTY (0),
    MORNING      (930),
    AFTERNOON     (1400),
    EVENING       (1830);
    private final int TIME;

    /**
     Time constructor, has class name, instructor name, time and index.
     Will correctly create time data type and set attributes accordingly.
     @param time, the time that the class begins at
     */
    Time(int time){
        this.TIME = time;
    }

    /**
     Override toString method, to print a better more readable string.
     @return string, returns the string which contains classname, instructor and time.
     */
    @Override
    public String toString(){
        return (String.format("%d:%02d",TIME/100, TIME%100));
    }

}