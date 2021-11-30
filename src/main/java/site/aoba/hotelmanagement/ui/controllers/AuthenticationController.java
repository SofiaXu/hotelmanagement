package site.aoba.hotelmanagement.ui.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.aoba.hotelmanagement.application.services.AuthenticationService;
import site.aoba.hotelmanagement.architecture.ui.dto.JsonResponse;
import site.aoba.hotelmanagement.ui.dto.requestmodels.LoginRequest;

import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @CrossOrigin
    @PostMapping(value = "/login")
    @ResponseBody
    public JsonResponse<String> login(@RequestBody LoginRequest loginRequest) {
        JsonResponse<String> result = new JsonResponse<>();
        try {
            String token = authenticationService.login(loginRequest.getUserId(), loginRequest.getPassword());
            if (token == null) {
                result.setContent(null);
                result.setMessage("Not Found");
                result.setStatusCode(404);
                result.setTimestamp(new Date());
                return result;
            }
            result.setContent(token);
            result.setMessage("OK");
            result.setStatusCode(200);
            result.setTimestamp(new Date());
            return result;
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            result.setContent(null);
            result.setMessage("Internal Server Error");
            result.setStatusCode(500);
            result.setTimestamp(new Date());
            return result;
        }
    }
}
