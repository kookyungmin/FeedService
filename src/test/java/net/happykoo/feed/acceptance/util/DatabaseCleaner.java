package net.happykoo.feed.acceptance.util;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Table;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Profile("test")
@Component
public class DatabaseCleaner implements InitializingBean {
    @PersistenceContext
    private EntityManager entityManager;
    private List<String> tableNames;
    private List<String> notGeneratedIdTableNames;

    @Override
    public void afterPropertiesSet() throws Exception {
        tableNames = entityManager.getMetamodel()
                .getEntities()
                .stream()
                .filter(entity -> entity.getJavaType().getAnnotation(Entity.class) != null)
                .map(entity -> entity.getJavaType().getAnnotation(Table.class).name())
                .toList();

        notGeneratedIdTableNames = List.of("feed_user_relation", "feed_like");
    }

    @Transactional
    public void execute() {
        entityManager.flush();
        //H2 에서 외래키 제약 조건 해제 하는 명령어
        entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE").executeUpdate();

        for(String tableName : tableNames) {
            entityManager.createNativeQuery("TRUNCATE TABLE " + tableName).executeUpdate();
            if (!notGeneratedIdTableNames.contains(tableName)) {
                //ID SEQUENCE 모두 1로 초기화
                entityManager.createNativeQuery("ALTER TABLE " + tableName + " ALTER COLUMN ID RESTART WITH 1").executeUpdate();
            }
        }

        entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY TRUE").executeUpdate();
    }
}
