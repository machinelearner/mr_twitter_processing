package com.thoughtworks.sprinklr.demo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.String.format;

public class TweetParser {
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("E MMM d k:m:s Z y");
    private String id;
    private String username;
    private boolean retweet;
    private String tweet;
    private Date date;
    private String via;
    private String userId;

    public TweetParser(String text) {
        try {
            String[] content = text.split("\t");
            userId = content[0];
            Document document = Jsoup.parse(text);
            id = document.select("a[rel=bookmark]").attr("href").replaceFirst(".*/status/", "");
            username = parse(document, "a.username");
            this.tweet = document.select("span.entry-content").text();
            retweet = parse(document, "span.entry-content").startsWith("RT");
            via = document.select("span.meta span a").text();
            String dateText = document.select("span.timestamp").attr("data").replace("{time:'", "").replace("'}", "");
            date = DATE_FORMAT.parse(dateText);
        } catch (Throwable e) {
            System.err.println("Encountered error while parsing date: " + e.getMessage());
        }
    }

    private String parse(Document document, String cssQuery) {
        Element element = document.select(cssQuery).first();
        return element == null ? "N/A" : element.text();
    }

    public String username() {
        return username;
    }

    public boolean isRetweet() {
        return retweet;
    }

    public String tweet() {
        return tweet;
    }

    public Date time() {
        return date;
    }

    public String via() {
        return via;
    }

    public String id() {
        return id;
    }

    @Override
    public String toString() {
        return "TweetParser{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", retweet=" + retweet +
                ", tweet='" + tweet + '\'' +
                ", date=" + date +
                ", via='" + via + '\'' +
                '}';
    }

    public String csv() {
        return format("%s\t%s\t%s\t%s\t%s", username, retweet, date, via, tweet);
    }

    public static void main(String[] args) {
        String csv = new TweetParser(args[0]).csv();
        System.out.println("csv = " + csv);
    }

    public String userId() {
        return userId;
    }
}