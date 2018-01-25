# The tuple returned from a native query was all lowercase, now is all upper case.

Not a bug, but changed behavior due to https://hibernate.atlassian.net/browse/HHH-12119

## Run with 

    cd orm/hibernate-orm-5/   

Followed by one of: 

    mvn clean install -D version.org.hibernate=5.2.12.Final

    mvn clean install -D version.org.hibernate=5.2.13-SNAPSHOT

    mvn clean install -D version.org.hibernate=5.3.0-SNAPSHOT
    
Note that the first one succeeds, while the other two fail.