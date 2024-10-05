package com.security.basic;

import com.security.demo.domain.entity.Student;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class test {

    @Test
    @DisplayName("Cloneable - clone() 테스트")
    void copyTest() {
        Student student = new Student();
        System.out.println("init oldStudent's Name : " + student.getName());
    }
}
