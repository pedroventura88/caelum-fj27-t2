package br.com.alura.forum.repository;

import br.com.alura.forum.model.OpenTopicsByCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class OpenTopicsByCategoryRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private	OpenTopicsByCategoryRepository openTopicsByCategoryRepository;

    @Test
    public void shouldReturnOpenTopicsByCategory() {
        TopicRepositoryTestSetup setup = new TopicRepositoryTestSetup(this.testEntityManager);
        setup.openTopicsByCategorySetup();

        List<OpenTopicsByCategory> openTopics = openTopicsByCategoryRepository.findOpenTopicsByCategory();

        assertThat(openTopics).isNotEmpty();
        assertThat(openTopics).hasSize(2);

        assertThat(openTopics.get(0).getCategoryName()).isEqualTo("Programação");
        assertThat(openTopics.get(0).getTopicCount()).isEqualTo(2);

        assertThat(openTopics.get(1).getCategoryName()).isEqualTo("Front-end");
        assertThat(openTopics.get(1).getTopicCount()).isEqualTo(1);
    }

}
