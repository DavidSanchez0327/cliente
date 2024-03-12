package co.com.sofka.jpa.gateway;

import co.com.sofka.jpa.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerAdapterRepository extends JpaRepository<CustomerEntity, String> {

}
