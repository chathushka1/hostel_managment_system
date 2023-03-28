package lk.ijse.gdse.hostel.entity;

public class Student {
     private String student_id;
     private String name;
     private String address;
     private String contact_no;
     private String dob;
     private String gender;

     public Student() {
     }

     public Student(String student_id, String name, String address, String contact_no, String dob, String gender) {
          this.setStudent_id(student_id);
          this.setName(name);
          this.setAddress(address);
          this.setContact_no(contact_no);
          this.setDob(dob);
          this.setGender(gender);
     }



     @Override
     public String toString() {
          return "Student{" +
                  "student_id='" + getStudent_id() + '\'' +
                  ", name='" + getName() + '\'' +
                  ", address='" + getAddress() + '\'' +
                  ", contact_no='" + getContact_no() + '\'' +
                  ", dob='" + getDob() + '\'' +
                  ", gender='" + getGender() + '\'' +
                  '}';
     }

     public String getStudent_id() {
          return student_id;
     }

     public void setStudent_id(String student_id) {
          this.student_id = student_id;
     }

     public String getName() {
          return name;
     }

     public void setName(String name) {
          this.name = name;
     }

     public String getAddress() {
          return address;
     }

     public void setAddress(String address) {
          this.address = address;
     }

     public String getContact_no() {
          return contact_no;
     }

     public void setContact_no(String contact_no) {
          this.contact_no = contact_no;
     }

     public String getDob() {
          return dob;
     }

     public void setDob(String dob) {
          this.dob = dob;
     }

     public String getGender() {
          return gender;
     }

     public void setGender(String gender) {
          this.gender = gender;
     }
}
