package yoon.project.onlineShop.dto.response;

import lombok.Data;

@Data
public class ErrorResponse {

    private String code;

    private String message;


    public ErrorResponse(){
        this.code = null;
        this.message = null;
    }
}
