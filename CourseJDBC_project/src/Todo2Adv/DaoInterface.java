package Todo2Adv;

import java.util.Collection;

public interface DaoInterface<T, K> {
	Collection<T> getAll();//Method to retrieve all the records
	T getOne(K key);//Method to retrieve single record based upon its identity (ID)
	void create(T t);//Method to create a new record
	void update(T t);
	void deleteOne(K key);
}
