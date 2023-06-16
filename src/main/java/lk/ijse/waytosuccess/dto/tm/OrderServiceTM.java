package lk.ijse.waytosuccess.dto.tm;

import com.jfoenix.controls.JFXButton;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class OrderServiceTM {
    private String service_code;
    private String description;
    private Double service_charge;
    private JFXButton button;
}
