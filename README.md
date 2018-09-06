Database:
---

MYSQL:
Database name = cs338
Table name = USERDATA

query: 

```sql
        CREATE database cs338;
        use cs338;
        CREATE TABLE cs338.USERDATA (
            id int PRIMARY KEY AUTO_INCREMENT,
            username varchar(255),
            password varchar(255),
            email varchar(255),
            phone VARCHAR(10)
        );

        INSERT INTO cs338.USERDATA(username,password,email,phone) VALUE ('test','test','bb822@drexel.edu','1111111');
```

Application Properties:
---

   ```
        binod.cs338.datasource.username=binodbhandari
        binod.cs338.datasource.password=password
   ```
Need to update application.properties file for SQL username and password everything else can be the same
if the above steps are followed. 

IDE DataSource Configuration: 
---

We need to add the database/data source configuration in the IDE. I have attached the picture for it.  

Since we need to update the application.properties file, we need to build it again. Please follow the following steps to generate the executable file. 


Gradle:
---
    Step:
    Use Command: `gradle clean build`

This will generate the new build folder and replace the existing one.
Path for the build file: `/CS338-Project/build/distributions`

`swing-project-java-1.0-SNAPSHOT.zip` will be generated inside distributions. 

Executable File:
---

Since this application load application.properties file, I created a different command in the build.gradle
that generate an executable file that will load the picture and `application.properties` file. 

Unzip swing-project-java-1.0-SNAPSHOT.zip, this will contain two folders bin and lib. 

bin contains the executable file: `swing-project-java`

lib contains all the jar file necessary for the project.

Simply double click the executable file, this will open the login page. 


Instruction For Using Application:
---

1. Enter username and password to log in
2. In case username and password is not in database click sign up
    Once the data is saved to the database, click login label which will take back to the login page. Enter your credentials.
3. Start the Server class
    
    ```java
        package com.binod.project.swing.chat;
        
        import java.io.*;
        import java.net.ServerSocket;
        import java.net.Socket;
        
        /**
         * Created by Binod Bhandari on 8/11/18.
         */
        public class Server {
        
            private static final ThreadClients[] threads = new ThreadClients[20];
        
            public void connectToServer() throws IOException{
                System.out.println("Group Chat Started");
        
                //server socket connection
                //listens to port 3456
                ServerSocket serverSocket = new ServerSocket(3456);
                do {
                    try {
                        Socket clientSocket = serverSocket.accept();
                        int i = 0;
                        //checks if the client Socket is null or not.
                        for (i = 0; i < 20; i++) {
                            if (threads[i] == null) {
                                (threads[i] = new ThreadClients(clientSocket, threads)).start();
                                break;
                            }
                        }
        
                    } catch (IOException e) {
                        System.out.print(e.toString());
                    }
                } while (true);
            }
            public static void main(String[] args) throws IOException {
                new Server().connectToServer();
            }
        }
    
    ```
4. Loading page will appear and after some time the channel page will open up. 
    It will fail to open if the server is not on. "Group Chat Started" message will appear in the console. 
5. Enter the name to start the channel, can run multiple instances on same time 
    to see a real-time message on different instances/thread. 
    "Adding this client to active client list Test" message will appear after entering the name. This will keep updated every time a new user is added. 
6. The left panel contains the channel and member panel where you can add the member name and 
    all the channels that you want to create. 
    Future: This can be updated from some database 
    (like Drexel classes and students enrolled in the class)
7. Enter exit to stop/kill the thread and that thread will stop receiving the message.





