package mn.erdenet.springpetshop.database;

import mn.erdenet.springpetshop.pojos.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Integer> {

    @Query("SELECT p FROM Pet p")
    public List<Pet> getAllPets();

    @Query("SELECT DISTINCT p.type FROM Pet p ORDER BY p.type")
    public List<String> getAllPetTypes();

}
