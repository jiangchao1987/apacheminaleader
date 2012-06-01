package com.candou.leader.demo.send.pojo;

import java.io.Serializable;

/**
 * Note: 发送消息实体
 * Author: JiangChao
 * Date: 5/31/14
 * Email: jiangchao1987@gmail.com
 */
public class MsgBody implements Serializable {

    private static final long serialVersionUID = 7967982134686695397L;

    private int id;
    private String name;
    
    public MsgBody(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
