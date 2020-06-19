package db;

public class dbIntegrityExeption extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public dbIntegrityExeption(String _msg) {
		super(_msg);
	}

}
