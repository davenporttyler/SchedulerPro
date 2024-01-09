package test.tdavenportc195.Models;

import java.time.LocalDateTime;

/**
 *      Reports Model Class
 *
 * @author Tyler Davenport
 */

public class Reports {


    //private int country_ID;
    private String customer_name;
    private int customer_id;
    private LocalDateTime end;
    private LocalDateTime start;
    private String description;
    private String title;
    private int appointment_id;
    private String contact_name;
    private String contact_id;
    private String country;
    private String division;
    private int customerCount;

    private String type;
    private String monthName;
    private int count;


    ///-----------------Reports Constructors-----------------------------------------------------------------///

    /**
     *          Constructor for Report 1
     * @param type          The appointment Type
     * @param monthName     The Month Name
     * @param count         The total count
     */
    public Reports(String type, String monthName, int count) {

        this.type = type;
        this.monthName = monthName;
        this.count = count;

    }

    /**
     *          Constructor for Report 3
     * @param customerCount The total Count
     * @param country       The Country Name
     * @param division      The Division Name
     */
    public Reports(int customerCount, String country, String division) {


        this.country = country;
        this.division = division;
        this.customerCount = customerCount;
    }

    /**
     *          Constructor for Report 2
     * @param contact_id        The Contact ID
     * @param contact_name      The Contact Name
     * @param appointment_id    The Appointment ID
     * @param title             The Title
     * @param type              The Type
     * @param description       The Description
     * @param start             The Start
     * @param end               The End
     * @param customer_id       The Customer ID
     * @param customer_name     The Customer Name
     */
    public Reports(String contact_id, String contact_name, int appointment_id, String title, String type, String description, LocalDateTime start,
                   LocalDateTime end, int customer_id, String customer_name)
    {
        this.contact_id = contact_id;
        this.contact_name = contact_name;
        this.appointment_id = appointment_id;
        this.title = title;
        this.type = type;
        this.description = description;
        this.start = start;
        this.end = end;
        this.customer_id = customer_id;
        this.customer_name = customer_name;

    }

    /**
     *      Getter for Customer Name
     * @return  The Customer Name
     */
    public String getCustomer_name() {
        return customer_name;
    }

    /**
     *      Setter for Customer Name
     * @param customer_name     The Customer Name to set
     */
    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    /**
     *      Getter for Customer ID
     * @return      The Customer ID
     */
    public int getCustomer_id() {
        return customer_id;
    }

    /**
     *      Setter for Customer ID
     * @param customer_id       The Customer ID to set
     */
    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    /**
     *      Getter for End
     * @return      The End
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
     *      Getter for Start
     * @return      The Start
     */
    public LocalDateTime getStart() {
        return start;
    }

    /**
     *      Setter for Start
     * @param start     The Start to set
     */
    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    /**
     *      Getter for Description
     * @return      The Description
     */
    public String getDescription() {
        return description;
    }

    /**
     *      Setter for Description
     * @param description       The Description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *      Getter for Title
     * @return      The Title
     */
    public String getTitle() {
        return title;
    }

    /**
     *      Setter for Title
     * @param title     The Title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *      Getter for Appointment ID
     * @return          The Appointment ID
     */
    public int getAppointment_id() {
        return appointment_id;
    }

    /**
     *      Setter for Appointment ID
     * @param appointment_id        The Appointment ID to set
     */
    public void setAppointment_id(int appointment_id) {
        this.appointment_id = appointment_id;
    }

    /**
     *      Getter for Contact Name
     * @return          The Contact Name
     */
    public String getContact_name() {
        return contact_name;
    }

    /**
     *      Setter for Contact Name
     * @param contact_name      The Contact Name to set
     */
    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    /**
     *      Getter for Contact ID
     * @return          The Contact ID
     */
    public String getContact_id() {
        return contact_id;
    }

    /**
     *      Setter for Contact ID
     * @param contact_id        The Contact ID to set
     */
    public void setContact_id(String contact_id) {
        this.contact_id = contact_id;
    }

    /**
     *      Getter for Country
     * @return          The Country
     */
    public String getCountry() {
        return country;
    }

    /**
     *      Setter for Country
     * @param country       The Country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     *      Getter for Division
     * @return          The Division
     */
    public String getDivision() {
        return division;
    }

    /**
     *      Setter for Division
     * @param division      The Division to set
     */
    public void setDivision(String division) {
        this.division = division;
    }

    /**
     *      Getter for Customer Count
     * @return          The Customer Count
     */
    public int getCustomerCount() {
        return customerCount;
    }

    /**
     *      Setter for Customer Count
     * @param customerCount     The Customer Count to set
     */
    public void setCustomerCount(int customerCount) {
        this.customerCount = customerCount;
    }

    /**
     *      Getter for Type
     * @return          The Type
     */
    public String getType() {
        return type;
    }

    /**
     *      Setter for Type
     * @param type      The Type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *      Getter for Month Name
     * @return      The Month Name
     */
    public String getMonthName() {
        return monthName;
    }

    /**
     *      Setter for Month Name
     * @param monthName         The Month Name to set
     */
    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }

    /**
     *      Getter for Count
     * @return      The Count
     */
    public int getCount() {
        return count;
    }

    /**
     *      Setter for Count
     * @param count     The Count to set
     */
    public void setCount(int count) {
        this.count = count;
    }




}
