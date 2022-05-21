package Application.UseCases.Command.Aeronaves.Eliminar;

import java.util.UUID;

import Application.Dto.AeronaveDto;
import SharedKernel.mediator.Request;

public class EliminarAeronaveCommand implements Request<AeronaveDto> {

    public AeronaveDto _AeronaveDto;

    public EliminarAeronaveCommand(UUID key) {
        this._AeronaveDto = new AeronaveDto();
        this._AeronaveDto.keyAeronave = key;
    }
}