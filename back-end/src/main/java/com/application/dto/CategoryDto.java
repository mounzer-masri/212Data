package com.application.dto;

/**
 * Created by  Munzir Masri on 15.8.2018.
 */
public class CategoryDto {
    private Long id;
    private String name;

    public CategoryDto(){

    }
    public CategoryDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
