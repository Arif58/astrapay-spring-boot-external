//package com.astrapay.exception;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@RestControllerAdvice
//public class CustomExceptionHandler {
//
////    @ExceptionHandler(CustomException.class)
////    public ResponseEntity<Object> handleCustomException(RuntimeException e) {
////        Map<String, Object> response = new HashMap<>();
////        response.put("status", "false");
////        response.put("message", e.getMessage());
////
////        return ResponseEntity.badRequest().body(response);
////    }
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Object> handleInvalidArgumentException(MethodArgumentNotValidException e) {
//
//        Map<String, String> response =  new HashMap<>();
//        response.put("status", "false");
//        e.getBindingResult().getFieldErrors().forEach(fieldError -> {
//            response.put("message", fieldError.getDefaultMessage());
//        });
//
//        return ResponseEntity.badRequest().body(response);
//    }
//
//}
