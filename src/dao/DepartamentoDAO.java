package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import model.Departamento;

public class DepartamentoDAO {
    
    private static DepartamentoDAO instance = new DepartamentoDAO();
    
    public static DepartamentoDAO getInstance() {
        return instance;
    }
    
    public void salvar(Departamento departamento) throws RuntimeException {
        EntityManager em = PersistenceUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
          if (departamento.getId() != null) {
                em.merge(departamento);
            } else {
                em.persist(departamento);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException(e);
        } finally {
            PersistenceUtil.close(em);
        }
    }
    
    public void excluir(Departamento departamento) throws RuntimeException {
        EntityManager em = PersistenceUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.remove(em.getReference(Departamento.class, departamento.getId()));
            tx.commit();
        } catch (Exception e) {
            if (tx != null & tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException(e);
        } finally {
            PersistenceUtil.close(em);
        }
    }
    
    public Departamento getDepartamento(long id) throws RuntimeException {
        EntityManager em = PersistenceUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        Departamento departamento = null;
        try {
            tx.begin();
            departamento = em.find(Departamento.class, id);
            tx.commit();
        } catch (Exception e) {
            if(tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException(e);
        } finally {
            PersistenceUtil.close(em);
        }
        return departamento;
    }
    
    public List<Departamento> getAllDepartamentos() throws RuntimeException {
        EntityManager em = PersistenceUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        List<Departamento> departamentos = null;
        try {
            tx.begin();
            TypedQuery<Departamento> query = 
                    em.createQuery("SELECT d FROM Departamento d", Departamento.class);
            departamentos = query.getResultList();
            tx.commit();
        } catch (Exception e) {
            if(tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException(e);
        } finally {
            PersistenceUtil.close(em);
        }
        return departamentos;
    }

  
    
}