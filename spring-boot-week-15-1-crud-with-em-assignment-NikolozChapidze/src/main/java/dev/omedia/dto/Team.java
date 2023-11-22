package dev.omedia.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "teams")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Team {
    @Id
    @Column(name = "team_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sportsman_id_gen")
    @SequenceGenerator(name = "sportsman_id_gen", sequenceName = "sportsman_id_seq")
    private long id;

    @Column(name = "name")
    @EqualsAndHashCode.Include
    private String name;

    @Column(name = "sportsmanNum")
    private int sportsmanNum;

    @Column(name = "foundingDate")
    private LocalDate foundingDate;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "sport_id", nullable = false)
    private Sport sport;

    @JsonIgnore
    @OneToMany(mappedBy = "team")
    transient private Set<Sportsman> sportsmen;

}
