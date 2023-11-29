package ToDo1Adv;

import java.util.Collection;

public interface DaoInterface<T, K> {
	Collection<T> getAll();//Method to retrieve all the records
	T getOne(K key);//Method to retrieve single record based upon its identity (ID)
}
