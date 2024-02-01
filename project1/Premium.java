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
     * Main testbed to test the membership fee
     * method for the 3 different membership types.
     * @param args, main method args.
     */
    public static void main(String[] args) {
        //test standard membership fee calculation
        Member StandardMember = new Member("John", "Doe", new Date("01/01/1980"), new Date("10/23/2023"), Location.BRIDGEWATER);
        System.out.println(StandardMember.membershipFee());

        //test family membership fee calculation
        Family FamilyMember = new Family("Jane", "Doe", new Date("01/01/1980"), new Date("10/23/2023"), Location.BRIDGEWATER, 1);
        System.out.println(FamilyMember.membershipFee());

        //test premium membership fee calculation
        Premium PremiumMember = new Premium("James", "Doe", new Date("01/01/1980"), new Date("10/23/2023"), Location.BRIDGEWATER, 3);
        System.out.println(PremiumMember.membershipFee());

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
