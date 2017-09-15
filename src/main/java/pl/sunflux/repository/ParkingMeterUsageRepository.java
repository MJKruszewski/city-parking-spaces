package pl.sunflux.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sunflux.entity.ParkingMeterUsage;
import pl.sunflux.entity.Vehicle;

/**
 * Created by maciej on 14.09.17.
 */
@Repository
public interface ParkingMeterUsageRepository extends JpaRepository<ParkingMeterUsage, Long> {
    public ParkingMeterUsage findByIdAndVehicleAndDateEndIsNull(Long id, Vehicle vehicle);
    public ParkingMeterUsage findFirstByVehicleOrderByIdDesc(Vehicle vehicle);
}
