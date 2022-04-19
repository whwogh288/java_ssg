package exam1.dao;

abstract public class Dao {
	protected int lastId;
	
	Dao() {
		lastId = 0;
	}
	
	public int gerLastId() {
		return lastId;
	}
	public int getNewId() {
		return lastId + 1;
	}
}
