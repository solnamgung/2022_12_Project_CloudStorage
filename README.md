Cloud Storage, Java
I am aiming to make a dent in the Cloud Storage market and is already facing stiff competition from rivals like Google Drive and Dropbox. That hasn’t dampened our spirits at all, however. I want to include personal information management features in the application to differentiate them from the competition, and the minimum viable product includes three user-facing features:

Simple File Storage: Upload/download/remove files
Note Management: Add/update/remove text notes
Password Management: Save, edit, and delete website credentials.
In this porject, I am responsible for developing the server, website, and tests, but other tasks like deployment belong to other teams at the company. I upload it on my Github.

Roadmap
The back-end with Spring Boot
The front-end with Thymeleaf
Application tests with Selenium
The Back-End
The back-end is all about security and connecting the front-end to database data and actions.

1. Managing User Access with Spring Security
Restrict unauthorized users from accessing pages other than the login and signup pages. To do this, I create a security configuration class that extends the WebSecurityConfigurerAdapter class from Spring. I place this class in a package reserved for security and configuration. Often this package is called security or config.
Spring Boot has built-in support for handling calls to the /login and /logout endpoints. I use the security configuration to override the default login page with one of your own, discussed in the front-end section.
I also need to implement a custom AuthenticationProvider which authorizes user logins by matching their credentials against those stored in the database.
2. Handling Front-End Calls with Controllers
Write controllers for the application that bind application data and functionality to the front-end. That means using Spring MVC’s application model to identify the templates served for different requests and populating the view model with data needed by the template.
The controllers I write should also be responsible for determining what, if any, error messages the application displays to the user. When a controller processes front-end requests, it should delegate the individual steps and logic of those requests to other services in the application, but it should interpret the results to ensure a smooth user experience.
(If you find yourself repeating tasks over and over again in controller methods, or your controller methods are getting long and complicated, consider abstracting some methods out into services! For example, consider the HashService and EncryptionService classes included in the starter code package service. These classes encapsulate simple, repetitive tasks and are available anywhere dependency injection is supported. Think about additional tasks that can be similarly abstracted and reused, and create new services to support them!)

3. Making Calls to the Database with MyBatis Mappers
These should be POJOs (Plain Old Java Objects) with fields that match the names and data types in the schema, and I create one class per database table. These classes typically are placed in a model or entity package.
To connect these model classes with database data, implement MyBatis mapper interfaces for each of the model types. These mappers should have methods that represent specific SQL queries and statements required by the functionality of the application. They should support the basic CRUD (Create, Read, Update, Delete) operations for their respective models at the very least. I place these classes in the mapper package.
The Front-End
1. Login Page
Everyone should be allowed access to this page, and users can use this page to login to the application.
Show login errors, like invalid username/password, on this page.

Login page: http://localhost:8080/login

2. Signup Page
Everyone should be allowed access to this page, and potential users can use this page to sign up for a new account.
Validate that the username supplied does not already exist in the application, and show such signup errors on the page when they arise.
Store the user’s password securely!

Sign Up page: http://localhost:8080/signup

3. Home Page
The home page should have a logout button that allows the user to log out of the application and keep their data private.
The home page is the center of the application and hosts the three required pieces of functionality. The existing template presents them as three tabs that can be clicked through by the user:
i. Files
The user should be able to upload files and see any files they previously uploaded.

Home page: http://localhost:8080/home

2. The user should be able to view/download or delete previously-uploaded files.

3. Any errors related to file actions should be displayed. For example, a user should not be able to upload two files with the same name, but they’ll never know unless you tell them!

ii. Notes

The user should be able to create notes and see a list of the notes they have previously created.

2. The user should be able to edit or delete previously-created notes.

iii. Credentials:

The user should be able to store credentials for specific websites and see a list of the credentials they’ve previously stored. If you display passwords in this list, make sure they’re encrypted!

2. The user should be able to view/edit or delete individual credentials. When the user views the credential, they should be able to see the unencrypted password.


Testing
1. Write Tests for User Signup, Login, and Unauthorized Access Restrictions.
Write a test that verifies that an unauthorized user can only access the login and signup pages.
Write a test that signs up a new user, logs in, verifies that the home page is accessible, logs out, and verifies that the home page is no longer accessible.
2. Write Tests for Note Creation, Viewing, Editing, and Deletion.
Write a test that creates a note, and verifies it is displayed.
Write a test that edits an existing note and verifies that the changes are displayed.
Write a test that deletes a note and verifies that the note is no longer displayed.
3. Write Tests for Credential Creation, Viewing, Editing, and Deletion.
Write a test that creates a set of credentials, verifies that they are displayed, and verifies that the displayed password is encrypted.
Write a test that views an existing set of credentials, verifies that the viewable password is unencrypted, edits the credentials, and verifies that the changes are displayed.
Write a test that deletes an existing set of credentials and verifies that the credentials are no longer displayed.
Password Security
- HashService
byte[] salt = new byte[16];
random.nextBytes(salt);
String encodedSalt = Base64.getEncoder().encodeToString(salt);
String hashedPassword = hashService.getHashedValue(plainPassword, encodedSalt);
return hashedPassword;
- EncryptionService
SecureRandom random = new SecureRandom();
byte[] key = new byte[16];
random.nextBytes(key);
String encodedKey = Base64.getEncoder().encodeToString(key);
String encryptedPassword = encryptionService.encryptValue(password, encodedKey);
String decryptedPassword = encryptionService.decryptValue(encryptedPassword, encodedKey);
Hash Function
Encryption
