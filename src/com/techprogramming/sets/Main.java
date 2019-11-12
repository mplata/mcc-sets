package com.techprogramming.sets;

import com.techprogramming.sets.Conjunto.Par;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String[] data = {"1","2","3","Plata"};
		String[] data2 = {"6","7","3","Delgado"};
		Integer[] integers = {1,4,7,2,1,9};
		Integer[] universe = {1,2,3,4,5,6,7,8,9};
		
		
		Conjunto<String> set = new Conjunto<String>(data);
		Conjunto<String> set2 = new Conjunto<String>(data2);
		Conjunto<String> union = set.union(set2);
		Conjunto<String> intersection = set.intersection(set2);
		Conjunto<String> diff = set.difference(set2);
		
		Conjunto<Integer> integersSet = new Conjunto<Integer>(integers);
		Conjunto<String> igual = new Conjunto<String>(data);
		Conjunto<Integer> universeSet = new Conjunto<Integer>(universe);
		Conjunto<Conjunto.Par<String,String>> product = set.productC(set2);
		
		Conjunto<Par<String, Integer>> productMixed = set.productC(integersSet);
		Conjunto<Integer> complement = integersSet.complement(universeSet);
		
		Conjunto<Conjunto<String>> pow = set.pow();
		System.out.println("Conjunto 1: "+set);
		System.out.println("Conjunto 2: "+set2);
		System.out.println("Union:"+union);
		System.out.println("Diferencia: "+diff);
		System.out.println("intersection: "+intersection);
		System.out.println("Producto: "+product);
		System.out.println("Producto diff clases: "+productMixed);
		
		System.out.println("igualdad: "+set.equals(igual));
		System.out.println(set);
		System.out.println("Conjunto potencia="+pow);
		Conjunto clone = (Conjunto) set.clone();
		System.out.println("Clonado "+clone);
	}

}
