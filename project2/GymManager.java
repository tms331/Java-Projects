package projectpackage;
import java.io.File;
import java.util.Scanner;

/**
 * This class will run the program, continuously taking user input until the user exits the program.
 * The user will add, remove and sort the member database and or the gym class check in database.
 * The class also will provide the user with appropriate error messages based upon their input.
 * @author Thomas Shea, James Artuso
 */
public class GymManager {
    private final MemberDatabase memberDatabaseObj = new MemberDatabase();
    private final ClassSchedule classScheduleObj = new ClassSchedule();

    /**
     * Method to check if the user inputted dates are valid.
     * @param inputString, user input stored in an array.
     * @return boolean, true if dates are valid, false otherwise.
     */
    private boolean hasValidDate(String[] inputString){
        Command command = Command.getCommand(inputString[0]);
        String DOBString = inputString[Command.getDOBPos(command)];

        if(Date.isValidDate(DOBString)){
            Date testDate = new Date(DOBString);
            if(testDate.isValid()){
                if(command == Command.REMOVE || testDate.isEightteen()){
                    return true;
                }else{
                    if(!testDate.isBorn()){
                        System.out.println(("DOB " + testDate.toString() + ": cannot be today or a future date!"));
                    }else{
                        System.out.println("DOB " + testDate.toString() + ": must be 18 or older to join!");
                    }
                }
            }else{
                System.out.println("DOB " + testDate.toString() + ": invalid calendar date!");
            }
        }else{
            System.out.println("Please use correct input format: ##/##/####");
        }
        return false;
    }

    /**
     * Checks to see if the location the user is
     * checking into is invalid or not.
     * @param inputString, the user input stored in an array.
     * @return boolean, true if the location is valid, false otherwise.
     */
    private boolean hasValidLocation(String[] inputString){
        Command command = Command.getCommand(inputString[0]);

        if(Command.getLocationPos(command) != -1) {
            String locationString = inputString[command.getLocationPos(command)];
            if (!Location.isValid(locationString.toUpperCase())) {
                System.out.println(locationString + " - invalid location!");
                return false;
            }
        }
        return true;
    }

    /**
     * Method will check that the classes instructor is valid
     * and or does actually teach the class.
     * @param instructorString, the instructor being checked.
     * @return false if instructor does not exist, true otherwise.
     */
    private boolean isValidInstructor(String instructorString){
        if(!Instructor.isValid(instructorString.toUpperCase())){
            System.out.println(instructorString + " - instructor does not exist.");
            return false;
        }
        return true;
    }

    /**
     * Will make sure member is checking in at an allowed location
     * @param testMember, the member checking in.
     * @param testClass, the class the member is checking into.
     * @return boolean, true if valid location, false otherwise.
     */
    private boolean validLocationSelection(Member testMember, FitnessClass testClass){
        if(testMember.getLocation() != testClass.getLocation()){
            String outputString = "%s checking in %s - %s location restriciton";
            if(!(testMember instanceof Family)) { //IE it is a member
                System.out.println(String.format(outputString, testMember.getFullName(), testClass.getLocation(), "standard membership"));
            }else{
                System.out.println(String.format(outputString, testMember.getFullName() + " guest", testClass.getLocation(), "guest"));
            }
            return false;
        }
        return true;
    }

    /**
     * Method to add users to the array, will give an error if
     * trying to add member that already exists in the array.
     * @param member - member being added
     */
    private void addMember(Member member){
        if (member == null) {
            return;
        }
            if (memberDatabaseObj.add(member)) {
                System.out.println(member.getFullName() + " added.");

            }else{
                System.out.println(member.getFullName() +
                        " is already in the database.");
            }
    }

    /**
     * Method to remove members from the database.
     * Will give an error if trying to remove non-existent member.
     * @param member - member being removed
     */
    private void removeMember(Member member){
        if (memberDatabaseObj.remove(member)) {
            System.out.println(member.getFullName() + " removed.");
        }else{
            System.out.println(member.getFullName() + " not in the database");
        }
    }

