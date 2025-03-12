package model.course;

public class Course implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String course_id;
	private String course_name;
	private String course_description;
	private String course_date;
	private String coach_id;
	private String coach_name;
	private String course_time;
	private String max_capacity;
	
	public String getcourse_id() {  return course_id;  }
	public String getcourse_name() {  return course_name;  }
	public String getcourse_description() {  return course_description;  }
	public String getcourse_date() {  return course_date;  }
	public String getcoach_id() {  return coach_id;  }
	public String getcoach_name() {  return coach_name;  }
	public String getcourse_time() {  return course_time;  }
	public String getmax_capacity() {  return max_capacity;  }

	public void setcourse_id(String course_id) {  this.course_id = course_id;  }
	public void setcourse_name(String course_name) {  this.course_name = course_name;  }
	public void setcourse_description(String course_description) {  this.course_description= course_description;  }
	public void setcourse_date(String course_date) {  this.course_date = course_date;  }
	public void setcoach_id(String coach_id) {  this.coach_id = coach_id;	}
	public void setcoach_name(String coach_name) {  this.coach_name = coach_name;	}
	public void setcourse_time(String course_time) {  this.course_time = course_time;	}
	public void setmax_capacity(String max_capacity) {  this.max_capacity = max_capacity;	}
	
}
