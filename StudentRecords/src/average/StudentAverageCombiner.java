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

public class StudentAverageCombiner extends MapReduceBase implements
		Reducer<Text, StudentRecord, Text, StudentRecord> {

	public void reduce(Text key, Iterator<StudentRecord> values,
			OutputCollector<Text, StudentRecord> output, Reporter reporter)
			throws IOException {

		StudentRecord record = new StudentRecord();
		float average = 0;
		int sum = 0;
		int counter=0;

		while (values.hasNext()) {
			record = values.next();
			sum = sum + record.getMarks().get();
			counter++;
		}

		average = (float) sum /counter;
		record.setAverageMarks(new FloatWritable(average));
      //  Text outputValue=new Text(record.getRoll()+"\t"+record.getSchoolName()+"\t"+record.getStudentName()+"\t"+record.getAge()+"\t"+record.getGender()+"\t"+average);
		output.collect(new Text(record.getSchoolName()+","+record.getClss()), record);
	}
	

}