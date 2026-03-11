package com.example.restcontroller.security;

import jakarta.persistence.*;

@Entity
@Table(name = "api_keys")
public class ApiKey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, name = "api_key")
    private String key;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Boolean active = true;

    public ApiKey() {}

    public ApiKey(String key, String name) {
        this.key = key;
        this.name = name;
    }

    public String getKey() { return key; }
    public String getName() { return name; }
    public Boolean getActive() { return active; }
}