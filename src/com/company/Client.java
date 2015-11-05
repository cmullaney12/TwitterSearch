package com.company;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;


/**
 * Created by Colin on 11/4/2015.
 */

/**
 * Client class that creates the Twitter singleton and runs multiple methods
 */
public class Client {

    Twitter twitter;
    RequestToken rt;

    //constructor for Client
    public Client(String consumer_key, String consumer_secret) {

        //initializes twitter object using Consumer Key and Secret
        this.twitter = TwitterFactory.getSingleton();
        twitter.setOAuthConsumer(consumer_key, consumer_secret);

        try {
            rt = twitter.getOAuthRequestToken();
        }
        catch (TwitterException e) {
            System.out.println("Requesting OAuth token failed");
            System.exit(-1);
        }
    }

    //returns the authentication URL so the user can get the acces pin
    public String getUrl() {
        return rt.getAuthenticationURL();
    }

    //gets the AccessToken using the RequestToken and the given pin
    public void setPin(String pin) {
        AccessToken token = null;

        try {
            token = twitter.getOAuthAccessToken(rt, pin);
        }
        catch (TwitterException e) {
            System.out.println("Are you sure your PIN was correct?");
            System.exit(-1);
        }
        //sets the AccessToken
        twitter.setOAuthAccessToken(token);
    }

    //updates twitter status to given phrase
    public void sendTweet(String phrase) {
        StatusUpdate status = new StatusUpdate(phrase);
        try {
            twitter.updateStatus(status);
        }
        catch (TwitterException e){
            System.out.print("Illegal status");
        }
    }

    //outputs up to 15 tweets containing the given phrase
    public void searchPhrase(String phrase) {

        try {
            Query q = new Query();
            q.setQuery(phrase);
            q.setCount(15);
            q.setLang("en");

            QueryResult r = twitter.search(q);

            for (Status s : r.getTweets()) {
                System.out.printf("@%s tweeted: %s at %s \n \n",
                        s.getUser().getScreenName(), s.getText(), s.getCreatedAt());
            }
        }
        catch (TwitterException e) {
            System.out.println("Phrase not valid");
        }
    }

    //outputs up to 15 tweets from the given username
    public void tweetsFrom(String username) {
        try {
            Query q = new Query();
            q.setQuery("from:" + username);
            q.setCount(15);
            q.setLang("en");

            QueryResult r = twitter.search(q);

            for (Status s : r.getTweets()) {
                System.out.printf("@%s tweeted: %s at %s \n \n",
                        s.getUser().getScreenName(), s.getText(), s.getCreatedAt());
            }
        }
        catch (TwitterException e) {
            System.out.println("Username not found");
        }
    }

    //outputs up to 15 tweets sent to the given username
    public void tweetsTo(String username) {
        try {
            Query q = new Query();
            q.setQuery("to:" + username);
            q.setCount(15);
            q.setLang("en");

            QueryResult r = twitter.search(q);

            for (Status s : r.getTweets()) {
                System.out.printf("@%s tweeted: %s at %s \n \n",
                        s.getUser().getScreenName(), s.getText(), s.getCreatedAt());
            }
        }
        catch (TwitterException e) {
            System.out.println("Username not found");
        }
    }
}
