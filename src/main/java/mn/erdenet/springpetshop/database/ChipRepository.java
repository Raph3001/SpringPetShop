package mn.erdenet.springpetshop.database;

import mn.erdenet.springpetshop.pojos.Chip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChipRepository extends JpaRepository<Chip, Integer> {

    @Query("SELECT DISTINCT c.type FROM Chip c ORDER BY c.type")
    public List<String> getAllChipTypes();

}
