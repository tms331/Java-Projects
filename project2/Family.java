package projectpackage;
/**
 *  This class is an extension of member, only with the additional attribute,
 *  guestPasses. Only Family and Premium members have guest passes. All 3
 *  forms of member (standard, family and premium) also have different
 *  membership fee costs associated with them. Membership fee method is overriden.
 *  @author Thomas Shea, James Artuso
 */
public class Family extends Member {
    int guestPasses;

    /**
     *  Constructor to make a member but with the additional guest pass
     *  attribute. Besides attribute all other fields are the same
     *  as the regular member constructor.
     *  @param fname, user first name
     *  @param lname, user last name
     *  @param dob, user date of birth
     *  @param expire, user membership expiration date
     *  @param location, user's gym location
     *  @param guestPasses, # of guest passes a user has
     */
    public Family(String fname, String lname, Date dob, Date expire, Location location, int guestPasses) {
        super(fname, lname, dob, expire, location);
        this.guestPasses = guestPasses;
    }

    /**
     *  Constructor to make a member but with the additional guest pass
     *  attribute. Besides attribute all other fields are the same
     *  as the regular member constructor.
     *  @param fname, user first name
     *  @param lname, user last name
     *  @param dob, user date of birth
     *  @param location, user's gym location
     *  @param guestPasses, # of guest passes a user has
     */
    public Family(String fname, String lname, String dob, Location location, int guestPasses){
        super(fname, lname, dob, location);
        this.guestPasses = guestPasses;
    }

    /**
     * Method to remove a guest pass from a user.
     * @return int, number of guest passes.
     */
    public int useGuestPass(){
        guestPasses = guestPasses - 1;
        return guestPasses;
    }

    /**
     * Method to add a guest pass to a user.
     * @return int, number of guest passes.
     */
    public int addGuestPass(){
        guestPasses = guestPasses + 1;
        return  guestPasses;
    }

    /**
     *  Getter method to return the amount of
     *  guestPasses a member has.
     *  @return int, the amount of guest passes.
     */
    public int getGuestPasses(){
        return guestPasses;
    }

    /**
     * Method to return a user-friendly string,
     * representation of user.
     * @return String, the string giving user details.
     */
    @Override
    public String toString() {
        String returnString = super.toString() + ", ("+
                this.getClass().getSimpleName() + ") Guest-passes remaining: "
                + guestPasses;
        return returnString;
    }

    /**
     *  Calculates the membership fee for a family member.
     *  Costs vary based upon membership type.
     *  @return int, the next amount due.
     */
    @Override
    public double membershipFee(){
        return (59.99 * 3) + 29.99;
    }

}
