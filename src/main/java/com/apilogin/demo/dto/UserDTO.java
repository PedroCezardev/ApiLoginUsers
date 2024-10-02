package com.apilogin.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {

    private String username;

    private String password;

    private String email;

    private String instituicaoParceira;

    private String propositoFinal;
    
}
