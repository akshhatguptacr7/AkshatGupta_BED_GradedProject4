package com.springbootproject.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
public class UpdatedEmployee {
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
}
