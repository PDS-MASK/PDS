package Emplacement;

import java.util.Date;

public class StoreHistory {
	private int id_history;
	private Date start_date;
	private Date end_date;
	private double tax_history;
	
	public StoreHistory(int id,Date start, Date end, double tax) {
		this.id_history = id;
		this.start_date = start;
		this.end_date = end;
		this.tax_history = tax;
	}
	public void set_id_history(int id) {
		this.id_history = id;
	}
	public int get_id_history() {
		return this.id_history;
	}
	public void set_start_date(Date start) {
		this.start_date = start;
	}
	public Date get_start_date() {
		return this.start_date;
	}
	public void set_end_date(Date end) {
		this.end_date = end;
	}
	public Date get_end_date() {
		return this.end_date;
	}
	public void set_tax_history(double tax) {
		this.tax_history = tax;
	}
	public double get_tax_history() {
		return this.tax_history;
	}
}
