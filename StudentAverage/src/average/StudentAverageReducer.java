package average;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

import util.StudentRecord;

public class StudentAverageReducer extends MapReduceBase implements
		Reducer<Text, IntWritable, Text, FloatWritable> {

//	public void reduce(Text key, Iterator<StudentRecord> values,
//			OutputCollector<Text, FloatWritable> output, Reporter reporter)
//			throws IOException {
//
//		StudentRecord record = new StudentRecord();
//		float average = 0;
//		int sum = 0;
//
//		while (values.hasNext()) {
//			record = values.next();
//			sum = sum + record.getMarks().get();
//		}
//
//		average = (float) sum / 6;
//
//		Text rollAndSchool = new Text(key.toString() + "\t"
//				+ record.getSchoolName());
//
//		output.collect(rollAndSchool, new FloatWritable(average));
//	}
	
	public void reduce(Text key, Iterator<IntWritable> values,
			OutputCollector<Text, FloatWritable> output, Reporter reporter)
			throws IOException {

		StudentRecord record = new StudentRecord();
		float average = 0;
		int sum = 0;
        int counter=0;
//        for(IntWritable mark : values){
//        
//        }
	
        while(values.hasNext()){
        IntWritable marks=values.next();
        sum+=marks.get();
        	counter++;
        }
        average = (float) sum / counter;
		int maxValue=0;
	//	maxValue = Math.max(maxValue, average.get());
		
		output.collect(key, new FloatWritable(average));
		
		}
}