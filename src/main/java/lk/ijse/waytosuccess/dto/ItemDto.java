package lk.ijse.waytosuccess.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class ItemDto {
    private String item_code;
    private String item_type;
    private String description;
    private Double unit_price;
    private int qty;
}