    /**
     * Method to load legacy members from given file.
     */
    private void loadMembers() {
        try {
            File file = new File("src/projectpackage/memberList.txt");
            Scanner fileScanner = new Scanner(file);

            System.out.println("\n-list of members loaded-");
            while (fileScanner.hasNextLine()) {
                String[] inputArray = fileScanner.nextLine().split(" ");

                if (inputArray.length == 5) {
                    String fname = inputArray[0];
                    String lname = inputArray[1];
                    Date dob = new Date(inputArray[2]);
                    Date expire = new Date(inputArray[3]);
                    Location loc = Location.valueOf(inputArray[4].toUpperCase());
                    memberDatabaseObj.add(new Member(fname, lname, dob, expire, loc));
                }
            }
            System.out.println(memberDatabaseObj.toString());
            System.out.println("-end of list-\n");
            fileScanner.close();
        }
        catch(Exception FileNotFoundException){
            System.out.println("memberList.txt not found!!!\n Members not loaded!!!");
        }
    }

    /**
     * Method to load classes from given file.
     */
    private void loadClasses() {
        try {
            File file = new File("src/projectpackage/classSchedule.txt");
            Scanner fileScanner = new Scanner(file);
            System.out.println("\n-Fitness classes loaded-");

            while(fileScanner.hasNextLine()){
                String[] inputArray = fileScanner.nextLine().split(" ");
                if(inputArray.length == 4) {
                    ClassType classType = ClassType.valueOf(inputArray[0].toUpperCase());
                    Instructor instructor = Instructor.valueOf(inputArray[1].toUpperCase());
                    Time classTime = Time.valueOf(inputArray[2].toUpperCase());
                    Location loc = Location.valueOf(inputArray[3].toUpperCase());
                    FitnessClass addedClass = new FitnessClass(classTime, instructor, loc, classType);
                    classScheduleObj.addClass(addedClass);
                }
            }
            System.out.println(classScheduleObj.toString());
            System.out.println("-end of class list-\n");
            fileScanner.close();
        }
        catch(Exception FileNotFoundException){
            System.out.println("classSchedule.txt not found!!!\n Class not loaded!");
        }
    }

    /**
     * Helper method to process user input, check if there are members in the database,
     * and choose correct print based on user input.
     * @param command, Takes the user inputted command.
     */
    private void printController(Command command){

        if(memberDatabaseObj.getSize() == 0){
            System.out.println("Member database is empty!");
            return;
        }
        System.out.println(""); //Adds buffer above all print cases
        switch(command){
            case PRINT  -> memberDatabaseObj.print();
            case PRINT_COUNTY -> memberDatabaseObj.printByCounty();
            case PRINT_NAME -> memberDatabaseObj.printByName();
            case PRINT_DATE -> memberDatabaseObj.printByExpirationDate();
            case PRINT_FEE -> memberDatabaseObj.printWithFees();
        }
        System.out.println(""); //Adds buffer below all print cases
    }

    /**
     * Method will add user based upon user input. Will add
     * standard, premium and family member types.
     * @param inputArray, user input stored in array.
     */
    private void addController(String[] inputArray){
        Command command = Command.getCommand(inputArray[0]);

        String fname = inputArray[Command.getFnamePos(command)];
        String lname = inputArray[Command.getLnamePos(command)];
        String dob = inputArray[Command.getDOBPos(command)];
        Location location = Location.valueOf(inputArray[Command.getLocationPos(command)].toUpperCase());


        Member addedMember = new Member(fname, lname, dob, location);

        switch(command){
            case ADD_FAMILY -> addedMember = new Family(fname, lname, dob, location, 1);
            case ADD_PREMIUM  -> addedMember = new Premium(fname, lname, dob, location, 3);
        }
        addMember(addedMember);
    }

    /**
     * Method to check which load command is being executed.
     * @param command, user inputted command.
     */
    private void loadController(Command command){
        switch(command){
            case LOAD_CLASS -> loadClasses();
            case LOAD_MEMBER -> loadMembers();
        }
    }

    /**
     * Method to remove members from the database.
     * Will give an error if trying to remove a member
     * that is not in the database.
     * @param inputArray, user input stored in array.
     */
    private void removeMemberController(String[] inputArray){
        Command command = Command.getCommand(inputArray[0]);
        if(memberDatabaseObj.getSize() == 0){
            System.out.println("Member database is empty!");
            return;
        }

        String fname = inputArray[Command.getFnamePos(command)];
        String lname = inputArray[Command.getLnamePos(command)];
        String dob = inputArray[Command.getDOBPos(command)];

        Member removedMember = new Member(fname, lname, dob);
        removeMember(removedMember);
    }

