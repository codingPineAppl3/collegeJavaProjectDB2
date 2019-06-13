package de.hda.fbi.db2.stud;

import java.sql.Timestamp;
import java.util.List;


import javax.persistence.*;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;
import de.hda.fbi.db2.stud.entity.*;

//import org.eclipse.persistence.config.CacheUsage;

/**
 * View Game Statistics.
 *
 * @author xiaominjin
 * @version 1.01
 */
public class ViewStatistics {
    private static final String PERSISTENCE_UNIT_NAME = "postgresPU";
    private static EntityManagerFactory factory;

    public void showPlayer(Timestamp timefrom, Timestamp timeto) {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        @SuppressWarnings("unchecked")
        List<Player> resultL =
                em.createQuery("select distinct p from Player p inner join Game g "
                        + "where g.time_start between :timefrom and :timeto")
                        .setParameter("timefrom", timefrom)
                        .setParameter("timeto", timeto)
                        .setHint(QueryHints.READ_ONLY, HintValues.TRUE)
                        .getResultList();
        for (Player ply : resultL) {
            System.out.println(ply);
        }
        em.close();
    }

    public void showNumberOfGame() {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager emg = factory.createEntityManager();
        @SuppressWarnings("unchecked")

        List resultP = emg.createQuery("select p.player_id, count(g.game_id)"
                + " from Game g inner join  g.player p"
                + " group by p.player_id order by count(g.game_id) ")
                .setHint(QueryHints.READ_ONLY, HintValues.TRUE)
                .getResultList();
        //   System.out.println("my test" + resultP.toString());
        for (Object game : resultP) {
            printResult(game);
            System.out.println();
        }
        emg.close();
    }

    public void selectedCategories() {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager emg = factory.createEntityManager();
        @SuppressWarnings("rawtypes")
        List resultC = emg.createQuery("select c.category_id, count(c.category_id)"
                + " from Question q inner join  q.category1 c, Game g "
                + " where q MEMBER OF g.listquestion "
                + " group by c.category_id order by count(c.category_id) ")
                .setHint(QueryHints.READ_ONLY, HintValues.TRUE)
                .getResultList();
        //   System.out.println("my test" + resultP.toString());
        for (Object game : resultC) {
            printResult(game);
            System.out.println();
        }
        emg.close();
    }

    public void showGame(int playerId) {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager emf = factory.createEntityManager();
        @SuppressWarnings("unchecked")
        List resultG =
                emf.createQuery("select g.game_id, g.time_start, count(q.qId), "
                        + "(select count(q1.qId) from "
                        + "Game g1 inner join g1.mapanswer a1, "
                        + " Question q1 "
                        + " where q1.qId = KEY(a1) and "
                        + " q1.correctanswer = VALUE(a1) and "
                        + " g.game_id = g1.game_id)"

                        + " from "
                        + "Game g inner join g.player p inner join g.mapanswer a, "
                        + " Question q "
                        + " where q.qId = KEY(a) and "
                        + " p.player_id = :player_id "
                        + " group by g.game_id ")
                        .setHint(QueryHints.READ_ONLY, HintValues.TRUE)
                        //     .setHint(QueryHints.CACHE_USAGE, CacheUsage.CheckCacheOnly)
                        .setParameter("playerId", playerId)
                        .getResultList();
        for (Object game : resultG) {
            printResult(game);
            System.out.println();
        }
        emf.close();
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
