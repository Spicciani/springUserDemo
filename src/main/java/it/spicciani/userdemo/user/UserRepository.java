package it.spicciani.userdemo.user;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

	Iterable<User> findByNameAndSurname(String name, String surname);
	Iterable<User> findByNameContainsAndSurnameContains(String name, String surname);

	Iterable<User> findByName(String name);
	Iterable<User> findByNameContains(String name);

	Iterable<User> findBySurname(String surname);
	Iterable<User> findBySurnameContains(String surname);

}
