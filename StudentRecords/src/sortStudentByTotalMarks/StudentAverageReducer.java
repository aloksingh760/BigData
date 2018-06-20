package sortStudentByTotalMarks;

import java.io.IOException;
import java.util.Iterator;

import javax.sound.midi.Receiver;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

import util.StudentRecord;

public class StudentAverageReducer extends MapReduceBase implements Reducer<Text, StudentRecord, Text, FloatWritable>{

	public void reduce(Text key, Iterator<StudentRecord> value,
			OutputCollector<Text, FloatWritable> output, Reporter reporter)
			throws IOException {
		// TODO Auto-generated method stub
        StudentRecord record=new StudentRecord();
        float averageMarks=0;
		while(value.hasNext()){
			record = value.next();
			averageMarks=record.getAverageMarks().get();
			
		}
		
		Text outputKey=new Text(record.getRoll() + "\t"+ record.getSchoolName()+ "\t"+record.getStudentName()+ "\t"+record.getAge()+ "\t"+record.getGender()+"\t"+record.getClss());
	output.collect(outputKey, new FloatWritable(averageMarks));
	}
	
	

}
