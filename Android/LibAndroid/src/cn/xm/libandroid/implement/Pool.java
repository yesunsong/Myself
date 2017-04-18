package cn.xm.libandroid.implement;

import java.util.ArrayList;
import java.util.List;

public class Pool<T> {
	private final List<T> freeOjects;
	private final PoolObjectFactory<T> factory;
	private final int maxSize;
	
	public Pool(PoolObjectFactory<T> factory,int maxSize){
		this.factory=factory;
		this.maxSize=maxSize;
		this.freeOjects=new ArrayList<T>(maxSize);
	}
	
	public T newObject(){
		T object=null;
		if (freeOjects.isEmpty()) {
			object=factory.createObject();
		}else {
			object=freeOjects.remove(freeOjects.size()-1);
		}
		return object;
	}
	
	public void free(T object){
		if (freeOjects.size()<maxSize) {
			freeOjects.add(object);
		}
	}          
}

