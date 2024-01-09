package test.tdavenportc195.Models;

import java.time.LocalDateTime;

/**
 *      Country Model Class
 *
 * @author Tyler Davenport
 */
public class Country {

    private int Country_ID;
    private String Country;
    private LocalDateTime Create_Date;
    private String Created_By;
    private LocalDateTime Last_Update;
    private String Last_Updated_By;

    ///----------------------Country Empty Constructor------------------------------------------------------------///

    /**
     *      Empty Country Constructor
     */
    public Country(){}

    ///-----------------------Country Constructor-----------------------------------------------------------///

    /**
     *      Main Country Constructor
     * @param Country_ID    The Country ID
     * @param Country       The Country Name
     */
    public Country(int Country_ID, String Country){

        this.Country_ID = Country_ID;
        this.Country = Country;


    }

    /**
     *      Constructor for DAO
     * @param country       The Country Name
     */
    public Country(String country) {

    }


    ///-----------------------Getters & Setters-----------------------------------------------------------///

    /**
     *       Getter for Country ID
     * @return      The Country ID
     */
    public int getCountry_ID() {
        return Country_ID;
    }

    /**
     *      Setter for Country ID
     * @param country_ID    The Country ID to be set
     */
    public void setCountry_ID(int country_ID) {
        Country_ID = country_ID;
    }

    /**
     *      Getter for Country
     * @return      The Country Name
     */
    public String getCountry() {
        return Country;
    }

    /**
     *      Setter for Country
     * @param country   The Country to be set
     */
    public void setCountry(String country) {
        Country = country;
    }



}
