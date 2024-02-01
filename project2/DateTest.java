package projectpackage;

import org.junit.Test;

import static org.junit.Assert.*;
/**
 * This class is used to check the isValid() method of the date class.
 * @author Thomas Shea, James Artuso
 */
public class DateTest {

    /**
     * Method to test Date works in a normal situation.
     */
    @Test
    public void test_correct_date() {
        Date testDate = new Date("5/4/2020");
        assertTrue(testDate.isValid());
    }

    /**
     * Method to test a day out of range of every month.
     */
    @Test
    public void test_day_over_range(){
        Date testDate = new Date("9/40/2022");
        assertFalse(testDate.isValid());
    }

    /**
     * Method to test day under the range of days for every month.
     */
    @Test
    public void test_day_under_range(){
        Date testDate = new Date("9/-1/2022");
        assertFalse(testDate.isValid());
    }

    /**
     * Method to test a month out of range of months.
     */
    @Test
    public void test_month_over_range(){
        Date testDate = new Date("13/23/2022");
        assertFalse(testDate.isValid());
    }

    /**
     * Method to test a month under the range of months.
     */
    @Test
    public void test_month_under_range(){
        Date testDate = new Date("0/20/2022");
        assertFalse(testDate.isValid());
    }

    /**
     * Method to test year out of range of years.
     */
    @Test
    public void test_year_under_range(){
        Date testDate = new Date("12/25/-1");
        assertFalse(testDate.isValid());
    }

    /**
     * Method to test Date accepts day 31 on months with 31 days.
     */
    @Test
    public void test_month_31_days_boundary_date() {
        Date testDate = new Date("3/31/2022");
        assertTrue(testDate.isValid());
    }

    /**
     * Method to test Date rejects day 31 on months with less than 31 days.
     */
    @Test
    public void test_month_30_days_boundary_date() {
        Date testDate = new Date("4/31/2022");
        assertFalse(testDate.isValid());
    }

    /**
     * Method to test Feb 29 on a non-leap year.
     */
    @Test
    public void test_non_leap_year_date() {
        Date testDate = new Date("2/29/2022");
        assertFalse(testDate.isValid());
    }

    /**
     * Method to test Feb 29 on a leap-year.
     */
    @Test
    public void test_leap_year_date() {
        Date testDate = new Date("2/29/2020");
        assertTrue(testDate.isValid());
    }

    /**
     * Method to test Feb 29 on a leap-year that is
     * divisible by 100 but not 400
     */
    @Test
    public void test_divisible_by_100_leap_date() {
        Date testDate = new Date("2/29/1900");
        assertFalse(testDate.isValid());
    }

    /**
     * Method to test Feb 29 on a leap-year that is divisble
     * by 100 and 400
     */
    @Test
    public void test_divisible_by_400_leap_date() {
        Date testDate = new Date("2/29/2000");
        assertTrue(testDate.isValid());
    }
}