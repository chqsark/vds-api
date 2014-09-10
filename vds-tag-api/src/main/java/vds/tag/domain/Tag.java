package vds.tag.domain;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Tag {
    private String selector;


    public String getSelector() {
        return selector;
    }

    public void setSelector(String selector) {
        this.selector = selector;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Tag)) {
            return false;
        }
        Tag tag = (Tag) o;

        return Objects.equals(selector, tag.selector);
    }

    @Override
    public int hashCode() {
        return Objects.hash(selector);
    }
}
