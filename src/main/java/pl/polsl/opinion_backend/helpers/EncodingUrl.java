package pl.polsl.opinion_backend.helpers;

import org.springframework.stereotype.Component;

@Component
public class EncodingUrl {

    public static String getUrlPathFromString(String string) {
        string = string.replace("%", "%25");
        string = string.replace(" ", "%20");
        string = string.replace("!", "%21");
        string = string.replace("\"", "%22");
        string = string.replace("#", "%23");
        string = string.replace("$", "%24");
        string = string.replace("&", "%26");
        string = string.replace("'", "%27");
        string = string.replace("(", "%28");
        string = string.replace(")", "%29");
        string = string.replace("*", "%2a");
        string = string.replace("+", "%2b");
        string = string.replace(",", "%2c");
        //string = string.replace("-", "%2d");
        string = string.replace(".", "%2e");
        string = string.replace("/", "%2f");
        string = string.replace("`", "%60");
        string = string.replace("“", "%93");
        string = string.replace(":", "%3a");
        string = string.replace(";", "%3b");
        string = string.replace("<", "%3c");
        string = string.replace("=", "%3d");
        string = string.replace(">", "%3e");
        string = string.replace("?", "%3f");
        string = string.replace("@", "%40");
        string = string.replace("[", "%5b");
        string = string.replace("\\", "%5c");
        string = string.replace("]", "%5d");
        string = string.replace("^", "%5e");
        string = string.replace("_", "%5f");

        return string;
    }

}
