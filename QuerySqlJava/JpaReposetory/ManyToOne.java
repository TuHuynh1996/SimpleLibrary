@Entity // mask as a table in db
@Data // lombok help generate constructor, get, set v.v.
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id //mask as a primary key
    @GeneratedValue // auto increament
    private Long id;

    private String city;
    private String province;

    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // 1-n to class (Person)
    // MapopedBy to variable "address" in class Person.
	//cascade = CascadeType.ALL: when Address delete, all persons mapping to that Address will be delete too
	// fetch = FetchType.LAZY: when get Address from db, persons will be empty till call menthod getPersons(),fetch = FetchType.LAZY <> fetch = FetchType.EAGER
    @EqualsAndHashCode.Exclude // cant use in equals and hashcode
    @ToString.Exclude // cant use in menthod toString()
    private Collection<Person> persons;
}

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @ManyToOne 
    @JoinColumn(name = "address_id") // mappedBy id, class Address
    @ToString.Exclude
    private Address address;
}