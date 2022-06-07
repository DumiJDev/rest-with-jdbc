package org.github.dumijdev.restwithjdbc;

import org.github.dumijdev.restwithjdbc.model.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@SpringBootApplication
public class RestWithJDBCApplication {
    public static void main(String[] args) {
        SpringApplication.run(RestWithJDBCApplication.class, args);
        RestTemplate rest = new RestTemplate();
        String name = "DumiJDev";
        Student student = new Student(0, name, LocalDate.now());
        for(int i = 0; i < 5000; i++){
            student.setName(name + " " + (i+1));
            student.setId(i+1);
            ResponseEntity<?> studentResponseEntity = rest.postForEntity("http://localhost:8080/api/v1/students/", student, Student.class);
            System.out.println(studentResponseEntity.getBody());
        }
    }
}
