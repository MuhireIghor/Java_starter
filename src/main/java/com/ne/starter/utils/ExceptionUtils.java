package com.ne.starter.utils;

import com.ne.starter.dtos.response.ApiResponse;
import com.ne.starter.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ExceptionUtils {
    public static  <T> ResponseEntity<ApiResponse> handleControllerExceptions(Exception e) {
        System.out.println("Exception caught in controller:");

        if (e instanceof NotFoundException) {
            return new ResponseEntity<>(new ApiResponse(
                    false,
                    e.getMessage()
            ), HttpStatus.NOT_FOUND);
        } else if(e instanceof InvalidUUIDException){
            return new ResponseEntity<>(new ApiResponse(
                    false,
                    e.getMessage()
            ), HttpStatus.BAD_REQUEST);
        }else if(e instanceof ResourceNotFoundException){
            return new ResponseEntity<>(new ApiResponse(
                    false,
                    e.getMessage()
            ), HttpStatus.NOT_FOUND);
        }else if (e instanceof InternalServerErrorException) {
            return new ResponseEntity<>(new ApiResponse(
                    false,
                    e.getMessage()
            ), HttpStatus.INTERNAL_SERVER_ERROR);
        } else if(e instanceof BadRequestAlertException){
            System.out.println("Here I reached=======");
            return new ResponseEntity<>(new ApiResponse(
                    false,
                    e.getMessage()
            ), HttpStatus.BAD_REQUEST);
        }else {
            // Handle other exceptions as needed
            return new ResponseEntity<>(new ApiResponse(
                    false,
                    e.getMessage()
            ), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public static <T> void handleServiceExceptions(Exception e) throws ResourceNotFoundException {
        System.out.println("Exception caught in service:");
        if (e instanceof NotFoundException) {
            throw new NotFoundException(e.getMessage());
        } else if( e instanceof ResourceNotFoundException){
            throw new ResourceNotFoundException(e.getMessage());
        }else if (e instanceof InternalServerErrorException) {
            throw new InternalServerErrorException(e.getMessage());
        } else if (e instanceof BadRequestAlertException){
            throw new BadRequestAlertException(e.getMessage());
        }
//        else {
//            // Rethrow other exceptions as needed
//            throw new RuntimeException("Failed!! Something went wrong", e);
//        }
    }

}