///-----------Title & Purpose of Application-------------------------------------------------------------------------------------///

Performance Assessment: Software II - Advanced Java Concepts (QAM2)

    The Purpose of this project was to create a GUI-based application for a company based on the requirements that it must include
appointment scheduling, customer handling, generation of reports, and a login form.



///-----------Author, Contact, Application Version, and Date---------------------------------------------------------------------///

Author:     Tyler Davenport, 009202780
Contact:    tdave68@wgu.edu
Version:    v1.0
Date:       10/15/2023



///-----------IDE, Compatibility Information, and MySQL Connector Driver----------------------------------------------------------///

Java SDK Version 17.0.1
openjfx Version 11.0.2
mysql-connector-java-8.0.25

IntelliJ IDEA 2021.1.3 (Community Edition)
Build #IC-211.7628.21, built on June 30, 2021
Runtime version: 11.0.11+9-b1341.60 amd64
VM: OpenJDK 64-Bit Server VM by JetBrains s.r.o.
VM: Windows 10 10.0, Memory: 768M, Cores: 4



///------------Directions to run the program--------------------------------------------------------------------------------------///

1)  Launch the program
2)  Log in to the presented form, as long as the given username and pw correspond to and match credentials in the database,
    the login will be successful
3)  You are presented with the Applications Home Screen, you will see "Appointments", "Customer", "Reports"
4)  The program is interactive and all relevant content can be accessed and switched between screens in the UI, at which point
    you are ready to close the program, just select the "Exit" button from the Home Screen.



///------------Additional Report for part A3f-------------------------------------------------------------------------------------///

        I chose to create an additional report based on the companies customer base, specifically where they are located by
country and division. This information is highly valuable as a company would need to know how many clients are in what locations
to best service them and allocate resources where they are most needed. The report pulls all the countries, and each division
in those countries that have customers, it then counts how many customers are in each division and displays the count.


///-------------Lambda's to improve Code------------------------------------------------------------------------------------------///

1)  AppointmentsAddController : In Initialize, used a function to generate random numbers to create double randomized
                                auto-set Appointment ID's.

2)  AppointmentsAddController:  In Initialize, used a function to use a provided Observable List, and randomly select
                                a meeting type from the list, and set automatically the Text Field for "Type".