    /**
     * Method will check if the class given is non-existent,
     * will give an error if it is.
     * @param testClass, the class being checked.
     * @return FitnessClass, return the class if real, null otherwise.
     */
    private FitnessClass classCheck(FitnessClass testClass){
        FitnessClass realClass = classScheduleObj.getFitnessClass(testClass);
        if(realClass == null){
            System.out.println(testClass.getClassType() + " by " + testClass.getInstructor() + " does not exist at " + Location.getTown(testClass.getLocation()));
            return null;
        }
        return realClass;
    }

    /**
     * Method to help check members into classes, will give an
     * appropriate error if user input is incorrect or membership is expired.
     * @param newMember, the member checking into a class.
     * @param realClass, the class the member is checking into.
     */
    private void checkInHelper(Member newMember, FitnessClass realClass){
        if(newMember.isExpired()){
            System.out.println(String.format("%s %s membership expired", newMember.getFullName(), newMember.getDob()));
            return;
        }
        if(!(newMember instanceof Family) && !validLocationSelection(newMember, realClass)){
            return;
        }
        realClass.checkIn(newMember);
        String outputString = "%s checked in %s";
        System.out.println(String.format(outputString, newMember.getFullName(), realClass));
        System.out.println(realClass.displayClass());
    }

    /**
     * Helper method to drop the user from a class or
     * give them an appropriate error message.
     * @param newMember, the member dropping the class.
     * @param realClass, the class being dropped.
     */
    private void dropHelper(Member newMember, FitnessClass realClass){
        if(!realClass.contains(newMember)){
            System.out.println(newMember.getFullName() + " did not check in.");
            return;
        }
        realClass.drop(newMember);
        String outputString = "%s done with the class.";
        System.out.println(String.format(outputString, newMember.getFullName()));
    }

    /**
     * Method to check if the class inputted exists.
     * @param classtype, the class being checked.
     * @return boolean, true if the class exists, false otherwise.
     */
    private boolean isValidClassType(String classtype){
        if(!ClassType.isValid(classtype.toUpperCase())){
            System.out.println(classtype + " - class does not exist");
            return false;
        }
        return true;
    }

    /**
     * Method will check if there is a time conflict between classes.
     * @param testMember, member checking into a class.
     * @param testClass, the class being checked into
     * @return boolean, will return true if there is no time conflict, false otherwise.
     */
    private boolean classTimeCheck(Member testMember, FitnessClass testClass){
        FitnessClass[] allClasses = classScheduleObj.getAllClasses();
        testClass = classScheduleObj.getFitnessClass(testClass);

        for(int i = 0; i < allClasses.length; i++){
            if(allClasses[i].contains(testMember) && allClasses[i].getTime() == testClass.getTime()){
                if(!testClass.equals(allClasses[i])){
                    System.out.println("Time conflict - " + testClass.toStringFullTown());
                }else{
                    System.out.println(testMember.getFullName() + " already checked in.");
                }
                return false;
            }
        }
        return true;
    }

    /**
     * Method to check if member is in the database.
     * @param inputMember, the member to be searched for.
     * @return member, will return the member if found.
     */
    private Member memberCheck(Member inputMember){
        Member testMember = memberDatabaseObj.getMember(inputMember);
        if(testMember == null){
            System.out.println(inputMember.getFullName() + " " + inputMember.getDob() + " is not in the database.");
        }
        return testMember;
    }

    /**
     * Method to check validity of user input. Will then call upon
     * another helper method based upon the command being executed.
     * @param inputArray, user input stored in an array.
     */
    private void classHelper(String[] inputArray){
        Command command = Command.getCommand(inputArray[0]);

        if(!isValidInstructor(inputArray[Command.getInstructorPos(command)])||
        !isValidClassType(inputArray[Command.getClassNamePos(command)])){
            return;
        }

        ClassType classType = ClassType.valueOf(inputArray[Command.getClassNamePos(command)].toUpperCase());
        String fname = inputArray[Command.getFnamePos(command)];
        String lname = inputArray[Command.getLnamePos(command)];
        String dob = inputArray[Command.getDOBPos(command)];
        Instructor instructor = Instructor.valueOf(inputArray[Command.getInstructorPos(command)].toUpperCase());
        Location loc = Location.valueOf(inputArray[Command.getLocationPos(command)].toUpperCase());

        FitnessClass testClass = classCheck(new FitnessClass(Time.MORNING, instructor, loc, classType));
        Member testMember = memberCheck(new Member(fname, lname, dob));

        if(testMember == null || testClass == null){
            return;
        }

        if(command == Command.CHECK_IN){
            if(classTimeCheck(testMember, testClass)){
                checkInHelper(testMember, testClass);
            }
        }else if(command == Command.DROP){
            dropHelper(testMember, testClass);
        }else{
            guestHelper(command, testMember, testClass);
        }
    }

