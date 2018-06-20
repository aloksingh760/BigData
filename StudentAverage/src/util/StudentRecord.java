package util;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

public class StudentRecord implements WritableComparable<StudentRecord> {

	private LongWritable roll;
	private Text schoolName;
	private Text studentName;
	private IntWritable age;
	private Text gender;
	private Text clss;
	private Text subject;
	private IntWritable marks;

	public StudentRecord() {
		set(new LongWritable(), new Text(), new Text(), new IntWritable(),
				new Text(), new Text(), new Text(), new IntWritable());
	}

	private void set(LongWritable roll, Text schoolName, Text studentName,
			IntWritable age, Text gender, Text clss, Text subject,
			IntWritable marks) {

		this.roll = roll;
		this.schoolName = schoolName;
		this.studentName = studentName;
		this.age = age;
		this.gender = gender;
		this.clss = clss;
		this.subject = subject;
		this.marks = marks;

	}

	@Override
	public void readFields(DataInput in) throws IOException {

		roll.readFields(in);
		schoolName.readFields(in);
		studentName.readFields(in);
		age.readFields(in);
		gender.readFields(in);
		clss.readFields(in);
		subject.readFields(in);
		marks.readFields(in);

	}

	@Override
	public void write(DataOutput out) throws IOException {

		roll.write(out);
		schoolName.write(out);
		studentName.write(out);
		age.write(out);
		gender.write(out);
		clss.write(out);
		subject.write(out);
		marks.write(out);

	}

	@Override
	public int hashCode() {

		return roll.hashCode() + schoolName.hashCode() + studentName.hashCode()
				+ age.hashCode() + gender.hashCode() + clss.hashCode()
				+ subject.hashCode() + marks.hashCode();

	}

	@Override
	public boolean equals(Object o) {

		if (o instanceof StudentRecord) {
			StudentRecord sr = (StudentRecord) o;
			return roll.equals(sr.roll) && schoolName.equals(sr.schoolName)
					&& studentName.equals(sr.studentName) && age.equals(sr.age)
					&& gender.equals(sr.gender) && clss.equals(sr.clss)
					&& subject.equals(sr.subject) && marks.equals(sr.marks);
		}
		return false;

	}

	@Override
	public String toString() {

		return roll + "," + schoolName + "," + studentName + "," + age
				+ "," + gender + "," + clss + "," + subject + "," + marks;

	}

	@Override
	public int compareTo(StudentRecord sr) {

		int cmp = roll.compareTo(sr.roll);
		if (cmp != 0) {
			return cmp;
		}

		cmp = schoolName.compareTo(sr.schoolName);
		if (cmp != 0) {
			return cmp;
		}

		cmp = studentName.compareTo(sr.studentName);
		if (cmp != 0) {
			return cmp;
		}

		cmp = age.compareTo(sr.age);
		if (cmp != 0) {
			return cmp;
		}

		cmp = gender.compareTo(sr.gender);
		if (cmp != 0) {
			return cmp;
		}

		cmp = clss.compareTo(sr.clss);
		if (cmp != 0) {
			return cmp;
		}

		cmp = subject.compareTo(sr.subject);
		if (cmp != 0) {
			return cmp;
		}

		return marks.compareTo(sr.marks);
	}

	public LongWritable getRoll() {
		return roll;
	}

	public void setRoll(LongWritable roll) {
		this.roll = roll;
	}

	public Text getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(Text schoolName) {
		this.schoolName = schoolName;
	}

	public Text getStudentName() {
		return studentName;
	}

	public void setStudentName(Text studentName) {
		this.studentName = studentName;
	}

	public IntWritable getAge() {
		return age;
	}

	public void setAge(IntWritable age) {
		this.age = age;
	}

	public Text getGender() {
		return gender;
	}

	public void setGender(Text gender) {
		this.gender = gender;
	}

	public Text getClss() {
		return clss;
	}

	public void setClss(Text clss) {
		this.clss = clss;
	}

	public Text getSubject() {
		return subject;
	}

	public void setSubject(Text subject) {
		this.subject = subject;
	}

	public IntWritable getMarks() {
		return marks;
	}

	public void setMarks(IntWritable marks) {
		this.marks = marks;
	}

}