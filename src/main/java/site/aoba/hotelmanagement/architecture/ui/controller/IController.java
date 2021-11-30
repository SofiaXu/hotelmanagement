package site.aoba.hotelmanagement.architecture.ui.controller;

import org.springframework.web.bind.annotation.RestController;
import site.aoba.hotelmanagement.architecture.ui.dto.JsonResponse;

import java.util.Date;

@RestController
public interface IController {
    default <T> JsonResponse<T> Ok(T content) {
        return new JsonResponse<>(200, "OK", new Date(), content);
    }

    default <T> JsonResponse<T> Error(int statusCode, String message, T content) {
        return new JsonResponse<>(statusCode, message, new Date(), content);
    }
}
