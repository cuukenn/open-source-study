package com.cuukenn.openstudysource.sample.distribute.message.rocketmq.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author changgg
 */
@Data
public class SimpleMessage implements Serializable {
    private static final long serialVersionUID = 8416271192677824365L;
    private String code;
    private String message;
    private String token;
}
