package lk.ijse.waytosuccess.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class CartServiceDto {
    private String service_code;
    private Double charge;
}
