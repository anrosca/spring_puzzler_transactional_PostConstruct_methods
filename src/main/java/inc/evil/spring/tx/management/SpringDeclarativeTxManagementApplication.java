package inc.evil.spring.tx.management;

import inc.evil.spring.tx.management.domain.Movie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Slf4j
@SpringBootApplication
public class SpringDeclarativeTxManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDeclarativeTxManagementApplication.class, args);
    }
}

@Service
@Slf4j
class MovieService {
    @Autowired
    private EntityManager entityManager;

    public MovieService() {
        log.debug("entityManager: {}", entityManager);
    }

    @PostConstruct
    @Transactional
    public void init() {
        log.debug("entityManager: {}", entityManager);
        Movie movie = Movie.builder()
                .name("Joker")
                .build();
        entityManager.persist(movie);
    }
}


/*
* Solution #1: using programmatic transaction management
*/
//@Service
//@Slf4j
//class MovieService {
//    @Autowired
//    private EntityManager entityManager;
//
//    private final PlatformTransactionManager transactionManager;
//
//    public MovieService(PlatformTransactionManager transactionManager) {
//        this.transactionManager = transactionManager;
//        log.debug("entityManager: {}", entityManager);
//    }
//
//    @PostConstruct
//    public void init() {
//        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
//        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
//            @Override
//            protected void doInTransactionWithoutResult(TransactionStatus status) {
//                log.debug("entityManager: {}", entityManager);
//                Movie movie = Movie.builder()
//                        .name("Joker")
//                        .build();
//                entityManager.persist(movie);
//            }
//        });
//    }
//}





/*
 * Solution #2: Listening to the ContextRefreshedEvent event
 */
//@Service
//@Slf4j
//class MovieService {
//    @Autowired
//    private EntityManager entityManager;
//
//    public MovieService() {
//        log.debug("entityManager: {}", entityManager);
//    }
//
//    @EventListener(ContextRefreshedEvent.class)
//    @Transactional
//    public void init() {
//        log.debug("entityManager: {}", entityManager);
//        Movie movie = Movie.builder()
//                .name("Joker")
//                .build();
//        entityManager.persist(movie);
//    }
//}





/*
 * Solution #3: Proxy self-injection
 */
//@Service
//@Slf4j
//class MovieService {
//    @Autowired
//    private EntityManager entityManager;
//
//    @Autowired
//    private MovieService proxy;
//
//    public MovieService() {
//        log.debug("entityManager: {}", entityManager);
//    }
//
//    @PostConstruct
//    public void init() {
//        log.debug("entityManager: {}", entityManager);
//        proxy.doInit();
//    }
//
//    @Transactional
//    public void doInit() {
//        Movie movie = Movie.builder()
//                .name("Joker")
//                .build();
//        entityManager.persist(movie);
//    }
//}
