package com.project.assetManage.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity(name ="deposit")
public class Deposit {

    @Id
    @Column(name="seq")
    private Long seq;

    @Column(name = "name")
    private String name;
    @Column(name = "bank")
    private String bank;
    @Column(name = "requirements")
    private String requirements;
    @Column(name = "image")
    private String image;
    @Column(name = "type")
    private String type;
}
