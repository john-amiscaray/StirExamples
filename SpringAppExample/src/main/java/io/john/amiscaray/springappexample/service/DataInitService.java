package io.john.amiscaray.springappexample.service;

import io.john.amiscaray.springappexample.domain.Employee;
import io.john.amiscaray.springappexample.repo.EmployeeRepo;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DataInitService implements CommandLineRunner {

    private EmployeeRepo repo;

    @Override
    public void run(String... args) throws Exception {
        repo.saveAll(List.of(
                new Employee(1, "John", "Junior Developer", 'e', 60000L),
                new Employee(2, "Claire", "Senior Developer", 'e', 60000L),
                new Employee(3, "Daniel", "CEO", 'c', 1000000L)
        ));
    }
}
