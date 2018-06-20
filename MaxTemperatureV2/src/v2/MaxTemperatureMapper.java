package src.v2;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class MaxTemperatureMapper  extends Mapper<LongWritable, Text, Text, IntWritable> {
	
	enum Temperatue{
		OVER_100
	}
private NcdcRecordParser parser = new NcdcRecordParser();
@Override
public void map(LongWritable key, Text value, Context context)
throws IOException, InterruptedException {
parser.parse(value);
if (parser.isValidTemperature()) {
	int airTemp=parser.getAirTemperature();
	if(airTemp>1000){
		System.err.println("Temperature over 100 degree for intput "+value);
		context.setStatus("Detected possibly corrupt records: see logs");
		context.getCounter(Temperatue.OVER_100).increment(1);
	}
	context.write(new Text(parser.getYear()),
			new IntWritable(parser.getAirTemperature()));
			
    }
	
 }


}