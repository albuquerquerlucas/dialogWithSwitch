package com.est.card;

import java.io.Serializable;

/**
 * Created by Mrluke on 25/02/2018.
 */

public class Cidade implements Serializable {

    private String cidade;
    private String status;

    public Cidade(String cidade, String status) {
        this.cidade = cidade;
        this.status = status;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
