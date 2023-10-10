package escaperoom.team;

import escaperoom.rating.Ratable;
import java.lang.IllegalArgumentException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Team implements Ratable {

    private String name;
    private List<TeamMember> members;
    private double rating;
    @Override
    public double getRating() {
        return this.rating;
    }

    private Team(String name, Collection<TeamMember> members){
        this.name = name;
        this.members = new ArrayList<>();
        this.members.addAll(members);
        this.rating = 0;
    }

    public static Team of(String name, Collection<TeamMember> members){
        return new Team(name, members);
    }

    public void updateRating(int points) throws IllegalArgumentException {
        if(points < 0){
            throw new IllegalArgumentException("Points negative");
        }
        this.rating += points;
    }

    public String getName() {
        return name;
    }
}
