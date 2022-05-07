package Domain.Model.Vuelos;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.google.gson.Gson;

import Domain.Event.VueloCreado;
import Domain.Model.Vuelos.ValueObjects.NumeroVuelo;
import SharedKernel.core.AggregateRoot;
import SharedKernel.core.BussinessRuleValidateExeption;

public class Vuelo extends AggregateRoot<UUID> {

    public String nroVuelo;
    public String keyAeronave;
    public String keyAeropuertoOrigen;
    public String keyAeropuertoDestino;
    public Date fecha_salida;
    public Date fecha_arribe;
    public List<Tripulante> listaTripulante;

    public Vuelo(String _nroVuelo, String _keyAeronave, String _keyAeropuertoOrigen, String _keyAeropuertoDestino,
            Date _fecha_salida, Date _fecha_arribe, List<Tripulante> _listaTripulante) {
        key = UUID.randomUUID();

        try {
            this.nroVuelo = new NumeroVuelo(_nroVuelo).toString();
        } catch (BussinessRuleValidateExeption e) {
            e.printStackTrace();
            return;
        }

        nroVuelo = _nroVuelo;
        keyAeronave = _keyAeronave;
        keyAeropuertoOrigen = _keyAeropuertoOrigen;
        keyAeropuertoDestino = _keyAeropuertoDestino;
        fecha_salida = _fecha_salida;
        fecha_arribe = _fecha_arribe;
        listaTripulante = _listaTripulante;
        System.out.println("Se a creado un nuevo vuelo");
    }

    public void eventCreado() {
        addDomainEvent(new VueloCreado(key, nroVuelo, keyAeronave, keyAeropuertoOrigen, keyAeropuertoDestino,
                fecha_salida, fecha_arribe, listaTripulante));
    }

    public void AgregarTripulante(Tripulante tripulante) {
        listaTripulante.parallelStream().filter(p -> p.getKey() == tripulante.getKey()).findFirst().ifPresent(p -> {
            throw new RuntimeException("El tripulante ya existe");
        });
        listaTripulante.add(tripulante);
    }

    @Override
    public String toString() {
        return new Gson().toJson(this, Vuelo.class);
    }
}