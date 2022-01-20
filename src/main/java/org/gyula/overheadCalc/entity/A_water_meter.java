package org.gyula.overheadCalc.entity;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name="a_water_meter")
public class A_water_meter {

    @Id
    @CreatedDate
    @Column(name = "curr_date")
    private LocalDateTime dateId = LocalDateTime.now();

    @Column(name = "meter")
    @NotNull
    private double water_meter;

    @ManyToOne
    @JoinColumn(name = "flat_id")
    private A_flat a_flat;

    public A_water_meter() {
    }

    public A_water_meter(double water_meter) {
        this.water_meter = water_meter;
    }
}
