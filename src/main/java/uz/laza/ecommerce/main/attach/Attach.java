package uz.laza.ecommerce.main.attach;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import uz.laza.ecommerce.main.Base;

@Setter
@Getter
@Entity
@Table(name = "attach")
public class Attach extends Base {

    @Column
    private String extension;

    @Column
    private String path;

    @Column(name = "origin_name")
    private String originName;

    @Column
    private Long size;

}
