
import java.util.*;

public class RecommendationRunner implements Recommender {
    private Random myRandom;
    private int toRateNum;
    private int numSimilarRaters;
    private int minimalRaters;
    private int maxRecNum;
    public RecommendationRunner(){
        myRandom = new Random();
        toRateNum = 10;
        numSimilarRaters = 20;
        minimalRaters = 5;
        maxRecNum = 20;
    }
    
    public ArrayList<String> getItemsToRate(){
        MovieDatabase.initialize("ratedmoviesfull.csv");
        ArrayList<String> allMov = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<String> answer = new ArrayList<String>();
        for(int k=0;k<toRateNum;k++){
            int currIndex = myRandom.nextInt(MovieDatabase.size());
            String id = allMov.get(currIndex);
            answer.add(id);
        }
        return answer;
    }

    public void printRecommendationsFor(String webRaterID){
        MovieDatabase.initialize("ratedmoviesfull.csv");
        FourthRatings fourth = new FourthRatings();
        ArrayList<Rating> result = fourth.getSimilarRatings(webRaterID, numSimilarRaters, minimalRaters);
        int num = result.size();
        System.out.println("<table>");
        System.out.println("<h1>Recommendation List:</h1>");
        if(num == 0){
            System.out.println("<h2>Not Found</h2>");
        } 
        else{
            if (num > maxRecNum){
                num = maxRecNum;
            }
            System.out.println("<tr><td><h3>Name</h3></td><td><h3>Poster</h3>"+
            "</td><td><h3>Minutes</h3></td><td><h3>Year</h3></td><td><h3>"+
            "Director/s</h3></td><td><h3>Genre/s</h3></td><td><h3>Country"+
              "</h3></td></tr>");
            for (int k=0; k<num; k++){
                Rating currRating = result.get(k);
                String currMovieID = currRating.getItem();
                String title = MovieDatabase.getTitle(currMovieID);
                String poster = MovieDatabase.getPoster(currMovieID);
                int minutes = MovieDatabase.getMinutes(currMovieID);
                int year = MovieDatabase.getYear(currMovieID);
                String directors = MovieDatabase.getDirector(currMovieID);
                String genres = MovieDatabase.getGenres(currMovieID);
                String country = MovieDatabase.getCountry(currMovieID);
                System.out.println("<tr><td><h4>" + title + "</h4></td>" + 
                "<td><img src=" + poster + " width=50%</td><td><h5>" + 
                minutes + "</h5></td><td><h5>" + year + "</h5></td><td><h5>" 
                + directors + "</h5></td><td><h5>" + genres + "</h5></td><td><h5>" + 
                country + "</h5></td></tr>");
            }
            System.out.println("</table>");
            
        }
    }
}
