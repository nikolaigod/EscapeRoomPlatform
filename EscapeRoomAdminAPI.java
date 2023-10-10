package escaperoom;

import escaperoom.exception.RoomNotFoundException;
import escaperoom.exception.PlatformCapacityExceededException;
import escaperoom.exception.RoomAlreadyExistsException;
import escaperoom.exception.TeamNotFoundException;
import escaperoom.room.EscapeRoom;
import escaperoom.team.Team;

import java.util.List;

public interface EscapeRoomAdminAPI {

    void addEscapeRoom(EscapeRoom room) throws RoomAlreadyExistsException, IllegalAccessException;

    void removeEscapeRoom(String roomName) throws RoomNotFoundException;

    List<EscapeRoom> getAllEscapeRooms();

    void registerAchievement(String roomName, String teamName, int escapeTime)
            throws RoomNotFoundException, TeamNotFoundException;

}

