package lk.ijse.waytosuccess.Entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class Service {
    private String service_code;
    private String description;
    private Double service_charge;
}
