package yoon.project.onlineShop.enums;

import lombok.Getter;

@Getter
public enum Errors {

    //A
    //USER REGISTER, LOGIN
    USER_EMAIL_INVALID("A001", "이메일 형식이 유효하지 않습니다."),

    USER_EMAIL_BLANK("A002", "이메일을 입력해주세요."),

    USER_PASSWORD_BLANK("A003", "비밀번호를 입력해주세요."),

    USER_NAME_BLANK("A004", "사용자 이름을 입력해주세요."),

    //B
    //PRODUCT REGISTER, UPDATE, DELETE

    PRODUCT_NAME_BLANK("B001", "상품 이름을 입력해주세요."),

    PRODUCT_PRICE_BLANK("B002", "상품 가격을 입력해주세요."),

    PRODUCT_IMAGE_BLANK("B003", "상품 이미지를 올려주세요");

    private final String code;

    private final String message;

    Errors(String code, String message){
        this.code = code;
        this.message = message;
    }
}
