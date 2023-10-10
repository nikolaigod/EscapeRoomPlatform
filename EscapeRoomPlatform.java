package escaperoom;

import escaperoom.exception.PlatformCapacityExceededException;
import escaperoom.exception.RoomAlreadyExistsException;
import escaperoom.exception.RoomNotFoundException;
import escaperoom.exception.TeamNotFoundException;
import escaperoom.room.EscapeRoom;
import escaperoom.room.Review;
import escaperoom.team.Team;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EscapeRoomPlatform implements EscapeRoomAdminAPI, EscapeRoomPortalAPI {
    private List<Team> teams;
    private List<EscapeRoom> rooms;
    private int maxCapacity;
    public EscapeRoomPlatform(Collection<Team> teams, int maxCapacity){
        this.maxCapacity = maxCapacity;
        this.teams = new ArrayList<>();
        this.teams.addAll(teams);
        this.rooms = new ArrayList<>();
    }

    @Override
    public void addEscapeRoom(EscapeRoom room) throws RoomAlreadyExistsException{
        if(room == null){
            throw new IllegalArgumentException("Room is null");
        }
        else if(this.rooms.size() == this.maxCapacity){
            throw new PlatformCapacityExceededException("Capacity exceeded");
        }

        for (EscapeRoom currRoom : this.rooms) {
            if (currRoom != null && currRoom.equals(room)) {
                throw new RoomAlreadyExistsException("Room with name " + currRoom.getName() + " already exists");
            }
        }

        this.rooms.add(room);
    }

    @Override
    public void removeEscapeRoom(String roomName) throws RoomNotFoundException {
        if(roomName.isBlank() || roomName.isEmpty() || roomName == null){
            throw new IllegalArgumentException("Name is wrong");
        }
        for (int i = 0; i < maxCapacity; i++) {
            if (this.rooms.get(i) != null && this.rooms.get(i).getName().equals(roomName)) {
                this.rooms.remove(this.rooms.get(i));
            }
        }

        throw new RoomNotFoundException("Room name: " + roomName);
    }

    @Override
    public List<EscapeRoom> getAllEscapeRooms() {
        return this.rooms;
    }

    @Override
    public void registerAchievement(String roomName, String teamName, int escapeTime) throws RoomNotFoundException, TeamNotFoundException {

        EscapeRoom room = getEscapeRoomByName(roomName);
        Team team = getTeamByName(teamName);

        if (escapeTime <= 0 || escapeTime > room.getMaxTimeToEscape()) {
            throw new IllegalArgumentException("Escape time should be in the range (0, maxTimeToEscape) for this room");
        }

        int bonus = 0;
        double escapeSpeed = escapeTime / (double) room.getMaxTimeToEscape();
        if (escapeSpeed <= 0.5) {
            bonus = 2;
        } else if (escapeSpeed <= 0.75) {
            bonus = 1;
        }

        team.updateRating(room.getDifficulty().getRank() + bonus);
    }

    @Override
    public EscapeRoom getEscapeRoomByName(String roomName) throws RoomNotFoundException {
        if (roomName == null || roomName.isEmpty() || roomName.isBlank()) {
            throw new IllegalArgumentException("Room name cannot be null, empty or blank");
        }

        for (EscapeRoom room : this.rooms) {
            if (room != null && room.getName().equals(roomName)) {
                return room;
            }
        }
        throw new RoomNotFoundException("Room name: " + roomName);
    }

    @Override
    public Team getTeamByName(String name) throws TeamNotFoundException{
        if (name == null || name.isEmpty() || name.isBlank()) {
            throw new IllegalArgumentException("Team name cannot be null, empty or blank");
        }

        for (Team t : teams) {
            if (t != null && t.getName().equals(name)) {
                return t;
            }
        }

        throw new TeamNotFoundException("Team name: " + name);
    }

    @Override
    public void reviewEscapeRoom(String roomName, Review review) throws RoomNotFoundException {
        if (review == null) {
            throw new IllegalArgumentException("Review cannot be null");
        }
        EscapeRoom room = getEscapeRoomByName(roomName);
        room.addReview(review);
    }

    @Override
    public List<Review> getReviews(String roomName) throws RoomNotFoundException {
        EscapeRoom room = getEscapeRoomByName(roomName);
        return room.getReviews();
    }

    @Override
    public Team getTopTeamByRating() {
        Team topTeam = null;
        double topTeamRating = 0.0;

        for (Team t : teams) {
            if (t != null && t.getRating() > topTeamRating) {
                topTeam = t;
                topTeamRating = t.getRating();
            }
        }

        return topTeam;
    }
}
