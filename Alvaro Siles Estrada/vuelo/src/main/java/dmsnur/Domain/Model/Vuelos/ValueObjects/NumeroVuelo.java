package dmsnur.Domain.Model.Vuelos.ValueObjects;

import dmsnur.SharedKernel.core.BussinessRuleValidateExeption;
import dmsnur.SharedKernel.core.ValueObject;
import dmsnur.SharedKernel.rule.StringNotNullOrEmptyRule;

public class NumeroVuelo extends ValueObject {
    public String value;

    public NumeroVuelo(String _value) throws BussinessRuleValidateExeption {
        CheckRule(new StringNotNullOrEmptyRule(_value));
        this.value = _value;
    }

    @Override
    public String toString() {
        return value;
    }
}