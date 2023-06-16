package lk.ijse.waytosuccess.Entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class CartItem {
    private String item_code;
    private Double unit_price;
    private Integer qty;
}
