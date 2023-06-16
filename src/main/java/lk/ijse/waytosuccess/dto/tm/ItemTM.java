package lk.ijse.waytosuccess.dto.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class ItemTM {
    private String item_code;
    private String item_type;
    private String description;
    private Double unit_price;
    private int qty;
}
