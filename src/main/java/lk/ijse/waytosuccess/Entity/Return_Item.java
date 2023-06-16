package lk.ijse.waytosuccess.Entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class Return_Item {
    private String return_code;
    private String item_code;
    private int qty;
    private String order_id;
}
