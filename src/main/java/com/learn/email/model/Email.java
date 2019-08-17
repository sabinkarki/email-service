package com.learn.email.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Email implements Serializable{

    private static final long serailVersionUID =1L;
    private String[] to;
    private String from;
    private String subject;
    private String content;
    private String mimeType;
    private String [] cc;

}
