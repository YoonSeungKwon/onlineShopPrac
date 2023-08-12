package yoon.project.onlineShop.enums;

import lombok.Getter;

@Getter
public enum Errors {

    //A
    //USER REGISTER, LOGIN
    USER_ID_BLANK("A001", "아이디를 입력해주세요."),

    USER_PASSWORD_BLANK("A002", "비밀번호를 입력해주세요."),

    USER_NAME_BLANK("A003", "사용자 이름을 입력해주세요."),

    //B
    //PRODUCT REGISTER, UPDATE, DELETE

    PRODUCT_NAME_BLANK("B001", "상품 이름을 입력해주세요."),

    PRODUCT_PRICE_BLANK("B002", "상품 가격을 입력해주세요."),

    PRODUCT_IMAGE_BLANK("B003", "상품 이미지를 올려주세요"),

    //C
    //Login Failed

    USER_NAME_NOT_FOUND("C001", "존재하지 않는 아이디입니다."),

    PASSWORD_NOT_FOUND("C002", "아이디 또는 비밀번호가 일치하지 않습니다."),

    //D ( 401)
    ACCESS_TOKEN_EXPIRED("D001", "엑세스 토큰이 만료되었습니다."),
    REFRESH_TOKEN_EXPIRED("D002", "리프레쉬 토큰이 만료되었습니다."),

    Internal_Server_Error("Z001", "서버 내부 에러");

    private final String code;

    private final String message;

    Errors(String code, String message){
        this.code = code;
        this.message = message;
    }
}
