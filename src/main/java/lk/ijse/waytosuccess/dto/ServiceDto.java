package lk.ijse.waytosuccess.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class ServiceDto {
    private String service_code;
    private String description;
    private Double service_charge;
}
