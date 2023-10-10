package escaperoom.room;

import escaperoom.rating.Ratable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EscapeRoom implements Ratable {
    private String name;
    private Theme theme;
    private Difficulty difficulty;
    private int maxTimeToEscape;
    private double priceToPlay;
    private List<Review> reviews;
    public EscapeRoom(String name, Theme theme, Difficulty difficulty, int maxTimeToEscape, double priceToPlay){
        this.name = name;
        this.theme = theme;
        this.difficulty = difficulty;
        this.maxTimeToEscape = maxTimeToEscape;
        this.priceToPlay = priceToPlay;
        this.reviews = new ArrayList<>();
    }

    /**
     * Returns the name of the escape room.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the difficulty of the escape room.
     */
    public Difficulty getDifficulty() {
        return this.difficulty;
    }

    /**
     * Returns the maximum time to escape the room.
     */
    public int getMaxTimeToEscape() {
        return this.maxTimeToEscape;
    }

    /**
     * Returns all user reviews stored for this escape room, in the order they have been added.
     */
    public List<Review> getReviews() {
        return this.reviews;
    }

    @Override
    public double getRating() {
        if(this.reviews.isEmpty()){
            return 0.0;
        }
        double rating = 0.0;
        for (Review review : this.reviews) {
            rating += review.rating();
        }
        return rating /= this.reviews.size();
    }

    public void addReview(Review review) {
        this.reviews.add((review));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EscapeRoom that = (EscapeRoom) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
