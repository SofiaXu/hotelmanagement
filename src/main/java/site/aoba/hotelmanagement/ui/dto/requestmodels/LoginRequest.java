package site.aoba.hotelmanagement.ui.dto.requestmodels;

import lombok.Data;

@Data
public class LoginRequest {
    private Integer userId;
    private String password;
}
