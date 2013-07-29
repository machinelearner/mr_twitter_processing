package com.thoughtworks.sprinklr.demo;

import org.junit.Test;

import java.util.Calendar;
import java.util.TimeZone;

import static junit.framework.Assert.assertEquals;

public class TweetParserTest {
    @Test
    public void testName() throws Exception {
/*
        String str = "<span class=\"status-content\">                  <span class=\"entry-content\">Fancy a cuppa? Who " +
                "has great afternoon tea venues they&apos;d like to add to this? <a href=\"http://bit.ly/iRmJwV\" " +
                "class=\"tweet-url web\" rel=\"nofollow\" target=\"_blank\">http://bit.ly/iRmJwV</a></span>          </span>    " +
                "<span class=\"meta entry-meta\" data=\"{}\">  <a class=\"entry-date\" rel=\"bookmark\" href=\"http://twitter.com/VisitEngland/status/71950884598317056\">    " +
                "<span class=\"published timestamp\" data=\"{time:&apos;Sat May 21 14:50:02 +0000 2011&apos;}\">about 3 hours ago</span></a>  " +
                "<span>via <a href=\"http://www.hootsuite.com\" rel=\"nofollow\">HootSuite</a></span>      </span>        <ul class=\"meta-data clearfix\" />  ";
*/
        String str = "1250349\t    <span class=\"status-content\">                  <span class=\"entry-content\">Ben&apos;s dad " +
                "Doug Roysdon is!  He will be helping us make/direct/choreograph a video for a song off of our latest " +
                "7&quot;.</span>          </span>    <span class=\"meta entry-meta\" data=\"{}\">  <a class=\"entry-date\" " +
                "rel=\"bookmark\" href=\"http://twitter.com/GodsAndQueens/status/71030673812619264\">    " +
                "<span class=\"published timestamp\" data=\"{time:&apos;Thu May 19 01:53:26 +0000 2011&apos;}\">" +
                "6:53 PM May 18th</span></a>  <span>via web</span>      </span>        " +
                "<ul class=\"meta-data clearfix\" />  \n";

        TweetParser tweetParser = new TweetParser(str);

        assertEquals("1250349", tweetParser.userId());
        Calendar instance = Calendar.getInstance();
        instance.set(2011, Calendar.MAY, 19, 1, 53, 26);
        instance.set(Calendar.MILLISECOND, 0);
        instance.setTimeZone(TimeZone.getTimeZone("UTC"));
        assertEquals(instance.getTime(), tweetParser.time());
        String text = "Ben's dad Doug Roysdon is! He will be helping us make/direct/choreograph a video for a song off of our latest 7\".";

        assertEquals(text, tweetParser.tweet());
    }
}
