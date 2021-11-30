package site.aoba.hotelmanagement.architecture.ui.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonResponse<T> implements Serializable {
    private int statusCode;
    private String message;
    private Date timestamp;
    private T content;
}
