package com.freddykilo.AlgorithmsAndDataStructures;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Freddy
 *
 */
public class LinkedList {
	public Link head = null;
	public Link tail = null;
	public int min = -1;
	
	public class Link{
		Link next;
		Link prev = null;
		int min = -1;
		public Object item;
		
		public Link(Object o){
			item = o;
		}
	}
	
	// Push item to stack
	public void push(Object o){
		Link newNode = new Link(o);
		if(head == null || (int)newNode.item < min){
			min = (int)newNode.item;
		}
		newNode.min = min;
		newNode.next = head;
		if(newNode.next != null) newNode.next.prev = newNode;
		head = newNode;
		if(head.next == null) tail = head;
	}
	
	// Add item to queue
	public void add(Object o){
		Link newNode = new Link(o);
		if(head == null){
			head = newNode;
			tail = newNode;
		} else {
			tail.next = newNode;
			tail.next.prev = tail;
			tail = newNode;
		}
	}
	
	public Link pop(){
		if(head == null) return null;
		if((int)head.item < (int)head.next.item) min = (int)head.next.min;
		head.prev = null;
		head = head.next;
		return head;
	}
	
	public Link remove(){
		if(tail == null) return null;
		tail = tail.prev;
		if(tail != null) tail.next = null;
		return tail;
	}
	
	public int min(){
		return min;
	}
	
	public LinkedList removeDuplicates(){
		Set<Object> set = new HashSet<>();
		Link tempLink = head;
		head = null;
		while(tempLink != null){
			if(!set.contains(tempLink.item)){
				set.add(tempLink.item);
				this.add(tempLink.item);
			}
			tempLink = tempLink.next;
		}
		return this;
	}
	
	public Link findKthToLast(int kthToLast){
		Link headLink = this.head;
		if(headLink == null) return null;
		int count = 1;
		while(headLink.next != null){
			count++;
			headLink = headLink.next;
		}
		if((count = count - kthToLast) < 0) return null;
		headLink = this.head;
		while(count > 0){
			headLink = headLink.next;
			count--;
		}
		return headLink;
	}
	
	public void print(){
		Link n = this.head;
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		while(n != null){
			sb.append(n.item + ", ");
			n = n.next;
		}
		sb.replace(sb.length()-2, sb.length(), "");
		sb.append("}");
		System.out.println(sb.toString());
	}
	

}
