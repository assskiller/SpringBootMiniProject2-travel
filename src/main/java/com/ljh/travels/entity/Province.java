package com.ljh.travels.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Province {
    private String id;
    private String name;
    private String tags;
    private Integer placeCounts;
}
