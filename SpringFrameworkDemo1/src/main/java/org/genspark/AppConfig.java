package org.genspark;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Student getStudent() {
        Student student = new Student();
        student.setName("John");
        student.setPh(new Phone());
        student.getPh().setMob("1234567890");
        student.setId(152312);
        student.setAdd(new Address());
        student.getAdd().setCity("Anytown");
        student.getAdd().setState("Anystate");
        student.getAdd().setCountry("Anycountry");
        student.getAdd().setZipcode("411045");
        return student;
    }

    @Bean
    public Employee getEmployee() {
        Employee employee = new Employee();
        employee.setName("Kathy");
        employee.setPh(new Phone());
        employee.getPh().setMob("0987654321");
        employee.setId(523147);
        employee.setAdd(new Address());
        employee.getAdd().setCity("Anytown");
        employee.getAdd().setState("Anystate");
        employee.getAdd().setCountry("Anycountry");
        employee.getAdd().setZipcode("411057");
        return employee;
    }

}
