package test.tdavenportc195.Models;


/**
 *          Contacts Model Class
 *
 * @author Tyler Davenport
 */
public class Contacts {

    private int Contact_ID;
    private String Contact_Name;
    private String Email;

    ///---------------------Contacts Empty Constructor-------------------------------------------------------------///

    /**
     * Empty Contact Constructor
     */
    public Contacts(){}

    ///---------------------Contacts Constructor-------------------------------------------------------------///

    /**
     *          Main Contact Constructor
     * @param Contact_ID    The ContactID for the Contact
     * @param Contact_Name  The Contact Name for the Contact
     * @param Email         The Email for the Contact
     */
    public Contacts(int Contact_ID, String Contact_Name, String Email){
        this.Contact_ID = Contact_ID;
        this.Contact_Name = Contact_Name;
        this.Email = Email;

    }

    ///----------------------Getters & Setters------------------------------------------------------------///

    /**
     *      Getter for Contact ID
     * @return      Returns Contact ID
     */
    public int getContact_ID() {
        return Contact_ID;
    }

    /**
     *      Setter for Contact ID
     * @param contact_ID    The Contact ID to be set
     */
    public void setContact_ID(int contact_ID) {
        Contact_ID = contact_ID;
    }

    /**
     *      Getter for Contact Name
     * @return      Returns Contact Name
     */
    public String getContact_Name() {
        return Contact_Name;
    }

    /**
     *      Setter for Contact Name
     * @param contact_Name  The Contact Name to be set
     */
    public void setContact_Name(String contact_Name) {
        Contact_Name = contact_Name;
    }

    /**
     *      Getter for Email
     * @return  Returns Email
     */
    public String getEmail() {
        return Email;
    }

    /**
     *      Setter for Email
     * @param email     The Email to be set
     */
    public void setEmail(String email) {
        Email = email;
    }

    /**
     *      To String Function
     * @return      String value of contact name
     */
    public String toString (){
        return Contact_Name;
    }
}
