package Emplacement;

public class Store {
	private int id_store;
	private String name_store;
	private String tel_store;
	
	public Store(int id, String name, String tel) {
		this.id_store = id;
		this.name_store = name;
		this.tel_store = tel;
	}
	
	public void set_id_store(int id) {
		this.id_store = id;
	}
	
	public int get_id_store(){
		return this.id_store;
	}
	
	public void set_name_store(String name) {
		this.name_store = name;
	}
	
	public String get_name_store() {
		return this.name_store;
	}
	
	public void set_tel_store(String tel) {
		this.tel_store = tel;
	}
	
	public String get_tel_store() {
		return this.tel_store;
	}
}
