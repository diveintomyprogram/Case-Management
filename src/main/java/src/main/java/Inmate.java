package src.main.java;

public class Inmate {

	public int ssn;
	public String firstName;
	public String lastName;
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
		height = 0;
		weight = 0;
		eyeColor = "";
		hairColor = "";
		tattoos = 0;
		mugshot = "";
	}
	
	public Inmate(int ssn, String fn, String ln, int h, int w, String ec, String hc, int t, String ms) {
		this.ssn = ssn;
		firstName = fn;
		lastName = ln;
		height = h;
		weight = w;
		eyeColor = ec;
		hairColor = hc;
		tattoos = t;
		mugshot = ms;
	}
	
	public String toString() {
		return "" + ssn + ", '" + firstName + "', '" + lastName + "', " + height + ", " + weight 
				+ ", '" + eyeColor + "', '" + hairColor + "', " + tattoos + ", '" + mugshot + "'";
	}
}
