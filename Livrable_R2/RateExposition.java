package Emplacement;

public class RateExposition {
	private int id_exposition_rate;
	private double rate_exposition;
	
	public RateExposition(int id, double rate) {
		this.id_exposition_rate = id;
		this.rate_exposition = rate;
	}
	
	public void set_id_exposition_rate(int id) {
		this.id_exposition_rate = id;
	}
	public int get_id_exposition_rate() {
		return this.id_exposition_rate;
	}
	public void set_rate_exposition(double rate) {
		this.rate_exposition = rate;
	}
	public double get_rate_exposition() {
		return this.rate_exposition;
	}
}
