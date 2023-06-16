package lk.ijse.waytosuccess.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class CartItemDto {
    private String item_code;
    private Double unit_price;
    private Integer qty;
}
