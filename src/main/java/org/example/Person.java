package org.example;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Person {
    public Person(String name, int age, String email, String school){
        this.name = name;
        this.age = age;
        this.email = email;
        this.school = school;
    }
    int id;
    String name;
    int age;
    String email;
    String school;
}
