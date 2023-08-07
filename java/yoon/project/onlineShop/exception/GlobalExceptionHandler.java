package yoon.project.onlineShop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import yoon.project.onlineShop.dto.response.ErrorResponse;
import yoon.project.onlineShop.enums.Errors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({UsernameNotFoundException.class})
    public ResponseEntity<ErrorResponse> UserNameNotFoundHandler(UsernameNotFoundException e){
        Errors errors = Errors.USER_NAME_NOT_FOUND;
        ErrorResponse response = new ErrorResponse();
        response.setCode(errors.getCode());
        response.setMessage(errors.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({BadCredentialsException.class})
    public ResponseEntity<ErrorResponse> BadCredentialHandler(BadCredentialsException e){
        Errors errors = Errors.PASSWORD_NOT_FOUND;
        ErrorResponse response = new ErrorResponse();
        response.setCode(errors.getCode());
        response.setMessage(errors.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorResponse> ValidatedExceptionHandler(MethodArgumentNotValidException e){
        BindingResult bindingResult = e.getBindingResult();
        String message = bindingResult.getAllErrors().get(0).getDefaultMessage();
        ErrorResponse response = new ErrorResponse();
        if(message == null){
            response.setMessage("잘못된 요청");
            response.setCode("Z001");
        }
        else if(message.equals(Errors.USER_ID_BLANK.getCode())){
            response.setMessage(Errors.USER_ID_BLANK.getMessage());
            response.setCode(Errors.USER_ID_BLANK.getCode());
        }
        else if(message.equals(Errors.USER_PASSWORD_BLANK.getCode())){
            response.setMessage(Errors.USER_PASSWORD_BLANK.getMessage());
            response.setCode(Errors.USER_PASSWORD_BLANK.getCode());
        }
        else if(message.equals(Errors.USER_NAME_BLANK.getCode())){
            response.setMessage(Errors.USER_NAME_BLANK.getMessage());
            response.setCode(Errors.USER_NAME_BLANK.getCode());
        }
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
