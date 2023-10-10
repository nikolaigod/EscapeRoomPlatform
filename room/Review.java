package escaperoom.room;
import java.lang.IllegalArgumentException;

public record Review(int rating, String reviewText) {

    public Review{
        if(reviewText == null || reviewText.length() > 200){
            throw new IllegalArgumentException("Wrong review");
        }
        else if(rating < 0 || rating > 10){
            throw new IllegalArgumentException("Wrong review");
        }
    }
}
