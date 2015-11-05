# TwitterSearch
A basic command line Java program to search through twitter and post tweets.

When the program is run, the user will be prompted to enter his/her Consumer Key and Consumer Secret.
After which, the user will be given an Authentication URL from Twitter to get an AccessToken PIN.
The user inputs this PIN, and if it is valid, can begin to interact with Twitter.

The user is given 5 options:  
  -tweet (updates twitter status)  
  -search phrase (outputs tweets containing a phrase)  
  -tweets from (outputs tweets sent from a certain user)  
  -tweets to (outputs tweets sent to a certain user)  
  -exit (exits the program)  
  
If the user does not select one of these 5 options, they will be prompted again.

The two main classes, Main.java and Client.java, are included in the src folder.
The twitter4j library has also been included.

It is easiest to git clone this repo and run the code in an IDE for java. I used IntelliJ.  
Make sure to add the twitter4j jar before running.
