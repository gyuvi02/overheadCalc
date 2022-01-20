package org.gyula.overheadCalc.entity;


import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name="a_gas_meter")
public class A_gas_meter {

    @Id
    @CreatedDate
    @Column(name = "curr_date")
    private LocalDateTime dateId = LocalDateTime.now();

    @Column(name = "meter")
    @NotNull
    private double gas_meter;

    @ManyToOne
    @JoinColumn(name = "flat_id")
    private A_flat a_flat;

    public A_gas_meter() {
    }

    public A_gas_meter(double gas_meter) {
        this.gas_meter = gas_meter;
    }
}
