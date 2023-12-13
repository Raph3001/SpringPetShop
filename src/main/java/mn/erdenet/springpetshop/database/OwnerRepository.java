package mn.erdenet.springpetshop.database;

import mn.erdenet.springpetshop.pojos.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Integer> {
}
