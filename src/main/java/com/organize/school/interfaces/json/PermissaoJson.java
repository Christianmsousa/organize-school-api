package com.organize.school.interfaces.json;

import lombok.Builder;
import javax.validation.constraints.NotNull;

@Builder
public class PermissaoJson {

    @NotNull
    private Long id;

    public PermissaoJson(){
    }

    public PermissaoJson(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "PermissaoJson{" +
                "id=" + id +
                '}';
    }
}
