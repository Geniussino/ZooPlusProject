package helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class CredentialsUtil {
    public static Properties load(String path) {
        Properties props = new Properties();
        try {
            props.load(new FileInputStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }
}
