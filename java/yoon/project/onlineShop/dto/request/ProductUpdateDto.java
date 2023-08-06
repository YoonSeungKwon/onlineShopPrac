package yoon.project.onlineShop.dto.request;

import lombok.Data;
import lombok.Getter;

@Getter
public class ProductUpdateDto {

    private String name;

    private int price;

    private String img;

}
