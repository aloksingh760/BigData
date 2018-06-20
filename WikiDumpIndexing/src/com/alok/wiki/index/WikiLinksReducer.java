package com.alok.wiki.index;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;


public class WikiLinksReducer extends Reducer<Text, Text, Text, Text> {

	
    @Override
    public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        //String pagerank = "1.0\t";
    	String pages ="";
        for (Text value : values) {
        	String word=value.toString()+",";
        	if(!pages.contains(word)){
        	pages=pages+word;
        	}
        }
        context.write(key, new Text(pages));
    }
}
