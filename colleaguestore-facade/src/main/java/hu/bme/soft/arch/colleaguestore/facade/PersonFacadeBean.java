package hu.bme.soft.arch.colleaguestore.facade;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;

import hu.bme.soft.arch.colleaguestore.domain.dto.PagingPersonDTO;
import hu.bme.soft.arch.colleaguestore.domain.dto.PersonDTO;
import hu.bme.soft.arch.colleaguestore.domain.dto.PersonFilterDTO;
import hu.bme.soft.arch.colleaguestore.persistence.PersonPersistenceManager;
import hu.bme.soft.arch.colleaguestore.persistence.entity.Person;

@Stateless
public class PersonFacadeBean implements PersonFacade {

	@Inject
	PersonPersistenceManager personPM;

	@Inject
	Logger logger;

	@Override
	public PagingPersonDTO getPersons(PersonFilterDTO personFilterDTO) {
		logger.info("Received list person request");
		List<PersonDTO> personDto = new ArrayList<>();
		List<Person> personEntities = personPM.getPersons(personFilterDTO);

		for (Person person : personEntities) {
			personDto.add(new PersonDTO(person.getId(), person.getFirstName(), person.getLastName(),
					person.getNationality(), person.getDateOfBirth(), person.getPosition()));
		}
		int totalLength = personPM.countPersons(personFilterDTO);
		return new PagingPersonDTO(personDto, totalLength, personFilterDTO.getOffset());
	}

	@Override
	public void createPerson(PersonDTO personDto) {
		personPM.persist(new Person(personDto.getFirstName(), personDto.getLastName(), personDto.getNationality(),
				personDto.getDateOfBirth(), personDto.getPosition()));
	}

	@Override
	public void updatePerson(PersonDTO person) {
		Person a = personPM.find(person.getId());
		a.setFirstName(person.getFirstName());
		a.setLastName(person.getLastName());
		a.setNationality(person.getNationality());
		a.setDateOfBirth(person.getDateOfBirth());
		a.setPosition(person.getPosition());
	}

	@Override
	public void deletePerson(Long id) {
		logger.info("Delete id: " + id);
		personPM.remove(id);
	}

	// curl -u admin:admin
	// http://152.66.245.130:84/restconf/operational/opendaylight-inventory:nodes/node/openflow:84590320485820/group/150010/group-statistics/

	@Override
	public void print() {
		try {
			String url = "http://152.66.245.130:84/restconf/operational/opendaylight-inventory:nodes/node/openflow:84590320485820/group/150010/group-statistics/";

			CredentialsProvider credsProvider = new BasicCredentialsProvider();
			// Credentials defaultcreds = new UsernamePasswordCredentials("admin", "admin");
			credsProvider.setCredentials(new AuthScope("152.66.245.130", AuthScope.ANY_PORT),
					new UsernamePasswordCredentials("admin", "admin"));

			CloseableHttpClient client = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();
			HttpGet request = new HttpGet(url);

			// add request header
			request.addHeader("User-Agent", "curl/7.35.0");
			CloseableHttpResponse response = client.execute(request);
			// HttpResponse response;
			// response = client.execute(request);

			System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
