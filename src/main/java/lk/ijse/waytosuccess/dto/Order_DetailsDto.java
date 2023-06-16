package lk.ijse.waytosuccess.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class Order_DetailsDto {
    private String order_id;
    private String item_code;
    private int qty;
}
