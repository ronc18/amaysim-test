package pages;

import elements.Element;
import org.openqa.selenium.support.ui.LoadableComponent;

import java.util.Objects;

/**
 * Created by Ron on 13/10/2017.
 */
public abstract class AmaysimPage<T extends AmaysimPage<T>> extends LoadableComponent<T> {
    protected static final String LI = "li";
    protected static final String SPAN = "span";
    protected static final String VALUE = "value";
    protected static final String ANCHOR_TAG = "a";

    private Element element;

    protected Element element() {
        if (Objects.isNull(element)) {
            element = new Element();
        }
        return element;
    }

    @Override
    public void load() {
        // No implementation required
    }
}
