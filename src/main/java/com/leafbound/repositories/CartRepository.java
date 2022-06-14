package com.leafbound.repositories;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.leafbound.models.Carts;
import com.leafbound.models.Products;
import com.leafbound.models.Users;

import io.swagger.annotations.ApiModelProperty;

@Repository
@Transactional
public interface CartRepository extends JpaRepository<User, Integer> {
	
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    @ApiModelProperty(name = "id", value = "An integer value that serves as the unique identifier for any carts entity.", required = true)
//    private int id;
//
//    @OneToOne
//    @JoinColumn(name = "product_id", unique = true, nullable = false)
//    @ApiModelProperty(name = "carts_product_id", value = "An integer value that serves as the product id for the product.", required = true)
//    private Products product;
//
//    @Column(name = "quantity", nullable = false)
//    @ApiModelProperty(name = "quantity", value = "An integer value describing the quantity of a product.", required = true)
//    private int quantity;
//
//    @OneToOne
//    @JoinColumn(name = "customer_id", unique = true, nullable = false)
//    @ApiModelProperty(name = "carts_users_id", value = "An integer value that serves as the customer id for the user.", required = true)
//    private Users user;
	
//product_id quantity customer_id
	
	@Query(value="UPDATE carts SET quantity=?1 product_id=?2 id=?3 RETURNING true", nativeQuery=true)
	public boolean update(int id, Products product, int quantity);
	
	@Query(value="DELETE FROM cart WHERE id=?1 RETURING true", nativeQuery=true)
	public boolean delete(int id);
	
	@Query(value="SELECT * FROM products WHERE id=?1", nativeQuery=true)
	public Products findProducts(int id);
}
