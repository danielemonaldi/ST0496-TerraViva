package it.unicam.cs.ids.TerraViva.Models.ToAuthorize.Contributes;

import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.AuthorizationEntity;
import it.unicam.cs.ids.TerraViva.Models.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

public abstract class Content<T> extends AuthorizationEntity implements Contribute<T> {
    private T data;

    public Content(User author, T data) {
        super(author);
        this.data = data;
    }

    public Content() {}

    @Override
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
