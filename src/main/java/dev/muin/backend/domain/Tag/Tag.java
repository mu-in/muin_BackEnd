package dev.muin.backend.domain.Tag;

import dev.muin.backend.domain.Store.Store;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
public class Tag {
    @Id
    @Enumerated(EnumType.STRING)
    @Column(length = 15)
    private Keyword keyword;

    @ManyToMany(mappedBy = "tags")
    private List<Store> stores;

}
