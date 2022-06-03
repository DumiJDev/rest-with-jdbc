package org.github.dumijdev.restwithjdbc.controller;

import org.github.dumijdev.restwithjdbc.dao.Page;
import org.github.dumijdev.restwithjdbc.dao.StudentDao;
import org.github.dumijdev.restwithjdbc.model.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RequestMapping("api/v1/students")
@RestController
public class StudentController {
    @GetMapping(params = {"size", "page"})
    public ResponseEntity<?> findAllStudent(@RequestParam(value = "size", required = false) int size,
                                            @RequestParam("page") int page) {
        var paging = new Page(page, size);

        return ok(StudentDao.findAll(paging));
    }

    @PostMapping
    public ResponseEntity<?> saveStudent(@RequestBody Student student) {
        return ok(StudentDao.save(student));
    }
}
