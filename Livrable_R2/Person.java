package Emplacement;

import java.util.Date;

public class Person {
	private int id_person;
	private String name_person;
	private String sex_person;
	private Date birthday_person;
	private String tel_person;
	private String adress_person;
	
		public Person(int id_person1,String name_person1, String sex_person1, Date birthday_person1,String tel_person1, String adress_person1) {
			this.id_person = id_person1;
			this.name_person = name_person1;
			this.sex_person = sex_person1;
			this.birthday_person = birthday_person1;
			this.tel_person = tel_person1;
			this.adress_person = adress_person1;
		}
		
		public void set_id_person(int id) {
			this.id_person = id;
		}
		
		public int get_id_person() {
			return this.id_person;
		}
		
		public void set_name_person(String name) {
			this.name_person = name;
		}
		
		public String get_name_person() {
			return this.name_person;
		}
		
		public void set_sex_person(String sex) {
			this.sex_person = sex;
		}
		
		public String get_sex_person() {
			return this.sex_person;
		}
		
		public void set_birthday_person(Date birthday) {
			this.birthday_person = birthday;
		}
		
		public Date get_birthday() {
			return this.birthday_person;
		}
		
		public void set_tel_person(String tel) {
			this.tel_person = tel;
		}
		
		public String get_tel_person() {
			return this.tel_person;
		}
		
		public void set_adress_person(String adress) {
			this.adress_person = adress;
		}

		public String get_adress_person() {
			return this.adress_person;
		}
}
