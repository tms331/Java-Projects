package projectpackage;
import java.io.File;
import java.time.LocalDate;
import java.util.Objects;
import java.util.regex.Pattern;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;
import javafx.collections.FXCollections;
import javafx.stage.FileChooser;

/**
 * This class will control the gui that the user uses. It sanitizes user input
 * for the model to use
 * The class also will provide the user with appropriate error messages based upon their input.
 * @author Thomas Shea, James Artuso
 */
public class GymManagerController {
    private final MemberDatabase memberDatabaseObj = new MemberDatabase();
    private final ClassSchedule classScheduleObj = new ClassSchedule();
    @FXML
    private TextField fnameField;
    @FXML
    private TextField lnameField;
    @FXML
    private DatePicker dobField;
    @FXML
    private TextArea outputText;
    @FXML
    private GridPane memberLocationPane;
    @FXML
    private GridPane membershipTypePane;
    @FXML
    private TextField loadMembersText;
    @FXML
    private TextField loadClassesText;

    @FXML
    private GridPane classLocationPane;
    @FXML
    private TextField instructorName;
    @FXML
    private TextField className;

    public ChoiceBox<String> dropDownMenu;
    ObservableList<String> dropDownMenuList = FXCollections.observableArrayList
            ("Print", "Print by County", "Print by Expiration Date", "Print by Name", "Print with Fees", "Display Classes");

    /**
     * Method to initialize items in the drop-down menu
     * Allows user to pick print commands from menu.
     */
    @FXML
    protected void initialize() {
        dropDownMenu.setItems(dropDownMenuList);
    }

    /**
     * Method to add members of different membership types to the member array.
     * Will give error messages if user does not give correct input in the data fields.
     */
    @FXML
    protected void onAddButtonClick() {
        String fname = getValidNameInput(fnameField);
        String lname = getValidNameInput(lnameField);
        Date dateOfBirth = getValidDateInput(dobField);
        Location addLocation = getValidLocationInput(memberLocationPane);

        if(fname == null || lname == null || dateOfBirth == null || !hasValidDate(dateOfBirth, false) || addLocation == null){
            return;
        }if (Pattern.compile("[0-9]").matcher(fname).find() ||
            Pattern.compile("[0-9]").matcher(lname).find()){
        display("First and last names cannot contain numbers.");
        return;
    }

        ObservableList<Node> memberships = membershipTypePane.getChildren();
        int selectedType = -1;
        for(int i = 0; i < membershipTypePane.getColumnCount(); i++){
            RadioButton button = (RadioButton) memberships.get(i);
            if(button.isSelected()){
                selectedType = i;
                i = membershipTypePane.getColumnCount();
            }
        }
        if(selectedType == -1){
            display("Please select a membership type");
            return;
        }
        Command membershipType = Command.ADD;
        switch(selectedType){
            case 1 -> membershipType = Command.ADD_FAMILY;
            case 2 -> membershipType = Command.ADD_PREMIUM;
        }
        addController(membershipType, fname, lname, dateOfBirth, addLocation);
    }
    /**
     * Method to remove members from the array.
     * Will give user error if data fields are filled out incorrectly.
     */
    @FXML
    protected void onRemoveButtonClick() {
        String fname = getValidNameInput(fnameField);
        String lname = getValidNameInput(lnameField);
        Date dateOfBirth = getValidDateInput(dobField);

        if(fname == null || lname == null || dateOfBirth == null || !hasValidDate(dateOfBirth, true)){
            return;
        }

        removeMemberController(fname, lname, dateOfBirth);
    }

