package test.tdavenportc195.Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import test.tdavenportc195.DAO.AppointmentDAO;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 *      Appointments Model Class
 *
 * @author Tyler Davenport
 */

public class Appointments {

    private int id;
    private String title;
    private String description;
    private String location;
    private String type;
    private LocalDateTime start;
    private LocalDateTime end;
    public int customerId;
    public int userId;
    public int contactId;
    public String customerName;
    public String userName;
    public String contactName;

    ///---------------------------Appointments Empty Constructor-------------------------------------------------------///

    /**
     * Empty Appointments Constructor
     */
    public Appointments() {}

    ///---------------------------Appointments Constructors-------------------------------------------------------///

    /**
     *      Appointments Constructor for all innate attributes
     * @param id            The ID of the appointment
     * @param title         The Title of the appointment
     * @param description   The Description of the appointment
     * @param location      The Location of the appointment
     * @param type          The Type of the appointment
     * @param start         The Start of the appointment
     * @param end           The End of the appointment
     * @param customerId    The CustomerID of the appointment
     * @param userId        The UserId of the appointment
     * @param contactId     The ContactID of the appointment
     */

    public Appointments(int id, String title, String description, String location, String type, LocalDateTime start, LocalDateTime end,
                        int customerId, int userId, int contactId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.customerId = customerId;
        this.userId = userId;
        this.contactId = contactId;
    }

    /**
     *
     *      Appointments Constructor for all innate attributes + names of user, customer, and contact
     * @param id            The ID of the appointment
     * @param title         The Title of the appointment
     * @param description   The Description of the appointment
     * @param location      The Location of the appointment
     * @param type          The Type of the appointment
     * @param start         The Start of the appointment
     * @param end           The End of the appointment
     * @param customerId    The CustomerID of the appointment
     * @param userId        The UserId of the appointment
     * @param contactId     The ContactID of the appointment
     * @param customerName  The Name of the customer for appointment
     * @param userName      The Name of the user for appointment
     * @param contactName   The Name of the contact for appointment
     */

    public Appointments(int id, String title, String description, String location, String type, LocalDateTime start, LocalDateTime end,
                        int customerId, int userId, int contactId, String customerName, String userName, String contactName) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.customerId = customerId;
        this.userId = userId;
        this.contactId = contactId;
        this.customerName = customerName;
        this.userName = userName;
        this.contactName = contactName;

    }

    /**
     *      Constructor for Appointment with only 2 parameters to check
     * @param id        The ID of the appointment
     * @param start     The Start of the appointment
     */

    public Appointments(int id, LocalDateTime start) {

        this.id = id;
        this.start = start;
    }

/*
    public static boolean checkAptCollisions (int id, int customerId, LocalDateTime start,
                                              LocalDateTime end) {

        ObservableList<Appointments> aptList = AppointmentDAO.getAllAppointments();
        LocalDateTime pullDBStart;
        LocalDateTime pullDBEnd;

        for (Appointments apt : aptList) {
            pullDBStart = apt.getStart();
            pullDBEnd = apt.getEnd();

            if (customerId != apt.getCustomerId()) {
                continue;
            }


        }
    }

    */

    ///---------------------------Getters & Setters-------------------------------------------------------///

    /**
     *      Getter for ID
     * @return      Returns ID
     */
    public int getId() {
        return id;
    }

    /**
     *      Setter for ID
     * @param id    The ID to be set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *      Getter for Title
     * @return      Returns Title
     */
    public String getTitle() {
        return title;
    }

    /**
     *      Setter for Title
     * @param title    The Title to be set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *      Getter for Description
     * @return      Returns Description
     */
    public String getDescription() {
        return description;
    }

    /**
     *      Setter for Description
     * @param description   The Description to be set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *      Getter for Location
     * @return      Returns Location
     */
    public String getLocation() {
        return location;
    }

    /**
     *      Setter for Location
     * @param location  The Location to be set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     *      Getter for Type
     * @return      Returns Type
     */
    public String getType() {
        return type;
    }

    /**
     *      Setter for Type
     * @param type    The Type to be set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *      Getter for Start
     * @return      Returns Start
     */
    public LocalDateTime getStart() {
        return start;
    }

    /**
     *      Setter for Start
     * @param start     The Start to be set
     */
    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    /**
     *      Getter for End
     * @return      Returns End
     */
    public LocalDateTime getEnd() {
        return end;
    }

    /**
     *      Setter for End
     * @param end       The End to be set
     */
    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    /**
     *      Getter for CustomerID
     * @return      Returns CustomerID
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     *      Setter for CustomerID
     * @param customerId    The CustomerID to be set
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     *      Getter for UserID
     * @return      Returns UserID
     */
    public int getUserId() {
        return userId;
    }

    /**
     *      Setter for UserID
     * @param userId    The UserID to be set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     *      Getter for ContactID
     * @return      Returns ContactID
     */
    public int getContactId() {
        return contactId;
    }

    /**
     *      Setter for ContactID
     * @param contactId The ContactID to be set
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /**
     *      Getter for Customer Name
     * @return      Returns Customer Name
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     *      Setter for Customer Name
     * @param customerName  The Customer Name to be set
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     *      Getter for User Name
     * @return      Returns User Name
     */
    public String getUserName() {
        return userName;
    }

    /**
     *      Setter for User Name
     * @param userName  The User Name to be set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     *      Getter for Contact Name
     * @return      Returns Contact Name
     */
    public String getContactName() {
        return contactName;
    }

    /**
     *      Setter for Contact Name
     * @param contactName   The Contact name to be set
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     *      Function to set the ID to a string
     * @return      String value of int ID
     */
    public String toString(){
        return String.valueOf(id);

    }



}
