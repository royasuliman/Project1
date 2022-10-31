package for_project1;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;



public class actor1 {
	
	static ArrayList<Actor2> actorArr = new ArrayList<>();
	
	
	public static ArrayList<String> jsonParser(String json){
		
		String[] values = json.split(",");
		ArrayList<String> result = new ArrayList<>(); 
		
		for(int i=0; i < values.length; i++) {
			if(values[i].indexOf("character")!= -1){
				int indexCharacter = values[i].indexOf("character") + 18;
				if (values[i].length() !=18) {
					
					String character = values[i].substring(18, values[i].length() -2);
					result.add(character);
					
				}
				
			}else if (values[i].indexOf("name") != -1) {
				int indexName =values[i].indexOf("name") +13; 
				String name = values[i].substring(13, values[i].length() -2);
				result.add(name);
			}
			
		}
		return result;
		
	}
	
	
	public static void readFile() throws IOException{
		
		FileReader csvFile = new FileReader("tmdb_5000_credits.csv");
		BufferedReader br = new BufferedReader(csvFile);
		
		String line = br.readLine();
		
		line = br.readLine();
		
		while (line != null) {
			
			String[] movie = line.split(",");
			
			String movieActor = movie[1];
			
			String[] list = line.split("\\[");
			
			ArrayList<String> returnArray = jsonParser(list[1]); 
			
			int j= 1; 
			for (int i = 0; i < returnArray.size()-1; i+= 2) {
				Actor2 actor = new Actor2(returnArray.get(j)); 
				j += 2; 
				if(actorArr.contains(actor)== false) {
					Movie newMovie = new Movie(movieActor, returnArray.get(i));
					actor.add(newMovie);
					actorArr.add(actor);
				}
				
			}
		line = br.readLine(); 
			
			
		}
		
		br.close();
		
		
    }
	
	public static String findSimilarActor(String name) {
		
		char[] inputActor = name.toCharArray();
 		int maxScore = 0;
		int count = 0;
		String actorSug = null;
		
 		
		for (int i = 0; i < actorArr.size() ; i++) {
			if(actorArr.get(i).getActor() != null) {
				char[] otherActor = (actorArr.get(i).getActor()).toCharArray();
				for (int j = 0; j < inputActor.length && j < otherActor.length ; j++) {
						if(inputActor[j] == otherActor[j]){
							count++;
						}
						if(maxScore < count) {
							maxScore = count;
							actorSug = actorArr.get(i).getActor();
						}
					}
			
			}
		
		}
		
		return actorSug;
					
		
	}
	
	public static boolean searchActor (String name) {
		for (int i = 0; i < actorArr.size(); i++) {
			if(actorArr.get(i).getActor() != null){
				if((actorArr.get(i).getActor()).compareTo(name) == 0) 
					return true;
			}
	    }
		return false;
		
	}
	
	
	
	
	
	
	public static void main(String[] args) throws IOException {
		
	
		System.out.println("Welcome to the Movie Wall!");
		readFile();
		
		boolean stay = true;
		while (stay) {
			Scanner in = new Scanner(System.in);
			System.out.println("Enter the name of an actor (or \"EXIT\" to quit):");
			String name = in.nextLine();
			if(name.equals("EXIT")) {
				System.out.println("Thanks for using the Movie Wall!");				
				stay = false;
			}else {
				boolean actorPresent = searchActor(name);
				
				if (actorPresent == false) {
					System.out.println("No such actor");
					String actorSuggestion = findSimilarActor(name);
					System.out.println("Did you mean " + actorSuggestion + "? (Y/N): ");
					Scanner nextIn = new Scanner(System.in);
					String yesNo = nextIn.nextLine();
					
					if(yesNo.equals("Y")) {
						System.out.println("Actor: " + actorSuggestion); 
						for (int i = 0; i < actorArr.size();i++){
							if(actorArr.get(i).getActor().compareTo(actorSuggestion) == 0) {
								actorArr.get(i).getMovies();							
							}
						}
					}else if(yesNo.equals("N")) {
						stay = true;
						
					}else {
						System.out.println("Invalid Input. Try Again.");
						stay = true;
						
					}
																		
				}else {
					System.out.println("Actor: " + name); 
					for (int i = 0; i < actorArr.size();i++){
						if((actorArr.get(i).getActor()).compareTo(name) == 0) {
							actorArr.get(i).getMovies();							
						}
					}
					
				}
				
				
				
				
			}
		}
		
		
		
	}
	
	
	
}








