package com.kp.study.algorithms.old.misc;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Temp {

	static int[] array = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
			14, 15, 16 };
	static int[] bitarray = new int[array.length + 1];

	// Testing Git.
	// Testing done.
	public static boolean isValid(String s) {
		Map<Character, Character> pairs = new HashMap<Character, Character>();
		pairs.put('(', ')');
		pairs.put('[', ']');
		pairs.put('{', '}');

		LinkedList<Character> q = new LinkedList<Character>();

		for (int i = 0; i < s.length(); i++) {

			if (pairs.keySet().contains(s.charAt(i))) {
				// starting bracket
				q.addLast(s.charAt(i));
			} else if (pairs.values().contains(s.charAt(i))) {
				// ending bracket
				if (!q.isEmpty() && pairs.get(q.peekLast()) == s.charAt(i)) {
					q.pollLast();
				} else {
					return false;
				}
			}
		}

		if (q.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		if (l1 == null)
			return l2;
		if (l2 == null)
			return l1;

		if (l1.val < l2.val) {
			l1.next = mergeTwoLists(l1.next, l2);
			return l1;
		} else {
			l2.next = mergeTwoLists(l1, l2.next);
			return l2;
		}
	}

	public static List<Integer> getRow(int rowIndex) {
		if (rowIndex == 0) {
			return new ArrayList<Integer>(Arrays.asList(1));
		} else if (rowIndex == 1) {
			return new ArrayList<Integer>(Arrays.asList(1, 1));
		} else {
			List<Integer> row = new ArrayList<Integer>(rowIndex + 1);
			for (int i = 0; i <= rowIndex; i++) {
				row.add(getPascalVal(rowIndex, i));
			}
			return row;
		}
	}

	public static int getPascalVal(int rowNumber, int colNumber) {
		BigInteger val = (getFactorial(rowNumber)
				.divide(getFactorial(colNumber).multiply(
						getFactorial(rowNumber - colNumber))));

		return val.intValue();
	}

	public static BigInteger getFactorial(int number) {
		if (number == 0 || number == 1) {
			return new BigInteger(1 + "");
		} else {
			return new BigInteger(number + "")
					.multiply(getFactorial(number - 1));
		}
	}

	Integer val = 10;

	public void changeit(Integer v) {
		v = 15;
	}

	public static void main(String[] args) {

		Temp t = new Temp();
		System.out.println("val: " + t.val);
		t.changeit(t.val);
		System.out.println("val: " + t.val);

		System.out.println("getPascalVal: " + getPascalVal(21, 1));

		if (true) {
			System.exit(1);
		}

		List<Integer> list = new ArrayList<Integer>(Arrays.asList(1, 2, 1));

		List<Integer> sum = getRow(21);

		for (Integer i : sum) {
			System.out.print(" " + i + " ");
		}

		if (true) {
			System.exit(1);
		}

		/*
		 * Scanner in = new Scanner(System.in);
		 * 
		 * while (true) { System.out.print("\n\nEnter x : "); int x =
		 * in.nextInt(); System.out.println("\n[ x & (-x) ] : " + (x & (-x))); }
		 */

		bitarray[0] = 0;
		for (int i = 1; i < bitarray.length; i++)
			update(i, array[i - 1]);

		for (int i = 0; i < bitarray.length; i++)
			System.out.println("bitarray[" + i + "]: " + bitarray[i]);

		Scanner in = new Scanner(System.in);
		while (true) {
			System.out.print("\n\nEnter lower bound : ");
			int lb = in.nextInt();
			System.out.print("\n\nEnter upper bound : ");
			int ub = in.nextInt();
			System.out.println("\nSum : " + get(lb, ub));
		}

	}

	public static void update(int x, int value) {
		for (; x <= bitarray.length; x += x & -x)
			bitarray[x] += value;
	}

	public static int get(int lb, int ub) {
		int sum = 0;
		for (; ub > lb; ub -= ub & -ub)
			sum += bitarray[ub];
		return sum;
	}
}
