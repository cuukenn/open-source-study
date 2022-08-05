package com.cuukenn.openstudysource.sample.jdk.assist;

import lombok.Data;

/**
 * @author changgg
 */
@Data
public class User implements IEntity {
    private String username;
    private String password;
}
