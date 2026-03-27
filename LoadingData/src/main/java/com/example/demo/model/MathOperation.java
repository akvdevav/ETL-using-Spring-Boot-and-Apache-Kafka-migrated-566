package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class MathOperation {

    private String numberOne;
    private String numberTwo;
    @Id
    private String operation;
    private String result;

    public MathOperation() {
    }

    public MathOperation(String numberOne, String numberTwo, String operation, String result) {
        this.numberOne = numberOne;
        this.numberTwo = numberTwo;
        this.operation = operation;
        this.result = result;
    }

    public String getNumberOne() {
        return numberOne;
    }

    public void setNumberOne(String numberOne) {
        this.numberOne = numberOne;
    }

    public String getNumberTwo() {
        return numberTwo;
    }

    public void setNumberTwo(String numberTwo) {
        this.numberTwo = numberTwo;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    // Additional helper methods required by other parts of the codebase

    /**
     * Returns the identifier of this entity.
     *
     * @return the operation field which serves as the primary key
     */
    public String getId() {
        return operation;
    }

    /**
     * Returns a CSV representation of the math operation.
     *
     * @return a string in the format "numberOne,numberTwo,operation,result"
     */
    public String toCsv() {
        return String.join(",", numberOne, numberTwo, operation, result);
    }
}