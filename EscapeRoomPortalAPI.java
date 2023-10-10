package escaperoom;

import escaperoom.exception.RoomNotFoundException;
import escaperoom.exception.TeamNotFoundException;
import escaperoom.room.EscapeRoom;
import escaperoom.room.Review;
import escaperoom.team.Team;

import java.util.List;

public interface EscapeRoomPortalAPI {

    Team getTeamByName(String name) throws TeamNotFoundException;

    EscapeRoom getEscapeRoomByName(String roomName) throws RoomNotFoundException;

    void reviewEscapeRoom(String roomName, Review review) throws RoomNotFoundException;

    List<Review> getReviews(String roomName) throws RoomNotFoundException;

    Team getTopTeamByRating();

}
