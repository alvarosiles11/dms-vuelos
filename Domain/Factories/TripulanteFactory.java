package Domain.Factories;

import Domain.Model.Vuelos.Tripulante;

public class TripulanteFactory implements ITripulanteFactory {

    public TripulanteFactory() {
    }

    @Override
    public Tripulante Create(String descripcion) {
        return new Tripulante(descripcion);
    }

}