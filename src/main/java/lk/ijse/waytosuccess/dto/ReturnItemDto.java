package lk.ijse.waytosuccess.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class ReturnItemDto {
    private String return_code;
    private String item_code;
    private int qty;
    private String order_id;
}
