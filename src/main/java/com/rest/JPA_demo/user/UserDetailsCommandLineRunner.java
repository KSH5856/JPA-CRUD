package com.rest.JPA_demo.user;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsCommandLineRunner implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserDetailsRepository repository;

//	public UserDetailsCommandLineRunner(UserDetailsRepository repository) {
//		super();
//		this.repository = repository;
//	}

	@Override
	public void run(String... args) throws Exception {
		repository.save(new UserDetails("Kashish", "Developer"));
		repository.save(new UserDetails("KSH", "SRE"));
		repository.save(new UserDetails("Jay", "Data Analyst"));

		List<UserDetails> user = repository.findAll();
		List<UserDetails> s = repository.findByRole("SRE");
		List<UserDetails> n = repository.findByName("Jay");
		logger.info(n.toString());
	}

}
