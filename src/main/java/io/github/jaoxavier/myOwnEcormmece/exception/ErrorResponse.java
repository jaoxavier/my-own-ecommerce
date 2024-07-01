package io.github.jaoxavier.myOwnEcormmece.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorResponse {
    private LocalDateTime timestamp;
    private Integer status;
    private String message;
    private String exception;
    private String trace;
}
