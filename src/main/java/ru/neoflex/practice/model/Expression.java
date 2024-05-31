package ru.neoflex.practice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Expression")
public class Expression {

    @Id
    @Column(name = "expression")
    private String expression;

    @Column(name = "result")
    private Integer result;

    @Override
    public String toString(){
        String JSONString = "{ \"expression\": \"" + expression + "\", \"result\": " + result+" }";
        return JSONString;
    }

}
