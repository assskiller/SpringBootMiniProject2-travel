package com.ljh.travels.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Place {
    private String id;
    private String name;
    private String picPath;
    private String hotTime;
    private Double hotTicket;
    private Double dimTicket;
    private String placeDes;
    private String provinceId;

}
