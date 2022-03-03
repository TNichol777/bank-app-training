package com.training.pms;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException, SQLException {
    BankApp bankApp = new BankApp();
    bankApp.startBankApp();
}
}


/*Design Requirements
1. Functionality should reflect the below user stories. 
2. Data is stored in a database. 
3. A custom stored procedure is called to perform some portion of the functionality. 
4. Data Access is performed through the use of JDBC in a data layer consisting of Data Access Objects. 
5. All input is received using the java.util.Scanner class. 
6. Log4j is implemented to log events to a file. 
7. A minimum of one (1) JUnit test is written to test some functionality. 

User Functionality
1.As a user, I can login.
2.As a user, I can register for a customer account.

Customer Functionality
1.As a customer, I can apply for a new bank account with a starting balance.
2.As a customer, I can view the balance of a specific account.
3.As a customer, I can make a withdrawal or deposit to a specific account.
4.As a customer, I can post a money transfer to another account.
5.As a customer, I can accept a money transfer from another account.

Employee Functionality
1.As an employee, I can approve or reject
*/