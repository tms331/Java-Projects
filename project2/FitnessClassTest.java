package projectpackage;

import org.junit.Test;

import static org.junit.Assert.*;
/**
 * This class is used to check the add and drop functionality
 * of the FitnessClass
 * @author Thomas Shea, James Artuso
 */
public class FitnessClassTest {

    /**
     * Method to test adding a Member to a FitnessClass
     */
    @Test
    public void test_add_member(){
        FitnessClass testClass = new FitnessClass(Time.MORNING, Instructor.DAVIS, Location.BRIDGEWATER, ClassType.PILATES);
        Member testMember = new Member("Test", "member", "01/01/2000");

        assertTrue(testClass.checkIn(testMember));
    }

    /**
     * Method to test dropping a Member from a FitnessClass.
     */
    @Test
    public void test_drop_member(){
        FitnessClass testClass = new FitnessClass(Time.MORNING, Instructor.DAVIS, Location.BRIDGEWATER, ClassType.PILATES);
        Member testMember = new Member("Test", "member", "01/01/2000");
        testClass.checkIn(testMember);
        assertTrue(testClass.drop(testMember));
    }

    /**
     * Method to test FitnessClass's rejection of adding a member twice.
     */
    @Test
    public void test_fail_add_member_again(){
        FitnessClass testClass = new FitnessClass(Time.MORNING, Instructor.DAVIS, Location.BRIDGEWATER, ClassType.PILATES);
        Member testMember = new Member("Test", "member", "01/01/2000");
        testClass.checkIn(testMember);
        assertFalse(testClass.checkIn(testMember));
    }

    /**
     * Method to test dropping a member when the member has not been added
     * to the FitnessClass.
     */
    @Test
    public void test_fail_drop_no_member(){
        FitnessClass testClass = new FitnessClass(Time.MORNING, Instructor.DAVIS, Location.BRIDGEWATER, ClassType.PILATES);
        Member testMember = new Member("Test", "member", "01/01/2000");
        assertFalse(testClass.drop(testMember));
    }

    /**
     * Method to test adding a Guest to a FitnessClass.
     */
    @Test
    public void test_add_guest(){
        FitnessClass testClass = new FitnessClass(Time.MORNING, Instructor.DAVIS, Location.BRIDGEWATER, ClassType.PILATES);
        Family testMember = new Family("Test", "member", "01/01/2000",Location.BRIDGEWATER, 1);

        assertTrue(testClass.addGuest(testMember));
    }

    /**
     * Method to test dropping a guest from a FitnessClass.
     */
    @Test
    public void test_drop_guest(){
        FitnessClass testClass = new FitnessClass(Time.MORNING, Instructor.DAVIS, Location.BRIDGEWATER, ClassType.PILATES);
        Family testMember = new Family("Test", "member", "01/01/2000",Location.BRIDGEWATER, 1);
        testClass.addGuest(testMember);
        assertTrue(testClass.dropGuest(testMember));
    }

    /**
     * Method to test adding multiple guests to a FitnessClass.
     */
    @Test
    public void test_add_multiple_guest(){
        FitnessClass testClass = new FitnessClass(Time.MORNING, Instructor.DAVIS, Location.BRIDGEWATER, ClassType.PILATES);
        Family testMember = new Premium("Test", "member", "01/01/2000",Location.BRIDGEWATER, 3);

        assertTrue(testClass.addGuest(testMember));
        assertTrue(testClass.addGuest(testMember));
        assertTrue(testClass.addGuest(testMember));
    }

    /**
     * Method to test adding a guest when the member ran out of
     * guest passes.
     */
    @Test
    public void test_fail_too_many_guest(){
        FitnessClass testClass = new FitnessClass(Time.MORNING, Instructor.DAVIS, Location.BRIDGEWATER, ClassType.PILATES);
        Family testMember = new Premium("Test", "member", "01/01/2000",Location.BRIDGEWATER, 3);

        assertTrue(testClass.addGuest(testMember));
        assertTrue(testClass.addGuest(testMember));
        assertTrue(testClass.addGuest(testMember));
        assertFalse(testClass.addGuest(testMember));
    }

    /**
     * Method to test dropping a guest that has not been added to the
     * FitnessClass.
     */
    @Test
    public void test_fail_drop_no_guest(){
        FitnessClass testClass = new FitnessClass(Time.MORNING, Instructor.DAVIS, Location.BRIDGEWATER, ClassType.PILATES);
        Family testMember = new Premium("Test", "member", "01/01/2000",Location.BRIDGEWATER, 3);

        assertFalse(testClass.drop(testMember));
    }

    /**
     * Method to test adding, dropping, then adding a member again.
     */
    @Test
    public void test_add_drop_add_guest(){
        FitnessClass testClass = new FitnessClass(Time.MORNING, Instructor.DAVIS, Location.BRIDGEWATER, ClassType.PILATES);
        Family testMember = new Family("Test", "member", "01/01/2000",Location.BRIDGEWATER, 1);

        assertTrue(testClass.addGuest(testMember));
        assertTrue(testClass.dropGuest(testMember));
        assertTrue(testClass.addGuest(testMember));
    }
}