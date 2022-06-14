package com.leafbound.models;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Entity
@Table(name="order_details")
@Data
@ApiModel(value="OrderDetails", description="This model serves as the basic model for all orderDetails entity API operations.")
public class OrderDetails {
	
// initial commit to begin work on OrderDetails model.

} OrderDetails {
    
}
