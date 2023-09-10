package it.spicciani.userdemo.user;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import it.spicciani.userdemo.tools.CsvManager;

@Component
public class UserService {
	private final UserRepository userRepo;
	private final CsvManager csvManager;

	@Autowired
	public UserService(UserRepository userRepo, CsvManager csvManager) {
		this.userRepo = userRepo;
		this.csvManager = csvManager;
	}
	
	public void saveUserFromCsvFile(MultipartFile file) {
	    try {
	      List<User> tutorials = csvManager.csvReader(file.getInputStream());
	      userRepo.saveAll(tutorials);
	    } catch (IOException e) {
	      throw new RuntimeException("Error during db operation: " + e.getMessage());
	    }
	  }
}
