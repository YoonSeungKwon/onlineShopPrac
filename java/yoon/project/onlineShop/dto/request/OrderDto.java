package yoon.project.onlineShop.dto.request;

import lombok.Getter;

@Getter
public class OrderDto {

    private String productIdx;

    private String address;

    private int count;

}
