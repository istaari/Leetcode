package LLD.rideSharingService;

public abstract class User {

    private String userid;
    private String name;
    private String contact;
    private Location location;

    public User(String userid, String name, String contact, Location location) {
        this.userid = userid;
        this.name = name;
        this.contact = contact;
        this.location = location;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }


}
