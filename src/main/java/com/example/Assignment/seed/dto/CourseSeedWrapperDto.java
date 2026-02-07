package com.example.Assignment.seed.dto;

import java.util.List;
public class CourseSeedWrapperDto {
    private List<CourseSeedDto> courses;

    public List<CourseSeedDto> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseSeedDto> courses) {
        this.courses = courses;
    }
}
