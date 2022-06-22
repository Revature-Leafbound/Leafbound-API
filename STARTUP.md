# Leafbound-API Startup Documentation

Github repository: [Leafbound-API](https://github.com/Revature-Leafbound/Leafbound-API.git)

## 1. Clone the project repository

1. Navigate to the main page of the repository here: https://github.com/Revature-Leafbound/Leafbound-API
2. Above the list of files, click on the green “**Code**” button.
3. To clone the repository using HTTPS, make sure that you are on the HTTPS tab.
4. Then, copy the URL for the repository, in this case it should be: https://github.com/Revature-Leafbound/Leafbound-API.git
5. Now that you are done copying the project URL, open Git Bash.
6. Change the current working directory to the location where you want the cloned directory.
7. Type `git clone`, and then paste the URL you copied earlier. Press Enter to create your local clone.
   - $ `git clone https://github.com/Revature-Leafbound/Leafbound-API.git`
8. Press Enter to create your local clone.
9. Wait for it to finish cloning and going through the steps.

   > Cloning into `Specified Directory`...
   >
   > remote: Counting objects: 10, done.
   >
   > remote: Compressing objects: 100% (8/8), done.
   >
   > remove: Total 10 (delta 1), reused 10 (delta 1)
   >
   > Unpacking objects: 100% (10/10), done.

10. Once complete, open the project in your desired IDE. We now have to set up the system environment variables in order to connect to a database.

## 2. Set system environment variables

1. To create or modify environment variables on Windows 10:

   1. On the Windows taskbar, right-click the Windows icon and select System.
   2. In the Settings window, under Related Settings, click Advanced system settings.
   3. On the Advanced tab, click Environment Variables.
   4. Click New to create a new environment variable.

2. We need to set up four separate variables here. They should be as follows:

   - Leafbound_DB_URL = jdbc:postgresql://localhost:5432/postgres
   - Leafbound_DB_User = [*This should be your local database user info*]
   - Leafbound_DB_Password = [*This should be your local database password*]
   - Leafbound_JWT_Secret = iyvbunoimokg[20upo8yi7gyvhbjknlm[p-=o0987ytfghvbjkgjhoi

3. Now that those are entered, click okay and close all windows. You must restart your device for the changes to the system environment variables to take effect.
4. Okay, now that you have restarted your device, it is time to proceed with launching the application.

## 3. Connect to the backend and run the application

1. Return to your IDE where you first cloned the project. Navigate through the file structure until locating the “_LeafboundApplication.java_” file as shown below.
2. Now right click on the “_LeafboundApplication.java_” file and select, “**Run As**” and then click, “**Spring Boot App**”
3. After the project has finished loading, it is now live and running.

## 4. Clone the Leafbound repository and start the Development server to check out the full functionality of the Leafbound E-commerce site.

For further details on connecting to the frontend, click [here](https://github.com/Revature-Leafbound/Leafbound-API/blob/main/STARTUP.md).
