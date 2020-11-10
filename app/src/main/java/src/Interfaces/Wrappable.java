package src.Interfaces;

import java.util.Map;

public interface Wrappable {
    Map<String, Object> wrap();
    Object unwrap(Map<String, Object> map);
}
