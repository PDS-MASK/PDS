package Emplacement;

public class Administrator {
	private String status;
	private String right;
	
	public Administrator(String status1,String right1) {
		this.status = status1;
		this.right = right1;
	}
	
	public void set_status(String status1) {
		this.status = status1;
	}
	
	public String get_status() {
		return this.status;
	}
	
	public void set_right(String right1) {
		this.right = right1;
	}
	
	public String get_right() {
		return this.right;
	}
}
