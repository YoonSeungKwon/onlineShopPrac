package yoon.project.onlineShop.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="order")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idx;

    @ManyToOne
    @JoinColumn(name="order_member")
    private Members buyer;

    @ManyToOne
    @JoinColumn(name="order_product")
    private Products product;

    @Column(nullable = false, length = 250)
    private String address;

    @ColumnDefault("1")
    private int count;

    @CreationTimestamp
    private LocalDateTime date;

    @Builder
    public Orders(Members buyer, Products product, String address, int count){
        this.buyer = buyer;
        this.product = product;
        this.address = address;
        this.count = count;
    }

}
