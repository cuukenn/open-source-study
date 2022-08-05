package com.cuukenn.openstudysource.sample.jdk.assist;

import lombok.Data;

/**
 * @author changgg
 */
@Data
public class UserDto implements IDto {
    private String username;
    private String password;
}
