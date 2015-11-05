package com.company;

import java.util.Scanner;

public class Main {

    //The Consumer Key and Secret for TwitterSearchRueLaLa
    private static final String CONSUMER_KEY = "Z1M4uaGJSyT3dMBZJeUFZ1X6P";
    private static final String CONSUMER_SECRET = "h42Oz9zsakFrXodeDgFqCAH25XNK758CcMpBOs88iMjgq1taiS";


    /**
     * My main method that prompts the user for Consumer Key/Secret and
     * creates a Client. Allows user to:
     *      -update Twitter Status
     *      -search for tweets containing a given phrase
     *      -search for tweets from a certain user
     *      -search for tweets to a certain user
     * @param args
     */
    public static void main(String[] args) {

        //keeps track whether the method is over
        boolean isOver = false;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello! Start by inputting your Consumer Key: ");
        String conKey = scanner.nextLine();
        System.out.println("Now input your Consumer Secret: ");
        String conSec = scanner.nextLine();
        System.out.println("Thanks!");

        //creates a client using Consumer Key and Secret
        Client client = new Client(conKey, conSec);

        //sends user to authorization URL to get pin
        System.out.println("Copy and paste this URL into a web browser: ");
        System.out.println(client.getUrl());
        System.out.print("Enter the PIN from Twitter: ");
        String pin = scanner.nextLine();

        //sets the authorization pin in the client
        client.setPin(pin);


        //keeps prompting the user until isOver == true
        while (!isOver) {
            //user has 5 options
            System.out.println("What do you want to do? (\"tweet\", " +
                    "\"search phrase\", " + "\"tweets from\", " + "\"tweets to\", or \"exit\")");
            String input = scanner.nextLine().toLowerCase();

            //calls sendTweet() on client
            if (input.equals("tweet")) {
                System.out.println("What do you want to tweet?");
                String tweet = scanner.nextLine();
                client.sendTweet(tweet);
            }

            //calls searchPhrase on client
            else if (input.equals("search phrase")) {
                System.out.println("What phrase?");
                String phrase = scanner.nextLine();

                client.searchPhrase(phrase);
            }

            //calls tweetsFrom() on client
            else if (input.equals("tweets from")) {
                System.out.println("From whom?");
                String username = scanner.nextLine();
                client.tweetsFrom(username);
            }

            //calls tweetsTo() on client
            else if (input.equals("tweets to")) {
                System.out.println("To whom?");
                String username = scanner.nextLine();
                client.tweetsTo(username);
            }

            //sets isOver to true (ending the while loop)
            else if (input.equals("exit")){
                isOver = true;
            }

            //prompts user again for a valid input
            else {
                System.out.println("Invalid input, try again!");
            }
        }
    }
}
