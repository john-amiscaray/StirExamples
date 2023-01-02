package io.john.amiscaray.springappexample.domain;

import io.john.amiscaray.springappexample.dto.EmployeeDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    private Integer id;

    private String name;

    private String position;

    private Character department;

    private Long salary;

    public EmployeeDTO toDTO(){

        return new EmployeeDTO(id, name, position, department, salary);

    }

}
