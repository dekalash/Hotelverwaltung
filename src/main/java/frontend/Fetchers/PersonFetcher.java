package frontend.Fetchers;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class PersonFetcher {

        private static final String baseUrl = "http://localhost:8080/api/person";
        static HttpClient client = HttpClient.newHttpClient();
        static CommonFetchers cf = new CommonFetchers();

        /**
         * Fetch all employees
         * 
         * @return String response.body()
         * @throws Exception
         */
        public String fetchEmployees() throws Exception {

                HttpRequest request = cf
                                .getPostRequest(baseUrl + "/read/e");

                HttpResponse<String> response = client.send(request,
                                BodyHandlers.ofString());
                return response.body();

        }

        /**
         * Fetch all employees
         * 
         * @param email email of the user
         * @return String response.body()
         * @throws Exception
         */
        public String fetchUserByEmail(String email) throws Exception {

                HttpRequest request = cf
                                .getPostRequest(baseUrl + "/readE?email=" + email);
                HttpResponse<String> response = client.send(request,
                                BodyHandlers.ofString());
                return response.body();

        }

        /**
         * Fetch all employees
         * 
         * @param phone phone of the user
         * @return String response.body()
         * @throws Exception
         */
        public String fetchPersonByPhone(String phone) throws Exception {

                HttpRequest request = cf
                                .getPostRequest(baseUrl + "/readE?phone=" + phone);

                HttpResponse<String> response = client.send(request,
                                BodyHandlers.ofString());
                return response.body();

        }

        /**
         * create a guest account
         * 
         * @param firstName first name of the user
         * @param lastName  last name of the user
         * @param email     email of the user
         * @param phone     phone of the user
         * @param gender    gender of the user as int
         * @return String response.body()
         */
        public String fetchCreateCustomer(String firstName, String lastName, String email, String phone, int gender)
                        throws Exception {

                HttpRequest request = cf
                                .getPostRequest(baseUrl + "/create?firstName=" + firstName + "&lastName=" + lastName
                                                + "&email=" + email
                                                + "&phone=" + phone + "&gender=" + gender);

                HttpResponse<String> response = client.send(request,
                                BodyHandlers.ofString());
                return response.body();

        }

        /**
         * update a guest account
         * 
         * @param firstName first name of the user
         * @param lastName  last name of the user
         * @param email     email of the user
         * @param phone     phone of the user
         * @param gender    gender of the user as int
         * @return String response.body()
         */
        public String fetchUpdateCustomer(String firstName, String lastName, String email, String phone, int gender)
                        throws Exception {

                HttpRequest request = cf
                                .getPostRequest(baseUrl + "/update?firstName=" + firstName + "&lastName=" + lastName
                                                + "&email=" + email
                                                + "&phone=" + phone + "&gender=" + gender);
                HttpResponse<String> response = client.send(request,
                                BodyHandlers.ofString());
                return response.body();

        }

        /**
         * update a guest account
         * 
         * @param firstName first name of the user
         * @param lastName  last name of the user
         * @param email     email of the user
         * @param phone     phone of the user
         * @param gender    gender of the user as int
         * @return String response.body()
         */
        public String fetchUpdateUserByUserId(Long userId, String firstName, String lastName, String email,
                        String phone, int gender)
                        throws Exception {

                HttpRequest request = cf
                                .getPostRequest(baseUrl + "/update?firstName=" + firstName + "&lastName=" + lastName
                                                + "&email=" + email
                                                + "&phone=" + phone + "&gender=" + gender + "&userId=" + userId);
                HttpResponse<String> response = client.send(request,
                                BodyHandlers.ofString());
                return response.body();

        }

        /**
         * updates address of a user
         * 
         * @param street  street name of the address
         * @param houseNr houseNr name of the user
         * @param email   email of the user
         * @param phone   phone of the user
         * @param city    city of the user
         * @param state   state of the user
         * @param zip     zip of the user
         * @return String response.body()
         */
        public String fetchUpdateUserAddress(String street, String houseNr, String email, String phone, String city,
                        String state, String zip)
                        throws Exception {
                HttpRequest request = cf
                                .getPostRequest(baseUrl + "/updateA?street" + street + "&houseNR=" + houseNr + "&email="
                                                + email
                                                + "&phone=" + phone + "&city=" + city + "&state=" + state + "&zip="
                                                + zip);
                HttpResponse<String> response = client.send(request,
                                BodyHandlers.ofString());
                return response.body();

        }

        /**
         * create a employee account
         * 
         * @param firstName first name of the user
         * @param lastName  last name of the user
         * @param email     email of the user
         * @param phone     phone of the user
         * @param gender    gender of the user as int
         * @param street    street of the user
         * @param houseNR   house number of the user
         * @param city      city of the user
         * @param state     state
         * @param zip       zip of the user
         * @return String response.body()
         */
        public static String fetchCreateEmployee(String firstName, String lastName, String email, String phone,
                        int gender, String street, int houseNR, String city, String state, String zip)
                        throws Exception {
                HttpRequest request = cf.getPostRequest(
                                baseUrl + "/create/e?firstName=" + firstName + "&lastName=" + lastName + "&email="
                                                + email
                                                + "&phone=" + phone + "&gender=" + gender + "&street=" + street +
                                                "&houseNR=" + houseNR + "&city=" + city + "&state=" + state + "&zip="
                                                + zip);

                HttpResponse<String> response = client.send(request,
                                BodyHandlers.ofString());
                return response.body();

        }

        /**
         * create a employee account
         * 
         * @param email   email of the user
         * @param phone   phone of the user
         * @param street  street name of the address
         * @param houseNr houseNr name of the user
         * @param city    city of the user
         * @param state   state of the user
         * @param zip     zip of the address
         * @return String response.body()
         */
        public static String fetchUpdateUserAddress(String email, String phone, String street, int houseNR, String city,
                        String state, String zip)
                        throws Exception {
                HttpRequest request = cf.getPostRequest(
                                baseUrl + "updateA" + "?email=" + email + "&phone=" + phone + "&street=" + street
                                                + "&houseNR=" + houseNR + "&city=" + city + "&state=" + state + "&zip="
                                                + zip);

                HttpResponse<String> response = client.send(request,
                                BodyHandlers.ofString());
                return response.body();

        }

}
