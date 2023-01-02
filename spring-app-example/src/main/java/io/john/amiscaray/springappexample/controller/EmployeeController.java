package io.john.amiscaray.springappexample.controller;

import io.john.amiscaray.springappexample.domain.Employee;
import io.john.amiscaray.springappexample.dto.EmployeeDTO;
import io.john.amiscaray.springappexample.repo.EmployeeRepo;
import io.john.amiscaray.stir.domain.HTMLDocument;
import io.john.amiscaray.stir.domain.elements.*;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {

    private EmployeeRepo repo;

    @GetMapping(value = "/all", produces = MediaType.TEXT_HTML_VALUE)
    public String getAllEmployees(){

        List<EmployeeDTO> employees = repo.findAll()
            .stream()
            .map(Employee::toDTO)
            .collect(Collectors.toList());

        return HTMLDocument.builder()
                .element(Div.builder()
                        .id("doc-content")
                        .cssClass("container")
                        .child(HGroup.builder()
                                .id("title")
                                .child(new Heading(1, "Employees"))
                                .build())
                        .child(new Table(employees, EmployeeDTO.class))
                        .build())
                .withBootStrap(true)
                .linkedStyle(new LinkedStyle("/styles.css"))
                .build()
                .generateDocumentString();

    }

}
