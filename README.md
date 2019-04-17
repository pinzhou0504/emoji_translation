# emojiHW
emoji homework for 04/17/19
if restart the laptop, docker needs to be restarted: docker start container ID;
creating a database called emojiHW in PgAdmin;
Open a new IntelliJ project, called emojiHW;
In terminal, touch a file called pom.xml. Copy & paste the content from previous emoji project, 
then make it as a maven project;
In terminal, mkdir a folder called src, 
In IntelliJ, add Directory main -> java (source root) -> com -> emojiHW (put emojiHW folder into com folder) -> config ->AppCongif (Java class) ->DatabaseConfig (Java class)
                                                                                                             -> domain ->Emoji (Java class) -> Users (Java class)
                                -> resources (right click 'emoji' on the top -> open module setting -> change to source-type file) -> V1.0...
                                -> write all versions of sql commands;
                                -> creating users and emoji tables
                                -> webapp -> WEB-INF -> web.xml
In terminal, touch .gitignore, ignore .idea, target, out, emoji.iml... either add from IntelliJ, or in the terminal: vi .gitignore
                                                                                                                     i
                                                                                                                     filesName
                                                                                                                     esc
                                                                                                                     :x/:wq
When deleting a database: 1. disconnect from current databse;
                          2. in other database, drop database deleteDatabse;
                          3. docker ps 
                             docker stop databseName (dealerDB)
                             docker start databseName (dealerDB);
                          4. in other database, create database deleteDatabase;


* under resources, create a whatever java class, then create two directories called db and migration seperatly, drug migration file into db folder, then delete whatever java class;
* whenever making changes, making a new sql version;
* any changes in sql version, changes should appear in the domain file, too;
* before mvn clean compile flyway:migrate, making sure all the postgres location, userID, password are right (AppCongif, DatabaseConfig, pom...);
* tring to remove exist files in GitHub: git rm -- cached -r fileName
* making a column to not null: alter table tableName alter column columnName set default 'default value';
                               alter table tableName alter column columnName set not null;      
* each project needs to create a new repository: git init
                                                 git add 
                                                 git commit -m 'specific memo'
                                                 git remote add origin SSH
                                                 git push -u origin master (every first time, next time can just use git push)
* In terminal, 'checksum' means schema is been eddited, need to delete docker and start over;


**bigint: larger range than int
**pgAdmin: database client
**Python: dynamic language
  Java: strong type language has specific return method including void, int, string ...
**modifiers: private: cannot be inherated
             protected: only inherated subject can use, others cannot
             public ..
                       
                                
