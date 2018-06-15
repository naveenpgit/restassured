import io.restassured.http.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

//http://ergast.com/mrd/
@RunWith(value = Parameterized.class)
public class ListOfCircuitsParameterizedTest {

    @Parameter(value = 0)
    public String season;

    @Parameter(value = 1)
    public int numberOfRaces;

    @Parameters(name = "test_NumberOfCircuits_ShouldBe_DataDriven({0} = {1})")
    public static Object[][] data() {
        return new Object[][]{
                {"2017", 20},
                {"2016", 21},
                {"1966", 9}
        };
    }


    @Test
    public void test_NumberOfCircuits_ShouldBe_DataDriven() {
        given().
                pathParam("raceSeason", season).
                when().
                get("http://ergast.com/api/f1/{raceSeason}/circuits.json").
                then().
                assertThat().
                body("MRData.CircuitTable.Circuits.circuitId", hasSize(numberOfRaces));
    }
}
