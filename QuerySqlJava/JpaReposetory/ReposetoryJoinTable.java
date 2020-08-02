// check file Reposetory in same folder for more info
public interface EntityRepositoryLeftJoinTable extends Repository<Entity1, Long> {

// Select Entity1 by Entity2.option
@Query("SELECT c FROM Entity1 c LEFT JOIN c.Entity2 d WHERE d.option = :option")    //This is using a named query method
public List<ReleaseDateType> FindAllWithDescriptionQuery(@Param("option") String option);
//In this case a query annotation is not need since spring constructs the query from the method name
//Entity2_Option is a variable of table join
public List<ReleaseDateType> findByEntity2_Option(String option); 

// add pagination
// check file Reposetory in same folder for more info
public List<ReleaseDateType> findByEntity2_Option(String option, Pageable of); 
}

