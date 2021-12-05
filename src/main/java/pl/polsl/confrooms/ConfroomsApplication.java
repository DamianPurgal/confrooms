package pl.polsl.confrooms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
public class ConfroomsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfroomsApplication.class, args);
    }

}