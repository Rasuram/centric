
package com.centric.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "PRODUCT")
@Getter
@Setter
@EqualsAndHashCode
public class Product implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "BRAND")
    private String brand;

    @ElementCollection
    @CollectionTable(
            name = "TAGS",
            joinColumns = @JoinColumn(name = "id")
    )
    private List<String> tags;

    @Column(name = "CATEGORY")
    private String category;

    @Column(name = "CREATED_AT")
    @JsonProperty("created_at")
    private String createdAt;

}
