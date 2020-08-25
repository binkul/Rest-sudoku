package com.example.rest.sudoku.exception.advice;

import java.time.LocalDateTime;
import java.util.Objects;

public class SudokuException {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;

    public SudokuException(LocalDateTime timestamp, int status, String error, String message) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof SudokuException)) return false;
        SudokuException that = (SudokuException) o;
        return status == that.status &&
                Objects.equals(timestamp, that.timestamp) &&
                Objects.equals(error, that.error) &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, status, error, message);
    }

    @Override
    public String toString() {
        return "SudokuException{" +
                "timestamp=" + timestamp +
                ", status=" + status +
                ", error='" + error + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
