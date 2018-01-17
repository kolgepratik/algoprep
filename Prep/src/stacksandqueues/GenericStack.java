/**
 * 
 */
package stacksandqueues;

import java.util.ArrayList;
import java.util.List;

/**
 * @author t2590pk
 *
 */
public class GenericStack<T> {
	List<T> data;

	public GenericStack() {
		super();
		data = new ArrayList<T>();
	}

	public void push(T item) {
		data.add(item);
	}

	public T pop() {
		if (this.isEmpty()) {
			return null;
		} else {
			return data.remove(data.size() - 1);
		}
	}

	public boolean isEmpty() {
		return data.isEmpty();
	}
}
