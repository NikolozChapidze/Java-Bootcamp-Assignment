package dev.omedia.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sports")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Sport {

    @Id
    @Column(name = "sport_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sport_id_gen")
    @SequenceGenerator(name = "sport_id_gen", sequenceName = "sport_id_seq")
    private long id;

    @EqualsAndHashCode.Include
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "playersNum")
    private int playersNum;

    @JsonIgnore
    @OneToMany(mappedBy = "sport")
    transient private Set<Team> teams;
}
