package com.example.demo.ResponseEntity;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Response {

    private static Map<String, Object> body(boolean success, String message, Object data) {
        Map<String, Object> map = new HashMap<>();
        map.put("success", success);
        map.put("message", message);
        map.put("data", data);
        map.put("timestamp", LocalDateTime.now());
        return map;
    }

    // 200 OK
    public static ResponseEntity<?> success(String message, Object data) {
        return ResponseEntity.ok(body(true, message, data));
    }

    // 201 CREATED
    public static ResponseEntity<?> created(String message, Object data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(body(true, message, data));
    }

    // 400 Bad Request
    public static ResponseEntity<?> badRequest(String message, Object data) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body(false, message, data));
    }

    // 401 Unauthorized
    public static ResponseEntity<?> unauthorized(String message) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body(false, message, null));
    }

    // 409 Conflict
    public static ResponseEntity<?> conflict(String message, Object data) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(body(false, message, data));
    }
    //404 Not found
    public static ResponseEntity<?> notfound(String message, Object data){
    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body(false,message,data));
    }
}
