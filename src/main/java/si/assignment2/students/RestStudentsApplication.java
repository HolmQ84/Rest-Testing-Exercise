package si.assignment2.students;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class RestStudentsApplication {
    public static void main(String[] args) {
        SpringApplication.run(RestStudentsApplication.class, args);
    }
}
