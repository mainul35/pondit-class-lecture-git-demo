package com.spring5.practice.exceptions.model;

import lombok.*;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ItemValidationError {
    private String itemName;
    private String field;
    private Object rejectedValue;
    private String message;
}
