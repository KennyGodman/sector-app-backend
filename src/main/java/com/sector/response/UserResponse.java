package com.sector.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class UserResponse {

    private String email;

    private String name;

    private String id;

}
