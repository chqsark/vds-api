package vds.tag.domain;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Page {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_page_id")
    @SequenceGenerator(name = "seq_page_id", sequenceName = "seq_page_id")
    @Min(1)
    private Integer id;

    @NotNull
    @Size(min = 1)
    private String url;

    @ElementCollection
    @CollectionTable(name = "tag", joinColumns = @JoinColumn(name = "page_id"))
    @MapKeyColumn(name = "selector")
    @Size(min = 1)
    private Set<Tag> tags = new HashSet<>();


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Page)) {
            return false;
        }
        Page page = (Page) o;

        return Objects.equals(url, page.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url);
    }
}
