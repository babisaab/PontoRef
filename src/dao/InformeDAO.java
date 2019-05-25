package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import model.Informe;

public class InformeDAO {
    
    private static InformeDAO instance = new InformeDAO();
    
    public static InformeDAO getInstance() {
        return instance;
    }
    
    public void salvar(Informe informe) throws RuntimeException {
        EntityManager em = PersistenceUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            if (informe.getId() != null) {
                em.merge(informe);
            } else {
                em.persist(informe);
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
    
    public void excluir(Informe informe) throws RuntimeException {
        EntityManager em = PersistenceUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.remove(em.getReference(Informe.class, informe.getId()));
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
    
    public Informe getInforme(long id) throws RuntimeException {
        EntityManager em = PersistenceUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        Informe informe = null;
        try {
            tx.begin();
            informe = em.find(Informe.class, id);
            tx.commit();
        } catch (Exception e) {
            if(tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException(e);
        } finally {
            PersistenceUtil.close(em);
        }
        return informe;
    }
    
    public List<Informe> getAllInformes() throws RuntimeException {
        EntityManager em = PersistenceUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        List<Informe> informes = null;
        try {
            tx.begin();
            TypedQuery<Informe> query = 
                    em.createQuery("SELECT i FROM Informe i", Informe.class);
            informes = query.getResultList();
            tx.commit();
        } catch (Exception e) {
            if(tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException(e);
        } finally {
            PersistenceUtil.close(em);
        }
        return informes;
    }
    
}
