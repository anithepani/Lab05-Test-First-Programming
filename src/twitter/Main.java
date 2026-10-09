/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

/**
 * This is the main program.
 * 
 * It reads a small sample of tweets from tweets.txt and prints some facts about
 * them. You may change this class if you wish, but you don't have to.
 */
public class Main {

    /**
     * Main method of the program. Reads the sample tweets from tweets.txt and
     * prints their timespan, the mentioned users, the tweets written by alyssa,
     * and the tweets containing "rivest" or "pset".
     * 
     * @param args command-line arguments (not used)
     * @throws IOException if tweets.txt cannot be read
     */
    public static void main(String[] args) throws IOException {
        final List<Tweet> tweets = readTweetsFromFile("tweets.txt");
        System.out.println("read " + tweets.size() + " tweets");

        final Timespan span = Extract.getTimespan(tweets);
        System.out.println("timespan: " + span.getStart() + " to " + span.getEnd());

        final Set<String> mentionedUsers = Extract.getMentionedUsers(tweets);
        System.out.println("mentioned users: " + mentionedUsers);

        final List<Tweet> byAlyssa = Filter.writtenBy(tweets, "alyssa");
        System.out.println("tweets written by alyssa: " + byAlyssa.size());
        for (Tweet tweet : byAlyssa) {
            System.out.println("  " + tweet);
        }

        final List<Tweet> relevant = Filter.containing(tweets, Arrays.asList("rivest", "pset"));
        System.out.println("tweets containing \"rivest\" or \"pset\": " + relevant.size());
        for (Tweet tweet : relevant) {
            System.out.println("  " + tweet);
        }
    }

    /*
     * Read a list of tweets from a JSON file with the same shape as the sample
     * used by PS1.
     */
    private static List<Tweet> readTweetsFromFile(String filename) throws IOException {
        final Reader reader = new FileReader(filename);
        final JsonReader jsonReader = Json.createReader(reader);
        final JsonArray array = jsonReader.readArray();
        final List<Tweet> tweets = new ArrayList<Tweet>();
        for (int i = 0; i < array.size(); i++) {
            final JsonObject object = array.getJsonObject(i);
            final long id = Long.parseLong(object.get("id").toString());
            final String author = object.getJsonObject("user").getString("screen_name");
            final String text = object.getString("text");
            final ZonedDateTime timestamp = ZonedDateTime.parse(object.getString("created_at"),
                    DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss Z yyyy", Locale.US));
            tweets.add(new Tweet(id, author, text, timestamp.toInstant()));
        }
        return tweets;
    }

}
