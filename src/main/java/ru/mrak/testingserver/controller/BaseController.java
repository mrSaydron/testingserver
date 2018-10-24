package ru.mrak.testingserver.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@Controller
public class BaseController {

    //200 OK («хорошо»)
    @GetMapping("/ok")
    public ResponseEntity<String> responseGetOk(HttpRequest httpRequest) {
        return new ResponseEntity<>(httpRequest.getMethodValue(), HttpStatus.OK);
    }

    //200 OK («хорошо»), 400
    @RequestMapping(path = "/putOkBadRequest", method = {PUT, POST})
    public ResponseEntity<String> responseOk(@RequestBody String request, HttpRequest httpRequest) {
        if(request.equals(httpRequest.getMethodValue())) {
            return new ResponseEntity<>(httpRequest.getMethodValue(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(httpRequest.getMethodValue(), HttpStatus.BAD_REQUEST);
        }
    }

    //302 Found («найдено»)
    @RequestMapping(path = "/found", method = {GET, PUT, POST})
    public ResponseEntity<String> responseFound(HttpRequest httpRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", httpRequest.getMethodValue());
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

    //500 Internal Server Error («внутренняя ошибка сервера»)
    @RequestMapping(path = "/getInternalServerError", method = {GET, PUT, POST})
    public ResponseEntity<String> responseInternalServerError(HttpRequest httpRequest) {
        return new ResponseEntity<>(httpRequest.getMethodValue(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
