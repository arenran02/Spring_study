package com.example.demo.dto.user;

public class UserRequestDTO {

    private String name;
    private String email;

    // 기본 생성자
    public UserRequestDTO() {}

    // 생성자
    public UserRequestDTO(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Getter/Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
