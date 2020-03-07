package src.main.java;

public class Inmate {

	public int ssn;
	public String firstName;
	public String lastName;
	public String dob;
	public int height;
	public int weight;
	public String eyeColor;
	public String hairColor;
	public int tattoos;
	public String mugshot;
	
	public Inmate() {
		ssn = 0;
		firstName = "";
		lastName = "";
		dob ="";
		height = 0;
		weight = 0;
		eyeColor = "";
		hairColor = "";
		tattoos = 0;
		mugshot = "";
	}
	
	public Inmate(int ssn, String fn, String ln, String dob, int h, int w, String ec, String hc, int t, String ms) {
		this.ssn = ssn;
		firstName = fn;
		lastName = ln;
		this.dob = dob;
		height = h;
		weight = w;
		eyeColor = ec;
		hairColor = hc;
		tattoos = t;
		mugshot = ms;
	}
	
	public String toString() {
		return "" + ssn + ", '" + firstName + "', '" + lastName + "', '" + this.dob + "', " + height + ", " + weight 
				+ ", '" + eyeColor + "', '" + hairColor + "', " + tattoos + ", '" + mugshot + "'";
	}
}
