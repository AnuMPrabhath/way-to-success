package lk.ijse.waytosuccess.dto.tm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class ServiceTM {
    private String service_code;
    private String description;
    private Double service_charge;
}
