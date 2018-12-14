package com.ex.hashcode;

/**
 * PhoneNumber class to show why you must override
 * equals() AND hashCode() if you decide to override
 * either one.
 * 
 * This is especially important when using hashed based
 * collections: HashMap, HashSet, HashTable
 * 
 * @author TJ
 *
 */
public class PhoneNumber {
	
	private int areaCode;
	private int prefix;
	private int lineNumber;
	
	public PhoneNumber(){}

	public PhoneNumber(int areaCode, int prefix, int lineNumber) {
		super();
		this.areaCode = areaCode;
		this.prefix = prefix;
		this.lineNumber = lineNumber;
	}

	public int getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(int areaCode) {
		this.areaCode = areaCode;
	}

	public int getPrefix() {
		return prefix;
	}

	public void setPrefix(int prefix) {
		this.prefix = prefix;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	@Override
	public int hashCode() {
		System.out.println("hashcode");
		final int prime = 31;
		int result = 1;
		result = prime * result + areaCode;
		result = prime * result + lineNumber;
		result = prime * result + prefix;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		System.out.println("equals");
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PhoneNumber other = (PhoneNumber) obj;
		if (areaCode != other.areaCode)
			return false;
		if (lineNumber != other.lineNumber)
			return false;
		if (prefix != other.prefix)
			return false;
		return true;
	}
	
	
	
}
