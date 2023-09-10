package it.spicciani.userdemo.user;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import it.spicciani.userdemo.tools.CsvManager;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api/v01/users")
public class UserController {

	private final UserRepository userRepo;
	private final CsvManager csvManager;

	@Autowired
	public UserController(UserRepository userRepo, CsvManager csvManager) {
		this.userRepo = userRepo;
		this.csvManager = csvManager;
	}

	@GetMapping
	Iterable<User> getUsers(@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "surname", required = false) String surname) {
		if (name != null && surname != null) {
			return userRepo.findByNameContainsAndSurnameContains(name, surname);
		} else if (name != null) {
			return userRepo.findByNameContains(name);
		} else if (surname != null) {
			return userRepo.findBySurname(surname);
		} else {
			return userRepo.findAll();
		}
	}

	@PostMapping
	User postUser(@RequestBody @Valid User user) {
		return userRepo.save(user);
	}

	@GetMapping("/{id}")
	Optional<User> getUserById(@PathVariable long id) {
		return userRepo.findById(id);
	}

	@PutMapping("/{id}")
	ResponseEntity<User> putUser(@PathVariable long id, @RequestBody @Valid User user) {
		return userRepo.existsById(id) ? new ResponseEntity<>(userRepo.save(user), HttpStatus.OK)
				: new ResponseEntity<>(userRepo.save(user), HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	void deleteUser(@PathVariable long id) {
		userRepo.deleteById(id);
	}

	
	@PostMapping(path = "/csv")
    public ResponseEntity<String> postCsv(@RequestParam("file") MultipartFile csvFile) throws Exception {
		if(csvManager.isCSVFile(csvFile)) {
			List<User> csvUsers = csvManager.csvReader(csvFile.getInputStream());
			userRepo.saveAll(csvUsers);
			return ResponseEntity.status(HttpStatus.OK).body("Operation completed");
		}else {
		    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not a csv file");
		}
		
    }

}
