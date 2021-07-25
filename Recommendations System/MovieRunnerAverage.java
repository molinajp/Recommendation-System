
import java.util.*;

public class MovieRunnerAverage {
    public void printAverageRatings(){
        SecondRatings second  = new SecondRatings("ratedmoviesfull.csv",
                                                  "ratings.csv");
        System.out.println("There are " + second.getMovieSize() + " movies");
        System.out.println("There are " + second.getRaterSize() + " raters");
        ArrayList<Rating> ratings = second.getAverageRatings(12);
        Collections.sort(ratings);
        for(Rating r : ratings){
            String title = second.getTitle(r.getItem());
            System.out.println(r.getValue() + " " + title);
        }
    }
    
    public void getAverageRatingOneMovie(){
        SecondRatings second = new SecondRatings("ratedmoviesfull.csv",
                                                  "ratings.csv");
        ArrayList<Rating> ratings = second.getAverageRatings(3);
        Collections.sort(ratings);
        for(Rating r : ratings){
            String id = second.getID("Vacation");
            if(id.equals(r.getItem())){
                System.out.println("The rating for this movie is " +
                                    r.getValue()); 
            }
        }
    }
}
