package com.ex.generics;

import java.util.ArrayList;
import java.util.List;

class Parent{}
class Child extends Parent{}
class GrandChild extends Child{}

public class SuperExtendsExample {
	public static void main(String[] args) {
		
		List<? super Child> listSuper = new ArrayList<Parent>();
		List<? super Child> listSuper2 = new ArrayList<Child>();
//		List<? super Child> listSuperWrong = new ArrayList<GrandChild>(); //compile error
		
		List<? extends Child> listExtends = new ArrayList<GrandChild>();
		List<? extends Child> listExtends2 = new ArrayList<Child>();
//		List<? extends Child> listExtendsWrong = new ArrayList<Parent>(); //compile error
		
	}
}
