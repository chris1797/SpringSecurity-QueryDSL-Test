package com.security.demo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Student {
    String name = "chris";


    /* 복사 생성자 */
    public Student(Student val) {
        this.name = val.name;
    }

    /* 복사 팩토리 */
    public static Student copy(Student original) {
        return new Student(original);
    }

//    @Override
//    public Student clone() throws CloneNotSupportedException {
//        return (Student) super.clone();
//    }

}