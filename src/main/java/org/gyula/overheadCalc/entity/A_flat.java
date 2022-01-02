package org.gyula.overheadCalc.entity;

import lombok.NonNull;

import javax.persistence.*;

@Entity
@Table(name="a_flat")
public class A_flat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "address")
    @NonNull
    private String address;

    @Column(name = "rent")
    private int rent;

    @Column(name = "associate_fee")
    private int associateFee;

    @Column(name = "gas_basic_price")
    private int gasBasicPrice;

    @Column(name = "electricity_basic_price")
    private int electricityBasicPrice;

    @Column(name = "water_basic_price")
    private int waterBasicPrice;

    @Column(name = "tenant_id")
    private int tenantId = 1; // tenant id = 1 is a dummy tenant to avoid constraint violation
//    private int tenantId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tenant_id", insertable = false, updatable = false)
    private A_tenant theTenant;


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
                  int waterBasicPrice, int tenantId, A_tenant theTenant) {
        this.address = address;
        this.rent = rent;
        this.associateFee = associateFee;
        this.gasBasicPrice = gasBasicPrice;
        this.electricityBasicPrice = electricityBasicPrice;
        this.waterBasicPrice = waterBasicPrice;
        this.tenantId = tenantId;
        this.theTenant = theTenant;
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
