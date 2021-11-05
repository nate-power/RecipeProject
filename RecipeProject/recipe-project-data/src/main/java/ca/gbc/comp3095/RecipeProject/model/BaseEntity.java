//*********************************************************************************
//* Project: < Null Recipes >
//        * Assignment: assignment #1
//        * Author(s): Justin Bartlett
//        * Student Number: 101246661
//        * Date: October 24 2021
//        * Description: This is the base entity which other entities extend. It currently contains logic for getting
//                      and setting auto-generated Long-type entity IDs.
//*********************************************************************************

package ca.gbc.comp3095.RecipeProject.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }
}