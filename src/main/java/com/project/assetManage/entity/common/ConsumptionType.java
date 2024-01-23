package com.project.assetManage.entity.common;

import com.project.assetManage.util.BaseDateTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity(name = "consumption_type_detail")
public class ConsumptionType extends BaseDateTimeEntity {

    @Id
    @Column(name = "consumption_code")
    private Long consumptionCode;

    @Column(name = "consumption_title")
    private String consumptionTitle;

    @Column(name = "consumption_contents")
    private String consumptionContents;

    @Column(name = "img_url")
    private String imgUrl;
}
