package com.ranze.likechat.web.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class EnvironmentUtil {
    @Autowired
    Environment environment;

    public String getActiveProfile() {
        String[] activeProfiles = environment.getActiveProfiles();
        if (activeProfiles.length != 0) {
            return activeProfiles[0];
        }
        return null;
    }

    public boolean isDev() {
        String activeProfile = getActiveProfile();
        return activeProfile != null && activeProfile.equals("dev");

    }
}
