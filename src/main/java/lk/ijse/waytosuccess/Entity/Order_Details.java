package lk.ijse.waytosuccess.Entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class Order_Details {
    private String order_id;
    private String item_code;
    private int qty;
}
