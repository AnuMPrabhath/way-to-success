package lk.ijse.waytosuccess.Entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class Customer {
    private  String cust_id;
    private  String cust_name;
    private  String contact;
    private  String address;
    private  String date;
}
