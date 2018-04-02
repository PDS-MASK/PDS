package Emplacement;

import java.util.Date;

public class PriceM {
	private int id_price;
	private Date start_date_price;
	private double price_m;
	
	public PriceM(int id, Date start,double price) {
		this.id_price = id;
		this.start_date_price = start;
		this.price_m = price;
	}
	
	public void set_id_price(int id) {
		this.id_price = id;
	}
	public int get_id_price() {
		return this.id_price;
	}
	public void set_start_date_price(Date start) {
		this.start_date_price = start;
	}
	public Date get_start_date_price() {
		return this.start_date_price;
	}
	public void set_price_m(double price) {
		this.price_m = price;
	}
	public double get_price_m() {
		return this.price_m;
	}
}
