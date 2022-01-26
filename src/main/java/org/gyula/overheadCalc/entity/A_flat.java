package org.gyula.overheadCalc.entity;

import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="a_flat")
public class A_flat implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "address")
    @Size(min = 10, message = "The address should be longer")
    private String address;

    @PositiveOrZero(message = "The rent should be a positive number")
    @Column(name = "rent")
    private int rent;

    @PositiveOrZero(message = "The associate fee should be a positive number")
    @Column(name = "associate_fee")
    private int associateFee;

    @PositiveOrZero(message = "The basic gas price should be a positive number")
    @Column(name = "gas_basic_price")
    private int gasBasicPrice;

    @PositiveOrZero(message = "The basic electricity price should be a positive number")
    @Column(name = "electricity_basic_price")
    private int electricityBasicPrice;

    @PositiveOrZero(message = "The basic water price should be a positive number")
    @Column(name = "water_basic_price")
    private int waterBasicPrice;

    @Column(name = "tenant_id")
    private int tenantId = 1; // tenant id = 1 is a dummy tenant to avoid constraint violation
//    private int tenantId;

    @PositiveOrZero(message = "The gas unit price should be a positive number")
    @Column(name = "gas_unit_price")
    private double gasUnitPrice;

    @PositiveOrZero(message = "The electricity unit price should be a positive number")
    @Column(name = "electricity_unit_price")
    private double electricityUnitPrice;

    @PositiveOrZero(message = "The water unit price should be a positive number")
    @Column(name = "water_unit_price")
    private double waterUnitPrice;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tenant_id", insertable = false, updatable = false)
    private A_tenant theTenant;

    @OneToMany(mappedBy = "a_flat", cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST})
    private List<A_gas_meter> gas_meters;

    @OneToMany(mappedBy = "a_flat", cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST})
    private List<A_electricity_meter> electricity_meters;

    @OneToMany(mappedBy = "a_flat", cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST})
    private List<A_water_meter> water_meters;

    public A_flat() {
    }

//    public A_flat(String address, int rent, int associateFee, int gasBasicPrice, int electricityBasicPrice,
//                  int waterBasicPrice, int tenantId) {
//        this.address = address;
//        this.rent = rent;
//        this.associateFee = associateFee;
//        this.gasBasicPrice = gasBasicPrice;
//        this.electricityBasicPrice = electricityBasicPrice;
//        this.waterBasicPrice = waterBasicPrice;
//        this.tenantId = tenantId;
//    }

    public A_flat(@NonNull String address, int rent, int associateFee, int gasBasicPrice, int electricityBasicPrice,
                  int waterBasicPrice, int tenantId, A_tenant theTenant, double gasUnitPrice,
                  double electricityUnitPrice, double waterUnitPrice) {
        this.address = address;
        this.rent = rent;
        this.associateFee = associateFee;
        this.gasBasicPrice = gasBasicPrice;
        this.electricityBasicPrice = electricityBasicPrice;
        this.waterBasicPrice = waterBasicPrice;
        this.tenantId = tenantId;
        this.theTenant = theTenant;
        this.gasUnitPrice = gasUnitPrice;
        this.electricityUnitPrice = electricityUnitPrice;
        this.waterUnitPrice = waterUnitPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getRent() {
        return rent;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    public int getAssociateFee() {
        return associateFee;
    }

    public void setAssociateFee(int associateFee) {
        this.associateFee = associateFee;
    }

    public int getGasBasicPrice() {
        return gasBasicPrice;
    }

    public void setGasBasicPrice(int gasBasicPrice) {
        this.gasBasicPrice = gasBasicPrice;
    }

    public int getElectricityBasicPrice() {
        return electricityBasicPrice;
    }

    public void setElectricityBasicPrice(int electricityBasicPrice) {
        this.electricityBasicPrice = electricityBasicPrice;
    }

    public int getWaterBasicPrice() {
        return waterBasicPrice;
    }

    public void setWaterBasicPrice(int waterBasicPrice) {
        this.waterBasicPrice = waterBasicPrice;
    }

    public int getTenantId() {
        return tenantId;
    }

    public void setTenantId(int tenantId) {
        this.tenantId = tenantId;
    }

    public A_tenant getTheTenant() {
        return theTenant;
    }

    public void setTheTenant(A_tenant theTenant) {
        this.theTenant = theTenant;
    }

    public double getGasUnitPrice() {
        return gasUnitPrice;
    }

    public void setGasUnitPrice(double gasUnitPrice) {
        this.gasUnitPrice = gasUnitPrice;
    }

    public double getElectricityUnitPrice() {
        return electricityUnitPrice;
    }

    public void setElectricityUnitPrice(double electricityUnitPrice) {
        this.electricityUnitPrice = electricityUnitPrice;
    }

    public double getWaterUnitPrice() {
        return waterUnitPrice;
    }

    public void setWaterUnitPrice(double waterUnitPrice) {
        this.waterUnitPrice = waterUnitPrice;
    }

    public List<A_gas_meter> getGas_meters() {
        return gas_meters;
    }

    public void setGas_meters(List<A_gas_meter> gas_meters) {
        this.gas_meters = gas_meters;
    }

    public List<A_electricity_meter> getElectricity_meters() {
        return electricity_meters;
    }

    public void setElectricity_meters(List<A_electricity_meter> electricity_meters) {
        this.electricity_meters = electricity_meters;
    }

    public List<A_water_meter> getWater_meters() {
        return water_meters;
    }

    public void setWater_meters(List<A_water_meter> water_meters) {
        this.water_meters = water_meters;
    }

    @Override
    public String toString() {
        return "A_flat{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", rent=" + rent +
                ", associateFee=" + associateFee +
                ", gasBasicPrice=" + gasBasicPrice +
                ", electricityBasicPrice=" + electricityBasicPrice +
                ", waterBasicPrice=" + waterBasicPrice +
                ", tenantId=" + tenantId +
                '}';
    }
}
