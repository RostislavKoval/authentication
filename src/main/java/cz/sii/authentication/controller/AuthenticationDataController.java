package cz.sii.authentication.controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RestController;

import cz.sii.authentication.entity.AuthenticationData;
import cz.sii.authentication.repository.AuthenticationDataRepository;

@RestController
@RequestMapping("/api")
public class AuthenticationDataController
{
	private final AuthenticationDataRepository authenticationDataRepository;

	@Autowired
	public AuthenticationDataController(AuthenticationDataRepository authenticationDataRepository)
	{
		this.authenticationDataRepository = authenticationDataRepository;
	}

	@GetMapping("/authentication_data/{id}")
	public ResponseEntity<AuthenticationData> getById(@PathVariable("id") long id)
	{
		Optional<AuthenticationData> authenticationData = authenticationDataRepository.findById(id);

		if (authenticationData.isPresent())
		{
			return new ResponseEntity<>(authenticationData.get(), HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/authentication_data/actor/{actor}")
	public ResponseEntity<List<AuthenticationData>> getByActor(@PathVariable("actor") String actor)
	{
		try
		{
			List<AuthenticationData> authenticationData = authenticationDataRepository.findByActor(actor);

			if (authenticationData.isEmpty())
			{
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			else
			{
				return new ResponseEntity<>(authenticationData, HttpStatus.OK);
			}
		}
		catch (Exception e)
		{
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/authentication_data/type/{type}")
	public ResponseEntity<List<AuthenticationData>> getByType(@PathVariable("type") String type)
	{
		try
		{
			List<AuthenticationData> authenticationData = authenticationDataRepository.findByType(type);

			if (authenticationData.isEmpty())
			{
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			else
			{
				return new ResponseEntity<>(authenticationData, HttpStatus.OK);
			}
		}
		catch (Exception e)
		{
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/authentication_data")
	public ResponseEntity<AuthenticationData> create(@RequestBody AuthenticationData authenticationData)
	{
		try
		{
			AuthenticationData _authenticationData = authenticationDataRepository.save(
				new AuthenticationData(
					authenticationData.getTimestamp(),
					authenticationData.getType(),
					authenticationData.getActor(),
					authenticationData.getTransactionData()
				)
			);

			return new ResponseEntity<>(_authenticationData, HttpStatus.CREATED);
		}
		catch (Exception e)
		{
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/authentication_data/{id}")
	public ResponseEntity<AuthenticationData> update(@PathVariable("id") long id, @RequestBody AuthenticationData authenticationData)
	{
		Optional<AuthenticationData> authenticationDataResponse = authenticationDataRepository.findById(id);

		if (authenticationDataResponse.isPresent())
		{
			AuthenticationData _authenticationData = authenticationDataResponse.get();
			_authenticationData.setTimestamp(authenticationData.getTimestamp());
			_authenticationData.setType(authenticationData.getType());
			_authenticationData.setActor(authenticationData.getActor());
			_authenticationData.setTransactionData(authenticationData.getTransactionData());
			return new ResponseEntity<>(authenticationDataRepository.save(_authenticationData), HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/authentication_data/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id)
	{
		try
		{
			authenticationDataRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		catch (Exception e)
		{
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
