package test.persistence.model;

public enum EGender {
	
	MALE("Male"),
	FEMALE("Female"),
	WTF("The author doesn't know either");
	
	public String string;
	
	private EGender(String genderString) {
		this.string = genderString;
	}
	
}
