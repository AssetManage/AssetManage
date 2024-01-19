package com.project.assetManage.entity;

import com.project.assetManage.util.BaseDateTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity(name = "member")
public class Member extends BaseDateTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="seq")
    private Long seq;
    @Column(name="member_id")
    private String id;
    @Column(name="password")
    private String password;
    @Column(name="name")
    private String name;
    @Column(name="age")
    private Integer age;
    @Column(name="gender")
    private Gender gender;
    @Column(name="rrn")
    private String rrn;
    @Column(name="phone")
    private String phone;
    @Column(name="job")
    private String job;
    @Column(name="income")
    private Long income;


    @Builder
    public Member(String id, String password, String name, Integer age, Gender gender, String rrn, String phone, String job, Long income) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.rrn = rrn;
        this.phone = phone;
        this.job = job;
        this.income = income;
    }
}
