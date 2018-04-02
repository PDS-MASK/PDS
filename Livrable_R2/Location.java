package Emplacement;

public class Location {
	private int id_location;
	private int num_location;
	private boolean availability; 
	private double surface;
	
	public Location(int id, int num, boolean avai, double surface1) {
		this.id_location = id;
		this.num_location = num;
		this.availability = avai;
		this.surface = surface1;
	}
	
	public void set_id_location(int id) {
		this.id_location = id;
	}
	
	public int get_id_location() {
		return this.id_location;
	}
	
	public void set_num_location(int num) {
		this.num_location = num;
	}
	public int get_num_location() {
		return this.num_location;
	}
	public void set_availability(boolean a) {
		this.availability = a;
	}
	public boolean get_availability() {
		return this.availability;
	}
	public void set_surface(double surface1) {
		this.surface = surface1;
	}
	public double get_surface() {
		return this.surface;
	}
}
