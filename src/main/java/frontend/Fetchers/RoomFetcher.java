package frontend.Fetchers;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;

public class RoomFetcher {
        private final String baseUrl = "http://localhost:8080/api/room";
        HttpClient client = HttpClient.newHttpClient();

        public String fetchRooms() throws Exception {
                HttpRequest request = HttpRequest.newBuilder()
                                .uri(URI.create(baseUrl + "/readA"))
                                .POST(BodyPublishers.ofString(""))
                                .build();

                HttpResponse<String> response = client.send(request,
                                BodyHandlers.ofString());
                return response.body();

        }

        public String fetchRoomById(String id) throws Exception {
                HttpRequest request = HttpRequest.newBuilder()
                                .uri(URI.create(baseUrl + "/read?" + id))
                                .POST(BodyPublishers.ofString(""))
                                .build();

                HttpResponse<String> response = client.send(request,
                                BodyHandlers.ofString());
                return response.body();

        }

        public String fetchRoomTypes() throws Exception {
                HttpRequest request = HttpRequest.newBuilder()
                                .uri(URI.create(baseUrl + "/readT"))
                                .POST(BodyPublishers.ofString(""))
                                .build();

                HttpResponse<String> response = client.send(request,
                                BodyHandlers.ofString());
                return response.body();

        }

        /**
         * @param roomId String room id
         * @param status int status can be between 0 - 3 (including)
         * @return String response.body()
         * @throws Exception
         */
        public String fetchUpdateRoomStatus(String roomId, int status) throws Exception {
                HttpRequest request = HttpRequest.newBuilder()
                                .uri(URI.create(baseUrl + "/updateRs" + "?" + "roomId=" + roomId + "&" + "status="
                                                + status))
                                .POST(BodyPublishers.ofString(""))
                                .build();

                HttpResponse<String> response = client.send(request,
                                BodyHandlers.ofString());
                return response.body();

        }

        /**
         * @param roomTypeId Id of a roomtype
         * @param floor      floor of the room (Must be between 0 and 99)
         * @param roomNr     room number (Must be between 0 and 99)
         * @param name       name of the room
         * @return String response.body()
         * @throws Exception
         */
        public String fetchCreateRoom(Long roomType, int floor, int roomNr, String name) throws Exception {
                HttpRequest request = HttpRequest.newBuilder()
                                .uri(URI.create(baseUrl + "/create" + "?" + "roomType=" + roomType + "&" + "floor="
                                                + floor + "&" + "roomNr=" + roomNr + "&" + "name=" + name))
                                .POST(BodyPublishers.ofString(""))
                                .build();

                HttpResponse<String> response = client.send(request,
                                BodyHandlers.ofString());
                return response.body();

        }

}
