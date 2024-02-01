package projectpackage;
/**
 * This Location enum class is for the location data type, which has a town, zip and county.
 * Location data type is an attribute of member showing which gym their membership is at.
 * There is a constructor to create the location data type and a toString method to create a printable string.
 * @author Thomas Shea, James Artuso
 */
public enum Location {
    EDISON      ("EDISON",      8837, "MIDDLESEX", 0),
    PISCATAWAY  ("PISCATAWAY",  8854, "MIDDLESEX", 1),
    BRIDGEWATER ("BRIDGEWATER", 8807, "SOMERSET", 2),
    FRANKLIN    ("FRANKLIN",    8873, "SOMERSET", 3),
    SOMERVILLE  ("SOMERVILLE",  8876, "SOMERSET", 4);
    private final String town;
    private final int zip;
    private final String county;
    private final int id;

    /**
     * Location constructor, location type has town zip and county.
     * Will create location data type and set attributes accordingly.
     * @param town the name of the town
     * @param zip zipcode of the town
     * @param county, the county in which the gym is.
     * @param id, the id number of the locaiton
     */
    Location(String town, int zip, String county, int id){
        this.town = town;
        this.zip = zip;
        this.county = county;
        this.id = id;
    }

    /**
     * Method to give a more user-friendly printable string.
     * @return string, the string which contains the town, zip and county.
     */
    @Override
    public String toString(){
        return (String.format("%s, %05d, %s",town, zip,county));
    }

    /**
     * Method to check if the user give location name is an
     * actual location within this class.
     * @param locationName, user inputted location name.
     * @return boolean, true if location name is valid.
     */
    public static boolean isValid(String locationName){
        Location[] locations = Location.values();
        for(int i = 0; i < locations.length; i++){
            if(locations[i].town.equals(locationName)){
                return true;
            }
        }
        return false;
    }

    /**
     * Getter method to return town of the location.
     * @param location, location given
     * @return String, the town of the location.
     */
    public static String getTown(Location location){
        return location.town;
    }

    /**
     * Gets location from id
     * @param id, the id of the location to find
     * @return Location, the location with the corresponding id
     */
    public static Location getLocationFromID(int id){
        for(Location l : values()){
            if(l.id == id) {
                return l;
            }
        }
        return null;
    }
}