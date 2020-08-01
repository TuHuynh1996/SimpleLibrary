// This is just some ex for demo
// check all key word below
public interface EntityRepository extends Repository<Entity, Long> {

	// multi find
	List<Entity> findByEmailAddressAndLastname(String option1, String option2);
	
	// Multi sort
    List<MstMainCity> findByOrderByOption1AscOption2Des();
	
	// search by option1 and option2
	@Query(value = "SELECT c FROM Entity c WHERE c.option1 LIKE %:option1% AND c.option2 LIKE %:option2%")
    Page<MstMainCity> searchByCountryCodeAndCityCode(@Param("option1") String countryCode, @Param("option2") String cityCode);
	
	// find all and pagination
	// in service: Page<Entity> entityList = EntityRepository.findAll(PageRequest.of(page, maxItem, Sort.by("option1").and(Sort.by("option2").descending())));
	// page: start form 0
	// maxItem: maxItem you want
	// add Sort
    Page<MstMainCity> findAll(Pageable of);
	
	// search by option1 and option2 and pagination
	@Query(value = "SELECT c FROM Entity c WHERE c.option1 LIKE %:option1% AND c.option2 LIKE %:option2%")
    Page<MstMainCity> findAllByCountryCodeAndCityCode(@Param("option1") String countryCode, @Param("option2") String cityCode, Pageable of);

	// count by option1 but id
    @Query(value = "SELECT COUNT(c) FROM Entity c WHERE c.option1 = :cityCode AND c.id <> :id")
    int countByOption1ExcepttId(@Param("cityCode") String cityCode, @Param("id") Integer integer);

	//check exists by option1
    boolean existsByCityCode(String option1);
	
	// get get Max Id
    @Query("SELECT COALESCE(MAX(c.id), 0) FROM MstMainCity c")
    Integer getMaxId();
}

And							findByLastnameAndFirstname									… where x.lastname = ?1 and x.firstname = ?2
Or							findByLastnameOrFirstname									… where x.lastname = ?1 or x.firstname = ?2
Is,Equals					findByFirstname,findByFirstnameIs,findByFirstnameEquals		… where x.firstname = 1?
Between						findByStartDateBetween										… where x.startDate between 1? and ?2
LessThan					findByAgeLessThan											… where x.age < ?1
LessThanEqual				findByAgeLessThanEqual										… where x.age <= ?1
GreaterThan					findByAgeGreaterThan										… where x.age > ?1
GreaterThanEqual			findByAgeGreaterThanEqual									… where x.age >= ?1
After						findByStartDateAfter										… where x.startDate > ?1
Before						findByStartDateBefore										… where x.startDate < ?1
IsNull						findByAgeIsNull												… where x.age is null
IsNotNull,NotNull			findByAge(Is)NotNull										… where x.age not null
Like						findByFirstnameLike											… where x.firstname like ?1
NotLike						findByFirstnameNotLike										… where x.firstname not like ?1
StartingWith				findByFirstnameStartingWith									… where x.firstname like ?1 (parameter bound with appended %)
EndingWith					findByFirstnameEndingWith									… where x.firstname like ?1 (parameter bound with prepended %)
Containing					findByFirstnameContaining									… where x.firstname like ?1 (parameter bound wrapped in %)
OrderBy						findByAgeOrderByLastnameDesc								… where x.age = ?1 order by x.lastname desc
Not							findByLastnameNot											… where x.lastname <> ?1
In							findByAgeIn(Collection<Age> ages)							… where x.age in ?1
NotIn						findByAgeNotIn(Collection<Age> age)							… where x.age not in ?1
True						findByActiveTrue()											… where x.active = true
False						findByActiveFalse()											… where x.active = false
IgnoreCase					findByFirstnameIgnoreCase									… where UPPER(x.firstame) = UPPER(?1)

