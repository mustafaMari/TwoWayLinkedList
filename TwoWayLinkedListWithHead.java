import java.util.*;

class TwoWayLinkedListWithHead<E> implements IList<E>{
	
	private class Element{
		public Element(E e) {
			object=e; 
		}
		public Element(E e, Element next, Element prev) {
			object=e; 
			this.next=next;
			this.prev=prev;
		}
		E object;
		Element next=null;
		Element prev=null;
	}
	//most be changed 
	Element head = new Element(null);
	Element tail = new Element(null);
	// can be realization with the field size or without
	int size;
	
	private class InnerIterator implements Iterator<E>{
		Element pos;
		
		public InnerIterator() {
			pos=head;
		}
		@Override
		public boolean hasNext() {
			return pos.next.object!=null;
		}
		
		@Override
		public E next() throws NullPointerException {
			if (hasNext()) {
			pos=pos.next;
			return  pos.object;
			}else 
			throw new NullPointerException("InnerIterator.next");
		}
	}
	
	private class InnerListIterator implements ListIterator<E>{
		Element p = head; 
		@Override
		public void add(E e) {
			throw new UnsupportedOperationException();
			
		}

		@Override
		public boolean hasNext() {  
			if (p.next.object!=null) return true; 
			return false; 
		}

		@Override
		public boolean hasPrevious() {
			if (p.prev!=null&&p.prev!=head) return true;
					return false; 
		}

		@Override
		public E next() throws NullPointerException {
			if (hasNext()) {
				p=p.next;
				return p.object;
			}else 
				throw new NullPointerException("InnerListIterator.next");
			
		}

		@Override
		public int nextIndex() {
			throw new UnsupportedOperationException();
		}

		@Override
		public E previous() throws NullPointerException{
			if (hasPrevious()) {
				p=p.prev;
				return p.object;
			}else 
				throw new NullPointerException("InnerListIterator.prev");
		}

		@Override
		public int previousIndex() {
			throw new UnsupportedOperationException();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
			
		}

		@Override
		public void set(E e) {
			if (p != head && p!= tail) {
				p.object=e;
			}else {
				throw new NoSuchElementException();
			}
		}
	}
	
	public TwoWayLinkedListWithHead() {
		// make a head	
		head.next=tail;
		tail.prev=head;
	}
	
	@Override
	public boolean add(E e) {
		Element currele = new Element(e, tail, tail.prev);
		tail.prev.next=currele;
		tail.prev=currele;
		return true;
	}

	@Override
	public void add(int index, E element) { 
		if (index>size()) throw new NoSuchElementException();
		Element currele=head; 
		for(int x = 0; true; x++ ) {
			if(x==index) {
				Element temp=currele.next;
				currele.next=new Element(element);
				currele.next.next=temp;
				return; 
			}else
				currele=currele.next;
		}
	}

	@Override
	public void clear() { //most be changed 
		head.next = tail;
		tail.prev = head;
	}

	@Override
	public boolean contains(E element) {
		Iterator<E> it= this.iterator();
		while(it.hasNext()) {
			if (it.next()==element) return true; 
		}
		return false;
	}

	@Override
	public E get(int index)throws NoSuchElementException { 
		if (index>=size()) throw new NoSuchElementException(); 
		Iterator<E> it=this.iterator(); 
		for (int x = 0; true; x++) {
			if (x == index) return it.next();
			it.next();
		}
	}

	@Override
	public E set(int index, E element) {
		E temp= remove(index);
		add(index, element);
		return temp;
	}

	@Override
	public int indexOf(E element) {
		Iterator<E> it = this.iterator();
		for (int x = 0; it.hasNext(); x++) {
			if(it.next().equals(element)) return x; 
		}
		return -1;
	}

	@Override
	public boolean isEmpty() { 
		return head.next == null && head.object == null && head.prev == null;
	}

	@Override
	public Iterator<E> iterator() {
		return new InnerIterator();
	}

	@Override
	public ListIterator<E> listIterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public E remove(int index) throws NoSuchElementException { 
		if (index>= size()) throw new NoSuchElementException(); 
		Element currele= head; 
		for ( int x = 0; true; x++) {
			if (x == index ) {
				E temp = currele.next.object;
				currele.next=currele.next.next; 
				return temp;
			}
			currele=currele.next;
		}
	}

	@Override
	public boolean remove(E e) {

		int temp=indexOf(e);
		if(temp == -1) return false;
		remove (temp);
		return true;
	}

	@Override
	public int size() {
		Iterator<E> it = this.iterator(); 
		int x=0; 
		for (; it.hasNext(); x++) it.next();
		return x;
	}
	public String toStringReverse()
	{
		String retStr = "";
		ListIterator<E> iter = new InnerListIterator();
		while (iter.hasNext()) retStr = "\n" + iter.next();
		// TODO use reverse direction of the iterator DONE
		while (iter.hasPrevious()) retStr += "\n" + iter.previous();
		return retStr;
		
	}
	
	
	@Override
	public String toString()
	{
		
		String s = "";
		Iterator<E> it = this.iterator();
		while (it.hasNext()) s += "\n" + it.next();
		return s;
		
	}
	
	
	public void add(TwoWayLinkedListWithHead<E> other)
	{
		
		if (other == null) throw new NullPointerException();
		if (this.equals(other)) return;
		
		tail.prev.next = other.head.next;
		other.head.next.prev = tail.prev;
		tail = other.tail;
		other.clear();
		
	}
}

