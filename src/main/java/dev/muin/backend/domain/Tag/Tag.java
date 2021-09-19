package dev.muin.backend.domain.Tag;

import dev.muin.backend.domain.Store.Store;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;

    private Keyword keyword;

    @ManyToMany(mappedBy = "tags")
    private List<Store> stores;

}
