package com.jesusfernandez.superheroapi.security;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Roles {

    //TODO specify in README which user can use each endpoint

    public static final String ADMIN = "ADMIN";
    public static final String USER = "USER";

}
