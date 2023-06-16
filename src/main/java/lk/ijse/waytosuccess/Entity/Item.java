package lk.ijse.waytosuccess.Entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class Item {
    private String item_code;
    private String item_type;
    private String description;
    private Double unit_price;
    private int qty;
}
