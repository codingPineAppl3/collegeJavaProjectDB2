package de.hda.fbi.db2.stud;

import java.sql.Timestamp;
import java.util.List;


import javax.persistence.*;
import de.hda.fbi.db2.stud.entity.*;
import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;


//import org.eclipse.persistence.config.CacheUsage;

/**
 * View Game Statistics.
 *
 * @author xiaominjin
 * @version 1.01
 */
public class ViewStatistics {
    private static final String PERSISTENCE_UNIT_NAME = "postgresPU";
    private EntityManagerFactory factory;

    public void showPlayer(Timestamp timefrom, Timestamp timeto) {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        try {
            @SuppressWarnings("unchecked")
            List<Player> resultL =
                    em.createQuery("select distinct p from Player p inner join Game g "
                            + "where g.gameStartTime between :timefrom and :timeto")
                            .setParameter("timefrom", timefrom)
                            .setParameter("timeto", timeto)
                            .setHint(QueryHints.READ_ONLY, HintValues.TRUE)
                            .getResultList();
            for (Player ply : resultL) {
                System.out.println(ply);
            }
        } catch (RuntimeException re) {
            //if (emg != null && emg.isOpen()) {
            //   emg.close();
            //}
            throw re;
        } finally {
            em.close();
        }
    }

    public void showNumberOfGame() {

        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager emg = factory.createEntityManager();

        try {
            @SuppressWarnings("unchecked")
            List resultP = emg.createQuery("select distinct p.playerID, count(g.gameID)"
                    + " from Game g inner join  g.player1 p"
                    + " group by p.playerID order by count(g.gameID) ")
                    .setHint(QueryHints.READ_ONLY, HintValues.TRUE)
                    .getResultList();
            //   System.out.println("my test" + resultP.toString());
            for (Object game : resultP) {
                printResult(game);
                System.out.println();
            }
            //emg.close();
        } catch (RuntimeException re) {
            //if (emg != null && emg.isOpen()) {
             //   emg.close();
            //}
            throw re;
        } finally {
            emg.close();
        }
    }

    public void selectedCategories() {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager emg = factory.createEntityManager();
        try {
            @SuppressWarnings("rawtypes")
            List resultC = emg.createQuery("select c.categoryID, count(c.categoryID)"
                    + " from Question q inner join  q.category c, Game g "
                    + " where q MEMBER OF g.questions "
                    + " group by c.categoryID order by count(c.categoryID) ")
                    .setHint(QueryHints.READ_ONLY, HintValues.TRUE)
                    .getResultList();
            //   System.out.println("my test" + resultP.toString());
            for (Object game : resultC) {
                printResult(game);
                System.out.println();
            }
            //emg.close();
        } catch (RuntimeException re) {
            //if (emg != null && emg.isOpen()) {
            //    emg.close();
            //}
            throw re;
        } finally {
            emg.close();
        }
    }

    public void showGame(int playerId) {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager emf = factory.createEntityManager();
        try {
            @SuppressWarnings("unchecked")
            List resultG =
                    emf.createQuery("select g.gameID, g.gameStartTime, count(q.qId), "
                            + "(select count(q1.qId) from "
                            + "Game g1 inner join g1.answerMap a1, "
                            + " Question q1 "
                            + " where q1.qId = KEY(a1) and "
                            + " q1.solution = VALUE(a1) and "
                            + " g.gameID = g1.gameID)"

                            + " from "
                            + "Game g inner join g.player1"
                            + " p inner join g.answerMap a, "
                            + " Question q "
                            + " where q.qId = KEY(a) and "
                            + " p.playerID = :playerID "
                            + " group by g.gameID ")
                            .setHint(QueryHints.READ_ONLY, HintValues.TRUE)
                            //     .setHint(QueryHints.CACHE_USAGE, CacheUsage.CheckCacheOnly)
                            .setParameter("playerID", playerId)
                            .getResultList();
            for (Object game : resultG) {
                printResult(game);
                System.out.println();
            }
            //emf.close();
        } catch (RuntimeException re) {
            //if (emf != null && emf.isOpen()) {
            //    emf.close();
            //}
            throw re;
        } finally {
            emf.close();
        }
    }

    private static void printResult(Object result) {
        if (result == null) {
            System.out.print("NULL");
        } else if (result instanceof Object[]) {
            Object[] row = (Object[]) result;

            for (int i = 0; i < row.length; i++) {
                printResult(row[i]);
            }

        } else if (result instanceof Long || result instanceof Double
                || result instanceof String || result instanceof Integer
                || result instanceof Timestamp) {
            System.out.print(result.toString() + " ");
        } else {
            System.out.print(result);
        }

    }
}
