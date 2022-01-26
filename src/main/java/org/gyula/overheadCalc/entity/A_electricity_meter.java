package org.gyula.overheadCalc.entity;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name="a_electricity_meter")
public class A_electricity_meter {

    @Id
    @CreatedDate
    @Column(name = "curr_date")
    private LocalDateTime dateId = LocalDateTime.now();

    @Column(name = "meter")
    @NotNull(message = "The electricity meter cannot be null")
    @Positive(message = "The electricity meter value should be a positive number")
    private double electricity_meter;

    @ManyToOne
    @JoinColumn(name = "flat_id")
    private A_flat a_flat;

    public A_electricity_meter() {
    }

    public A_electricity_meter(double electricity_meter) {
        this.electricity_meter = electricity_meter;
    }
}