    /**
     * Method to handle member load button click. Checks to make sure file is selected.
     * Calls upon other method in member-database to load members.
     */
    @FXML
    protected void onLoadMemberClick(){
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            loadMembers(selectedFile);
            loadMembersText.setText("Members Loaded from  " + selectedFile.getName());
        }
        else{
            display("File not selected");
        }
    }

    /**
     * Method to handle class load button click. Checks to make sure file is selected.
     * Calls upon other method in member-database to load members.
     */
    @FXML
    protected void onLoadClassesClick(){
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            loadClasses(selectedFile);
            loadClassesText.setText("Classes Loaded from  " + selectedFile.getName());
        }
        else{
            display("File not selected");
        }
    }

    /**
     * Method to check members into the appropriate gym classes.
     */
    @FXML
    protected void onCheckInButtonClick(){
        String fname = getValidNameInput(fnameField);
        String lname = getValidNameInput(lnameField);
        Date dateOfBirth = getValidDateInput(dobField);
        Location loc = getValidLocationInput(classLocationPane);
        Instructor instructor = getValidInstructorInput(instructorName);
        ClassType classtype = getValidClassType(className);

        if(fname == null || lname == null || dateOfBirth == null || !hasValidDate(dateOfBirth, true)
        || loc == null || instructor == null || classtype == null){
            return;
        }

        FitnessClass testClass = classCheck(new FitnessClass(Time.MORNING, instructor, loc, classtype));
        Member testMember = memberCheck(new Member(fname, lname, dateOfBirth.toString()));

        if(testMember == null || testClass == null){
            return;
        }
        if(classTimeCheck(testMember, testClass)) {
            checkInHelper(testMember, testClass);
        }
    }

    /**
     * Method to drop members from the selected class.
     */
    @FXML
    protected void onDropButtonClick(){
        String fname = getValidNameInput(fnameField);
        String lname = getValidNameInput(lnameField);
        Date dateOfBirth = getValidDateInput(dobField);
        Location loc = getValidLocationInput(classLocationPane);
        Instructor instructor = getValidInstructorInput(instructorName);
        ClassType classtype = getValidClassType(className);

        if(fname == null || lname == null || dateOfBirth == null || !hasValidDate(dateOfBirth, true)
                || loc == null || instructor == null || classtype == null){
            return;
        }

        FitnessClass testClass = classCheck(new FitnessClass(Time.MORNING, instructor, loc, classtype));
        Member testMember = memberCheck(new Member(fname, lname, dateOfBirth.toString()));

        if(testMember == null || testClass == null){
            return;
        }
        dropHelper(testMember, testClass);
    }

    /**
     * Method to check in guests if the member has guest passes.
     */
    @FXML
    protected void onGuestCheckInButtonClick(){
        String fname = getValidNameInput(fnameField);
        String lname = getValidNameInput(lnameField);
        Date dateOfBirth = getValidDateInput(dobField);
        Location loc = getValidLocationInput(classLocationPane);
        Instructor instructor = getValidInstructorInput(instructorName);
        ClassType classtype = getValidClassType(className);

        if(fname == null || lname == null || dateOfBirth == null || !hasValidDate(dateOfBirth, true)
                || loc == null || instructor == null || classtype == null){
            return;
        }

        FitnessClass testClass = classCheck(new FitnessClass(Time.MORNING, instructor, loc, classtype));
        Member testMember = memberCheck(new Member(fname, lname, dateOfBirth.toString()));

        if(testMember == null || testClass == null){
            return;
        }

        Family familyMember;
        if(!(testMember instanceof Family)){
            display("Standard membership - guest check-in is not allowed.");
            return;
        }else{
            familyMember = (Family) testMember;
        }

        if(validLocationSelection(familyMember, testClass)) {
            if (testClass.addGuest(familyMember)) {
                display(familyMember.getFullName() + " (guest) checked in " + testClass);
                display(testClass.displayClass());
            } else {
                display(familyMember.getFullName() + " ran out of guest passes.");
            }
        }
    }
    /**
     * Method that drops guests that have been checked in.
     * Will give appropriate error messages.
     */
    @FXML
    protected void onGuestDropButtonClick(){
        String fname = getValidNameInput(fnameField);
        String lname = getValidNameInput(lnameField);
        Date dateOfBirth = getValidDateInput(dobField);
        Location loc = getValidLocationInput(classLocationPane);
        Instructor instructor = getValidInstructorInput(instructorName);
        ClassType classtype = getValidClassType(className);

        if(fname == null || lname == null || dateOfBirth == null || !hasValidDate(dateOfBirth, true)
                || loc == null || instructor == null || classtype == null){
            return;
        }

        FitnessClass testClass = classCheck(new FitnessClass(Time.MORNING, instructor, loc, classtype));
        Member testMember = memberCheck(new Member(fname, lname, dateOfBirth.toString()));

        if(testMember == null || testClass == null){
            return;
        }
        Family familyMember;
        if(!(testMember instanceof Family)){
            display("Standard membership - guest check-in is not allowed.");
            return;
        }else{
            familyMember = (Family) testMember;
        }

        if(testClass.dropGuest(familyMember)){
            display(testMember.getFullName() + " Guest done with the class.");
        }else{
            display(testMember.getFullName() + " Guest not enrolled in " + testClass);
        }
    }

    /**
     * Method to execute print methods from drop down menu.
     */
    @FXML
    protected void executeCommandButtonClick(){
        if(Objects.equals(dropDownMenu.getValue(), "Print")) {
            display("-list of members-\n" + memberDatabaseObj.print() + "\n-end of list-\n");
        }else if(Objects.equals(dropDownMenu.getValue(), "Print by County")){
            display("-list of members sorted by county and zipcode-\n" + memberDatabaseObj.printByCounty() + "\n-end of list-\n");
        }else if(Objects.equals(dropDownMenu.getValue(), "Print by Expiration Date")){
            display("-list of members sorted by membership expiration date-\n" + memberDatabaseObj.printByExpirationDate() +
                    "\n-end of list-\n");
        }else if(Objects.equals(dropDownMenu.getValue(), "Print by Name")){
            display("-list of members sorted by last name, and first name-\n" + memberDatabaseObj.printByName() +
                    "\n-end of list-\n");
        }else if(Objects.equals(dropDownMenu.getValue(), "Print with Fees")){
            display("-list of members with membership fees-\n" + memberDatabaseObj.printWithFees() +
                    "\n-end of list-\n");
        }else if(Objects.equals(dropDownMenu.getValue(), "Display Classes")){
            display(classScheduleObj.displaySchedule());
        }
    }
    /**
     * Method to display given text.
     * @param s, string to be displayed.
     */
    private void display(String s){
        outputText.appendText(s+"\n");
    }

    /**
     * Method to get correct location input from what user has provided.
     * @param locationPane, pane of the GUI containing possible locations.
     * @return location, returns location if valid location is selected.
     */
    private Location getValidLocationInput(GridPane locationPane){
        ObservableList<Node> locations = locationPane.getChildren();
        int selectedLocationID = -1;
        for(int i = 0; i < locationPane.getColumnCount(); i++){
            RadioButton button = (RadioButton) locations.get(i);
            if(button.isSelected()){
                selectedLocationID = i;
                i = locationPane.getColumnCount();
            }
        }
        if(selectedLocationID == -1){
            display("Please select a location");
            return null;
        }
        return Location.getLocationFromID(selectedLocationID);
    }

    /**
     * Checks to see if user gave input in the name field.
     * @param name, name text field to be checked.
     * @return string, returns if name is valid string.
     */
    private String getValidNameInput(TextField name){
        if(name.getText().isEmpty() || name.getText().isBlank()){
            display("Please enter a name");
            return null;
        }
        return name.getText();
    }

    /**
     * Method to check if date is valid.
     * @param datePicker, checks input from date-picker.
     * @return date, return date if valid.
     */
    private Date getValidDateInput(DatePicker datePicker){
        if(datePicker.getValue() == null){
            display("Please choose a date");
            return null;
        }
        LocalDate localDate = datePicker.getValue();
        return new Date(localDate.getMonthValue(), localDate.getDayOfMonth(), localDate.getYear());
    }

    /**
     * Method to check that instructor input is valid.
     * @param instructor, the field holding instructors name.
     * @return instructor, if valid will return valid instructor.
     */
    private Instructor getValidInstructorInput(TextField instructor){
        if(instructor.getText().isEmpty() || instructor.getText().isBlank()){
            display("Please enter an instructor name");
        }
        else {
            if (isValidInstructor(instructor.getText())) {
                return Instructor.valueOf(instructor.getText().toUpperCase());
            }
        }
        return null;
    }

    /**
     * Method to check that input for class type is valid.
     * @param classType, user input for class-type, that's being checked.
     * @return classtype, if valid will return the correct class.
     */
    private ClassType getValidClassType(TextField classType){
        if(classType.getText().isEmpty() || classType.getText().isBlank()){
            display("Please enter a class");
        }
        else {
            if (isValidClassType(classType.getText())) {
                return ClassType.valueOf(classType.getText().toUpperCase());
            }
        }
        return null;
    }

    /**
     * Method to check if the user inputted dates are valid.
     * @param testDate, the date being tested
     * @param bypass, if true, bypass the age restriction check
     * @return boolean, true if dates are valid, false otherwise.
     */
    private boolean hasValidDate(Date testDate, boolean bypass){
        if(testDate.isValid()){
            if(bypass || testDate.isEightteen()){
                return true;
            }else{
                if(!testDate.isBorn()){
                    display(("DOB " + testDate.toString() + ": cannot be today or a future date!"));
                }else{
                    display("DOB " + testDate.toString() + ": must be 18 or older to join!");
                }
            }
        }else{
            display("DOB " + testDate.toString() + ": invalid calendar date!");
        }
        return false;
    }

    /**
     * Method will check that the classes instructor is valid
     * and or does actually teach the class.
     * @param instructorString, the instructor being checked.
     * @return false if instructor does not exist, true otherwise.
     */
    private boolean isValidInstructor(String instructorString){
        if(!Instructor.isValid(instructorString.toUpperCase())){
            display(instructorString + " - instructor does not exist.");
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
                display(String.format(outputString, testMember.getFullName(), testClass.getLocation(), "standard membership"));
            }else{
                display(String.format(outputString, testMember.getFullName() + " guest", testClass.getLocation(), "guest"));
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
                display(member.getFullName() + " added.");

            }else{
                display(member.getFullName() +
                        " is already in the database.");
            }
    }

    /**
     * Method to remove members from the database.
     * Will give an error if trying to remove non-existent member.
     * @param member - member being removed
     */
    private void removeMember(Member member){
        Member testMember = memberCheck(member);
        if(testMember == null){
            return;
        }
        if (memberDatabaseObj.remove(testMember)) {
            classScheduleObj.removeAllInstancesOf(testMember);
            display(testMember.getFullName() + " removed.");
        }
    }

    /**
     * Method to load legacy members from given file.
     * @param file, The file to load from
     */
    private void loadMembers(File file) {
        try {
            display("\n-list of members loaded-");
            display(memberDatabaseObj.memberLoop(file));
            display("-end of list-\n");
        }
        catch(Exception e){
            display("Wrong file type/Invalid formatting");
        }
    }

    /**
     * Method to load classes from given file.
     * @param file, the file to load from
     */
    private void loadClasses(File file) {
        try {
            display("\n-Fitness classes loaded-");
            display(classScheduleObj.classLoop(file));
            display("-end of class list-\n");
        }
        catch(Exception e){
            display("Wrong file/Invalid formatting");
        }
    }

    /**
     * Method will add user based upon user input. Will add
     * standard, premium and family member types.
     * @param c, the type of membershi
     * @param fname, first name of the member
     * @param lname, last name of the member
     * @param dob, the date of birth of the member
     * @param location, the location the member is apart of
     */
    private void addController(Command c, String fname, String lname, Date dob, Location location){

        Member addedMember = new Member(fname, lname, dob, location);

        switch(c){
            case ADD_FAMILY -> addedMember = new Family(fname, lname, dob, location, 1);
            case ADD_PREMIUM  -> addedMember = new Premium(fname, lname, dob, location, 3);
        }
        addMember(addedMember);
    }

    /**
     * Method to remove members from the database.
     * Will give an error if trying to remove a member
     * that is not in the database.
     * @param fname, users first name
     * @param lname, users last name
     * @param dob, users date of birth
     */
    private void removeMemberController(String fname, String lname, Date dob){
        if(memberDatabaseObj.getSize() == 0){
            display("Member database is empty!");
            return;
        }
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
            display(testClass.getClassType() + " by " + testClass.getInstructor() + " does not exist at " + Location.getTown(testClass.getLocation()));
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
            display(String.format("%s %s membership expired", newMember.getFullName(), newMember.getDob()));
            return;
        }
        if(!(newMember instanceof Family) && !validLocationSelection(newMember, realClass)){
            return;
        }
        realClass.checkIn(newMember);
        String outputString = "%s checked in %s";
        display(String.format(outputString, newMember.getFullName(), realClass));
        display(realClass.displayClass());
    }

    /**
     * Helper method to drop the user from a class or
     * give them an appropriate error message.
     * @param newMember, the member dropping the class.
     * @param realClass, the class being dropped.
     */
    private void dropHelper(Member newMember, FitnessClass realClass){
        if(!realClass.contains(newMember)){
            display(newMember.getFullName() + " did not check in.");
            return;
        }
        realClass.drop(newMember);
        String outputString = "%s done with the class.";
        display(String.format(outputString, newMember.getFullName()));
    }

    /**
     * Method to check if the class inputted exists.
     * @param classtype, the class being checked.
     * @return boolean, true if the class exists, false otherwise.
     */
    private boolean isValidClassType(String classtype){
        if(!ClassType.isValid(classtype.toUpperCase())){
            display(classtype + " - class does not exist");
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
                    display("Time conflict - " + testClass.toStringFullTown());
                }else{
                    display(testMember.getFullName() + " already checked in.");
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
            display(inputMember.getFullName() + " " + inputMember.getDob() + " is not in the database.");
        }
        return testMember;
    }
}