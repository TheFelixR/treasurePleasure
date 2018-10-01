package goteborgsuniversitet.maptestapp.core.Containers;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;

public interface  Inventory<T> {

     void appendTo(T item) throws Exception;
     void remove (T item) throws Exception;

}
