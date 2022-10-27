package com.stone.system.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Data//provide get and set methods
@AllArgsConstructor // provide arguments constructor
@NoArgsConstructor  //provide non args constructor

public class CustomizeException extends RuntimeException{
    private Integer code;
    private String message;


}
