package projectpackage;
import java.util.StringTokenizer;
import java.util.Calendar;
/**
 * This class is used to create the date data type, which is an attribute of member.
 * The date type is used to store the user's date of birth and membership expiration date.
 * The class has functions to check if the user inputted date is valid as well as compare dates.
 * Lastly the class has get methods to return attributes of the date type, such as month, day, year.
 * @author Thomas Shea, James Artuso
 */
public class Date implements Comparable<Date>{

    public static final int JAN = 0;
    public static final int FEB = 1;
    public static final int MAR = 2;
    public static final int APR = 3;
    public static final int MAY = 4;
    public static final int JUN = 5;
    public static final int JUL = 6;
    public static final int AUG = 7;
    public static final int SEP = 8;
    public static final int OCT = 9;
    public static final int NOV = 10;
    public static final int DEC = 11;

    private static final int QUADRENNIAL = 4;
    private static final int CENTENNIAL = 100;
    private static final int QUATERCENTENNIAL = 400;

    private static final int[] MONTH_MAX_DAYS = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    private int year;
    private int month; //Month is 1 less than calendar number (IE jan = 0, feb = 1, ...)
    private int day;


    /**
     * Date constructor to construct today's date.
     */
    public Date(){
        Calendar todaysCalendar = Calendar.getInstance();
        year = todaysCalendar.get(Calendar.YEAR);
        month = todaysCalendar.get(Calendar.MONTH);
        day = todaysCalendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * Date constructor to construct date based upon the
     * user input string.
     * @param date, user inputted date string.
     */
    public Date(String date){
        StringTokenizer token = new StringTokenizer(date, "/", false);
        month = Integer.parseInt(token.nextToken()) - 1;
        day = Integer.parseInt(token.nextToken());
        year = Integer.parseInt(token.nextToken());
    }

    /**
     * Method to determine if the given date is valid.
     * @return boolean, true if the date is valid, false otherwise.
     */
    public boolean isValid(){
        if(month >= JAN && month <= DEC) {
            if(year >= 0){
                int maxDay = MONTH_MAX_DAYS[month];
                if(month == FEB && isLeapYear()){
                    maxDay = 29;
                }
                if(day > 0 && day <= maxDay) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Method to compare dates by month, day and year.
     * @param otherDate the other date being compared to.
     * @return int, to show if one is greater than the other or equal.
     */
    public int compareTo(Date otherDate){
        if(year - otherDate.getYear() > 0){
            return 1;

        }else if(year - otherDate.getYear() < 0){
            return -1;

        }else{
            if(month - otherDate.getMonth() > 0){
                return 1;

            }else if(month - otherDate.getMonth() < 0){
                return -1;

            }else{
                if (day - otherDate.getDay() > 0) {
                    return 1;

                }else if (day - otherDate.getDay() < 0) {
                    return -1;

                }else{
                    return 0;
                }
            }
        }
    }

    /**
     * Getter method to return portion of date, the year.
     * @return year of the date.
     */
    public int getYear(){
        return year;
    }

    /**
     * Getter method to return portion of date, the month.
     * @return month of the date.
     */
    public int getMonth(){
        return month;
    }

    /**
     * Getter method to return portion of date, the day.
     * @return day of the date.
     */
    public int getDay(){
        return day;
    }

    /**
     * Method to check if the dates are equal. Compares
     * all three attributes of date month, day and year.
     * @param obj other date to be compared to.
     * @return boolean, true if they are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj){
        if(obj instanceof Date){
            Date otherDate = (Date) obj;
            return(
                    year == otherDate.getYear() && month == otherDate.getMonth() && day == otherDate.getDay()
            );
        }
        return false;
    }

    /**
     * Method to put the date into a printable
     * string, in typical forward slash format.
     * @return string, the printable string of date.
     */
    @Override
    public String toString(){
        return( (month + 1) + "/" + day + "/" + year);
    }

    /**
     * Method to figure out if the date is a leap year.
     * @return boolean, true if it is a leap year, false otherwise
     */
    private boolean isLeapYear(){
        return
                (
                        year % QUADRENNIAL == 0 &&
                                ( (year % CENTENNIAL != 0) || (year % QUATERCENTENNIAL == 0) )
                );
    }

    /**
     * Method to check if the date is over 18 years ago
     * @return boolean, true if 18, false else wise.
     */
    public boolean isEightteen(){
        Date todaysDate = new Date();
        Date testDate = new Date((todaysDate.getMonth() + 1) + "/" + todaysDate.getDay() + "/" + (todaysDate.getYear() - 18));
        return(this.compareTo(testDate) < 1);
    }

    /**
     * Checks to see if the date after today.
     * Will return false if the date is after today.
     * @return boolean, return false if today is birthday, true else wise
     */
    public boolean isBorn(){
        Date todaysDate = new Date();
        return(this.compareTo(todaysDate) < 0);
    }

    /**
     * Method to get expiration date 3 months from current date.
     * @return Date, returns the date 3 months from now.
     */
    public static Date get3Months(){
        Date returnDate = new Date();
        returnDate.month = returnDate.month + 3;
        if(returnDate.day == 31){
            if(MONTH_MAX_DAYS[returnDate.month % 12] < 31){
                returnDate.day = returnDate.day - MONTH_MAX_DAYS[returnDate.month];
                returnDate.month += 1;
            }
        }
        if(returnDate.month > DEC){
            returnDate.month = returnDate.month - 12;
            returnDate.year = returnDate.year + 1;
        }

        return returnDate;
    }

    /**
     * This method is used to make sure a String input is in the correct Date format
     * ##/##/##
     * @param dateString, the string being tested.
     * @return boolean, true if format is correct, false otherwise.
     */
    public static boolean isValidDate(String dateString){
        StringTokenizer token = new StringTokenizer(dateString, "/", false);
        try{
            while(token.hasMoreTokens()){
                Integer.parseInt(token.nextToken());
            }
        }

        catch(NumberFormatException e){
            return false;
        }
        return true;
    }

}
