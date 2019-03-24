package br.com.randomthings.dto;

import java.time.LocalDate;

import br.com.randomthings.domain.Client;
import br.com.randomthings.domain.Gender;
import br.com.randomthings.domain.TelephoneType;
import lombok.Data;

@Data
public class ClientDTO {
	private String firstName;
	private String lastName;
	private String email;
	private Gender gender;
	private String cpf;
	private LocalDate birthDate;
	private String phone;
	private TelephoneType telephoneType;
	private String password;
	
	public static ClientDTO from(Client client) {
		ClientDTO clientDTO = new ClientDTO();
		
		clientDTO.setFirstName(client.getName());
		clientDTO.setLastName(client.getLastName());
		clientDTO.setEmail(client.getUser().getEmail());
		clientDTO.setGender(client.getGender());
		clientDTO.setCpf(client.getCpf());
		clientDTO.setBirthDate(client.getBirthDate());
		clientDTO.setPhone(client.getContact().getNumber());
		clientDTO.setTelephoneType(client.getContact().getTelephoneType());
		clientDTO.setPassword(client.getUser().getPassword());
		
		return clientDTO;
	}
}
