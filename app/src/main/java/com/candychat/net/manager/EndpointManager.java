package com.candychat.net.manager;

/**
 * Created by Mac on 3/3/15.
 */
public class EndpointManager {

    public static String prefix = "http://candychat.net";

    public static String partnerAvatar = "";

    public static String defaultCover = "http://candychat.net/themes/vdomax1.1/images/default-cover.png";

    public static String defaultMaleAvatar = "http://candychat.net/themes/grape/images/default-male-avatarIv.png";

    public static String getDefaultFemaleAvatar = "http://candychat.net/themes/grape/images/default-male-avatarIv.png";

    public static String getAvatarPath(String path) {
        if (path != null) {
            if (!path.equals(""))
                return prefix + "/" + path;
            else
                return defaultMaleAvatar;
        } else {
            return defaultMaleAvatar;
        }

    }
}
