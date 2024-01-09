package test.tdavenportc195.Models;

import javafx.geometry.Pos;

import java.time.LocalDateTime;


/**
 *      Customers Model Class
 *
 * @author Tyler Davenport
 */
public class Customers {


    private int Customer_ID;
    private String Customer_Name;
    private String Address;
    private String Postal_Code;
    private String Phone;
    private LocalDateTime Create_Date;
    private String Created_By;
    public int Division_ID;
    public String Country;
    public String Division;

    ///----------------------Empty Customers Constructor------------------------------------------------------------///

    /**
     *      Empty Customers Constructor
     */
    public Customers() {}

    ///----------------------6 item Customers Constructor------------------------------------------------------------///

    /**
     *      Customer Constructor
     * @param Customer_ID       The Customer ID
     * @param Customer_Name     The Customer Name
     * @param Address           The Customer Address
     * @param Postal_Code       The Customer Postal Code
     * @param Phone             The Customer Phone
     * @param Division_ID       The Division ID
     */
    public Customers(int Customer_ID, String Customer_Name, String Address, String Postal_Code, String Phone,
                      int Division_ID) {

        this.Customer_ID = Customer_ID;
        this.Customer_Name = Customer_Name;
        this.Address = Address;
        this.Postal_Code = Postal_Code;
        this.Phone = Phone;
        this.Division_ID = Division_ID;


    }

    ///----------------------8 item Customers Constructor------------------------------------------------------------///

    /**
     *
     * @param Customer_ID       The Customer ID
     * @param Customer_Name     The Customer Name
     * @param Address           The Customer Address
     * @param Postal_Code       The Customer Postal Code
     * @param Phone             The Customer Phone
     * @param Division_ID       The Division ID
     * @param Division_Name     The Division Name
     * @param Country_Name      The Country Name
     */
    public Customers(int Customer_ID, String Customer_Name, String Address, String Postal_Code, String Phone, int Division_ID,
                     String Division_Name, String Country_Name) {

        this.Customer_ID = Customer_ID;
        this.Customer_Name = Customer_Name;
        this.Address = Address;
        this.Postal_Code = Postal_Code;
        this.Phone = Phone;
        this.Division_ID = Division_ID;
        this.Division = Division_Name;
        this.Country = Country_Name;

    }

    ///-----------------5 Item Customers Constructor-----------------------------------------------------------------///
    /**
     *
     * @param Customer_ID       The Customer ID
     * @param Customer_Name     The Customer Name
     * @param Address           The Customer Address
     * @param Postal_Code       The Customer Postal Code
     * @param Phone             The Customer Phone
     */
    public Customers(int Customer_ID, String Customer_Name, String Address, String Postal_Code, String Phone) {
        this.Customer_ID = Customer_ID;
        this.Customer_Name = Customer_Name;
        this.Address = Address;
        this.Postal_Code = Postal_Code;
        this.Phone = Phone;

    }

    ///--------------------------Getters & Setters--------------------------------------------------------///

    /**
     *      Getter for Country
     * @return      The Country
     */
    public String getCountry() {
        return Country;
    }

    /**
     *      Setter for Country
     * @param country       The Country to set
     */
    public void setCountry(String country) {
        Country = country;
    }

    /**
     *      Getter for Division
     * @return      The Division
     */
    public String getDivision() {
        return Division;
    }

    /**
     *      Setter for Division
     * @param division      The Division to set
     */
    public void setDivision(String division) {
        Division = division;
    }

    /**
     *      Getter for Customer ID
     * @return      The Customer ID
     */
    public int getCustomer_ID() {
        return Customer_ID;
    }

    /**
     *      Setter for Customer ID
     * @param customer_ID       The Customer ID to set
     */
    public void setCustomer_ID(int customer_ID) {
        Customer_ID = customer_ID;
    }

    /**
     *      Getter for Customer Name
     * @return      The Customer Name
     */
    public String getCustomer_Name() {
        return Customer_Name;
    }

    /**
     *      Setter for Customer Name
     * @param customer_Name     The Customer Name to set
     */
    public void setCustomer_Name(String customer_Name) {
        Customer_Name = customer_Name;
    }

    /**
     *      Getter for Address
     * @return      The Address
     */
    public String getAddress() {
        return Address;
    }

    /**
     *      Setter for Address
     * @param address       The Address to set
     */
    public void setAddress(String address) {
        Address = address;
    }

    /**
     *      Getter for Postal Code
     * @return      The Postal Code
     */
    public String getPostal_Code() {
        return Postal_Code;
    }

    /**
     *      Setter for Postal Code
     * @param postal_Code       The Postal Code to set
     */
    public void setPostal_Code(String postal_Code) {
        Postal_Code = postal_Code;
    }

    /**
     *      Getter for Phone
     * @return      The Phone
     */
    public String getPhone() {
        return Phone;
    }

    /**
     *      Setter for Phone
     * @param phone     The Phone to set
     */
    public void setPhone(String phone) {
        Phone = phone;
    }

    /**
     *      Getter for Division ID
     * @return      The Division ID
     */
    public int getDivision_ID() {
        return Division_ID;
    }

    /**
     *      Setter for Division ID
     * @param division_ID       The Division ID to set
     */
    public void setDivision_ID(int division_ID) {
        Division_ID = division_ID;
    }

    /**
     *      To String function for Customer Name
     * @return      String of Customer Name
     */
    public String toString(){

        return Customer_Name;
    }




}
