package for_project1;

public class Movie {
	
	protected String title; 
	protected String character;
	
	public Movie(String theTitle, String theCharacter) {
		this.title = theTitle; 
		this.character = theCharacter; 
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCharacter() {
		return character;
	}
	public void setCharacter(String character) {
		this.character = character;
		
	} 
	
	@Override
	public String toString() {
		return "Movie:" + title + "as" + character;
		
	}
	
	
	
	

}
