package projectpackage;
import java.util.ArrayList;
/**
 * The "fitness class" class is for managing the fitness classes at the gym.
 * Members can check into and drop out of classes. The only restriction is if
 * the member attempts to check into two classes that run at the same time
 * or a class that does not exist. Additionally, they will not be able to check
 * into classes if they have already checked into them. They also will not
 * be able to drop classes they are not participating in.
 * @author Thomas Shea, James Artuso
 */
public class FitnessClass {
    private MemberDatabase classDatabase;

    private ArrayList<Member> guestList;
    private Time classTime;
    private Instructor instructor;
    private Location location;
    private ClassType classType;

    /**
     * Fitness class constructor which has its own database of members that
     * are associated with the class types array. Class databases is an array
     * containing member databases for each class.
     * @param classTime - Time of class
     * @param instructor - intructor of the class
     * @param location - Location of the class
     * @param type - Type of class being taught
     */
    public FitnessClass(Time classTime, Instructor instructor, Location location, ClassType type){
        classDatabase = new MemberDatabase();
        guestList = new ArrayList<Member>();
        this.classTime = classTime;
        this.instructor = instructor;
        this.location = location;
        this.classType = type;
    }

    /**
     * Adds member to the guest list.
     * @param member, member whose guest is being added
     * @return boolean, true if guest is added
     */
    public boolean addGuest(Member member){
        if( member instanceof Family) {
            Family familyMember = (Family) member;
            if (familyMember.getGuestPasses() > 0) {
                guestList.add(familyMember);
                familyMember.useGuestPass();
                return true;
            }
        }
        return false;
    }

    /**
     * Drops member given from the guest list.
     * @param member, member whose guest is being dropped.
     * @return boolean, true if member has been dropped.
     */
    public boolean dropGuest(Member member){
        if(member instanceof Family) {
            Family familyMember = (Family) member;
            if (guestList.remove(familyMember)) {
                familyMember.addGuestPass();
                return true;
            }
        }
        return false;
    }

    /**
     * Getter method to return class instructor.
     * @return instructor, returns.
     */
    public Instructor getInstructor() {
        return instructor;
    }

    /**
     * Getter method to return class time.
     * @return, class time.
     */
    public Time getTime() {
        return classTime;
    }

    /**
     * Getter method to return class type.
     * @return, class type.
     */
    public ClassType getClassType(){
        return classType;
    }

    /**
     * Getter method to return location.
     * @return, class location.
     */
    public Location getLocation(){
        return location;
    }

    /**
     * Getter method to check if database contains a member.
     * @param member - member being checked to be in classDatabase
     * @return boolean, true if database contains member, false otherwise.
     */
    public boolean contains(Member member){
        return classDatabase.contains(member);
    }

    /**
     * Method to check in members, add them to a class.
     * @param member - member being checked in.
     * @return boolean, true if member is added, false otherwise.
     */
    public boolean checkIn(Member member){
        return classDatabase.add(member);
    }

    /**
     * Method to drop members from a class.
     * @param member being dropped.
     * @return boolean, true if member has been dropped, false otherwise.
     */
    public boolean drop(Member member){
        return classDatabase.remove(member);
    }

    /**
     * Getter method to check if objects, and obj fields are equal.
     * @return boolean, true if objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof FitnessClass) {
            FitnessClass otherClass = (FitnessClass) obj;
            return ( (this.instructor == otherClass.instructor) &&
                    (this.classType == otherClass.classType) && (this.location == otherClass.location));
        }
        return false;
    }

    /**
     * Method to construct a string to display class information.
     * @return String, returns the constructed string.
     */
    @Override
    public String toString(){
        return classType + " - " + instructor + ", " + classTime.toString() + ", " + Location.getTown(location);
    }

    /**
     * Method to construct a string that contains the full location information
     * @return String, returns the constructed string.
     */
    public String toStringFullTown(){
        return classType + " - " + instructor + ", " + classTime.toString() + ", " + location;
    }

    /**
     * Method to print classes and their participants
     * @return String, returns the string of all classes and participants.
     */
    public String displayClass(){
        String returnString = "";
        if(classDatabase.getSize() > 0){
            returnString = returnString + "- Participants -\n";
            returnString = returnString + "\t" + classDatabase.toString() + "\n";
        }
        if(guestList.size() > 0){
            returnString = returnString + " - Guests - \n";
            for(int i = 0; i < guestList.size(); i++){
                returnString = returnString + guestList.get(i).toString() + "\n";
            }
        }
        return returnString;
    }

}