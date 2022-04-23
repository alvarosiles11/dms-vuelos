
package Domain.ValueObjects;

import kernel.core.BussinessRuleValidateExeption;
import kernel.core.ValueObject;
import kernel.rule.StringNotNullOrEmptyRule;

 
public class PersonNameValue extends ValueObject {

    public String Value;

    public PersonNameValue(String nombre) {
        try {
            CheckRule(new StringNotNullOrEmptyRule(nombre));
        } catch (BussinessRuleValidateExeption e1) {
            e1.printStackTrace();
        }
        if (nombre.length() > 500) {
            try {
                throw new BussinessRuleValidateExeption("PersonName can't be more than 500 characters");
            } catch (BussinessRuleValidateExeption e) {
                e.printStackTrace();
            }
        }
        Value = nombre;
    }
}