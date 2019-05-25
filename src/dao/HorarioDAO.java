package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import model.Horario;

public class HorarioDAO {
    
    private static HorarioDAO instance = new HorarioDAO();
    
    public static HorarioDAO getInstance() {
        return instance;
    }
    
    public void salvar(Horario horario) throws RuntimeException {
        EntityManager em = PersistenceUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            if (horario.getId() != null) {
                em.merge(horario);
            } else {
                em.persist(horario);
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
    
    public void excluir(Horario horario) throws RuntimeException {
        EntityManager em = PersistenceUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.remove(em.getReference(Horario.class, horario.getId()));
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
    
    public Horario getHorario(long id) throws RuntimeException {
        EntityManager em = PersistenceUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        Horario horario = null;
        try {
            tx.begin();
            horario = em.find(Horario.class, id);
            tx.commit();
        } catch (Exception e) {
            if(tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException(e);
        } finally {
            PersistenceUtil.close(em);
        }
        return horario;
    }
    
    public List<Horario> getAllHorarios() throws RuntimeException {
        EntityManager em = PersistenceUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        List<Horario> horarios = null;
        try {
            tx.begin();
            TypedQuery<Horario> query = 
                    em.createQuery("SELECT h FROM Horario h", Horario.class);
            horarios = query.getResultList();
            tx.commit();
        } catch (Exception e) {
            if(tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException(e);
        } finally {
            PersistenceUtil.close(em);
        }
        return horarios;
    }
    
}
