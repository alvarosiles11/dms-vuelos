package Model.Vuelos;

import core.Entity;
import java.util.UUID;

public class Tripulante extends Entity<UUID> {

  public UUID keyVuelo;
  public String keyTripulante;
  public String cargo;

  public Tripulante(UUID _keyVuelo, String _keyTripulante, String _cargo) {
    this.key = UUID.randomUUID();
    this.keyVuelo = _keyVuelo;
    this.keyTripulante = _keyTripulante;
    this.cargo = _cargo;
  }
}
