package projectpackage;
/**
 * This class is an extension of member, only with the additional attribute,
 * guestPasses. Only Family and Premium members have guest passes. All 3
 * forms of member (standard, family and premium) also have different
 * membership fee costs associated with them. Membership fee method is overriden.
 * @author Thomas Shea, James Artuso
 */
public class Premium extends Family{

    /**
     * Constructor to make a member but with the additional guest pass
     * attribute. Besides attribute all other fields are the same
     * as the regular member constructor.
     * @param fname, user first name
     * @param lname, user last name
     * @param dob, user date of birth
     * @param expire, user membership expiration date
     * @param location, user's gym location
     * @param guestPasses, # of guest passes a user has
     */
    public Premium(String fname, String lname, Date dob, Date expire, Location location, int guestPasses) {
        super(fname, lname, dob, expire, location, guestPasses);
    }

    /** Contructor to create a member with a first name, last name, and a given Date
     * @param fname, users first name.
     * @param lname, users last name.
     * @param dob, users date of birth.
     * @param loc, Location that the user is apart of
     * @param guestPasses number of guest passes the user has
     */
    public Premium(String fname, String lname, Date dob, Location loc, int guestPasses){
        this(fname, lname, dob, Date.get3Months(), loc, guestPasses);
    }

    /**
     * Constructor to make a member but with the additional guest pass
     * attribute. Besides attribute all other fields are the same
     * as the regular member constructor.
     * @param fname, user first name
     * @param lname, user last name
     * @param dob, user date of birth
     * @param location, user's gym location
     * @param guestPasses, # of guest passes a user has
     */
    public Premium(String fname, String lname, String dob, Location location, int guestPasses){
        super(fname, lname, dob, location, guestPasses);
    }


    /**
     * Calculates the membership fee for
     * a premium member. Costs vary based
     * upon membership type.
     * @return int, the next amount due.
     */
    @Override
    public double membershipFee(){
        return (59.99 * 11);
    }

}
