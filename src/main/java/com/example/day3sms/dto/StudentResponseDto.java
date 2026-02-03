package com.example.day3sms.dto;

import lombok.Getter;

@Getter
public class StudentResponseDto {
    String id;
    String name;
    int age;
    String email;

    public StudentResponseDto(String id, String name, int age, String email) {
        this.id=id;
        this.name=name;
        this.age=age;
        this.email=email;
    }
}
