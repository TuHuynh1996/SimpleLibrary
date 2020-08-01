@Entity
class Student {
 
    @Id
    Long id;
 
    @ManyToMany
	@JoinTable(
	name = "course_like", // name of middlemen table
	joinColumns = @JoinColumn(name = "student_id"), // id of Student
	inverseJoinColumns = @JoinColumn(name = "course_id")) //id of Course
	Set<Course> likedCourses;
 
    // additional properties
    // standard constructors, getters, and setters
}
 
@Entity
class Course {
 
    @Id
    Long id;
	
	@ManyToMany(mappedBy = "likedCourses") // variable mapping of class Student
	Set<Student> likes;

 
    // additional properties
    // standard constructors, getters, and setters
}