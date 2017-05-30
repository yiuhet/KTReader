package com.example.yiuhet.ktreader.utils;

import java.util.List;

/**
 * Created by yiuhet on 2017/5/30.
 */

public class WebUtil {
    private WebUtil() {
    }

    public static final String BASE_URL = "file:///android_asset/";
    public static final String MIME_TYPE = "text/html";
    public static final String ENCODING = "utf-8";
    public static final String FAIL_URL = "http//:daily.zhihu.com/";

    private static final String CSS_LINK_PATTERN = " <link href=\"%s\" type=\"text/css\" rel=\"stylesheet\" />";
    private static final String DIV_IMAGE_PLACE_HOLDER = "class=\"img-place-holder\"";

    public static String buildHtmlWithCss(String html, List<String> cssUrls) {
        StringBuilder result = new StringBuilder();
        for (String cssUrl : cssUrls) {
            result.append(String.format(CSS_LINK_PATTERN, cssUrl));
        }
        result.append(html.replace(DIV_IMAGE_PLACE_HOLDER, ""));
        return result.toString();
    }
}
