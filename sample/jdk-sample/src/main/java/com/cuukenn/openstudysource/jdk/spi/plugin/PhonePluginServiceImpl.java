package com.cuukenn.openstudysource.jdk.spi.plugin;

import com.cuukenn.openstudysource.jdk.spi.metadata.IPluginService;

/**
 * @author changgg
 */
public class PhonePluginServiceImpl implements IPluginService {
    @Override
    public String getPluginName() {
        return "phone-plugin";
    }
}
