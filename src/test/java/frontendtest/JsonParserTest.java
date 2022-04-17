package frontendtest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import frontend.data.Booking;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import frontend.data.Room;
import frontend.data.Staff;
import frontend.util.JsonParser;

@Testable
public class JsonParserTest {
    private static String JSON_RESOURCE_DIRECTORY = "src/test/resources/json/";
    private static String EMPTY_LIST_JSON = "EmptyArray";

    /**
     * Extracts a json file to a string.
     * @param fileName the name of the json file (excluding file extension)
     * @return the file content as a string
     * @throws IOException if the file cannot be read for any reason
     */
    private String readJson(String fileName) throws IOException {
        Path filePath = Paths.get(JSON_RESOURCE_DIRECTORY + fileName + ".json");
        return Files.readString(filePath);

    }

    @Test
    public void testRoomEmptyList() throws IOException {
        List<Room> staff = JsonParser.parseRooms(readJson(EMPTY_LIST_JSON));
        assertEquals(0, staff.size());
    }

    @Test
    public void testRoomParsing() throws IOException {
        List<Room> rooms = JsonParser.parseRooms(readJson("Rooms"));

        assertEquals(5, rooms.size());
        //test room ids
        for (int i = 0; i < rooms.size(); i++) {
            assertEquals(i + 1, (Integer.valueOf((int) rooms.get(i).getRoomNumber())));
            //assertEquals("Saarblick Penthouse "+i+1, rooms.get(i).get---); name does not exist in frontend.data.Room
        }

        Room penthouse1 = rooms.get(0);
        assertEquals("Small Penthouse", penthouse1.getRoomType());
        assertEquals("140.0", penthouse1.getPrice());
        assertEquals("0", penthouse1.getOneBedRoom());
        assertEquals("2", penthouse1.getTwoBedRoom());
        //assertEquals("indicator for status = 0", penthouse1.getIndicator());
    }
  
    @Test
    public void testBookingempty() throws IOException {
        List<Booking> bookings = JsonParser.parseBookings(readJson(EMPTY_LIST_JSON));
        assertEquals(0, bookings.size());
    }

    @Test
    public void testBookingParsing() throws IOException {
        List<Booking> bookings = JsonParser.parseBookings(readJson("Booking"));
        //test room ids
        //for (int i = 0; i<bookings.size(); i++) {
        //assertEquals(i+1, (Integer.valueOf(bookings.get(i).getRoomNumber())));
        //assertEquals("Saarblick Penthouse "+i+1, rooms.get(i).get---); name does not exist in frontend.data.Room
        //}
        Booking bookingsTest01 = bookings.get(0);
        assertEquals("Simon", bookingsTest01.getFirstName());
        assertEquals("Pilger", bookingsTest01.getLastName());
        assertEquals("02-02-1992", bookingsTest01.getBirthDate());
        assertEquals("66111", bookingsTest01.getPostalCode());
        assertEquals("Alt-Saarbrucken", bookingsTest01.getCity());
        assertEquals("KEK", bookingsTest01.getStreet());
        assertEquals("s.pilger@test.de", bookingsTest01.getEmail());
        assertEquals("041295578", bookingsTest01.getPhoneNumber());
        assertEquals("2022-04-01T16:22:54", bookingsTest01.getStartDate());
        assertEquals("2022-04-05T16:22:54", bookingsTest01.getEndDate());
        assertEquals("420.0", bookingsTest01.getPrice());
    }

    @Test
    public void testStaffEmptyList() throws IOException {
        List<Staff> staff = JsonParser.parseStaff(readJson(EMPTY_LIST_JSON));
        assertEquals(0, staff.size());
    }

    @Test
    public void testStaffParsing() throws IOException {
        List<Staff> staff = JsonParser.parseStaff(readJson("Staff"));

        assertEquals(1, staff.size());

        //TODO test staff id's if staff id is stored in frontend
        /* for (int i = 0; i < staff.size(); i++) {
            assertEquals(i + 1, (Integer.valueOf(staff.get(i).getStaffId())));
            //assertEquals("Saarblick Penthouse "+i+1, rooms.get(i).get---); name does not exist in frontend.data.Room
        } */

        Staff staff1 = staff.get(0);
        assertEquals("Jonas", staff1.getFirstName());
        assertEquals("Türk", staff1.getLastName());
        assertEquals("01.02.2003", staff1.getBirthDate());
        assertEquals("348184", staff1.getPostalCode());
        assertEquals("san fernando", staff1.getCity());
        assertEquals("trinidad straße", staff1.getStreet());
        assertEquals("jt@web.de", staff1.getEmail());
        assertEquals("123456789", staff1.getPhoneNumber());
    }

    @Test
    public void testStaff2EmployeesParsing() throws IOException {
        List<Staff> staff = JsonParser.parseStaff(readJson("Staff2Employees"));
        assertEquals(2, staff.size());

        Staff staff1 = staff.get(0);
        assertEquals("Jonas", staff1.getFirstName());
        assertEquals("Türk", staff1.getLastName());
        assertEquals("01.02.2003", staff1.getBirthDate());
        assertEquals("348184", staff1.getPostalCode());
        assertEquals("san fernando", staff1.getCity());
        assertEquals("trinidad straße", staff1.getStreet());
        assertEquals("jt@web.de", staff1.getEmail());
        assertEquals("123456789", staff1.getPhoneNumber());

        Staff staff2 = staff.get(1);
        assertEquals("Gustav", staff2.getFirstName());
        assertEquals("Gürtelgans", staff2.getLastName());
        assertEquals("31.12.1932", staff2.getBirthDate());
        assertEquals("217311", staff2.getPostalCode());
        assertEquals("port of spain", staff2.getCity());
        assertEquals("tobago straße", staff2.getStreet());
        assertEquals("gustav@web.de", staff2.getEmail());
        assertEquals("2818929", staff2.getPhoneNumber());
    }

    @Test
    public void testRoomTypesEmptyList() throws IOException{
        List<String> roomTypes = JsonParser.parseRoomTypes(readJson(EMPTY_LIST_JSON));
        assertEquals(0, roomTypes.size());
    }

    @Test
    public void testRoomTypeParsing() throws IOException{
        List<String> roomTypes = JsonParser.parseRoomTypes(readJson("RoomTypes"));
        assertEquals(3, roomTypes.size());
        assertEquals("Small Penthouse", roomTypes.get(0));
        assertEquals("Large Penthouse", roomTypes.get(1));
        assertEquals("Ultimate Penthouse", roomTypes.get(2));
    }
}
