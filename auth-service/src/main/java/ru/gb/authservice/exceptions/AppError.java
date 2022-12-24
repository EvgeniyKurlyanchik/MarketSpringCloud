package ru.gb.authservice.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppError {
    private String code;
    private String message;

    public AppError(int value, String пароли_не_совпадают) {
    }
}
