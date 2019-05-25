package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import model.Afastamento;

public class AfastamentoDAO {
    
    private static AfastamentoDAO instance = new AfastamentoDAO();
    
    public static AfastamentoDAO getInstance() {
        return instance;
    }
    
    public void salvar(Afastamento afastamento) throws RuntimeException {
        EntityManager em = PersistenceUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            if (afastamento.getId() != null) {
                em.merge(afastamento);
            } else {
                em.persist(afastamento);
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
    
    public void excluir(Afastamento afastamento) throws RuntimeException {
        EntityManager em = PersistenceUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.remove(em.getReference(Afastamento.class, afastamento.getId()));
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
    
    public Afastamento getAfastamento(long id) throws RuntimeException {
        EntityManager em = PersistenceUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        Afastamento afastamento = null;
        try {
            tx.begin();
            afastamento = em.find(Afastamento.class, id);
            tx.commit();
        } catch (Exception e) {
            if(tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException(e);
        } finally {
            PersistenceUtil.close(em);
        }
        return afastamento;
    }
    
    public List<Afastamento> getAllAfastamentos() throws RuntimeException {
        EntityManager em = PersistenceUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        List<Afastamento> afastamentos = null;
        try {
            tx.begin();
            TypedQuery<Afastamento> query = 
                    em.createQuery("SELECT a FROM Afastamento a", Afastamento.class);
            afastamentos = query.getResultList();
            tx.commit();
        } catch (Exception e) {
            if(tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException(e);
        } finally {
            PersistenceUtil.close(em);
        }
        return afastamentos;
    }
    
}
