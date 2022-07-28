package com.cuukenn.openstudysource.jdk.assist;

import lombok.Data;

/**
 * @author changgg
 */
@Data
public class UserDto implements IDto {
    private String username;
    private String password;
}
