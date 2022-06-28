package studentProgram;

import java.util.Objects;


public class StudentModel implements Comparable<Object> {
	public static final int SU;
	
	public static int totalCount;
	public static int totalSum;
	public static double totalAvr;
	
	private String id;
	private String name;
	private String gender;
	private int kor;
	private int eng;
	private int math;
	private int total;
	private double avr;
	private char grade;
	
	static {
		totalCount = 0;
		totalSum = 0;
		totalAvr = 0;
		SU = 3;
	}

	public StudentModel() {
		this(null, null, null, 0, 0, 0);
	}

	
	
	public StudentModel(String id, String name, String gender, int kor, int eng, int math) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
		this.total = 0;
		this.avr = 0.0;
		this.grade = '\u0000';
	}


	public StudentModel(String id, String name, String gender, int kor, int eng, int math, int total, double avr,
			char grade) {
		this(id, name, gender, kor, eng, math);
		this.total = total;
		this.avr = avr;
		this.grade = grade;
	}


	public int calTotal() {
		
		return this.total = this.kor + this.eng + this.math;
		
	}
	
	public double calAvr() {
		return this.avr = this.total / (double)SU;
	}
	
	public char calGrade(double avr) {
		if(this.avr>= 90.0) {
			return this.grade = 'A';
		}
		else if(this.avr>= 80.0) {
			return this.grade = 'B';
		}
		else if(this.avr>= 70.0) {
			return this.grade = 'C';
		}
		else if(this.avr>= 60.0) {
			return this.grade = 'D';
		}
		else {
			return this.grade = 'F';
		}
	}

	
	
	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof StudentModel))
	         throw new IllegalArgumentException("No Model in this class");
		StudentModel sm = (StudentModel)obj;
	      return this.id.equals(sm.id);
	}

	@Override
	public String toString() {
		return "[" + name + " \t " + gender + " \t " + id + " \t " + kor + " \t " + math
				+ " \t " + eng + " \t " + total + " \t " + String.format("%.2f", avr) + " \t " + grade + "]";
	}
	

	@Override
	public int compareTo(Object obj) {
		if(!(obj instanceof StudentModel))
	         throw new IllegalArgumentException("No Model in this class");
		StudentModel sm = (StudentModel)obj;
		if(this.total > sm.total) {
			return 1;
		}
		if(this.total < sm.total) {
			return -1;
		}
		else return 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getKor() {
		return kor;
	}

	public void setKor(int kor) {
		this.kor = kor;
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}

	public int getEng() {
		return eng;
	}

	public void setEng(int eng) {
		this.eng = eng;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public double getAvr() {
		return avr;
	}

	public void setAvr(double avr) {
		this.avr = avr;
	}

	public char getGrade() {
		return grade;
	}

	public void setGrade(char grade) {
		this.grade = grade;
	}
	
	
	
}
