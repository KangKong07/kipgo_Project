package com.spring.backend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDto {

    private String memberId;

    private String memberPwd;

    private String name;

    private String telNo;

    private String email;

}
