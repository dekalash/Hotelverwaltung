package frontend.Fetchers;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class BookingFetcher {

        private final String baseUrl = "http://localhost:8080/api/booking";
        HttpClient client = HttpClient.newHttpClient();
        CommonFetchers cf = new CommonFetchers();

        /**
         * Fetch all bookings
         * 
         * @return String response.body()
         * @throws Exception
         */
        public String fetchAllBookings() throws Exception {

                HttpRequest request = cf
                                .getPostRequest(baseUrl + "/readA");

                HttpResponse<String> response = client.send(request,
                                BodyHandlers.ofString());
                return response.body();

        }

        /**
         * Fetch all bookings between an interval
         * 
         * @param startDate start date example format : 2007-12-03T10:15:30
         * @param endDate   end date example format : 2007-12-03T10:15:30
         * @return String response.body()
         * @throws Exception
         */
        public String fetchBookingsWithInterval(String startDate, String endDate) throws Exception {

                HttpRequest request = cf
                                .getPostRequest(baseUrl + "/readP?" + "startDate=" + startDate + "&" + "endDate="
                                                + endDate);

                HttpResponse<String> response = client.send(request,
                                BodyHandlers.ofString());
                return response.body();

        }

        /**
         * Fetch last 10 bookings
         * 
         * 
         * @return String response.body()
         * @throws Exception
         */
        public String fetchRecentBookings(String startDate, String endDate) throws Exception {

                HttpRequest request = cf
                                .getPostRequest(baseUrl + "/readR");

                HttpResponse<String> response = client.send(request,
                                BodyHandlers.ofString());
                return response.body();

        }

        /**
         * Fetch bookings in an interval
         * 
         * @return String response.body()
         * @throws Exception
         */
        public String fetchRecentBookings() throws Exception {

                HttpRequest request = cf
                                .getPostRequest(baseUrl + "/readR");

                HttpResponse<String> response = client.send(request,
                                BodyHandlers.ofString());
                return response.body();

        }

        /**
         * fetch bookings that ends today
         * 
         * @return String response.body()
         * @throws Exception
         */
        public String fetchTodaysEndingBookings() throws Exception {

                HttpRequest request = cf
                                .getPostRequest(baseUrl + "/readEB");

                HttpResponse<String> response = client.send(request,
                                BodyHandlers.ofString());
                return response.body();
        }

        /**
         * get bookings which starts today
         * 
         * @return String response.body()
         * @throws Exception
         */
        public String fetchTodaysBeginningBookings() throws Exception {

                HttpRequest request = cf
                                .getPostRequest(baseUrl + "/readBB");

                HttpResponse<String> response = client.send(request,
                                BodyHandlers.ofString());
                return response.body();

        }

        /**
         * create a booking
         * 
         * @param checkInDate  check in date example format : yyyy-mm-dd 2023-12-03
         * @param checkOutDate check out date example format : yyyy-mm-dd 2023-12-03
         * @param room         room id
         * @param personId     peron id
         * @return String response.body()
         * @throws Exception
         */
        public String fetchCreateBooking(String checkInDate, String checkOutDate, String room, long personId)
                        throws Exception {

                HttpRequest request = cf
                                .getPostRequest(baseUrl + "/create" + "?" + "checkInDate=" + checkInDate + "&"
                                                + "checkOutDate="
                                                + checkOutDate + "&" + "room=" + room + "&" + "personId=" + personId);

                HttpResponse<String> response = client.send(request,
                                BodyHandlers.ofString());
                return response.body();

        }
}
