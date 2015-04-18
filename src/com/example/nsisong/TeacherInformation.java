package com.example.nsisong;
public class TeacherInformation {
	public String FirstName;
	public String LastName;
	public String RoomNumber;
	public int XCoordinate;
	public int YCoordinate; 
	
	public TeacherInformation( String FN, String LN, String RN, String X, String Y)
	{
		FirstName = FN;
		LastName = LN;
		RoomNumber = RN;
		XCoordinate = Integer.parseInt(X);
		YCoordinate = Integer.parseInt(Y);
	}}
	
	