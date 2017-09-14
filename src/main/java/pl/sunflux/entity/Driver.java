package pl.sunflux.entity;

import pl.sunflux.entity.constants.DriverTypeEnum;

import javax.persistence.*;

/**
 * Created by maciej on 14.09.17.
 */
@Table(name = "driver")
@Entity
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "driver_unique_name", unique = true, nullable = false)
    private String driverUniqueName;
    @Column(name = "driver_type", nullable = false)
    private DriverTypeEnum driverTypeEnum = DriverTypeEnum.REGULAR;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDriverUniqueName() {
        return driverUniqueName;
    }

    public void setDriverUniqueName(String driverUniqueName) {
        this.driverUniqueName = driverUniqueName;
    }

    public DriverTypeEnum getDriverTypeEnum() {
        return driverTypeEnum;
    }

    public void setDriverTypeEnum(DriverTypeEnum driverTypeEnum) {
        this.driverTypeEnum = driverTypeEnum;
    }
}