package com.freddykilo.AlgorithmsAndDataStructures;

public class BinaryTree {
	
	public Node root = null;
	public Node min = null;
	public Node max = null;
	int size = 0;
	int depth = 0;
	
	public class Node {
		public int value;
		Node parent = null;
		Node left = null;
		Node right = null;
		
		public Node(int value){
			this.value = value;
		}
	}
	
	public void insert(int value){
		Node node = new Node(value);
		size++;
		if(root == null){
			root = node;
			min = node;
			max = node;
			depth = 1;
			return;
		}
		if(value < min.value) min = node;
		else if(value > max.value) max = node;
		Node cursor = root;
		Node parent = null;
		int depthCount = 1;
		while(cursor != null){
			parent = cursor;
			if(node.value < cursor.value){
				cursor = parent.left;
			} else {
				cursor = parent.right;
			}
			depthCount++;
		}
		if(depthCount > depth) depth = depthCount;
		node.parent = parent;
		if(node.value < parent.value) parent.left = node;
		else parent.right = node;
	}
	
	public void delete(int value){
		Node z = find(value);
		if(z.left == null && z.right == null){ // x has no children
			if(isLeftChild(z)) z.parent.left = null;
			else z.parent.right = null;
		} else if(z.left != null && z.right == null){ // x has only 1 child on the left
			z.left.parent = z.parent;
			if(isLeftChild(z)) z.parent.left = z.left;
			else z.parent.right = z.left;
		} else if(z.right != null && z.left == null){ // x has only 1 child on the right
			z.right.parent = z.parent;
			if(!isLeftChild(z)) z.parent.right = z.right;
			else z.parent.left = z.right;
		} else { // x has 2 children
			Node y = getSuccessor(z);
			if(z != root){ // update z.parent.child
				if(isLeftChild(z)) z.parent.left = y;
				else z.parent.right = y;
			}
			y.left = z.left;
			z.left.parent = y;
			if(isLeftChild(y)){
				y.parent.left = y.right;
				y.right.parent = y.parent;
				y.right = z.right;
				z.right.parent = y;
			}
			y.parent = z.parent;
			if(root == z) root = y;
			z = null;
		}
	}
	
	public void balance() {
		int[] intArray = new int[this.size];
		int arraySize = intArray.length;
		treeToArray(root, intArray);
		this.root = null;
		addBackInto(0, arraySize - 1, intArray);
		insert(intArray[arraySize - 1]);
	}

	private void treeToArray(Node x, int[] intArray) {
	    if(x != null) {
	    	treeToArray(x.right, intArray);
	    	size--;
	    	intArray[size] = x.value;
	    	treeToArray(x.left, intArray);
	    }
	}
	
	private void addBackInto(int lo, int hi, int[] intArray) {
		int midpoint = (lo + hi) / 2;
	    if(lo < hi){
	    	insert(intArray[midpoint]);
	    	addBackInto(lo, midpoint, intArray);
	    	addBackInto(midpoint+1, hi, intArray);
	    }
	}
	
	public Node find(int key){
		return find(this.root, key);
	}
	
	private Node find(Node x, int key){
		if(x == null) return null;
		if(x.value == key) return x;
		if(key < x.value){
			return find(x.left, key);
		} else {
			return find(x.right, key);
		}
	}
	
	public Node getSuccessor(Node x){
		if(x == max) return x;
		if(x.right != null) return getMin(x.right);
		while(x.parent.left != x){
			x = x.parent;
		}
		return x.parent;
	}
	
	public Node getPredecessor(Node x){
		if(x == min) return x;
		if(x.left != null) return getMax(x.left);
		while(x.parent.right != x){
			x = x.parent;
		}
		return x.parent;
	}
	
	private boolean isLeftChild(Node x){
		return x == x.parent.left;
	}
	
	public void printInOrder(){
		inOrderWalk(this.root);
		System.out.println();
	}
	
	// In-order traversal
	private void inOrderWalk(Node x){
		if(x != null){
			inOrderWalk(x.left);
			System.out.print(x.value + " ");
			inOrderWalk(x.right);
		}
	}
	
	public void printPreOrder(){
		preOrderWalk(this.root);
		System.out.println();
	}
	
	// Pre-order traversal
	public void preOrderWalk(Node x){
		if(x != null){
			System.out.print(x.value + " ");
			preOrderWalk(x.left);
			preOrderWalk(x.right);
		}
	}
	
	public void printPostOrder(){
		postOrderWalk(this.root);
		System.out.println();
	}
	
	// Post-order traversal
	public void postOrderWalk(Node x){
		if(x != null){
			postOrderWalk(x.left);
			postOrderWalk(x.right);
			System.out.print(x.value + " ");
		}
	}
	
	public Node getMin(){
		return min;
	}
	
	public Node getMax(){
		return max;
	}
	
	// Recursive
	public Node getMin(Node x){
		if(x != null && x.left != null){
			return getMin(x.left);
		}
		return x;
	}
	
	// Iterative
	public Node getMax(Node x){
		while(x != null && x.right != null){
			x = x.right;
		}
		return x;
	}
	
}
