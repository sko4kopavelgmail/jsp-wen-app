package app.Controller;

import java.util.Map;

public class Utils {
    public static boolean validate(Map<String,String> form){
        for (String key: form.keySet())
            if (key.isEmpty())
                return false;

        return true;
    }
}
