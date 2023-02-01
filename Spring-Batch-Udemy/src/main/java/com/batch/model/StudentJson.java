package com.batch.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StudentJson {
	
		private Long studentId;
		
		//@JsonProperty("first_name") // We can use this property in case of name is different in file
		private String firstName;
		
		private String lastName;
		
		private String email;

		public Long getStudentId() {
			return studentId;
		}

		public void setStudentId(Long studentId) {
			this.studentId = studentId;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		@Override
		public String toString() {
			return "StudentJson [studentId=" + studentId + ", firstName=" + firstName + ", lastName=" + lastName
					+ ", email=" + email + "]";
		}
		
		

}
