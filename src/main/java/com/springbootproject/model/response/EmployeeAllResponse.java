package com.springbootproject.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeAllResponse {
    public Integer id ;
    public String firstname;
    public String lastname;
    public String email;
}
