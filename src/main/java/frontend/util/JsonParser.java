package frontend.util;

import java.util.ArrayList;
import java.util.List;

import frontend.data.Booking;
import frontend.data.IndicatorEnum;
import org.json.JSONArray;
import org.json.JSONObject;

import frontend.data.Room;
import frontend.data.Staff;

/**
 * Class used to parse json server responses to instances of frontend.data classes
 * (Booking, Room, Staff).
 */
public class JsonParser {

    /**
     * Parses a json as a list of rooms.
     * The json must consist only of a json array containing rooms as json objects (see src\test\resources\json\Rooms.json).
     * @param json the json file contents
     * @return a list containing the rooms (or an empty list if the json array is empty)
     */
    public static List<Room> parseRooms(String json) {
        JSONArray jsonArray = new JSONArray(json);
        List<Room> rooms = new ArrayList<Room>(jsonArray.length());
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject room = jsonArray.getJSONObject(i);
            rooms.add(parseRoom(room));
        }
        return rooms;
    }

    /**
     * Parses a json object as a room.
     * @param room the json object
     * @return an instance of room containing the values of the json
     */
    private static Room parseRoom(JSONObject room) {
        //extract type
        JSONObject type = room.getJSONObject("roomType");

        IndicatorEnum indicatorEnum;

        if (room.getInt("status") == 0){
            indicatorEnum = IndicatorEnum.Frei;
        } else  if (room.getInt("status") == 1) {
            indicatorEnum = IndicatorEnum.Gebucht;
        } else {
            indicatorEnum = IndicatorEnum.Gewartet;
        }

        return new Room(indicatorEnum, room.getLong("roomId"), type.getString("roomType"), type.get("pricePerNight").toString(),
                type.getNumber("singleBeds").toString(), type.getNumber("doubleBeds").toString());
    }

    /**
     * Parses a json as a list of employees (staff instances).
     * The json must consist only of a json array containing employees as json objects (see src\test\resources\json\Staff.json).
     * @param json the json file contents
     * @return a list containing the employees (or an empty list if the json array is empty)
     */
    public static List<Staff> parseStaff(String json) {
        JSONArray jsonArray = new JSONArray(json);
        List<Staff> staff = new ArrayList<Staff>(jsonArray.length());
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject employee = jsonArray.getJSONObject(i);
            staff.add(parseEmployee(employee));
        }
        return staff;
    }

    /**
     * Parses a json object as an staff instance.
     * @param employee the jsonobject
     * @return an instance of Staff containing the values of the json
     */
    public static Staff parseEmployee(JSONObject employee) {
        return new Staff(employee.getString("firstName"), employee.getString("lastName"), employee.getString("birthdate"),
                employee.getString("zip"), employee.getString("city"), employee.getString("street"), employee.getString("email"),
                employee.getString("phone"));
    }

    /**
     * Parses a json as a list of Bookings.
     * The json must consist only of a json array containing bookings as json objects (see src\test\resources\json\Booking.json).
     * @param json the json file contents
     * @return a list containing the rooms (or an empty list if the json array is empty)
     */
    public static List<Booking> parseBookings(String json) {
        JSONArray jsonArray = new JSONArray(json);
        List<Booking> bookings = new ArrayList<Booking>(jsonArray.length());
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject booking = jsonArray.getJSONObject(i);
            bookings.add(parseBooking(booking));
        }
        return bookings;
    }

    /**
     * Parses a json object as a booking.
     * @param booking the json object
     * @return an instance of room containing the values of the json
     */
    private static Booking parseBooking(JSONObject booking) {
        //todo roomTyp
        JSONArray rooms = booking.getJSONArray("rooms");
        JSONObject roomA = null;
        for(int i = rooms.length()-1; i>=0; i--){
            roomA = rooms.getJSONObject(i); 
        }
        JSONObject room = roomA.getJSONObject("roomType");
        System.out.println(roomA);
        IndicatorEnum indicatorEnum;

        if (roomA.getInt("status") == 2) {
            indicatorEnum = IndicatorEnum.Gebucht;
        } else {
            indicatorEnum = IndicatorEnum.Gewartet;
        }

        int roomTypeId = room.getInt("roomTypeId");
        String roomType = room.getString("roomType");
        JSONObject type = booking.getJSONObject("person");
        //todo pricePerNight
        //todo replace personId at the place of roomId and set actual roomId from database
        return new Booking(indicatorEnum, type.getString("firstName"), type.getString("lastName"), type.getString("birthdate"),
                type.getString("zip"), type.getString("city"), type.getString("street"), type.getString("email"),
                type.getString("phone"), roomType, (long) roomTypeId, booking.getString("checkInDate"),
                booking.getString("checkOutDate"), String.valueOf(booking.getDouble("totalPrice")));
    }

    /**
    * Parses a json as a list of Roomtypes and returns their names.
    * The json must consist only of a json array containing roomtypes as json objects (see src\test\resources\json\RoomTypes.json).
    * @param json the json file contents
    * @return a list containing the room type names (or an empty list if the json array is empty)
    */
    public static List<String> parseRoomTypes(String json) {
        JSONArray jsonArray = new JSONArray(json);
        List<String> roomTypeNames = new ArrayList<String>(jsonArray.length());
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject roomType = jsonArray.getJSONObject(i);
            roomTypeNames.add(roomType.getString("roomType"));
        }
        return roomTypeNames;
    }
}