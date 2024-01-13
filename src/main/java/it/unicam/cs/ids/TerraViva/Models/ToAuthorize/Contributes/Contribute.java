package it.unicam.cs.ids.TerraViva.Models.ToAuthorize.Contributes;

import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.AuthorizationEntity;
import it.unicam.cs.ids.TerraViva.Models.User;

public interface Contribute {
    public String getData();
    public void setData(String data);
    AuthorizationEntity getRefersTo();
    void setRefersTo(AuthorizationEntity refersTo);
}
