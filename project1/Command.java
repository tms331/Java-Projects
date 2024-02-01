package projectpackage;
/**
 * This class is for all commands the program will handle.
 * @author Thomas Shea, James Artuso
 */
public enum Command {
    //NEED TO IMPLEMENT ALL PRINTS
    ADD     (5, "A", 1, 2, 3,4, -1, -1),
    ADD_FAMILY (5, "AF", 1, 2, 3, 4, -1, -1),
    ADD_PREMIUM (5, "AP", 1, 2, 3, 4, -1, -1),
    REMOVE    (4, "R", 1, 2, 3, -1, -1,-1),
    CHECK_IN     (7, "C", 4, 5, 6, 3, 1, 2),
    CHECK_IN_GUEST (7, "CG", 4, 5, 6, 3, 1, 2),
    DROP (7, "D", 4, 5, 6, 3, 1, 2),
    DROP_GUEST (7, "DG", 4, 5, 6, 3, 1, 2),
    FITNESS_DISPLAY (1,"S", -1, -1,-1, -1, -1, -1),
    PRINT (1, "P", -1, -1, -1, -1, -1, -1),
    PRINT_DATE (1, "PD", -1, -1, -1, -1, -1, -1),
    PRINT_NAME (1, "PN", -1, -1, -1, -1, -1, -1),
    PRINT_COUNTY (1, "PC", -1, -1, -1, -1, -1, -1),
    PRINT_FEE (1, "PF", -1, -1, -1, -1, -1, -1),
    LOAD (1, "L", -1, -1, -1, -1, -1, -1),
    LOAD_MEMBER (1, "LM", -1, -1, -1, -1, -1, -1),
    LOAD_CLASS (1, "LS", -1, -1, -1, -1, -1, -1),
    QUIT (1, "Q", -1, -1, -1, -1, -1, -1),
    EMPTY (-1, " ", -1, -1, -1, -1, -1, -1),
    FAIL (1, "F", -1, -1, -1, -1, -1, -1),
    INVALID (-1, " ", -1, -1, -1, -1, -1, -1);

    private final int length;
    private final String command;
    private final int fnamePos;
    private final int lnamePos;
    private final int dobPos;
    private final int locationPos;
    private final int classNamePos;
    private final int instructorPos;

    /**
     * Constructor for the user input.
     * @param length, length of a command.
     * @param command, command being executed.
     * @param fnamePos, first name.
     * @param lnamePos, last name.
     * @param dobPos, date of birth.
     * @param locationPos, location.
     * @param classNamePos, class name (if check in/out command).
     * @param instructorPos, instructor name (if check in/out command).
     */
    Command(int length, String command, int fnamePos, int lnamePos, int dobPos, int locationPos, int classNamePos, int instructorPos) {
        this.length = length;
        this.command = command;
        this.fnamePos = fnamePos;
        this.lnamePos = lnamePos;
        this.dobPos = dobPos;
        this.locationPos = locationPos;
        this.classNamePos = classNamePos;
        this.instructorPos = instructorPos;
    }

    /**
     * Getter for command, will return command
     * if found to be valid, otherwise invalid.
     * @param inputString, takes the user inputted command.
     * @return command, will return the command if found.
     */
    public static Command getCommand(String inputString){
        for(Command c : values()){
            if(c.command.equals(inputString)) {
                return c;
            }
        }
        return INVALID;
    }

    /**
     * Getter method to return command length.
     * @param command, user inputted command.
     * @return int, command length.
     */
    public static int getCommandLength(Command command){
        return command.length;
    }

    /**
     * Getter method to return first name position.
     * @param command, user inputted command.
     * @return int, first name position.
     */
    public static int getFnamePos(Command command){
        return command.fnamePos;
    }

    /**
     * Getter method to return last name position.
     * @param command, user inputted command.
     * @return int, last name position.
     */
    public static int getLnamePos(Command command){
        return command.lnamePos;
    }

    /**
     * Getter method to return date of birth position.
     * @param command, user inputted command.
     * @return int, date of birth position.
     */
    public static int getDOBPos(Command command){
        return command.dobPos;
    }

    /**
     * Getter method to return location position.
     * @param command, user inputted command.
     * @return int, location position.
     */
    public static int getLocationPos(Command command){
        return command.locationPos;
    }

    /**
     * Getter method to return class name position.
     * @param command, user inputted command.
     * @return int, class name position.
     */
    public static int getClassNamePos(Command command){
        return command.classNamePos;
    }

    /**
     * Getter method to return instructor position.
     * @param command, user inputted command.
     * @return int, instructor position.
     */
    public static int getInstructorPos(Command command){
        return command.instructorPos;
    }

}