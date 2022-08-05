package com.cuukenn.openstudysource.sample.jdk.spi.plugin;

import com.cuukenn.openstudysource.sample.jdk.spi.metadata.IPluginService;

/**
 * @author changgg
 */
public class PhonePluginServiceImpl implements IPluginService {
    @Override
    public String getPluginName() {
        return "phone-plugin";
    }
}
