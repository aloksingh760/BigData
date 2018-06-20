package src.v2;

import java.io.IOException;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.util.Tool;

public class MaxTemperatureWithCounter extends Configured implements Tool{

	
	enum Temperature{
		MISSING,
		MALFORMED
	}
	
	static class MaxTemperatureMapperCounter extends Mapper<LongWritable, Text, Text, IntWritable>{
		
		private NcdcRecordParser parser=new NcdcRecordParser();
		
		@Override
		public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
			
			parser.parse(value);
			if(parser.isValidTemperature()){
				
				int airTemp=parser.getAirTemperature();
				context.write(new Text(parser.getYear()), new IntWritable(airTemp));
			
			}
			
		}
	}
	@Override
	public int run(String[] arg0) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
