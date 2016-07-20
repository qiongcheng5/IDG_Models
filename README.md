1. Database installation from the backup file        
	   mysql -u root -p
			mysql> create database monarch_dto;

        mysql -u root -p monarch_dto < monarch_dto.sql

2. Dependent software 
   2.a) cURL
   cURL is a lightweight, command-line tool for making HTTP requests without a web browser. cURL lets you try out various API requests in a command-line interface such as the command prompt in Windows or Terminal on the Mac. You don't need to build a working web application just to try out the APIs.
   
   2.a.1) Install cURL in windows
     - Go to http://curl.haxx.se/download.html and download the executable file
     - Unzip it and add its bin subdirectory path into the system environment variable PATH
     - Go to http://curl.haxx.se/docs/caextract.html and download the digital certificate file named cacert.pem to its bin subdirectory path
     
   2.a.2) Check whether cURL has been installed in Linux/MacOS by default
   
3. Configuration
   3.a) Change the basedir property in the file config.properties at the subdirectory src\dto\util
   3.b) Change the database settings in the file db.config at the subdirectory src\dto\util
   # IDG_Models
