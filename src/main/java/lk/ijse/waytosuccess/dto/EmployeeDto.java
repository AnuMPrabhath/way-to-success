package lk.ijse.waytosuccess.dto;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class EmployeeDto {
    private String emp_id;
    private String emp_name;
    private String contact;
    private String address;
    private String date;
}
