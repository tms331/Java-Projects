package projectpackage;
/**
 * This class contains the methods that manage the users in the database such as adding and removing.
 * Methods from this class are called upon in Gym Manager class based on user input.
 * Contains the add, remove and print methods as well as some helper methods.
 * @author Thomas Shea, James Artuso
 */
public class MemberDatabase {

    private Member [] mlist = new Member[4];
    private int size = 0;
    private static final int NOT_FOUND = -1;

    /**
     * Method finds the exact index that a member is at by comparing the
     * first name, last name and date of birth. Calls upon equals method from
     * Member class to accomplish this comparison.
     * @param member the member that is being searched for.
     * @return returns an integer, will return -1 if the user is not found,
     * otherwise will return the index of the item in the array.
     */
    private int find(Member member){
        int locationInArray;

        for(int i = 0; i < mlist.length; i++){
            if(member.equals(mlist[i])){
                locationInArray = i;
                return locationInArray;
            }
        }
        return NOT_FOUND;
    }

    /**
     * Helper method for the add method below. This method will copy
     * all elements from the old array into a new one that is 4 elements
     * larger than the old array.
     */
    private void grow(){
        Member[] tmpArray = new Member[mlist.length + 4];
        for(int i = 0; i < mlist.length; i++){
            tmpArray[i] = mlist[i];
        }
        mlist = tmpArray;
    }

    /**
     * Add method will add user to the array so long as the user is not
     * already in the array. Array has default size of 4 and will expand
     * by 4 only when array has been filled up.
     * @param member to be added to the array
     * @return returns true if the member is not already in the array.
     */
    public boolean add(Member member){
        if(find(member) != NOT_FOUND){
            return false;
        }

        if(size == mlist.length){
            grow();
        }

        mlist[size] = member;
        size++;
        return true;
    }

    /**
     * Function to detect if member is found within array.
     * @param member, checks if this member is within array.
     * @return boolean true if found, false otherwise
     */
    public boolean contains(Member member){
        return find(member) != NOT_FOUND;
    }

    /**
     * Function to detect if member is found within array.
     * @param member, checks if member is within array.
     * @return member, returns the member at the correct index.
     */
    public Member getMember(Member member){
        if(contains(member)) {
            return mlist[find(member)];
        }
        return null;
    }

    /**
     * Removes member from the array and shifts the array to the left to
     * ensure that there are no "gaps". Uses find method to determine the
     * index that the member is located at. Adjusts size accordingly so that
     * when adding new item it will be added to the end of the list.
     * @param member to be removed from the array.
     * @return returns true if the member is within the array.
     */
    public boolean remove(Member member){
        int arrayIndex = find(member);

        if(arrayIndex != NOT_FOUND) {
            if (arrayIndex != mlist.length) {
                mlist[arrayIndex] = null;
                for (int i = arrayIndex; i < mlist.length - 1; i++) {
                    mlist[i] = mlist[i + 1];
                }
                mlist[mlist.length-1] = null;
                size = size - 1;
            }

        }else{
            return false;
        }
        return true;
    }

    /**
     * Basic method to print the list of members as they were
     * added to the array by the user. Uses print assist to print.
     */
    public void print(){
            System.out.println("-list of members-");
            System.out.println(this.toString());
            System.out.println("-end of list-");
    }

    /**
     * Sorts the array members by their county and zipcode. County arranged in
     * alphabetical order and zipcodes with the counties is organized from
     * smallest to largest(top to bottom).
     */
    public void printByCounty(){
        System.out.println("-list of members sorted by county and zipcode-");
        for(int i = 0; i < size - 1; i++){
            for(int j = i + 1; j < size; j++) {
                if (mlist[i].getLocation().compareTo(mlist[j].getLocation()) > 0) {
                    Member temp = mlist[i];
                    mlist[i] = mlist[j];
                    mlist[j] = temp;
                }
            }
        }
        System.out.println(this.toString());
        System.out.println("-end of list-");
    }

    /**
     * Sorts the array by members expiration dates and then prints the sorted list.
     * Most expired memberships will appear at the top while the least at the bottom.
     */
    public void printByExpirationDate(){
        System.out.println("-list of members sorted by membership expiration date-");
        for(int i = 0; i < size - 1; i++){
            for(int j = i + 1; j < size; j++){
                if (mlist[i].getExpirationDate().compareTo(mlist[j].getExpirationDate()) > 0) {
                    Member temp = mlist[i];
                    mlist[i] = mlist[j];
                    mlist[j] = temp;
                }
            }
        }
        System.out.println(this.toString());
        System.out.println("-end of list-");
    }

    /**
     * Sorts the array by members last names and then first names. Alphabetical order,
     * starting from the top.
     */
    public void printByName(){
        System.out.println("-list of members sorted by last name, and first name-");
        for(int i = 0; i < size - 1; i++){
            for(int j = i + 1; j < size; j++){
                if (mlist[i].compareTo(mlist[j]) > 0) {
                    Member temp = mlist[i];
                    mlist[i] = mlist[j];
                    mlist[j] = temp;
                }
            }
        }
        System.out.println(this.toString());
        System.out.println("-end of list-");
    }

    /**
     *  Method to print the members with associated fees.
     *  Method is called upon when the PF command is inputted.
     *  Prints all members with the next amount due, charges
     *  vary based upon membership type.
     */
    public void printWithFees(){
        System.out.println("-list of members with membership fees-");
        if(size == 0){System.out.println("Member database is empty");}
        String returnString = "";
        for (int i = 0; i < mlist.length; i++) {
            if (mlist[i] != null) {

                returnString = returnString + mlist[i].toString() + ", Membership fee: $ " + mlist[i].membershipFee() + "\n";

            }
        }
        System.out.println(returnString.substring(0, returnString.length() - 1));
        System.out.println("-end of list-");
    }


    /**
     * Overrides default toString method, prints a more user-friendly string
     * @return String, returns the printable string.
     */
    @Override
    public String toString(){
        if(size == 0){return "Member database is empty.";}
        String returnString = "";
        for (int i = 0; i < mlist.length; i++) {
            if (mlist[i] != null) {

                returnString = returnString + mlist[i].toString() + "\n";

            }
        }
        return returnString.substring(0, returnString.length() - 1);
    }

    /**
     * Getter method to get the size of MemberDatabase
     * @return int, returns size variable
     */
    public int getSize(){
        return size;
    }
}