package com.accenture.controller.advice;

import com.accenture.mapper.CarMapper;

import java.time.LocalDateTime;

public record ErrorDto(LocalDateTime timestamp, int errorCode, String errorMessage){
}
