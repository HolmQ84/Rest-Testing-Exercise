package si.assignment2.students;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
class RestStudentsApplicationTests {

    // TODO - Create a Servlet Container to run application as a @BeforeAll annotation
    //  and a @AfterAll annotation where the container is removed.
    // @BeforeAll

    @Test
    public void testThatAEndpointWithAExistingIdReturnsHttpStatusOk() {
        try {
            // Given
            int id = 0;
            HttpUriRequest request = new HttpGet("http://localhost:8081/students/" + id);
            // When
            HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
            // Then
            assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_OK)
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testThatAEndpointWithANonExistingIdReturnsHttpStatusNotFound() {
        try {
            // Given
            int id = 307;
            HttpUriRequest request = new HttpGet("http://localhost:8081/students/" + id);
            // When
            HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
            // Then
            assertThat(
                httpResponse.getStatusLine().getStatusCode(),
                equalTo(HttpStatus.SC_NOT_FOUND)
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getResponseType() {
        try {
            //Given
            String jsonMimeType = "application/json";
            HttpUriRequest request = new HttpGet("http://localhost:8081/students/");
            // When
            HttpResponse response = HttpClientBuilder.create().build().execute(request);
            // Then
            String mimeType = ContentType.getOrDefault(response.getEntity()).getMimeType();
            Assertions.assertEquals(
                jsonMimeType,
                mimeType
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
