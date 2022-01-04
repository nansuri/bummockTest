package Core;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Riza Nansuri
 * @version $Id: CoreContext.java, v 0.1 2022‐01‐04 09.52 Riza Nansuri Exp $$
 */
public class CoreContext {
    private final Map<String, Object> attributes = new HashMap();

    public void setAttribute(String name, Object value) {
        this.attributes.put(name, value);
    }

    public Object getAttribute(String name) {
        return attributes.get(name);
    }
}
