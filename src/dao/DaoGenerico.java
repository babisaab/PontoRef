package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import model.Departamento;
import model.EntidadeBase;

public class DaoGenerico<T extends EntidadeBase> {

    private static EntityManager manager = PersistenceUtil.getEntityManager();

    public T findById(Class<T> clazz, Long id) {
        return manager.find(clazz, id);
    }

    public List<T> findAll(Class<T> clazz) {
        List<T> objects = null;
        manager.getTransaction().begin();
        TypedQuery<T> query
                = manager.createQuery("SELECT e FROM  " + clazz.getSimpleName() + " e ", clazz);
        objects = query.getResultList();
        manager.getTransaction().commit();

        return objects;
    }

    public void saveOrUpdate(T obj) {
        try {
            manager.getTransaction().begin();
            if (obj.getId() == null) {
                manager.persist(obj);
            } else {
                manager.merge(obj);
            }
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
        }
    }

    public void remove(Class<T> clazz, Long id) {
        T t = findById(clazz, id);
        try {
            manager.getTransaction().begin();
            manager.remove(t);
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
        }
    }

}
