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

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import static io.john.amiscaray.stir.util.ElementDescriptorProcessor.element;

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

        LinkedHashMap<String, String> navMap = new LinkedHashMap<>();
        navMap.put("Home", "#");
        navMap.put("Employees", "#");
        Nav nav = Nav.fromLabelHrefMap(navMap);
        nav.setId("nav");

        return HTMLDocument.builder()
                .format("""
                <!DOCTYPE html>
                <html lang="<& str_lang &>">
                    <head>
                        <meta charset="UTF-8">
                        <meta name="viewport" content="width=device-width, initial-scale=1.0">
                        <& str_meta &>
                        <title><& str_title &></title>
                        <& str_hscripts &>
                        <& str_lstyles &>
                        <& str_styles &>
                    </head>
                    <body>
                        <& nav str_content &>
                        <& str_fscripts &>
                    </body>
                </html>""")
                .element(Div.builder()
                        .id("doc-content")
                        .cssClass("container")
                        .child(element("hgroup#title{h1('Employees')}"))
                        .child(new Table(employees, EmployeeDTO.class))
                        .build())
                .formatArg("nav", nav)
                .withBootStrap(true)
                .linkedStyle(new LinkedStyle("/styles.css"))
                .build()
                .generateDocumentString();

    }

}
