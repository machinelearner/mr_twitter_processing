package com.thoughtworks.sprinklr.demo;


import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class TweetParseMapper extends Mapper<LongWritable, Text, Text, Text> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        TweetParser tweetParser = new TweetParser(value.toString());

        context.write(new Text(tweetParser.id()), new Text(tweetParser.csv()));
    }
}