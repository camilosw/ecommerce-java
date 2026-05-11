package ecommerce.api.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @ManyToOne
    private Category parent;

    @OneToMany(mappedBy = "parent")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Category> children;
}
