package dev.omedia.dto;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "sportsmen")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Sportsman {

    @Id
    @Column(name = "sportsman_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sportsman_id_gen")
    @SequenceGenerator(name = "sportsman_id_gen", sequenceName = "sportsman_id_seq")
    private long id;

    @EqualsAndHashCode.Include
    @Column(name = "personalId")
    private String personalId;

    @Column(name = "fullName")
    private String fullName;

    @Column(name = "birthday")
    private LocalDate birthday;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;
}
