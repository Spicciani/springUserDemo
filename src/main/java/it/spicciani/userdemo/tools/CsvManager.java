package it.spicciani.userdemo.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import it.spicciani.userdemo.user.User;

@Component
public class CsvManager {
	public static String TYPE = "text/csv";

	public boolean isCSVFile(MultipartFile file) {

		if (!TYPE.equals(file.getContentType())) {
			return false;
		}

		return true;
	}

	public List<User> csvReader(InputStream is) {
		try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				CSVParser csvParser = new CSVParser(fileReader,
						CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
			
			 List<User> csvUserList = csvParser.getRecords().stream().map(csvRow ->{
				return new User( 
						csvRow.get("name"), csvRow.get("surname"), 
						LocalDate.parse(csvRow.get("birthDate")), 
						csvRow.get("email"), csvRow.get("address"), csvRow.get("email"));
			}).collect(Collectors.toList());
			
			return csvUserList;
		} catch (IOException e) {
			throw new RuntimeException("Parsing file error: " + e.getMessage());
		}
	}

	public void csvWriter(Writer writer, Iterable<User> users) throws IOException {

        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            csvPrinter.printRecord("id", "name", "surname","birthDate","email", "address", "note");
            for (User user : users) {
                csvPrinter.printRecord(
                		user.getId(), 
                		user.getName(), 
                		user.getSurname(),
                		user.getBirthDate(),
                		user.getEmail(), 
                		user.getAddress(),
                		user.getNote());
            }
        } catch (IOException e) {
           throw e;
        }
    }
}
