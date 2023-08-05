package yoon.project.onlineShop.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ResponseMessage {

    private HttpStatus code;

    private String message;

    private Object data;

    public ResponseMessage(){
        this.code = HttpStatus.BAD_REQUEST;
        this.message = null;
        this.data = null;
    }

}
