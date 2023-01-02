package com.techinc.coffeehouse.repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import org.hibernate.SessionFactory;

public class AbstractDao<E, T> {
    private final SessionFactory sessionFactory;
    private final Class<E> domainClass;

    public AbstractDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;

        Type type = getClass().getGenericSuperclass();
        this.domainClass = (Class<E>) ((ParameterizedType) type).getActualTypeArguments()[0];
    }

    public void addEntity(E entity) {
        sessionFactory.getCurrentSession().persist(entity);
    }

    public void removeEntity(E entity) {
        sessionFactory.getCurrentSession().remove(entity);
    }

    public Optional<E> getEntityById(T id) {
        E entity = sessionFactory.getCurrentSession().get(domainClass, (Serializable) id);
        return Optional.ofNullable(entity);
    }

    public List<E> getAllEntities() {
        String query = "from " + domainClass.getSimpleName();
        return sessionFactory.getCurrentSession()
                             .createQuery(query)
                             .list();
    }

    protected SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
