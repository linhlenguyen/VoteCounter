#Vote counter system

The system has to store is a list of users and a list of which player each user has voted for.
This can be archived by Hash map/Dictionary from userId to a list of votes
Each user can only have a limited number of votes, therefore, the list of votes can be implemented as a bounded set that only takes into account n most recent votes
The system has to be able to handle and queries millions of records to count the total number of votes in under 1 second. This is not easy to archive if we have to re-compute the result every time a new entry is added. I've tried out a quick solution to profile the time in Haskell, and the best result I've got for 10 millions records were about 9->12 seconds. This is not fast enough.
Therefore, it is necessary to compute the results as soon an entry is added. By maintain another Hash map from Players to the number of votes they have received and updating this map, we can calculate the results in milliseconds
Since the map is only as big as the total number of player, 5 in the example, it requires very little space.

##Summary the solution
* When an entry is added
* The userId and their votes is added to a Hash map
* Read user previous votes if the user has exceed their total number of votes
* Subtract the previous votes count from the total vote count map
* Increment the total vote count for the new votes

Vote results can be read directly from the total vote count map

##Performance analysis
* Insert into first Hash map takes O(1) -> O(log n) (depending on the map implementation)
* Read an exiting entry from first map takes O(1)
* Update existing entry from second map takes O(1)
* Insert into second Hash map takes O(1) (since size of the map is fixed)

The solution takes no longer than O(log n) time.
It takes a bit of time to set up millions of records at the beginning, however after that insert and queries time will be O(1) -> O(log n) time

##After thought
Next step would be to persist this information in a database. For this particular problem, a SQL database with 3 tables would be sufficient. One table storing user informations, potentially using their phone numbers as Id. One table for User and Vote link and another table for Vote to Player map.
It is difficult to find a free cloud-based solution that allow millions of records
A solution with a graph database like Neo4j can be quite interesting, because it can map out the relationship between groups of users and votes.

To compile
```
javac Main.java
```
To run
```
java Main
```

For component tests, download and add junit to your classPath and compile using
```
javac -cp .:junit-4.XX.jar [fileName].java
```
To run
```
java -cp .:junit-4.XX.jar:hamcrest-core-1.3.jar org.junit.runner.JUnitCore [className]
```
[More about JUnit](https://github.com/junit-team/junit4/wiki/Getting-started)
