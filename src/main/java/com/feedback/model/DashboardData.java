package com.feedback.model;

//DashboardData.java
public class DashboardData {
 public long getTotalFaculties() {
		return totalFaculties;
	}

	public void setTotalFaculties(long totalFaculties) {
		this.totalFaculties = totalFaculties;
	}

	public long getTotalStudents() {
		return totalStudents;
	}

	public void setTotalStudents(long totalStudents) {
		this.totalStudents = totalStudents;
	}

	public long getTotalUsers() {
		return totalUsers;
	}

	public void setTotalUsers(long totalUsers) {
		this.totalUsers = totalUsers;
	}

	public long getTotalClasses() {
		return totalClasses;
	}

	public void setTotalClasses(long totalClasses) {
		this.totalClasses = totalClasses;
	}

	public String getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getEvaluationStatus() {
		return evaluationStatus;
	}

	public void setEvaluationStatus(String evaluationStatus) {
		this.evaluationStatus = evaluationStatus;
	}

private long totalFaculties;
 private long totalStudents;
 private long totalUsers;
 private long totalClasses;
 private String academicYear;
 private String semester;
 private String evaluationStatus;

 public DashboardData(long totalFaculties, long totalStudents, long totalUsers, long totalClasses, String academicYear, String semester, String evaluationStatus) {
     this.totalFaculties = totalFaculties;
     this.totalStudents = totalStudents;
     this.totalUsers = totalUsers;
     this.totalClasses = totalClasses;
     this.academicYear = academicYear;
     this.semester = semester;
     this.evaluationStatus = evaluationStatus;
 }

 // Getters and Setters
}
