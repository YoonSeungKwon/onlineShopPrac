package yoon.project.onlineShop.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ProductResponse {

    private String name;

    private String seller;

    private int price;

    private LocalDateTime regdate;

}
