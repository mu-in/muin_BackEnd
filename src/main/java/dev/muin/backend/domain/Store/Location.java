package dev.muin.backend.domain.Store;

import javax.persistence.Embeddable;

@Embeddable
public class Location {
    private double latitude;
    private double longitude;
}
