package com.cuukenn.openstudysource.jdk.assist;

import lombok.Data;

/**
 * @author changgg
 */
@Data
public class User implements IEntity {
    private String username;
    private String password;
}
