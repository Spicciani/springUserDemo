package it.spicciani.userdemo.tools;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import it.spicciani.userdemo.user.User;
import it.spicciani.userdemo.user.UserRepository;

@Component
public class AutoRun implements CommandLineRunner {

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public void run(String... args) throws Exception {
		
		User dummy = new User();
		dummy.setName("Marco");
		dummy.setSurname("Spicciani");
		dummy.setAddress("Via Simonetti n 5");
		dummy.setBirthDate(LocalDate.of(1980, 9, 23));
		dummy.setEmail("marco.spicciani@gmail.com");
		userRepo.save(dummy);
		
		dummy = new User();
		dummy.setName("Mario");
		dummy.setSurname("Rossi");
		dummy.setAddress("Via Simonetti n 5");
		dummy.setBirthDate(LocalDate.of(1986, 9, 23));
		dummy.setEmail("mario.rossi@gmail.com");
		userRepo.save(dummy);
		
		dummy = new User();
		dummy.setName("Luigi");
		dummy.setSurname("Rossi");
		dummy.setAddress("Via Simonetti n 5");
		dummy.setBirthDate(LocalDate.of(1987, 9, 23));
		dummy.setEmail("luigi.rossi@gmail.com");
		userRepo.save(dummy);
	}

}
