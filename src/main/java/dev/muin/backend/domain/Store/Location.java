package dev.muin.backend.domain.Store;

import lombok.Getter;

import javax.persistence.Embeddable;

@Getter
@Embeddable
public class Location {
    private double latitude;
    private double longitude;
}