    /**
     * Method to check in and check out guests. Will adjust guest passes accordingly.
     * Will print an appropriate error if user is out of guest passes or user
     * attempts to check out of class they have not checked into.
     * @param command, the command being attempted.
     * @param testMember, the member that the operation is being performed on.
     * @param testClass, the class the user is attempting to check in or out of.
     */
    private void guestHelper(Command command, Member testMember, FitnessClass testClass){
        Family familyMember = null;
        if(!(testMember instanceof Family)){
            System.out.println("Standard membership - guest check-in is not allowed.");
            return;
        }else{
            familyMember = (Family) testMember;
        }

        if(command == Command.DROP_GUEST){
            if(testClass.dropGuest(familyMember)){
                System.out.println(testMember.getFullName() + " Guest done with the class.");
            }else{
                System.out.println(testMember.getFullName() + " Guest not enrolled in " + testClass);
            }
            return;
        }

        //Now, if it wasn't drop it must then be add
        if(validLocationSelection(familyMember, testClass)) {
            if (testClass.addGuest(familyMember)) {
                System.out.println(familyMember.getFullName() + " (guest) checked in " + testClass);
                System.out.println(testClass.displayClass());
            } else {
                System.out.println(familyMember.getFullName() + " ran out of guest passes.");
            }
        }
    }

    /**
     * Helper method to check if the user input is of the correct length.
     * @param inputArray, user input stored as an array
     * @return returnBool, returns true if the input has the correct number of tokens.
     */
    private boolean correctInputLength(String[] inputArray){
        Command command = Command.getCommand(inputArray[0]);
        return inputArray.length == Command.getCommandLength(command);
    }

    /**
     * Method to check if command exists and that the command is the correct
     * length meaning the command was input with the correct fields.
     * @param inputArray, user input stored as an array.
     * @return command, returns the command if valid.
     */
    private Command commandSanitize(String[] inputArray){
        String inputCommand = inputArray[0];
        Command command = Command.EMPTY;

        if (!inputCommand.isBlank()) {
            command = Command.getCommand(inputCommand);
            if (!correctInputLength(inputArray) && command != Command.INVALID) {
                command = Command.FAIL;
            }
            if (Command.getCommandLength(command) > 1) {
                if (!hasValidDate(inputArray) ||
                        !hasValidLocation(inputArray)) {
                    command = Command.EMPTY;
                }
            }
        }
        return command;
    }

    /**
     * Method that will continuously take user input until the user exits the program.
     * Scans in user input, splits and loads it into a string array called "inputArray"
     * Has switch statements based upon which command the user inputted and will call
     * upon the appropriate method from MemberDatabase class. Will let the user know
     * if they attempted an invalid or non-existent command.
     */
    public void run () {
        System.out.println("Gym Manager running...\n");
        Scanner input = new Scanner(System.in);
        while(true) {
            String userInput = input.nextLine();
            String[] inputArray = userInput.split(" ");
            Command command = commandSanitize(inputArray);

            switch (command) {
                case ADD, ADD_FAMILY, ADD_PREMIUM -> addController(inputArray);
                case REMOVE -> removeMemberController(inputArray);
                case LOAD_CLASS, LOAD_MEMBER -> loadController(command);
                case PRINT, PRINT_COUNTY, PRINT_DATE , PRINT_NAME, PRINT_FEE -> printController(command);
                case FITNESS_DISPLAY -> System.out.println(classScheduleObj.displaySchedule());
                case CHECK_IN, DROP, CHECK_IN_GUEST, DROP_GUEST -> classHelper(inputArray);
                case QUIT -> {
                    System.out.println("Gym Manager terminated.");
                    input.close();
                    System.exit(0);
                }
                case FAIL -> {
                    System.out.println("Bad Input. Please follow correct format.");
                }
                default -> {
                    if(command != Command.EMPTY) {
                        System.out.println(inputArray[0] + " is an invalid command!");
                    }
                }
            }
        }
    }

}