package yoon.project.onlineShop.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="product")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idx;

    @ManyToOne
    @JoinColumn(name="product_member")
    private Members seller;

    @Column(nullable = false, length = 250)
    private String name;

    @Column(nullable = false)
    private int price;

    private String img;

    @CreationTimestamp
    private LocalDateTime regdate;

    @Builder
    public Products(Members seller, String name, int price, String img){
        this.seller = seller;
        this.name = name;
        this.price = price;
        this.img = img;
    }
}
