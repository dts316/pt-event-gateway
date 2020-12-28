package com.philantree.eventgateway.controller;

import com.philantree.eventgateway.exception.UserException;
import com.philantree.eventgateway.model.User;
import com.philantree.eventgateway.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/v1/user", produces = "application/json")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    @Autowired
    UserService userService;

    Instant instant = Instant.now();
    long timestamp = instant.toEpochMilli();

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) throws UserException{
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .cacheControl(CacheControl.noCache())
                    .headers(headers)
                    .body(userService.getUser(id));
        }
        catch(Exception e) {
            log.error(e.getLocalizedMessage());
            throw new UserException(HttpStatus.NOT_FOUND, "User with id: [" +id+"] not found");
        }
    }

    @GetMapping("/validate/{id}")
    public ResponseEntity<?> validateUser(
            @RequestHeader(value="Access-key") String accessKey,
            @RequestHeader(value="Secret-key") String secretKey,
            @PathVariable Long id)
    {
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(userService.checkCredentials(accessKey, secretKey,id, timestamp));
        }
        catch(Exception e) {
            log.error(e.getLocalizedMessage());
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("Error Message");
        }
    }
}
