package co.com.sofka.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "client")
@Entity
public class CustomerEntity {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "password")
    private String password;
    @Column(name = "state")
    private Boolean state;
    @Column(name = "personid")
    private String personId;
}
