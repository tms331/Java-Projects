package projectpackage;
/**
 * This class is used to create and compare members of the member datatype.
 * Contains constructors to create the member data type, and get methods to access attributes of the member.
 * Also has a method to create a printable string showing member data to the user.
 * @author Thomas Shea, James Artuso
 */
public class Member implements Comparable<Member>{

    private String fname;
    private String lname;
    private Date dob;
    private Date expire;
    private Location location;

    /**
     * Constructor to create a member from raw input.
     * @param fname, users first name.
     * @param lname, users last name.
     * @param dob, users date of birth.
     */
    public Member(String fname, String lname, String dob){
        this(fname, lname, new Date(dob), Date.get3Months(), Location.PISCATAWAY);
    }

    /**
     * Constructor to create a member from raw input.
     * @param fname, users first name.
     * @param lname, users last name.
     * @param dob, users date of birth.
     * @param location, location of gym.
     */
    public Member(String fname, String lname, String dob, Location location){
        this(fname, lname, new Date(dob), Date.get3Months(), location); //REPLACE EXPIRE DATE WITH SOMETHING
    }

    /**
     * Constructor that will finalize the member, giving it all the correct fields.
     * Gives member the correct attributes, and dates are now date type.
     * @param fname users first name.
     * @param lname users last name.
     * @param dob users date of birth.
     * @param expire users membership expiration date.
     * @param location, location of the gym that they attend or have a membership at.
     */
    public Member(String fname, String lname, Date dob, Date expire, Location location){
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
        this.expire = expire;
        this.location = location;
    }

    /**
     * Calculates the membership fee for a standard member.
     * Costs vary based upon membership type.
     * @return int, the next amount due.
     */
    public double membershipFee(){
        return (39.99 * 3) + 29.99;
    }

    /**
     * Overrides default toString method, gives a user-friendly printable string.
     * Only condition is that will print a slightly different string based upon
     * whether the member's membership is expired or not.
     * @return String, a string containing dob, membership, location and full name.
     */
    @Override
    public String toString(){
        if(expire.compareTo(new Date()) < 0) {
            return(getFullName() + ", DOB: " + dob.toString() + ", Membership expired " + expire.toString() +
                    ", Location: " + location.toString());
        }

        return(getFullName() + ", DOB: " + dob.toString() + ", Membership expires " + expire.toString() +
                ", Location: " + location.toString());
    }

    /**
     * Method to check if members are equal, compares all their attributes.
     * @param obj parameter, can pass multiple types in, mainly
     * for member comparison.
     * @return boolean, true if equals, false else wise.
     */
    @Override
    public boolean equals(Object obj){
        if(obj instanceof Member){
            Member otherMember = (Member) obj;
            return(fname.toUpperCase().equals(otherMember.getFirstName().toUpperCase()) &&
                    lname.toUpperCase().equals(otherMember.getLastName().toUpperCase()) &&
                    dob.equals(otherMember.getDob())
            );
        }
        return false;
    }

    /**
     * Compares expiration date vs the current date
     * @return boolean, return true if expired, false else wise
     */
    public boolean isExpired(){
        Date todaysDate = new Date();
        return(expire.compareTo(todaysDate) < 1);
    }

    /**
     * Compares last and first names for sorting purposes.
     * @param member, one of the members to be compared to.
     * @return int, based on the comparison.
     */
    @Override
    public int compareTo(Member member){
        String fullName = lname.toUpperCase() + fname.toUpperCase();
        String otherFullName = member.getLastName().toUpperCase() + member.getFirstName().toUpperCase();
        return (fullName.compareTo(otherFullName));
    }

    /**
     * Getter method to return members first name.
     * @return string, first name.
     */
    public String getFirstName(){
        return fname;
    }

    /**
     * Getter method to return members last name.
     * @return string, last name.
     */
    public String getLastName(){
        return lname;
    }

    /**
     * Getter method to return members date of birth.
     * @return date data type, date of birth.
     */
    public Date getDob(){
        return dob;
    }

    /**
     * Getter method to return members membership expiration date.
     * @return date data type, expiration date.
     */
    public Date getExpirationDate(){
        return expire;
    }

    /**
     * Getter method to return the members gym location.
     * @return location data type, location.
     */
    public Location getLocation(){
        return location;
    }

    /**
     * Getter method to return the members full name in the order of
     * firstname lastname
     * @return String, firstname lastname of member
     */
    public String getFullName(){
        return getFirstName() + " " + getLastName();
    }

}
