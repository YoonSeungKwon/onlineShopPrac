package yoon.project.onlineShop.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class MemberLoginDto {

    @NotBlank(message = "A001")
    private String id;

    @NotBlank(message = "A002")
    private String password;

}
