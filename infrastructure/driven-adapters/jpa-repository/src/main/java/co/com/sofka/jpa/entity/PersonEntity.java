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
@Table(name = "person")
@Entity
public class PersonEntity {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "gender")
    private String gender;
    @Column(name = "address")
    private String address;
    @Column(name = "phone")
    private String phone;
    @Column(name = "identity_id")
    private String identityId;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private Integer age;
    @Column(name = "state")
    private Boolean state;
}



