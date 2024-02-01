package projectpackage;

import org.junit.Test;

import static org.junit.Assert.*;
/**
 * This class is used to check the member_fee() method used by members.
 * @author Thomas Shea, James Artuso
 */
public class PremiumTest {

    /**
     * Method to test Standard Member fee
     */
    @Test
    public void test_member_fee() {
        Member testMember = new Member("Test", "Member", "1/1/2000");
        assertEquals(149.96, testMember.membershipFee(),0.0);
    }
    /**
     * Method to test Family fee
     */
    @Test
    public void test_family_fee(){
        Family testFamily = new Family("Test", "Family", "1/1/2000", Location.BRIDGEWATER, 1);
        assertEquals(209.96, testFamily.membershipFee(),0.0);
    }

    /**
     * Method to test Premium fee
     */
    @Test
    public void test_premium_fee(){
        Premium testPremium = new Premium("Test", "Family", "1/1/2000", Location.BRIDGEWATER, 3);
        assertEquals(659.89, testPremium.membershipFee(),0.0);
    }

    /**
     * Method to test Family fee as a Member
     */
    @Test
    public void test_family_as_member_fee(){
        Member testFamily = new Family("Test", "Family", "1/1/2000", Location.BRIDGEWATER, 1);
        assertEquals(209.96, testFamily.membershipFee(),0.0);
    }

    /**
     * Method to test Premium fee as a Member
     */
    @Test
    public void test_premium_as_member_fee(){
        Member testPremium = new Premium("Test", "Family", "1/1/2000", Location.BRIDGEWATER, 3);
        assertEquals(659.89, testPremium.membershipFee(),0.0);
    }

    /**
     * Method to test Premium fee as a Family
     */
    @Test
    public void test_premium_as_family_fee(){
        Family testPremium = new Premium("Test", "Family", "1/1/2000", Location.BRIDGEWATER, 3);
        assertEquals(659.89, testPremium.membershipFee(),0.0);
    }
}