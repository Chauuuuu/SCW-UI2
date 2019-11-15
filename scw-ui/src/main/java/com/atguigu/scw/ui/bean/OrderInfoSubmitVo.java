package com.atguigu.scw.ui.bean;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class OrderInfoSubmitVo implements Serializable{
    private String address;
    private String invoice;
    private String invoictitle;
    private String remark;
    private Integer rtncount;
}
