package lk.ijse.waytosuccess.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class Place_orderDto {
    private String order_id;
    private String date;
    private String cust_id;
}
