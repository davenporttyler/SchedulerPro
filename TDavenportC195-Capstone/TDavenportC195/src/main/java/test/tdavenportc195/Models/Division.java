package test.tdavenportc195.Models;

import java.time.LocalDateTime;

/**
 *      Division Model Class
 *
 * @author Tyler Davenport
 */
public class Division {

    private int Division_ID;
    private String Division;
    private LocalDateTime Create_Date;
    private String Created_By;
    private LocalDateTime Last_Update;
    private String Last_Updated_By;
    private int Country_ID;

    ///-----------------------Empty Division Constructor-----------------------------------------------------------///

    /**
     *      Empty Division Constructor
     */
    public Division(){}

    ///-----------------------Division Constructor-----------------------------------------------------------///

    /**
     *      Main Division Constructor
     * @param Division_ID       The Division ID
     * @param Division          The Division Name
     * @param Country_ID        The Country ID
     */
    public Division(int Division_ID, String Division, int Country_ID){

        this.Division_ID = Division_ID;
        this.Division = Division;
        this.Country_ID = Country_ID;

    }

    ///--------------------------Getters & Setters--------------------------------------------------------///

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
     *      Getter for Country ID
     * @return      The Country ID
     */
    public int getCountry_ID() {
        return Country_ID;
    }

    /**
     *      Setter for Country ID
     * @param country_ID        The Country ID to set
     */
    public void setCountry_ID(int country_ID) {
        Country_ID = country_ID;
    }





}
