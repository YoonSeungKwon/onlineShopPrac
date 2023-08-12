package yoon.project.onlineShop.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class OrderResponse {

    private String buyer;

    private String product;

    private String address;

    private int count;

    private int total;

    private LocalDateTime regdate;

}
