## Run with 

    cd orm/hibernate-orm-5/   

    mvn clean test

Results in a single failing test because `TupleElement.getAlias()` returns `null`.
