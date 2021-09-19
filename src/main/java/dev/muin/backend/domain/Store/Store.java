package dev.muin.backend.domain.Store;

import dev.muin.backend.domain.Tag.Tag;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;


@Getter
@Entity
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="store_id")
    private short id;
    private String uuid;

    @Embedded
    private Location location;

    private String name;

    @JoinTable(
        name = "store_tag",
        joinColumns = @JoinColumn(name = "store_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id"))
    @ManyToMany
    private List<Tag> tags;

    @Override
    public String toString() {
        return String.format("{id=%d,uuid=%d,location=(%lf,%lf),name=%s,keyword=%s");
    }
}
