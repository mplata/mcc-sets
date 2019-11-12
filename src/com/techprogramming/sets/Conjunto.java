package com.techprogramming.sets;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.lang3.SerializationUtils;

@SuppressWarnings("serial")
public class Conjunto<E extends Serializable> implements Serializable {

	private HashSet<E> set;

	public Conjunto() {

		set = new HashSet<E>();

	}

	public Conjunto(E[] source) {

		set = new HashSet<E>();
		set.addAll(Arrays.asList(source));

	}

	public Conjunto(Collection<E> collection) {

		set = new HashSet<E>();
		set.addAll(collection);

	}

	public int size() {
		return this.set.size();
	}

	public Conjunto<E> union(Conjunto<E> conjuntoB) {

		HashSet<E> another = new HashSet<E>(this.set);
		another.addAll(conjuntoB.set);
		return new Conjunto<E>(another);
	}

	public Conjunto<E> intersection(Conjunto<E> conjuntoB) {

		HashSet<E> another = new HashSet<E>(this.set);
		another.retainAll(conjuntoB.set);
		return new Conjunto<E>(another);
	}

	public Conjunto<E> difference(Conjunto<E> conjuntoB) {

		HashSet<E> another = new HashSet<E>(this.set);
		another.removeAll(conjuntoB.set);
		return new Conjunto<E>(another);
	}

	public boolean subset(Conjunto<E> conjuntoB) {

		return this.set.containsAll(conjuntoB.set);
	}

	public boolean contains(E element) {

		return this.set.contains(element);

	}

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

	public Conjunto<E> complement(Conjunto<E> universe) {

		HashSet<E> another = new HashSet<E>(universe.set);
		another.removeAll(this.set);
		return new Conjunto<E>(another);
	}

	public Conjunto<Conjunto<E>> pow() {

		Conjunto<Conjunto<E>> powerSet = new Conjunto<Conjunto<E>>();

		int n = this.set.size();

		int powerSetSize = (int) Math.pow(2, n);

		for (int i = 0; i < powerSetSize; i++) {

			String binary = Integer.toBinaryString(i);
			while (binary.length() < n) {
				binary = "0" + binary;
			}
			System.out.println("Binary "+binary);
			Conjunto<E> temp = new Conjunto<E>();
			E value;
			for (int j = 0; j < n; j++) {
				value = (E) this.set.toArray()[j];
				if (binary.charAt(j) == '1') {
					System.out.println("Agregar "+value);
					temp.set.add(value);
				}
			}
			System.out.println("Temp "+temp);
			powerSet.set.add(temp);

		}

		return powerSet;
	}

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
