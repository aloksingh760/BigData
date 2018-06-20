package util;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

public class MovieRecord implements WritableComparable<MovieRecord>{

	private IntWritable movieId;
	private Text movieName;
	private IntWritable releaseYear;
	private  IntWritable movieRating;
	
	
	
	
	public void readFields(DataInput arg0) throws IOException {
		// TODO Auto-generated method stub
		
	}

	public void write(DataOutput arg0) throws IOException {
		// TODO Auto-generated method stub
		
	}

	public int compareTo(MovieRecord o) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public IntWritable getMovieId() {
		return movieId;
	}

	public void setMovieId(IntWritable movieId) {
		this.movieId = movieId;
	}

	public Text getMovieName() {
		return movieName;
	}

	public void setMovieName(Text movieName) {
		this.movieName = movieName;
	}

	public IntWritable getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(IntWritable releaseYear) {
		this.releaseYear = releaseYear;
	}

	public IntWritable getMovieRating() {
		return movieRating;
	}

	public void setMovieRating(IntWritable movieRating) {
		this.movieRating = movieRating;
	}


}
