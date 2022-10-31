package for_project1;
import java.util.ArrayList;


public class Actor2 {
	
	protected String name; 
	protected ArrayList<Movie> movies; 
	
	public Actor2(String theName) {
		this.name = theName; 
		movies = new ArrayList<Movie>();
	}
	
	public String getActor() {
		return name;
	}


	public void setActor(String name) {
		this.name = name;
	}

	public void getMovies() {
		for( int i =0; i< movies.size(); i++) {
			System.out.println(movies.get(i).toString());
		}
	}
	
	public void add(Movie newMovie) {
		movies.add(newMovie);
	}
	

}
