package com.car.carentity;

import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class UserDetailsDTO {
    private String username;
    private boolean hasCar;
    private int hasCarExtraDays;
    private int amountToPay;
    private int extraAmountCharged;
    private Date fromDate;
    private Date returnDate;
    private String carModel; 

    public UserDetailsDTO(String username, boolean hasCar, int hasCarExtraDays, int amountToPay,int extraAmountCharged, Date fromDate, Date returnDate, String carModel) {
        this.username = username;
        this.hasCar = hasCar;
        this.hasCarExtraDays = hasCarExtraDays;
        this.amountToPay = amountToPay;
        this.extraAmountCharged = extraAmountCharged;
        this.fromDate = fromDate;
        this.returnDate = returnDate;
        this.carModel = carModel;
    }
    
    
}
