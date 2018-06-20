package fare_better_problem_9d;

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

public class StudentAverageReducer extends MapReduceBase implements Reducer<Text, StudentRecord, Text, Text>{

	public void reduce(Text key, Iterator<StudentRecord> value,
			OutputCollector<Text, Text> output, Reporter reporter)
			throws IOException {
		// TODO Auto-generated method stub
        StudentRecord record=new StudentRecord();
        int girlsCount=0;
        int boysCount=0;
        float totalMarksOfGirls=Float.MIN_VALUE;
        float totalMarksOfBoys=Float.MIN_VALUE;
		//float maxAverageMarks = Float.MIN_VALUE;
		while(value.hasNext()){
			record = value.next();
			//System.out.println(record.getGender());
			if(record.getGender().toString().equals("F")){
				System.out.println(record.getGender());
				girlsCount++;
				totalMarksOfGirls+=record.getAverageMarks().get();
			}else if(record.getGender().toString().equals("M")){
				System.out.println(record.getGender());
				boysCount++;
				totalMarksOfBoys+=record.getAverageMarks().get();
			}
//			float averageMarks=record.getAverageMarks().get();
//			if(maxAverageMarks<averageMarks){
//				maxAverageMarks=averageMarks;
//			}
		}
		System.out.println("total marks for boys :"+totalMarksOfBoys+" total count is :"+boysCount);
		System.out.println("total marks for girls :"+totalMarksOfGirls+" total count is :"+girlsCount);
		
		
		Text fairAndBetter;
		float avgMarksForAllGirls=(float)totalMarksOfGirls/girlsCount;
		float avgMarksForAllBoys=(float)totalMarksOfBoys/boysCount;
		if(avgMarksForAllBoys>avgMarksForAllGirls){
			fairAndBetter=new Text("Boys are fare and better than girls because boys average marks is :"+avgMarksForAllBoys+" is greater than girls average marks :"+avgMarksForAllGirls);
		}else{
			fairAndBetter=new Text("Girls are better than Boys because Girls average marks is :"+avgMarksForAllGirls+" is greater than boys average marks :"+avgMarksForAllBoys);
		}
		
		//Text outputKey=new Text(record.getClss() + "\t"+record.getRoll() + "\t"+ record.getSchoolName()+ "\t"+record.getStudentName()+ "\t"+record.getAge()+ "\t"+record.getGender());
	output.collect(key, fairAndBetter);
	}
	
	

}
