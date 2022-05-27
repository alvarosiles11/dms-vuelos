package dmsnur.Application.Dto;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;

public class AeronaveDtoTest {
	@Test
	public void CheckPropertiesValid() {
		UUID keyAeronave = UUID.randomUUID();
		String matricula = "xyz-1990";

		AeronaveDto aeronave = new AeronaveDto();

		Assert.assertEquals(null, aeronave.keyAeronave);
		Assert.assertEquals(null, aeronave.matricula);

		aeronave.keyAeronave = keyAeronave;
		aeronave.matricula = matricula;

		Assert.assertEquals(keyAeronave, aeronave.keyAeronave);
		Assert.assertEquals(matricula, aeronave.matricula);
	}
}