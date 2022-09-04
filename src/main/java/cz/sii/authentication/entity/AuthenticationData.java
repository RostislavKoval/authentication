package cz.sii.authentication.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Map;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity(name = "authentication_data")
public class AuthenticationData
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NonNull
	private Long timestamp;
	@NonNull
	private String type;
	@NonNull
	private String actor;
	@NonNull
	@ElementCollection
	private Map<String, String> transactionData;
}
