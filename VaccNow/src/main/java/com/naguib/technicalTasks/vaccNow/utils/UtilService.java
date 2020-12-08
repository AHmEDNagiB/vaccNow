package com.naguib.technicalTasks.vaccNow.utils;

import com.naguib.technicalTasks.vaccNow.controller.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UtilService {

    public ResponseEntity<ResponseDTO> getResponse(ResponseDTO responseDTO) {
        if (responseDTO != null && responseDTO.getErrorMessage() == null) {
            return new ResponseEntity(responseDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity(responseDTO, HttpStatus.valueOf(responseDTO.getErrorCode()));
        }
    }

    public Date getDateFromString(String date, String format) {
        if (date == null || format == null) {
            return null;
        }
        try {
            return new SimpleDateFormat(format).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

    }
}
