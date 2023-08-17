package com.distribuida.app.singer.repo;

import com.distribuida.app.singer.db.Singer;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SingerRepository implements PanacheRepositoryBase<Singer, Integer> {
}
