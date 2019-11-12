package com.techprogramming.sets;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.lang3.SerializationUtils;
/**
 * Clase Conjunto, utiliza un tipo de dato generico E
 */
@SuppressWarnings("serial")
public class Conjunto<E extends Serializable> implements Serializable {
	
	/**
    * Estructura de datos para almacenar los elementos
    * del conjunto.
    */
	private HashSet<E> set;
	
	/**
    *Constructor que genera el conjunto vacio.
    */
	public Conjunto() {

		this.set = new HashSet<E>();

	}
	
	/**
    * 
    * Constructor que recibe un arregle primitivo 
    * de elementos tipo E
    * 
    */
	public Conjunto(E[] source) {

		this.set = new HashSet<E>();
		this.set.addAll(Arrays.asList(source));

	}
	
	/**
	* 
    * Constructor que recibe una colección
    * de elementos tipo E
    *
    */
	public Conjunto(Collection<E> collection) {

		this.set = new HashSet<E>();
		this.set.addAll(collection);

	}
	
	/**
    * Agrega un elemento E a la colección. 
    *
    */
	public void add(E element) {
		
		this.set.add(element);
		
	}
	
	/**
    * Obtener la cardinalidad del conjunto.
    * 
    */
	public int size() {
		return this.set.size();
	}
	
	/**
	* 
    * Obtener unión de este conjunto con el conjunto B que recibe
    * como parametro, La unión de A con B es el conjunto de aquellos 
    * elementos que están en A o que están en B. 
    * 
    */
	public Conjunto<E> union(Conjunto<E> conjuntoB) {

		HashSet<E> another = new HashSet<E>(this.set);
		another.addAll(conjuntoB.set);
		return new Conjunto<E>(another);
	}

	/**
	* 
    * Obtener intersección de este conjunto con el conjunto B que recibe
    * como parametro, La intersección de A con B es el conjunto de aquellos 
    * elementos que están en A y que están en B. 
    * 
    */
	public Conjunto<E> intersection(Conjunto<E> conjuntoB) {

		HashSet<E> another = new HashSet<E>(this.set);
		another.retainAll(conjuntoB.set);
		return new Conjunto<E>(another);
	}
	
	/**
	* 
    * Obtener la diferencia de este conjunto con el conjunto B que recibe
    * como parametro, La diferencia de A con B es el conjunto de aquellos 
    * elementos que están en A y que no están en B.
    * 
    */
	public Conjunto<E> difference(Conjunto<E> conjuntoB) {

		HashSet<E> another = new HashSet<E>(this.set);
		another.removeAll(conjuntoB.set);
		return new Conjunto<E>(another);
	}
	
	/**
	* 
	* Regresa si el conjunto B recibido como parametro
	* es subconjunto de este conjunto. Un conjunto B 
	* es un subconjunto del conjunto A 
    * si todo elemento de A es también elemento de B.
    * 
    */
	public boolean subset(Conjunto<E> conjuntoB) {

		return this.set.containsAll(conjuntoB.set) || conjuntoB.empty();
	}
	
	/*
	 * Regresa si este conjunto esta vacio.
	 * */
	public boolean empty() {
		return this.set.isEmpty();
	}
	/**
	* 
	* Regresa si el conjunto B recibido como parametro
	* es subconjunto propio de este conjunto. Un conjunto A es 
	* un subconjunto propio del conjunto B si todo 
	* elemento de A es también elemento de B y además 
	* existe un elemento de B que no es elemento de A.
    * 
    */
	public boolean subsetP(Conjunto<E> conjuntoB) {
		
		return this.set.contains(conjuntoB) && !this.set.containsAll(conjuntoB.set);
	}
	
	/**
	* 
	* Regresa si el conjunto B es contenido en 
	* este conjunto. 
    * 
    */
	public boolean contains(E element) {

		return this.set.contains(element);

	}
	
	/**
	* 
	* Regresa el producto cartesiano de este conjunto
	* con el conjunto B recibido como parametro. 
	* Sean A y B dos conjuntos (posiblemente iguales pero no vacíos),
	* el producto cartesiano de A con B es el conjunto de todas 
	* las parejas ordenadas ( a, b ) donde a pertenece 
	* a A y b pertenece a B.
    * 
    */
	public <T extends Serializable> Conjunto<Par<E, T>> productC(Conjunto<T> another) {

		Conjunto<Par<E, T>> product = new Conjunto<Conjunto.Par<E, T>>();

		Set<E> conjuntoA = this.set;
		Set<T> conjuntoB = another.set;

		E e;
		T t;
		for (Iterator<E> iteratorA = conjuntoA.iterator(); iteratorA.hasNext();) {
			e = iteratorA.next();
			for (Iterator<T> iteratorB = conjuntoB.iterator(); iteratorB.hasNext();) {
				t = iteratorB.next();
				Par<E, T> par = new Par<E, T>(e, t);
				product.set.add(par);
			}
		}
		return product;
	}
	
	/**
	* 
	* Regresa el complemento de este conjunto
	* con el conjunto Universo recibido como parametro. 
	* Sea A un subconjunto de un conjunto universal U. 
	* El complemento de A son todos aquellos elementos de U que
	* no están en A.
    * 
    */
	public Conjunto<E> complement(Conjunto<E> universe) {

		HashSet<E> another = new HashSet<E>(universe.set);
		another.removeAll(this.set);
		return new Conjunto<E>(another);
	}
	
	/**
	* 
	* Regresa el conjunto potencia de este conjunto.
	* El conjunto potencia de un conjunto A es el 
	* conjunto que contiene todos los posibles subconjuntos de A
    * 
    */
	public Conjunto<Conjunto<E>> pow() {

		Conjunto<Conjunto<E>> powerSet = new Conjunto<Conjunto<E>>();

		int n = this.set.size();

		int powerSetSize = (int) Math.pow(2, n);

		for (int i = 0; i < powerSetSize; i++) {

			String binary = Integer.toBinaryString(i);
			while (binary.length() < n) {
				binary = "0" + binary;
			}
			Conjunto<E> temp = new Conjunto<E>();
			E value;
			for (int j = 0; j < n; j++) {
				value = (E) this.set.toArray()[j];
				if (binary.charAt(j) == '1') {
					temp.set.add(value);
				}
			}
			powerSet.set.add(temp);

		}

		return powerSet;
	}
	
	/*
	 * Regresa un clon de este conjunto
	 * */
	@Override
	protected Object clone() {
		
		Conjunto<E> clone = (Conjunto<E>) SerializationUtils.clone(this);
		return clone;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Conjunto))
			return false;
		Conjunto<?> other = (Conjunto<?>) obj;
		if (set == null) {
			if (other.set != null)
				return false;
		} else if (!set.equals(other.set))
			return false;
		return true;
	}
	
	/*
	 * Regresa una representación String de este conjunto
	 * */
	@Override
	public String toString() {

		StringBuilder b = new StringBuilder();
		b.append("{");
		int i = 0;
		for (E e : set) {
			b.append(e);
			if (i != set.size() - 1) {
				b.append(", ");
			}
			i++;
		}

		b.append("}");
		return b.toString();
	}
	
	/*
	 * Clase para representar pares ordenados de un conjunto
	 * */
	static class Par<E extends Serializable, T extends Serializable> implements Serializable {

		private E e;
		private T t;

		public Par(E e, T t) {
			this.e = e;
			this.t = t;
		}

		@Override
		public String toString() {
			return "(" + e + "," + t + ")";
		}
	}
}
