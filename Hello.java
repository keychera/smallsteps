import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

class Hello {
    public static void main(String[] args) {
        HelpMe.greet("Java");

        when().
            get("/lotto/{id}", 5).
        then().
            statusCode(200).
            body("lotto.lottoId", equalTo(5),
                 "lotto.winners.winnerId", hasItems(23, 44));
    }
}
