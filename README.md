# Different implicit cascading behavior between Version 5.2.12.Final and 5.3.0-SNAPSHOT


## Run with 

    cd orm/hibernate-orm-5/   

    mvn clean install -D version.org.hibernate=5.2.12.Final

    mvn clean install -D version.org.hibernate=5.3.0-SNAPSHOT
    
Note that the first build fails with a test failure, while the second succeeds.

The test cases persist/merge one entity which references in its one id attribute a second entity WITHOUT any cascade annotation. Yet it seems to get saved except for when 'merge' is used in version 5.3