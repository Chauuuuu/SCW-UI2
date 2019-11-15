package com.atguigu.scw.ui.vo.reponse;

import com.atguigu.scw.ui.bean.TReturn;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString
public class ProjectResponseVo implements Serializable{
    private Integer id;
    private String name;
    private String remark;
    private Long money;
    private Integer day;
    private Byte status;
    private String deploydate;
    private Long supportmoney;
    private Integer supporter;
    private Integer completion;
    private Integer memberid;
    private String createdate;
    private Integer follower;
    private String headerImage;
    private List<String> detailsImage = new ArrayList<>();
    private List<TReturn> returns = new ArrayList<>();
}
