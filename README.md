# DoCIS Lab #5

Development of Corporate Information Systems Laboratory #5.

## Database

Run `init.sql` to create tables and fill them with test values.

## Spring Security
### Purpose of work
Become familiar with the security setup in Spring.

### General formulation
Modify Laboratory #4 by adding the following functionality:

1. Add a simple registration page. The user enters his username and password 
   and this information is entered into the database, 
   the user is assigned the role of the user (User) of the application.
2. Add a simple authentication form. 
   The form is created programmatically, 
   not automatically generated by Spring.
3. The application must have an Admin user with a role other than User.
4. Delimit access levels to application pages. 
   The User only has access to the pages for viewing all records and requests. 
   The Admin has the ability to add, edit and delete entries.
5. Information about users and their roles should be stored in a database. 
   The method of storage is at the student's discretion.
6. Provide an option to log out of the application.
7. Demonstrate the ability to configure view-level security. 
   This is done by implementing the user's login greeting 
   and displaying an item based on the user's role.

Translated with www.DeepL.com/Translator (free version)
