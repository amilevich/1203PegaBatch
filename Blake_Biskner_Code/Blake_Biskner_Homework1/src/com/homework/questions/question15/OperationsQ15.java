package com.homework.questions.question15;

/**
 * Operations Implementing Class
 * @author Blake Biskner
 * @version 1.15
 */

public class OperationsQ15 implements OperationalQ15{
	// Class State
	private double value;
	// Class Behavior
	public OperationsQ15(double value) {
		this.value=value;
	}
	public double getValue() {
		return value;
	}
	// Implement interface methods
	public double addition(double a) {
		return (this.getValue()+a);
	}
	public double subtraction(double a) {
		return(this.getValue()-a);
	}
	public double multiplication(double a) {
		return(this.getValue()*a);
	}
	public double division(double a) {
		return(this.getValue()/a);
	}
}