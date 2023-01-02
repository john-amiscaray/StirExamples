package io.john.amiscaray.springappexample.dto;

import io.john.amiscaray.stir.annotation.TableData;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EmployeeDTO {

    @TableData(columnName = "Employee ID")
    private Integer id;

    @TableData(columnName = "Name")
    private String name;

    @TableData(columnName = "Position")
    private String position;

    @TableData(columnName = "Department")
    private Character department;

    @TableData(columnName = "Salary")
    private Long salary;

}
