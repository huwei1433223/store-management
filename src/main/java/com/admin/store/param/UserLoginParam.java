package com.admin.store.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Name=UserLoginParam
 * @Author：文以载道
 * @Description： :
 * time：2023/10/30的8:43
 * package：com.admin.store.param
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserLoginParam implements Serializable {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
