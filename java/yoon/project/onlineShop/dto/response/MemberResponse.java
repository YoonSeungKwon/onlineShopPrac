package yoon.project.onlineShop.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MemberResponse {

    private String id;

    private String name;

    private String role;

    private LocalDateTime regdate;

}
