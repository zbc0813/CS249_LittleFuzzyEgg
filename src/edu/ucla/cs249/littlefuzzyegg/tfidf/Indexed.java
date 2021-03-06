package edu.ucla.cs249.littlefuzzyegg.tfidf;

import java.util.*;
import edu.ucla.cs249.littlefuzzyegg.split.Dictionary;


public class Indexed<T> {
	private final T key;
	private final BagOfTags bag;
	
	public Indexed(T key) {
		this.key = key;
		this.bag = new BagOfTags();
	}
	
	public void addCount(List<Tag> tags, boolean fromProduct) {
		bag.addCount(tags, fromProduct);
	}
		
	public T getKey() {
		return key;
	}
	
	public List<Tag> getTags() {
		return bag.getTags();
	}
	
	public int getCount(Tag tag) {
		return bag.getCount(tag);
	}
	
	public BagOfTags getBag() {
		return bag;
	}
}
