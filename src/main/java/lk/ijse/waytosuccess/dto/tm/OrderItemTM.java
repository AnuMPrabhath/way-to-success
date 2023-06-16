package lk.ijse.waytosuccess.dto.tm;

import com.jfoenix.controls.JFXButton;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class OrderItemTM {
    private String item_code;
    private String item_type;
    private String description;
    private Double unit_price;
    private Integer qty;
    private Double total;
    private JFXButton button;
}
